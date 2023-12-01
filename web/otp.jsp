<%-- 
    Document   : otp
    Created on : Sep 22, 2023, 7:47:44 PM
    Author     : ytbhe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Enter OTP</title>
        <link rel="stylesheet" href="./css/loginCSS.css">
        <style>
            /* Add some CSS for styling the countdown timer */
            #countdown {
                font-size: 24px;
                font-weight: bold;
                color: red;
            }
        </style>


    </head>
    <body>

        <div class="box login">
            <div class="content">
                <form action="forget" method="get">
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
                    <h1>Enter OTP</h1>
                    <p>Please enter the OTP sent to your email address.</p>
                    <div id="countdown"></div>

                    <input type="text" id="email" name="email" value="${email}" readonly>
                    <input type="text" id="otp" name="otp" placeholder="OTP" required>

                    <br>

                    <button type="submit">Verify OTP</button>
                    <a href="login.jsp">BACK</a>
                </form>
            </div>
        </div>
        <script src="./js/timeOTP.js"></script>

    </body>
</html>