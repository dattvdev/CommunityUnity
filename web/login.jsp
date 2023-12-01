<%-- 
    Document   : login2
    Created on : Jul 3, 2023, 5:27:10 PM
    Author     : Twna21
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CommunityUnity</title>
        <link rel="stylesheet" href="./css/loginCSS.css">
        <script src="./js/checkinput.js"></script>

    </head>

    <body>

        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="signup" method="post">
                    <h1>Create Account</h1>
                    <span id="error_message" style="color: red;  white-space: pre-line;"></span>
                    <input type="text" id="username" placeholder="username" name="su_username" onkeyup="checkUserNAme();lowerCase();"/>
                    <input type="email" id="email" name="email" placeholder="email" onkeyup="checkEmail();trimInput();"/>
                    <input type="password" id="newpass"  name="su_password" placeholder="Password" onkeyup="checkStrong();" />
                    <input type="password" id ="repass" name="repass" placeholder="Repeat Password" onkeyup="checkPasswordMatch();"/>

                    <div class="a" style="display:flex;text-align: left;align-items: start;padding-bottom: 30px;">
                        <label for="Is">Is:</label>
                        <select id="is" name="is">
                            <option value="1">Volunteer</option>
                            <option value="2">Organizer</option>
                        </select>
                    </div>


                    <button class="btn btn-dark btn-lg btn-block" type="submit">SIGN UP</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="login" method ="POST">
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/CommunityUnity/LoginGoogleController&response_type=code&client_id=552963219492-rlahrheu7p4a3vab2pimmed69atebaeo.apps.googleusercontent.com&approval_prompt=force" class="social">
                            <img src="./images/OIP.jpg" style="height: 100%;" alt="Image Description">
                            <i class="fab fa-google"></i></a>

                    </div>

                    <c:if test="${not empty requestScope.ERROR_MASSEGE}">
                        <!-- Error MSG -->
                        <div class="alert alert-danger" role="alert" style="color: red">
                            ${requestScope.ERROR_MASSEGE}
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.MSG_SUCCESS}">
                        <!-- Error MSG -->
                        <div class="alert alert-success" role="alert">
                            ${requestScope.MSG_SUCCESS}
                        </div>
                    </c:if>
                    <span>or use your account</span>
                    <span id="error_message1" style="color: red;  white-space: pre-line;"></span>
                    <input type="text" id="username1" placeholder="username" name="username" onkeyup="checkUserNAmeLogin();lowerCase1();"/>
                    <input type="password" placeholder="Password" name="password" />
                    <a href="foget.jsp">Forgot your password?</a>
                    <button class="btn btn-dark btn-lg btn-block" type="submit">Login</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>

        <footer>    
        </footer>

        <script src="./js/login.js" type="text/javascript"></script>
    </body>

</html>