<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Account" %>
<%@ page import="dao.AccountDAO" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>CommunityUnity</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="./css/donationcss.css">
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,700italic,400italic' rel='stylesheet'
              type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <style>
            .navbar-light {
                background-color: black;
            }
            .py-5{
                padding-top: 10rem !important;
            }

            .donation-box .title {
                background-color: #86B817;
            }

            .donation-box .donate-button {
                background-color: #86B817;}

            .donation-box .amount .button {
                background-color: #86B817;
                text-transform: lowercase;

            }

        </style>

    </head>

    <body>



        <%@include file="./components/header.jsp" %>
        <!-- About Start -->
        <div class="col-md-12" style="display: flex; padding: 5%; justify-content: space-between;">
            <div class="col-md-5" >


                <img src="images/blogging-services.jpg" alt="" style="width: 100%">

            </div>
          
            <div class="col-md-5">


                <h3>Create New Blog Post</h3>

                <form action="createpost" method="POST" enctype="multipart/form-data">

                    <label for="title">Title:</label>
                    <textarea  id="title" name="title" required style="width: 60%;"> </textarea><br><br>

                    <label for="content">Content:</label>
                    <textarea id="content" name="content" required style="width: 60%;"></textarea><br><br>

                    <label for="shortcontent">Short Content:</label>
                    <textarea id="shortcontent" name="shortcontent" style="width: 60%;"></textarea><br><br>



                    <label for="category">Category:</label>

                    <select id="category" name="category">


                        <option value="health">Health</option>
                        <option value="animal">Animal</option>

                        <option value="environment">Environment</option>
                        <option value="volunteer">Volunteer</option>

                    </select><br><br>

                    <label for="photo">Photo:</label>
                    <input type="file" id="photo" name="photo"><br>


                    <br>  <br>
                    <input type="submit" value="Submit Post">

                </form>


            </div>

        </div>


        <!-- About End -->


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="./js/myjs.js"></script>
        <script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

        <script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>






        <%@include file="./components/footer.jsp" %>
    </body>

</html>