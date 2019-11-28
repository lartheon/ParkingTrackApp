#car-reg-spring-security-jpa-mysql

#WebMvcController.java
#
Sets up how each of the java endpoints is mapped to a front-end view (html page) 

#WebSecurityConfig.java
#
extends on WebSecurityConfigurerAdapter
inserts in memory the user details user:"user" and password:"password"

everyone has view permissions on "/" and the "home.html" page, eg localhost http://localhost:8080
if an attempt is made to view any other page the login page is called "/login"


#KeyStore
#
keystore_cmd created car_reg.p12
and Generated 2,048 bit RSA key pair and self-signed certificate (SHA256withRSA) with a validity of 3,650 days

Added password: either <> or <password> with alias as car_reg 
 
