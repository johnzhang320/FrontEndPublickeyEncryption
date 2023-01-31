package com.front.end.pk.encrypt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Blob;
import java.sql.Timestamp;

/**
 * Agents entity. @author MyEclipse Persistence Tools
 */
@Data

@AllArgsConstructor

@NoArgsConstructor

@Builder

public class AgentTableDemoDto implements java.io.Serializable {

	private Boolean passwordMatched;
	private String message;
	private String decryptedPassword;

	private String description;

	private String encryptedPassword;

	private String userName;

	private String hashedPassword;

	private String password;

	private String publicKey;

	private String privateKey;

	private String emailAddress;
	private String creditNumber;

	private String cardHolderName;

	private String expiringDate;

	private String securityCode;

	private String encryptedCredit;

	private String decryptedCredit;

	private String encryptedSecurityCode;

	private String decryptedSecurityCode;

	private String socialSecurity;

	private String fullName;
	private String DecryptedSSO;

	private String EncryptedSSO;

}