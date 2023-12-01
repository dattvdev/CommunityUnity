

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <meta name="viewport" content="width=device-width, initial-scale=1">

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
        <link rel="stylesheet" href="./BlogsComponent/css/base.css">
        <link rel="stylesheet" href="./BlogsComponent/css/main.css">
        <script src="./BlogsComponent/js/modernizr.js"></script>
        <script defer src="./BlogsComponent/js/fontawesome/all.min.js"></script>

        <link rel="apple-touch-icon" sizes="180x180" href="./BlogsComponent/apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="./BlogsComponent/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="./BlogsComponent/favicon-16x16.png">
        <link rel="manifest" href="site.webmanifest">

        <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="./css/donationcss.css">
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,700italic,400italic' rel='stylesheet'
              type='text/css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <link rel="apple-touch-icon" sizes="180x180" href="apple-touch-icon.png">
        <link rel="icon" type="image/png" sizes="32x32" href="favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="favicon-16x16.png">
        <link rel="manifest" href="site.webmanifest">

        <style>
            
            .nav-link{
                display: flex;
            }
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

</head>

<body style="
      justify-content: center;
      align-items: center;
      text-align: center;
      background-color: white;
      ">
    <%@include file="./components/header.jsp" %>
    <img src="./images/payment_failled.gif" style="
    padding-top: 5%;
">
    <div class="ab">
        <div class="a">
            <h1>PAYMENT FAILED</h1>
        </div>
        <div class="a">
            <a href="home">Back Home</a>
        </div>


    </div>

</body>

</html>


