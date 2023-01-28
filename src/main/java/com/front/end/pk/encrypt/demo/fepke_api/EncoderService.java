package com.front.end.pk.encrypt.demo.fepke_api;

import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {
    @Autowired
    PasswordEncoder passwordEncoder;

    public String fepkeDecrpt(String fepkeEncryptedString) throws PasswordException{
        if (fepkeEncryptedString==null) {
            throw new PasswordException("fepkeEncryptedString is required");
        }
        return KeyPairManager.getInstance().decrypt(fepkeEncryptedString);
    }

    public String getFepkePublicKey() throws Exception{
        return KeyPairManager.getInstance().getKeyPair().getPublic().toString();
    }

    public  boolean checkPasswordExist(String passwordPlainText, String passwordBcryptString) throws PasswordException{
        if (passwordPlainText==null) {
            throw new PasswordException("Raw Password is required");
        }
        if (passwordBcryptString==null) {
            throw new PasswordException("BCrypt Encoded Password is required");
        }
        /**
         *  matchResult variable is working for debug
         */
        boolean matchResult = passwordEncoder.matches(passwordPlainText,passwordBcryptString);
        return  matchResult;
    }

    public String bcryptEncodingPassword (String passwordPlainText) throws PasswordException {
        if (passwordPlainText == null) {
            throw new PasswordException("Raw Password is required");
        }
        return passwordEncoder.encode(passwordPlainText);
    }

}
