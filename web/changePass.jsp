<%-- 
    Document   : changePass
    Created on : Sep 27, 2023, 4:40:56 PM
    Author     : ytbhe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Password</title>
        <script src="./js/checkinput.js"></script>
        <link rel="stylesheet" href="./css/loginCSS.css">
    </head>
    <body>

        <div class="box login">
            <div class="content">

                <form action="changepass" method="post">
                    <h2>Change Password</h2>
                    <input type="email" name="email" placeholder="email" value="${email}" readonly>
                    <input type="password" id="newpass"  placeholder="New Password: Abc123@!" name="new_password" onkeyup="checkStrong();"required>
                    <span id="error_message" style="color: red;  white-space: pre-line;"></span>
                    <input type="password" id="repass" placeholder="Confirm New Password" name="repass" required onkeyup="checkPasswordMatch();"><br>

                    <button class="btn btn-dark btn-lg btn-block" type="submit">Change Password</button>
                </form>
            </div>
        </div>
    </body>
</html>