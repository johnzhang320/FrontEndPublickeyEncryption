package com.front.end.pk.encrypt.demo.controller;

import com.front.end.pk.encrypt.demo.dto.AgentTableDto;
import com.front.end.pk.encrypt.demo.fepke_api.EncoderService;
import com.front.end.pk.encrypt.demo.model.AgentTable;
import com.front.end.pk.encrypt.demo.repository.AgentTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PasswordException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/")
public class BcryptEncoderAPIController {
    @Autowired
    private EncoderService encoderService;
    @Autowired
    private AgentTableRepository agentTableRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * Postman Test
     * @param passwordPlainText
     * @return
     */
    @GetMapping("/savePassword/{username}/{passwordPlainText}")
    public ResponseEntity<AgentTableDto> savePassword(@PathVariable String username,@PathVariable String passwordPlainText) {
        try {

            String bcryptedpwd=encoderService.bcryptEncodingPassword(passwordPlainText);
            log.info("bcryptedpwd="+bcryptedpwd);
            Optional<AgentTable> agentTableOpt = agentTableRepository.findAgentTableByUserName(username);
            if (agentTableOpt.isPresent()) {
                AgentTable agentTable =agentTableOpt.get();
                agentTable.setPassword(bcryptedpwd.trim());
                agentTableRepository.save(agentTable);
                AgentTableDto agentTableDto= modelMapper.map(agentTable, AgentTableDto.class);
                return ResponseEntity.ok(agentTableDto);
            }

        } catch (PasswordException e) {
            return new ResponseEntity<AgentTableDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AgentTableDto>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/checkPassword/{username}/{passwordPlainText}")
    public ResponseEntity<AgentTableDto> checkPassword(@PathVariable String username,@PathVariable String passwordPlainText) {
        try {
            String bcryptedpwd=null;
            log.info("bcryptedpwd="+bcryptedpwd);
            Optional<AgentTable> agentTableOpt = agentTableRepository.findAgentTableByUserName(username);
            if (agentTableOpt.isPresent()) {
                AgentTable agentTable =agentTableOpt.get();
                bcryptedpwd = agentTable.getPassword();
                boolean checkResult = encoderService.checkPasswordExist(passwordPlainText,bcryptedpwd);

                AgentTableDto agentTableDto= modelMapper.map(agentTable, AgentTableDto.class);
                agentTableDto.setPasswordMatched(checkResult);
                if (checkResult) {
                    agentTableDto.setMessage("Entered Password:"+passwordPlainText+" is used for user:"+username);
                } else {
                    agentTableDto.setMessage("Entered Password:"+passwordPlainText+" is new for user:"+username);
                }
                return ResponseEntity.ok(agentTableDto);
            }

        } catch (PasswordException e) {

            return new ResponseEntity<AgentTableDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AgentTableDto>(HttpStatus.BAD_REQUEST);
    }

}
