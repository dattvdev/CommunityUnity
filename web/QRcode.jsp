<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Account" %>
<%@ page import="dao.AccountDAO" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Vounteer</title>
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



        </style>

    </head>

    <body>



        <%@include file="./components/header.jsp" %>
        <!-- About Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="row g-5">

                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="donation-container">

                            <div class="col-md-7" style="background-color: #f5f7f9;   font-size: 20px;">
                                <form id="donationForm" action="donationpage" method="post" >

                                    <table >
                                        <tr><h3>Thông Tin Đơn Hàng</h3></tr>
                                        <tr>--------------------------</tr><br>

                                        <tr>Số tiền thanh toán</tr><br>
                                        <tr>${donation} vnđ</tr><br><br>


                                        <tr>Giá trị đơn hàng</tr><br>
                                        <tr>${donation} vnd</tr> <br><br>

                                        <tr>Phí giao dịch</tr> <br>
                                        <tr>0 vnđ</tr><br><br>
                                        <tr>Mã đơn hàng</tr><br>
                                        <tr>${maDH}</tr> <br><br>
                                        <tr>Thời gian</tr><br>
                                        <tr>${time}</tr> <br><br>
                                    </table>
                                      </div>
                            <div style="padding-left: 90px; font-size: 17px; padding-top:4%;">
                                        <button>
                                            <span class="btn-text font-size: 20px;">
                                                Đã thanh toán
                                            </span>
                                        </button>
                                    </div>
                                </form>

                        </div>

                    </div>

                    <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s" style="min-height: 400px;">

                        <h4 style="justify-content: center;padding-left: 86px;">Quét mã qua ứng dụng Ví VNPAY</h4>
                        <form>

                            <div class="position-relative h-100 " >
                                <img class="" src='https://img.vietqr.io/image/${namebank}-${namecard}-print.png?amount=${donation}&addInfo=${text}&accountName=${nameAcc}' alt="" style="object-fit: cover;">
                               
                            </div>
                            <div style="padding-left: 213px;">
                                <button>
                                    <span class="btn-text">
                                        <a href="donation.jsp">Hủy thanh toán</a>
                                    </span>
                                </button>
                            </div>

                        </form>



                    </div>



                </div>
            </div>
        </div>

        <img class="" src="images/bankpng.png" alt="" style="object-fit: cover; padding: 6%;" loading="lazy">


    </button>
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
    </script>





    <%@include file="./components/footer.jsp" %>
</body>

</html>