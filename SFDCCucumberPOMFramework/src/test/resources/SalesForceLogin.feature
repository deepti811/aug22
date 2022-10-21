Feature: Login feature
Background: 
Given navigate to url
@smoke
Scenario: Login error message to salesforce
When user on "Loginpage"
When user enter value into textbox "username"
When user clear textbox password
When user click on "login button"
Then verify "errormessage" displayed

Scenario: Login to salesforce
When user on "Loginpage"
When user enter value into textbox "username"
When user enter value into textbox "password"
When user click on "login button"
When user on "Homepage"
Then verify "Home page" displayed

Scenario: Check Remember Me
When user on "LoginPage"
When user enter value into textbox "username"
When user enter value into textbox "password"
When user click on "Remember me checkbox"
When user click on "login button"
When user on "HomePage"
When user click on "user menu"
When user click on "Logout"
When user on "LoginPage"
Then verify "username" displayed

Scenario: Forgot password
When user on "LoginPage"
When user click on "Forgot password"
When user on "ForgetpasswordPage"
When user enter value into textbox1  username
When user click on "continue "
Then verify "confirmation message" displayed

Scenario: Login wrong credentials
When user on "LoginPage"
When user enter value into textbox "wrongusername"
When user enter value into textbox "wrongpassword"
When user click on "login button"
Then verify "error message2" displayed

  