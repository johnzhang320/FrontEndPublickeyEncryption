<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
  
fieldset table tr td {
     border:1px;
   }
 </style> 

<div >        
	<spring:message code="title.agent.login"/>  
</div>

<fieldset >     
	<legend><span class="AccountCreateTableHeader"> <spring:message code="title.signup.success"/></span> </legend>
	<br>
	<table>   
		 
		<tr><td><spring:message code="label.public.key"/>
		</td><td>
		<textArea id="showEncryptedString" rows="8" style="width:500px;" > ${agentTableDemoDto.publicKey}</textArea>                                                         
		</td></tr>
	</table>
	<br>
	<table>   
	 
		<tr><td><spring:message code="label.encrypted.password"/>
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTableDemoDto.encryptedPassword} </textArea>                                                         
		</td></tr>
		<tr><td><spring:message code="label.decrypted.password"/>
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTableDemoDto.decryptedPassword}</textArea>    
		</td></tr>
		<tr><td><spring:message code="label.hashed.password"/></td><td>	<textArea id="showEncryptedString" rows="2" style="width:500px;"> ${agentTableDemoDto.hashedPassword}</textArea>
	    </td></tr>
	</table>	
	<br>
	<table>   
 		<tr><td>Encrypted Credit Card Number
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTableDemoDto.encryptedCredit} </textArea>                                                         
		</td></tr>
		<tr><td>Decrypted Credit Card Number
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTableDemoDto.decryptedCredit}</textArea>    
	 	</td></tr>
	 	
	</table>	
	<br>
	<table>   
		
		<tr><td>Encrypted Secial Security Number
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTableDemoDto.encryptedSSO} </textArea>                                                         
		</td></tr>
		<tr><td>Decrypted Secial Security Number
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTableDemoDto.decryptedSSO}</textArea>    
	    </td></tr>
	</table>	
	
</fieldset>
