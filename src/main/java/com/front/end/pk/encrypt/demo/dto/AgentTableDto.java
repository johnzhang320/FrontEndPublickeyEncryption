package com.front.end.pk.encrypt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;


/**
 * Agents entity. @author MyEclipse Persistence Tools
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
 public class AgentTableDto {

	@NotBlank(message = "Username is required")
	private String userName;
	@NotBlank(message="password is required")
	private String password;

	@Email(message = "Email is invalid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String emailAddress;

	@NotBlank(message="CreditNumber is required")
	private String creditNumber;

	@NotBlank(message = "Credit Card Holder is required")
	private String cardHolderName;

	@NotBlank(message = "Expired Date is required")
	@DateTimeFormat(pattern = "mm/yy")
	private String expiringDate;

	@NotBlank(message = "Security Code is required")
	private String securityCode;

	@NotBlank(message = "Social Security Number is required")
	private String socialSecurity;
	@NotBlank(message = "SSO full name is required")
	private String fullName;

	private Boolean passwordMatched;

	private String message;
}
