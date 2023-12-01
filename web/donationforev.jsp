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


        <div class="col-md-12" style="width: 100%; display: flex;">


            <div class="col-md-4">
                <img src="images/volunteer.jpg" style="width: 100%;">
            </div>
            <div class="col-md-4">
                <div class="donation-container" style="width: 100%">

                    <div class="donation-box" style="height: 85%">
                        <form id="donationForm" action="payment" method="post" onsubmit="return validateDonation()">
                            <div class="title">Donation Information</div>

                            <div class="fields">
                                <input type="text" id="firstName" value="${sessionScope.LOGIN_USER.fullName}" readonly required> 
                                <input type="email" id="email" value="${sessionScope.LOGIN_USER.email}" required>
                                <input type="hidden" name="id" value="${id}" id="id" placeholder="${id}">
                                <input type="hidden" name="iduser" value="${sessionScope.LOGIN_USER.accId}" id="id" placeholder="${sessionScope.LOGIN_USER.accId}">
                                <input type="hidden" name="idEvent" value="${idEvent}" id="id" placeholder="${idEvent}">


                            </div>


                            <div class="amount">
                                <div class="button"> <label>
                                        <input type="radio" name="donation" value="50000" checked >50.000đ</label></div>
                                <div class="button"> <label>
                                        <input type="radio" name="donation" value="100000">100.000đ</label></div>
                                <div class="button"> <label>
                                        <input type="radio" name="donation" value="200000">200.000đ</label></div>

                                <div class="button">
                                    <label>
                                        <input type="radio" class="set-amount" name="donation" value="">
                                        <input type="number" name="donation"  id="customAmount" oninput="updateDonationValue(this)" style="width:100%;">
                                    </label>
                                </div>


                            </div>





                            <input type="hidden"  name="bankCode" value="VNBANK">
                            <input type="hidden" id="language" name="language" value="vn">
                            <div class="donate-button"><input type="submit" style="background-color: #7beb7b" value="Donate"> </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- About Start -->

            <div class="col-md-3" style=" padding-top: 3%;">
                <div class="block" data-widget-id="52" data-widget-key="th_donate" data-widget-definition="thdonate_topDonors">
                    <div class="block-container">
                        <h3 class="block-minorHeader" style="text-align: center">
                            Recent donation
                        </h3>
                        <ul class="block-body">



                            <!--start-->
                            <li class="block-row">
                                <c:forEach var="o" items="${listtrans}">
                                    <div class="contentRow-main contentRow-main--close">
                                        <div class="ax" style="display: flex;">

                                            <div class="contentRow-inline">
                                                <img src="${o.photo}" style="width: 100px; height: auto;">

                                            </div>
                                            <div class="contentRow-inline" style="font-size: 20px; padding-left: 5px; color: red;">
                                                <span>${o.giverName}</span>

                                                <div class="contentRow-minor"  style="width: 100px; height: auto;">
                                                    <span id="formattedAmount${o.paymentId}"style="color: blue;"></span>
                                                </div>
                                                <p style="color: blueviolet;">${o.transactionDate}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <script>
                                        function formatAmount(amount) {
                                            return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(amount);
                                        }

                                        // Get the amount value from your data
                                        var amount = ${o.amount};

                                        // Format the amount using the function and display it
                                        document.getElementById("formattedAmount${o.paymentId}").textContent = formatAmount(amount);
                                    </script>
                                </c:forEach>



                                <!--end-->
                                </div>
                            </li>




                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- About End -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="./js/myjs.js"></script>
    <script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

    <script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>
    <script>

                                        function updateDonationValue(input) {
                                            // Get the input value
                                            var customValue = input.value;

                                            // Find the radio button with class "set-amount"
                                            var radioButton = document.querySelector('input[type="radio"].set-amount');

                                            // Update the radio button's value attribute
                                            if (radioButton) {
                                                radioButton.value = customValue;
                                            }
                                        }

                                        function validateDonation() {
                                            var selectedDonation = document.querySelector('input[name="donation"]:checked');
                                            var customAmountInput = document.getElementById('customAmount');

                                            // Check if either the radio button is checked or a custom amount is entered
                                            if (!selectedDonation && customAmountInput.value.trim() === "") {
                                                alert("Please choose a donation amount or enter a custom amount.");
                                                return false; // Prevent form submission
                                            }

                                            // If the "set-amount" radio button is checked, ensure that a custom amount is entered
                                            if (selectedDonation && selectedDonation.classList.contains("set-amount") && customAmountInput.value.trim() === "") {
                                                alert("Please enter a custom amount.");
                                                return false; // Prevent form submission
                                            }

                                            return true; // Allow form submission
                                        }
    </script>





    <%@include file="./components/footer.jsp" %>
</body>

</html>