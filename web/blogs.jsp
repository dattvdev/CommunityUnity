<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Account" %>
<%@ page import="dao.AccountDAO" %>
<%@ page import="dao.BlogsDAO" %>

<!DOCTYPE html>
<html  class="no-js" lang="en">
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
            
         
            .nav-link.dropdown-toggle{
                display: flex;
            }

            
            .navbar-light {
                background-color: black;
            }
            .py-5{
                padding-top: 10rem !important;
            }

            .nav-link.dropdown-toggle{
                display: flex;
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
        `       <%
            BlogsDAO dao = new BlogsDAO();
            int countCate1 = dao.getToTalCate("health");
            int countCate2 = dao.getToTalCate("animal");
            int countCate3 = dao.getToTalCate("environment");
            int countCate4 = dao.getToTalCate("volunteer");
        %>

        <!--Blogs start-->

        <!-- Content-->
        <div class="col-md-12" style="display: flex; padding: 5%;">
            <div class="col-md-10">

                <div class="">

                    <div id="main" class="s-content__main">


                        <!--test-->

                        <ul>
                            <c:forEach var="o" items="${blogs}">
                                <c:if test="${o.pending == 1}">
                                    <article class="entry">

                                        <header class="entry__header">

                                            <h2 class="entry__title h1">
                                                <a href="blogsdetail?id=${o.blogId}" title="">${o.title}</a>
                                            </h2>
                                            <img src="${o.photo}">
                                            <div class="entry__meta">
                                                <ul>
                                                    <li>${o.date}</li>
                                                    <li>
                                                        <a href="#0" title="" rel="category tag">${o.category}</a>,

                                                    </li>
                                                    <li>${o.author}</li>
                                                </ul>
                                            </div>

                                        </header>

                                        <div class="entry__content">
                                            <p>
                                                ${o.shortContent}
                                            </p>
                                        </div>

                                    </article> 
                                </c:if>
                                <hr>
                            </c:forEach>

                        </ul>
                        <!--test-->



                        <!-- page nav -->
                        <div class="mt-3 paging wow fadeInUp">  
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${spage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="blogs?spage=1" aria-label="Trang đầu">
                                            <span aria-hidden="true">&laquo;&laquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item ${spage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="blogs?spage=${spage - 1}" aria-label="Trang trước">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach var="j" begin="1" step="1" end="${stotalPage}">
                                        <li class="page-item ${spage == j ? 'active' : ''}">
                                            <a class="page-link" href="blogs?spage=${j}">${j}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item ${spage == stotalPage ? 'disabled' : ''}">
                                        <a class="page-link" href="blogs?spage=${spage + 1}" aria-label="Trang sau">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item ${spage == stotalPage ? 'disabled' : ''}">
                                        <a class="page-link" href="blogs?spage=${stotalPage}" aria-label="Trang cuối">
                                            <span aria-hidden="true">&raquo;&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>

                    </div> <!-- end main -->
                </div>
            </div>

            <div class="col-md-2">
                <div id="sidebar" class="s-content__sidebar ">

                    <div class="widget widget--search">
                        <h3 class="h6">Search</h3> 
                        <form action="searchblog">
                            <input type="text" name="text" placeholder="Search here..." class="text-search">
                            <input type="submit" class="submit-search">
                        </form>
                    </div>

                    <div class="widget widget--categories">
                        <h3 class="h6">Categories.</h3> 


                        <ul style="color: #84be5b;  ">
                            <li>Health (<%= countCate1%>)</li>
                            <li>Animal (<%= countCate2%>)</li>
                            <li>Environment (<%= countCate3%>)</li>
                            <li>Volunteer (<%= countCate4%>)</li>

                        </ul>
                    </div>

                    <div class="widget widget_popular">
                        <h3 class="h6">Create your blogs.</h3>
                        <span id="error_message" style="color: red;  white-space: pre-line;"></span>
                        <ul class="link-list">
                            <li><a href="createpost">Add new post</a></li>

                        </ul>
                    </div>





                </div> <!-- end sidebar -->
            </div>
        </div> <!-- end row -->

    </div> <!-- end content-wrap -->
</div>
<!--Blogs end-->

<!-- Footer
================================================== -->
<footer class="s-footer">

    <div class="row s-footer__top">
        <div class="column">
            <ul class="s-footer__social">
                <li><a href="#0"><i class="fab fa-facebook-f" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-twitter" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-youtube" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-vimeo-v" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-instagram" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-linkedin" aria-hidden="true"></i></a></li>
                <li><a href="#0"><i class="fab fa-skype" aria-hidden="true"></i></a></li>
            </ul>
        </div>
    </div> <!-- end footer__top -->

    <div class="row s-footer__bottom">

        <div class="large-6 tab-full column s-footer__info">
            <h3 class="h6">About Keep It Simple</h3>

            <p>
                Lorem ipsum Ullamco commodo laboris sit dolore commodo aliquip incididunt fugiat esse dolor 
                aute fugiat minim eiusmod do velit labore fugiat officia ad sit culpa labore in consectetur 
                sint cillum sint consectetur voluptate adipisicing Duis
            </p>

            <p>
                Lorem ipsum Sed nulla deserunt voluptate elit occaecat culpa cupidatat sit irure sint 
                sint incididunt cupidatat esse in Ut sed commodo tempor consequat culpa fugiat incididunt.
            </p>
        </div>

        <div class="large-6 tab-full column">
            <div class="row">
                <div class="large-8 tab-full column">

                    <h3 class="h6">Photostream</h3>

                    <ul class="photostream group">
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                        <li><a href="#0"><img alt="thumbnail" src="images/thumb.jpg"></a></li>
                    </ul>

                </div>

                <div class="large-4 tab-full column">
                    <h3  class="h6">Navigate</h3>

                    <ul class="s-footer__list s-footer-list--nav group">
                        <li><a href="home">Home</a></li>
                        <li><a href="blogs">Blog</a></li>
                        <li><a href="#0">Demo</a></li>
                        <li><a href="logout">Logout</a></li>
                        <li><a href="#0">About</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="ss-copyright">
            <span>© Copyright Keep It Simple 2019</span> 
            <span>Design by <a href="https://www.styleshout.com/">StyleShout</a></span>
        </div>

    </div> <!-- end footer__bottom -->


    <div class="ss-go-top">
        <a class="smoothscroll" title="Back to Top" href="#top">
            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M12 0l8 9h-6v15h-4v-15h-6z"/></svg>
        </a>
    </div> <!-- end ss-go-top -->

</footer> <!-- end Footer-->





<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="./js/myjs.js"></script>
<script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script src="https://raw.githubusercontent.com/jerryluk/jquery.autogrow/master/jquery.autogrow-min.js"></script>

<script src="./BlogsComponent/js/jquery-3.2.1.min.js"></script>
<script src="./BlogsComponent/js/main.js"></script>

<script src="./js/location.js"></script> 
<script src="./js/BrowserJoin.js"></script>    
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