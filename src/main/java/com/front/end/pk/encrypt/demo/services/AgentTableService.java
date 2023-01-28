package com.front.end.pk.encrypt.demo.services;

import com.front.end.pk.encrypt.demo.fepke_api.EncoderService;
import com.front.end.pk.encrypt.demo.model.AgentTable;
import com.front.end.pk.encrypt.demo.repository.AgentTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AgentTableService {
    @Autowired
    AgentTableRepository agentTableRepository;

    @Autowired
    EncoderService encoderService;

    public AgentTable validatPasswordReturnExistAgentTable(String username, String passwordPlainText) throws PasswordException {

        if (passwordPlainText.length()<8 || passwordPlainText.length()>15) {
            throw new PasswordException("PasswordSize required 8 - 15 characters");
        }

        Optional<AgentTable> existAgentTableOpt = agentTableRepository.findAgentTableByUserName(username);

        AgentTable existAgentTable = null;
        if (existAgentTableOpt.isPresent()) {
            existAgentTable = existAgentTableOpt.get();
            String savedBcryptedpassword = existAgentTable.getPassword();
            boolean checkResult = encoderService.checkPasswordExist(passwordPlainText, savedBcryptedpassword);
            if (checkResult) {
                throw new PasswordException("Password: "+passwordPlainText+" exists for user: "+username);
            }
        }
        return existAgentTable;
    }


}
