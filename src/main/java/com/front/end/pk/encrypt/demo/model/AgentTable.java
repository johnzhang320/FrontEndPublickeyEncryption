package com.front.end.pk.encrypt.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.front.end.pk.encrypt.demo.fepke_api.EncoderService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.openssl.PasswordException;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
/**
 * AgentTable entity. @author MyEclipse Persistence Tools
 */
  @Entity
  @Table(name = "agent_table", catalog = "agentdb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email_address"),
		@UniqueConstraint(columnNames = "user_name") })

  public class AgentTable implements java.io.Serializable {

	private static final long serialVersionUID=01L;
	// Fields
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "agentId", unique = true, nullable = false)
	private Integer agentId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email_address", unique = true)
	private String emailAddress;

	@Column(name = "credit_number", unique = true)
	private String creditNumber;

	@Column(name = "credit_holder_name")
	private String cardHolderName;

	private String expiringDate;

	private String securityCode;

	@Column(name = "social_security", unique = true)
	private String socialSecurity;

	@Column(name = "full_name", unique = true)
	private String fullName;

 }