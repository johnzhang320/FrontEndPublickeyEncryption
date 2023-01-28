package com.front.end.pk.encrypt.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.front.end.pk.encrypt.demo.dto.AgentTableDemoDto;
import com.front.end.pk.encrypt.demo.dto.AgentTableDto;
import com.front.end.pk.encrypt.demo.fepke_api.EncoderService;
import com.front.end.pk.encrypt.demo.model.AgentTable;
import com.front.end.pk.encrypt.demo.repository.AgentTableRepository;
import com.front.end.pk.encrypt.demo.services.AgentTableService;
import com.front.end.pk.encrypt.demo.services.Constants;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PasswordException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.security.util.Password;

import java.util.Optional;

/**
* Class level Controller for login form controlling
* @author johnzhang
*
*/
@Slf4j
@Controller
@RequestMapping("/")
public class FrontEndCryptionDemoController {
   // Logger LOG = Logger.LoggerFactory.getLogger( FrontEndCryptionDemoController.class);

	@Autowired
	EncoderService encoderService;

	@Autowired
	AgentTableService agentTableService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	AgentTableRepository agentTableRepository;

	@RequestMapping(value="/signup",method = RequestMethod.GET)
	public ModelAndView signupForm(ModelAndView modelAndView)
			throws Exception {
		modelAndView.setViewName("FrontEndCryptionDemo");
		modelAndView.addObject("agentTableRequestDto",new AgentTableDto());
		/**
		 *  Return to tile definition name: AgentLogin defined in tiles.xml
		 */
		return modelAndView;
	}
	@RequestMapping(value="/frontEndCryptionDemo.html",method = RequestMethod.POST)
	public ModelAndView processSubmit(
			@Valid @ModelAttribute("agentTableRequestDto") AgentTableDto agentTableRequestDto,
			BindingResult bindingResult,
			HttpServletRequest request
		)	throws Exception { 
		
		log.info("FrontEndCryptionDemoController prcessSubmit() begin");
		ModelAndView modelAndViewError =new ModelAndView("FrontEndCryptionDemo");
		ModelAndView modelAndViewDemo =new ModelAndView("redirect:frontEndCryptionDemoSubmitSuccess.html");

		if (bindingResult.hasErrors()) {
			return modelAndViewError;
		} else {
			/**
			 *  DTO Object -> Entity Object using ModelMapper
			 *  save a lot of boilerplate code
			 */
			AgentTable agentTableDao = modelMapper.map(agentTableRequestDto,AgentTable.class);
			AgentTableDemoDto agentTableDemoDto = modelMapper.map(agentTableRequestDto, AgentTableDemoDto.class);

			/**
			 *   Encrypt password , credit card number and social security number
			 *   by javascript code which sent to agentTableRequestDto
			 */
			String encryptedPassword =agentTableRequestDto.getPassword();
			String encryptedCreditCardNumber = agentTableRequestDto.getCreditNumber();
			String encryptedSocialSecurityNumber = agentTableRequestDto.getSocialSecurity();
			/**
			 *  Decrypt password , credit card number and social security number
			 *  by fepkeDecrpt method
			 */
			String passwordPlanText = encoderService.fepkeDecrpt(encryptedPassword);
			String creditCardNumberPlanText =  encoderService.fepkeDecrpt(encryptedCreditCardNumber);
			String socialSecurityNumberPlanText = encoderService.fepkeDecrpt(encryptedSocialSecurityNumber);

			String publicKey = encoderService.getFepkePublicKey();
			AgentTable existAgentTable=null;
			/**
			 *  Verify if password length is 8 ~15 chars and then using BCryptPasswordEncoder.matches(plainText,bcryptString)
			 *  to validate if entered password matches saved bcrypted password to see if people entered
			 *   same password for same user, if validation is passed , return exist entity
			 *  to get primary key agentId, if it is invalided, handling bindingResult to ensure error
			 *  message occurs in same signup webpage
			 *
			 */
			try {
				existAgentTable = agentTableService.validatPasswordReturnExistAgentTable(
													agentTableRequestDto.getUserName(),
													passwordPlanText);

			} catch (PasswordException ex) {
				bindingResult.addError(new FieldError("AgentTableRequestDto", "password",
						ex.getMessage()));
				return modelAndViewError;
			}
			/**
			 *  if the record exists for this username, means this time update exist record
			 */
		    if (existAgentTable!=null) {
				agentTableDao.setAgentId(existAgentTable.getAgentId());
			}
			/**
			 * Save bcrypt encoded password to database by agentTableDao
			 */
			String bcryptedPassword= encoderService.bcryptEncodingPassword(passwordPlanText);

			agentTableDao.setPassword(bcryptedPassword);

			/**
			 *  save creditCardNumberPlainText and socialSecurityNumberPlanText to database
			 *  for further business use
			 */

			agentTableDao.setCreditNumber(creditCardNumberPlanText);
			agentTableDao.setSocialSecurity(socialSecurityNumberPlanText);

			/**
			 *  save to database
			 */
			agentTableRepository.save(agentTableDao);
			 /**
			 *  Preparing Success Demo Page with FEPKE encrypted and decryption and Bcrypted
			  * Password
			 */

			agentTableDemoDto.setEncryptedPassword(encryptedPassword);
			agentTableDemoDto.setDecryptedPassword(passwordPlanText);

			agentTableDemoDto.setHashedPassword(bcryptedPassword);

			agentTableDemoDto.setPublicKey(publicKey);

			agentTableDemoDto.setDecryptedCredit(creditCardNumberPlanText);
			agentTableDemoDto.setEncryptedCredit(encryptedCreditCardNumber);

			agentTableDemoDto.setDecryptedSSO(socialSecurityNumberPlanText);
			agentTableDemoDto.setEncryptedSSO(encryptedSocialSecurityNumber);
			
				
		    request.getSession().setAttribute("agentTableDemoDto",agentTableDemoDto);

		    log.info("FrontEndCryptionDemoController processSubmit() end");

		    /**
		     *  Redirect to protect retry submitted form
		     */
		    return modelAndViewDemo;
		}
		 
	}

	@RequestMapping( value = "/frontEndCryptionDemoSubmitSuccess.html", method = RequestMethod.GET)
	public String agentLoginSuccess (Model model, HttpServletRequest request) throws Exception {
		log.info("frontEndCryptionDemoSubmitSuccess () begin");

		AgentTable agentTable = (AgentTable) request.getSession().getAttribute(Constants.AGENT_FORM);
		if (null == agentTable) {
			agentTable = new AgentTable();
		}
		model.addAttribute("agentTable",agentTable);
		log.info("frontEndCryptionDemoSubmitSuccess () end");

		return "FrontEndCryptionDemoSubmitSuccess";  // call handler define in tiles.xml
	}

}
