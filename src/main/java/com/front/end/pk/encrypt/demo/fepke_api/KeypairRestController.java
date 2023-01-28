package com.front.end.pk.encrypt.demo.fepke_api;

import java.security.KeyPair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/")
public class KeypairRestController {

	@RequestMapping(value="/getKeyPair.html", method=RequestMethod.POST)
	public void getKeypair(HttpServletRequest request,HttpServletResponse response) {
		String keyString ="";
		try {
			log.info("doPost() Begin:");
			KeyPair keys = KeyPairManager.getInstance().getKeyPair();
		    request.getSession().setAttribute(Constants.KEY_PAIR, keys);  		    
		    keyString =  KeyPairManager.getInstance().getKeyString();		   
		    response.getOutputStream().print(keyString);  
		    
		} catch (Exception e) {
				log.info("Generate Key Failed because of "+e.getMessage());
				keyString="Generate Key Failed because of "
				+e.getMessage();
		}
		 log.info("doPost() End:");
		 return ;
	}
	
	@RequestMapping(value="/getKeyPair.html", method=RequestMethod.GET)
	public void getKeypairGet(HttpServletRequest request,HttpServletResponse response) {
		String keyString ="";
		try {
			log.info("doGet() Begin:");
			KeyPair keys = KeyPairManager.getInstance().getKeyPair();
		    request.getSession().setAttribute(Constants.KEY_PAIR, keys);  		    
		    keyString =  KeyPairManager.getInstance().getKeyString();		   
		    response.getOutputStream().print(keyString);  
		    
		} catch (Exception e) {
				log.info("Generate Key Failed because of "+e.getMessage());
				keyString="Generate Key Failed because of "
				+e.getMessage();
		}
		 log.info("doGet() End:");
		 return ;
	}
}
