<%-- 
    Document   : HomePage.jsp
    Created on : Sep 15, 2023, 5:03:54 PM
    Author     : ytbhe
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Community Volunteer</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">



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
    </head>

    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Topbar Start -->
        <div class="container-fluid bg-dark px-5 d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-8 text-center text-lg-start mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <small class="me-3 text-light"><i class="fa fa-map-marker-alt me-2"></i>FPT DA NANG</small>
                        <small class="me-3 text-light"><i class="fa fa-phone-alt me-2"></i>+012 345 6789</small>
                        <small class="text-light"><i
                                class="fa fa-envelope-open me-2"></i>communityunity21@gmail.com</small>
                    </div>
                </div>
                <div class="col-lg-4 text-center text-lg-end">
                    <div class="d-inline-flex align-items-center" style="height: 45px;">
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i
                                class="fab fa-twitter fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i
                                class="fab fa-facebook-f fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i
                                class="fab fa-linkedin-in fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle me-2" href=""><i
                                class="fab fa-instagram fw-normal"></i></a>
                        <a class="btn btn-sm btn-outline-light btn-sm-square rounded-circle" href=""><i
                                class="fab fa-youtube fw-normal"></i></a>
                    </div>
                </div>

            </div>
        </div>


        <!-- Navbar & Hero Start -->
        <div class="container-fluid position-relative p-0">
            <!-- header -->
            <%@include file="./components/header.jsp" %>
            <div class="container-fluid bg-primary py-5 mb-5 hero-header">
                <div class="container py-5">
                    <div class="row justify-content-center py-5">
                        <div class="col-lg-10 pt-lg-5 mt-lg-5 text-center">
                            <h1 class="display-3 text-white mb-3 animated slideInDown">Help us to save earth</h1>

                            <p class="fs-4 text-white mb-4 animated slideInDown">Make the World Great Again</p>
                            <form action="SearchControl" method="get">
                                <div class="position-relative w-75 mx-auto animated slideInDown">
                                    <input class="form-control border-0 rounded-pill w-100 py-3 ps-4 pe-5" type="text" name="searchTerm" placeholder="Eg: Da Nang" required>
                                    <button type="submit" class="btn btn-primary rounded-pill py-2 px-4 position-absolute top-0 end-0 me-2" style="margin-top: 7px;">Search</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Navbar & Hero End -->

        <div class="container-xxl py-5" style="background-color: #f9f9f9;">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="list-group">
                            <a href="CatePageControl?cate=1" class="list-group-item list-group-item-action text-center" style="max-width: 60%; margin: 0 auto;">Sắp diễn ra</a>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="list-group">
                            <a href="CatePageControl?cate=2" class="list-group-item list-group-item-action text-center" style="max-width: 60%; margin: 0 auto;">Đang diễn ra</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="list-group">
                            <a href="CatePageControl?cate=3" class="list-group-item list-group-item-action text-center" style="max-width: 60%; margin: 0 auto;">Đã kết thúc</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Package Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h1 class="mb-5">Events</h1>
                </div>     
                <c:if test="${not empty content}">

                    <div class="row g-4 justify-content-center">
                        <div class="row row-product" >
                            <c:forEach var="activity" items="${content}" varStatus="status">

                                <div class="col-lg-4 col-md-6 wow fadeInUp mb-3 "  data-wow-delay="0.1s" >
                                    <div class="package-item">
                                        <div class="overflow-hidden " style="width: 100%; height: 270px;" >
                                            <img  style="width: 100%; height: 270px;" class="img-fluid"  src="${activity.photo}" alt="Lỗi" >
                                        </div>
                                        <div class="d-flex border-bottom">
                                            <small class="flex-fill text-center border-end py-2"><i class="fa fa-map-marker-alt text-primary me-2"></i>${activity.getLocation()}</small>                                              
                                            <small class="flex-fill text-center py-2"><i class="fa fa-user text-primary me-2"></i>${activity.getNumberMember()}</small>
                                        </div>
                                        <div class="text-center p-4" style="height: 169px;">
                                            <h4 href="EventDetailControl?id=${activity.getActivityId()}" class="mb-0">${activity.getActivityName()}</h4>
                                            <br>

                                            <div class="d-flex justify-content-center mb-2">
                                                <a href="EventDetailControl?id=${activity.getActivityId()}" class="btn btn-sm btn-primary px-3 " style="border-radius: 30px 30px 30px 30px;">Detail</a>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${status.count % 3 == 0 && status.count != content.size()}">
                                </div>
                                <div class="row row-product">

                                </c:if>
                            </c:forEach>     
                        </c:if>
                    </div>

                </div>
                <div class="mt-3 paging wow fadeInUp">  
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${spage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="SearchControl?spage=1&searchTerm=${searchTerm}" aria-label="Trang đầu">
                                    <span aria-hidden="true">&laquo;&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item ${spage == 1 ? 'disabled' : ''}">
                                <a class="page-link" href="SearchControl?spage=${spage - 1}&searchTerm=${searchTerm}" aria-label="Trang trước">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <c:forEach var="j" begin="1" step="1" end="${stotalPage}">
                                <li class="page-item ${spage == j ? 'active' : ''}">
                                    <a class="page-link" href="SearchControl?spage=${j}&searchTerm=${searchTerm}">${j}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${spage == stotalPage ? 'disabled' : ''}">
                                <a class="page-link" href="SearchControl?spage=${spage + 1}&searchTerm=${searchTerm}" aria-label="Trang sau">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            <li class="page-item ${spage == stotalPage ? 'disabled' : ''}">
                                <a class="page-link" href="SearchControl?spage=${stotalPage}&searchTerm=${searchTerm}" aria-label="Trang cuối">
                                    <span aria-hidden="true">&raquo;&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>
            <!-- Package End -->



            <!-- Service Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6
                            class="section-title bg-white text-center text-primary px-3">
                            Activites</h6>
                        <h1 class="mb-5">Our activities</h1>
                    </div>
                    <div class="row g-4">
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.1s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-globe text-primary mb-4"></i>
                                    <h5>Education</h5>
                                    <p>Tutoring or teaching students, assisting with
                                        after-school programs, helping with educational
                                        nonprofit organizations.<br>
                                    </p>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.3s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-hotel text-primary mb-4"></i>
                                    <h5>Health and human services</h5>
                                    <p>Volunteering at hospitals, nursing homes,
                                        hospices, food banks, shelters, assisting
                                        individuals with disabilities.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.5s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-user text-primary mb-4"></i>
                                    <h5>Environment </h5>
                                    <p>Working with land conservation efforts, parks,
                                        beaches, trails, environmental nonprofits. Tasks
                                        include restoration, research, education.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.7s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-cog text-primary mb-4"></i>
                                    <h5>Animal welfare</h5>
                                    <p>Volunteering at animal shelters, rescues,
                                        sanctuaries, veterinary clinics, wildlife
                                        rehabilitation. Tasks include socializing
                                        animals, cleaning, fundraising.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.1s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-globe text-primary mb-4"></i>
                                    <h5>Arts and culture</h5>
                                    <p> Assisting with museums, theaters, music/dance
                                        groups. Tasks include event support, docent/tour
                                        guiding, administrative work.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.3s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-hotel text-primary mb-4"></i>
                                    <h5>Community improvement</h5>
                                    <p>Volunteering with neighborhood associations,
                                        community centers, libraries. Tasks include
                                        event planning, beautification projects,
                                        administrative support.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.5s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-user text-primary mb-4"></i>
                                    <h5>International/global </h5>
                                    <p>Volunteering abroad or for international
                                        nonprofit organizations focused on issues like
                                        poverty, healthcare, education, human rights.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-sm-6 wow fadeInUp"
                             data-wow-delay="0.7s">
                            <div class="service-item rounded pt-3">
                                <div class="p-4">
                                    <i class="fa fa-3x fa-cog text-primary mb-4"></i>
                                    <h5>Political/social advocacy</h5>
                                    <p>Volunteering for campaigns, organizations that
                                        focus on issues like voter registration, women's
                                        rights, LGBTQ+ rights, racial justice, poverty
                                        advocacy. Tasks include outreach, fundraising,
                                        administrative support.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Service End -->



            <!-- Destination Start -->
            <div class="container-xxl py-5 destination">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Destination</h6>
                        <h1 class="mb-5">Popular Destination</h1>
                    </div>
                    <div class="row g-3">
                        <div class="col-lg-7 col-md-6">
                            <div class="row g-3">
                                <div class="col-lg-12 col-md-12 wow zoomIn" data-wow-delay="0.1s">
                                    <a class="position-relative d-block overflow-hidden" href="">
                                        <img class="img-fluid" src="img/destination-1.jpg" alt="">
                                        <div class="bg-white text-danger fw-bold position-absolute top-0 start-0 m-3 py-1 px-2">30% OFF</div>
                                        <div class="bg-white text-primary fw-bold position-absolute bottom-0 end-0 m-3 py-1 px-2">Thailand</div>
                                    </a>
                                </div>
                                <div class="col-lg-6 col-md-12 wow zoomIn" data-wow-delay="0.3s">
                                    <a class="position-relative d-block overflow-hidden" href="">
                                        <img class="img-fluid" src="img/destination-2.jpg" alt="">
                                        <div class="bg-white text-danger fw-bold position-absolute top-0 start-0 m-3 py-1 px-2">25% OFF</div>
                                        <div class="bg-white text-primary fw-bold position-absolute bottom-0 end-0 m-3 py-1 px-2">Malaysia</div>
                                    </a>
                                </div>
                                <div class="col-lg-6 col-md-12 wow zoomIn" data-wow-delay="0.5s">
                                    <a class="position-relative d-block overflow-hidden" href="">
                                        <img class="img-fluid" src="img/destination-3.jpg" alt="">
                                        <div class="bg-white text-danger fw-bold position-absolute top-0 start-0 m-3 py-1 px-2">35% OFF</div>
                                        <div class="bg-white text-primary fw-bold position-absolute bottom-0 end-0 m-3 py-1 px-2">Australia</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5 col-md-6 wow zoomIn" data-wow-delay="0.7s" style="min-height: 350px;">
                            <a class="position-relative d-block h-100 overflow-hidden" href="">
                                <img class="img-fluid position-absolute w-100 h-100" src="img/destination-4.jpg" alt="" style="object-fit: cover;">
                                <div class="bg-white text-danger fw-bold position-absolute top-0 start-0 m-3 py-1 px-2">20% OFF</div>
                                <div class="bg-white text-primary fw-bold position-absolute bottom-0 end-0 m-3 py-1 px-2">Indonesia</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Destination Start -->




            <!-- Booking Start -->
            <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
                <div class="container">
                    <div class="booking p-5">
                        <div class="row g-5 align-items-center">
                            <div class="col-md-6 text-white">
                                <h6 class="text-white text-uppercase">Booking</h6>
                                <h1 class="text-white mb-4">Online Booking</h1>
                                <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos. Clita erat ipsum et lorem et sit.</p>
                                <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos. Clita erat ipsum et lorem et sit, sed stet lorem sit clita duo justo magna dolore erat amet</p>
                                <a class="btn btn-outline-light py-3 px-5 mt-2" href="">Read More</a>
                            </div>
                            <div class="col-md-6">
                                <h1 class="text-white mb-4">Book A Tour</h1>
                                <form>
                                    <div class="row g-3">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input type="text" class="form-control bg-transparent" id="name" placeholder="Your Name">
                                                <label for="name">Your Name</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input type="email" class="form-control bg-transparent" id="email" placeholder="Your Email">
                                                <label for="email">Your Email</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating date" id="date3" data-target-input="nearest">
                                                <input type="text" class="form-control bg-transparent datetimepicker-input" id="datetime" placeholder="Date & Time" data-target="#date3" data-toggle="datetimepicker" />
                                                <label for="datetime">Date & Time</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <select class="form-select bg-transparent" id="select1">
                                                    <option value="1">Destination 1</option>
                                                    <option value="2">Destination 2</option>
                                                    <option value="3">Destination 3</option>
                                                </select>
                                                <label for="select1">Destination</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form-floating">
                                                <textarea class="form-control bg-transparent" placeholder="Special Request" id="message" style="height: 100px"></textarea>
                                                <label for="message">Special Request</label>
                                            </div>
                                        </div>
                                        <div class="col-12">
                                            <button class="btn btn-outline-light w-100 py-3" type="submit">Book Now</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Booking Start -->


            <!-- Process Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center pb-4 wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Process</h6>
                        <h1 class="mb-5">3 Easy Steps</h1>
                    </div>
                    <div class="row gy-5 gx-4 justify-content-center">
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-globe fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Choose A Destination</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-dollar-sign fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Pay Online</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6 text-center pt-4 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="position-relative border border-primary pt-5 pb-4 px-4">
                                <div class="d-inline-flex align-items-center justify-content-center bg-primary rounded-circle position-absolute top-0 start-50 translate-middle shadow" style="width: 100px; height: 100px;">
                                    <i class="fa fa-plane fa-3x text-white"></i>
                                </div>
                                <h5 class="mt-4">Fly Today</h5>
                                <hr class="w-25 mx-auto bg-primary mb-1">
                                <hr class="w-50 mx-auto bg-primary mt-0">
                                <p class="mb-0">Tempor erat elitr rebum clita dolor diam ipsum sit diam amet diam eos erat ipsum et lorem et sit sed stet lorem sit</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Process Start -->


            <!-- Team Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title bg-white text-center text-primary px-3">Travel Guide</h6>
                        <h1 class="mb-5">Meet Our Guide</h1>
                    </div>
                    <div class="row g-4">
                        <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                            <div class="team-item">
                                <div class="overflow-hidden">
                                    <img class="img-fluid" src="img/team-1.jpg" alt="">
                                </div>
                                <div class="position-relative d-flex justify-content-center" style="margin-top: -19px;">
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                                </div>
                                <div class="text-center p-4">
                                    <h5 class="mb-0">Full Name</h5>
                                    <small>Designation</small>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                            <div class="team-item">
                                <div class="overflow-hidden">
                                    <img class="img-fluid" src="img/team-2.jpg" alt="">
                                </div>
                                <div class="position-relative d-flex justify-content-center" style="margin-top: -19px;">
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                                </div>
                                <div class="text-center p-4">
                                    <h5 class="mb-0">Full Name</h5>
                                    <small>Designation</small>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                            <div class="team-item">
                                <div class="overflow-hidden">
                                    <img class="img-fluid" src="img/team-3.jpg" alt="">
                                </div>
                                <div class="position-relative d-flex justify-content-center" style="margin-top: -19px;">
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                                </div>
                                <div class="text-center p-4">
                                    <h5 class="mb-0">Full Name</h5>
                                    <small>Designation</small>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                            <div class="team-item">
                                <div class="overflow-hidden">
                                    <img class="img-fluid" src="img/team-4.jpg" alt="">
                                </div>
                                <div class="position-relative d-flex justify-content-center" style="margin-top: -19px;">
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-facebook-f"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="btn btn-square mx-1" href=""><i class="fab fa-instagram"></i></a>
                                </div>
                                <div class="text-center p-4">
                                    <h5 class="mb-0">Full Name</h5>
                                    <small>Designation</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Team End -->


            <!-- Testimonial Start -->
            <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
                <div class="container">
                    <div class="text-center">
                        <h6 class="section-title bg-white text-center text-primary px-3">Testimonial</h6>
                        <h1 class="mb-5">Our Clients Say!!!</h1>
                    </div>
                    <div class="owl-carousel testimonial-carousel position-relative">
                        <div class="testimonial-item bg-white text-center border p-4">
                            <img class="bg-white rounded-circle shadow p-1 mx-auto mb-3" src="img/testimonial-1.jpg" style="width: 80px; height: 80px;">
                            <h5 class="mb-0">John Doe</h5>
                            <p>New York, USA</p>
                            <p class="mb-0">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit diam amet diam et eos. Clita erat ipsum et lorem et sit.</p>
                        </div>
                        <div class="testimonial-item bg-white text-center border p-4">
                            <img class="bg-white rounded-circle shadow p-1 mx-auto mb-3" src="img/testimonial-2.jpg" style="width: 80px; height: 80px;">
                            <h5 class="mb-0">John Doe</h5>
                            <p>New York, USA</p>
                            <p class="mt-2 mb-0">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit diam amet diam et eos. Clita erat ipsum et lorem et sit.</p>
                        </div>
                        <div class="testimonial-item bg-white text-center border p-4">
                            <img class="bg-white rounded-circle shadow p-1 mx-auto mb-3" src="img/testimonial-3.jpg" style="width: 80px; height: 80px;">
                            <h5 class="mb-0">John Doe</h5>
                            <p>New York, USA</p>
                            <p class="mt-2 mb-0">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit diam amet diam et eos. Clita erat ipsum et lorem et sit.</p>
                        </div>
                        <div class="testimonial-item bg-white text-center border p-4">
                            <img class="bg-white rounded-circle shadow p-1 mx-auto mb-3" src="img/testimonial-4.jpg" style="width: 80px; height: 80px;">
                            <h5 class="mb-0">John Doe</h5>
                            <p>New York, USA</p>
                            <p class="mt-2 mb-0">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit diam amet diam et eos. Clita erat ipsum et lorem et sit.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Testimonial End -->

            <%@include file="./components/footer.jsp"%>


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>


            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
            <script src="lib/wow/wow.min.js"></script>
            <script src="lib/easing/easing.min.js"></script>
            <script src="lib/waypoints/waypoints.min.js"></script>
            <script src="lib/owlcarousel/owl.carousel.min.js"></script>
            <script src="lib/tempusdominus/js/moment.min.js"></script>
            <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
            <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

            <!-- Template Javascript -->
            <script src="js/main.js"></script>
    </body>

</html>