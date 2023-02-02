package com.front.end.pk.encrypt.demo.fepke_api;

import com.front.end.pk.encrypt.demo.exception.MyPasswordException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {
    @Autowired
    PasswordEncoder passwordEncoder;

    public String fepkeDecrpt(String fepkeEncryptedString) throws MyPasswordException {
        if (fepkeEncryptedString==null) {
            throw new MyPasswordException("fepkeEncryptedString is required");
        }
        return KeyPairManager.getInstance().decrypt(fepkeEncryptedString);
    }

    public String getFepkePublicKey() throws Exception{
        return KeyPairManager.getInstance().getKeyPair().getPublic().toString();
    }

    public  boolean checkPasswordExist(String passwordPlainText, String passwordBcryptString) throws MyPasswordException{
        if (passwordPlainText==null) {
            throw new MyPasswordException("Raw Password is required");
        }
        if (passwordBcryptString==null) {
            throw new MyPasswordException("BCrypt Encoded Password is required");
        }
        /**
         *  matchResult variable is working for debug
         */
        boolean matchResult = passwordEncoder.matches(passwordPlainText,passwordBcryptString);
        return  matchResult;
    }

    public String bcryptEncodingPassword (String passwordPlainText) throws MyPasswordException {
        if (passwordPlainText == null) {
            throw new MyPasswordException("Raw Password is required");
        }
        return passwordEncoder.encode(passwordPlainText);
    }

}
