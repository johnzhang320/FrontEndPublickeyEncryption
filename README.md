# UIEncyptPasswordByPublickey
Topic name: UI code encrypts password by public key which server dynamically generated once user completely inputs password 

As we konwn, Https channel blocks the hachers steal password over network but can not resist invaded virus such as:
Trojan Horse disguises malware as legitimate software or a harmless link, possibly through a misleading email, 
a falsified website, or a fake advertisement to sneak into your local machine or device to steal your password.

They are using keylogger to make system call to log the password keystroke, using Rainbow table contained many hashing combinations
to unencrypt hashed password, using Third Parties attack, hacker hold key to many other customer system to attack.

We provide a solution to address this Trojan Hourse attack, we know that password will be valid only when it is entered completely

 This solution's providing as following:
 
(1) UI code use keyup, keydown, key_blur events to encrypt password instead of ui form submittion, which narrow down
    the log chance by keylogger of Trojan and protect password in keyin level or cursor left level instead of submit 
    button level.
    
(2) Instead of use hashed password, UI side fetch server public key at cursor left (keyblur) or typing password letters
    reach to changable number, then start encrypt password right away in one time or multiple time.
    
(3) Each time UI retrieved server public key is different because server generate public key pair is only for current request.
    Only authenticated user client can issue such request. So Hacker goes through third part with existing public key not to 
    be able to issue valid request.
    
(4) Password will be verified and stored by Spring BCryptPasswordEncoder.

(5) We will illustrate how this solution work basically and continuing github submittion to describe how this solution 
    be integrate to Spring-security, Spring-data, Spring-outh2 



