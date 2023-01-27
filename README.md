# Password, BankAccount Number, SocialSecurity Number FrontEndPublicKeyEncryption(FEPKE) Signup Demo

   1.  Why Front End Encryption
      Although applying Https be able prevent the hackers from stealing sensitive information over network, some invaded virus, like
      
      Trojan Horse and Active-X, are able to sneak into your local machine to steal the information through harmless links, misleading email, 
      
      a falsified website, or a fake advertisement.
      

      They are using keylogger to make system call to log the keystroke and record critical javascript variable, using Rainbow table guess your 
      
      hashed password in UI
      

      We attempt to hide the sensitive form field variables and when we complete enter sensitive field and leave focus from the field, encrypt 
      the data that you just entered, dynamically obtain pubic key from server to encrypt form field by javascript. 

  2.   Why do we use Public Key to encrypt sensitive information in frontend
  

  2.1   The sensitive data such as BankAccount Number and Social Security number must be decrypted in server side for further use,
  
        Normally signup pages consist of page of creating username and password and press 'Continue' to next pages , finally 'Submit'
        
        sensitive information plain text stay for a while between pages, which give the invaded virus timing chance to steal them, leaving
        
        Field encryption tries to eliminate such chances!
       
  
  2.2  Regarding the password, some developers use one time hash algorithm, such as SHA256 or MD5, to hash password in UI and send to server
       save it to Database.
       
       
       This way leaves the room for Hugh Hash String of 'Rain Table' to brute force 'Guess' hashed password, we use public key cryptography
       to be able to avoid this hash "Guess' because PublicKey-encrypted RSA String is different from Hashed String and even more 
       complicated(1024, 2048 bytes etc). 
       
       
  2.3  About this mechanism, each time when a user loads the Signup page, the server will generate a new public key which is different from 
       previous public key when the user loaded the same Signup page last time.
       
       
  2.4  Since Spring Boot 2.0, Spring Security uses BCryptPasswordEncoder to save salt hashed password, for the same password plain text, this encoder
       generates a different encoded string, we save this encoded password string to the database. Rain Table is delimaly to guess changeable hashed
       password.
       
      
       But BCryptPasswordEncoder.matched(passwordPlainText, database_saved_bcrypted_password) required passwordPlainText, FEPKE mechanism encrypts 
       password in the UI side and provide a decrypt method to get password plain text, then we can verify for the same username to use the same 
       In order to take advantage of BCryptPasswordEncoder,  We can not SHA256 ot MD5 hash passwords in UI. 
       
        

2. What does this solution provide  
   see the FEPKE Machine workflow:
   
   
(1) UI code use keyup, keydown, key_blur events to encrypt password instead of ui form submission, which narrow down
    the log chance by keylogger of Trojan and protect password in keyin level or cursor left level instead of submit 
    button level.
    
(2) Instead of use hashed password, UI side fetch server public key at cursor left (keyblur) or typing password letters
    reach a changeable number, then start encrypting the password right away in one time or multiple time.
    
(3) Each time the UI retrieves the server public key is different because the server generated public key pair is only for the current request.
    Only authenticated user clients can issue such requests. So Hacker goes through the third part with existing public keys not to 
    be able to issue valid requests.
    
(4) Passwords will be verified and stored by Spring BCryptPasswordEncoder.

(5) We will illustrate how this solution work basically and continuing github submission to describe how this solution 



