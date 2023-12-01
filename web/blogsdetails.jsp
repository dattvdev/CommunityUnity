<%-- 
    Document   : single
    Created on : Oct 19, 2023, 5:07:37 PM
    Author     : ytbhe
--%><%@page import="dao.BlogsDAO"%>
<%@page import="dao.AccountDAO"%>
<%@page import="entity.Account"%>
<%@page import="entity.Account"%>
<%@page import="entity.Blogs"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>


        <meta charset="utf-8">
        <title>CommunityUnity</title>
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
        <link rel="stylesheet" href="./BlogsComponent/css/base.css">
        <link rel="stylesheet" href="./BlogsComponent/css/main.css">
        <!-- script
   ================================================== -->
        <script src="./BlogsComponent/js/modernizr.js"></script>
        <script defer src="./BlogsComponent/js/fontawesome/all.min.js"></script>

        <!-- favicons
        ================================================= -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css">

        <link rel="apple-tou================================== -->
              <link rel="apple-touch-icon" sizes="180x180" href="apple-touch-icon.png">
              <link rel="icon" type="image/png" sizes="32x32" href="favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="16x16" href="favicon-16x16.png">
        <link rel="manifest" href="site.webmanifest">
        <link rel="stylesheet" href="path-to-bootstrap-css/bootstrap.min.css">
        <script src="path-to-bootstrap-js/bootstrap.min.js"></script>
        <style>

            .nav-link.dropdown-toggle{
                display: flex;
            }

            .commentlist>.comment{
                padding-left: 6%;
            }
            body{
                background: white;
            }
        </style>

    </head>
    <%@include file="./components/header.jsp" %>
    <body id="top" class="ss-single">


        <!-- Header
    



        <!-- Content
        ================================================== -->

        <%
            String name = ((Account) session.getAttribute("LOGIN_USER")).getUserName();
            Account a = new Account();
            AccountDAO dao = new AccountDAO();

            a = dao.getAccount_BYUSER(name);
            int userID = a.getAccId();


        %>

        `       <%                    BlogsDAO o = new BlogsDAO();
            int countCate1 = o.getToTalCate("health");
            int countCate2 = o.getToTalCate("animal");
            int countCate3 = o.getToTalCate("environment");
            int countCate4 = o.getToTalCate("volunteer");
        %>

        <div class="col-md-12" style="display: flex;padding: 5%">

            <div class="col-md-10">
                <div class="s-content" style="width: 100%;">



                    <div id="main" class="s-content__main">
                        <c:set var="p" value="${blogsdetails}"/>


                        <article class="entry">
                            <c:if test="${blogsdetails != null}">
                                <header class="entry__header">

                                    <h2 class="entry__title h1" style="text-align: center;">
                                        ${p.title}
                                    </h2>

                                    <div class="entry__meta">
                                        <ul>
                                            <li>${p.date}</li>
                                            <li><a href="#" title="" rel="category tag">Ghost</a></li>
                                            <li>${p.author}</li>
                                        </ul>
                                    </div>

                                </header> <!-- entry__header -->

                                <div class="entry__content-media">
                                    <img src="${p.photo}" 
                                         srcset="${p.photo} 1000w, 
                                         ${p.photo} 500w" 
                                         sizes="(max-width: 1000px) 100vw, 1000px" alt="null">
                                </div>

                                <div class="entry__content">
                                    <p style="margin: 5%;">
                                        ${p.content}
                                    </p>

                                </div> <!-- entry__content -->

                            </c:if>

                                <p class="entry__tags" style="color: #84be5b;">
                                    <span style="color:black;">Tagged in </span>:
                                ${p.category}
                            </p>

                            <ul class="entry__post-nav h-group">
                                <li class="prev"><a rel="prev" href="#0"><strong class="h6">Previous Article</strong> Duis Sed Odio Sit Amet Nibh Vulputate</a></li>
                                <li class="next"><a rel="next" href="#0"><strong class="h6">Next Article</strong> Morbi Elit Consequat Ipsum</a></li>
                            </ul>

                        </article> <!-- end entry -->

                        <form action="LikeServlet" method="post">

                            <input type="hidden" name="postId" value="${p.blogId}">

                            <input type="hidden" name="userId" value="<%=userID%>">

                            <button type="submit" name="action" value="Like">

                                <i class="fa fa-thumbs-up"></i> ${count}

                            </button>

                            <button id="commentButton" type="button" name="action" value="Comment">
                                <i class="fa fa-comment"></i> ${countcmt}
                            </button>


                        </form>



                        <div class="comments-wrap">

                            <div id="comments" style="padding-left: 10%;">

                                <h3>${countcmt} Comments</h3>

                                <!-- START commentlist -->

                                <ol class="commentlist" style="
                                    padding-left: 5%;">

                                    <li class="depth-1 comment">
                                        <c:forEach var="o" items="${comments}">

                                            <div class="comment__avatar">
                                                <img class="avatar" src="${o.photoUser}" alt="photo" width="50" height="50">
                                            </div>

                                            <div class="comment__content">

                                                <div class="comment__info">


                                                    <div class="comment__author" style="display: flex;"> <div class="qa" style="width: 533px;"> ${o.commentAuthorName}
                                                            <div class="comment__meta">
                                                                <div class="comment__time">${o.commentDate}</div>
                                                                <p style="width: 100%;">${o.commentContent} </p>

                                                            </div>
                                                        </div> 

                                                        <c:if test="${o.commentAuthorId == userIDLG || sessionScope.LOGIN_USER.role  == 0 }">
                                                            <!-- Show the delete comment button here -->

                                                            <form action="blogsdetail" method="post" style="  text-align: end;">


                                                                <input type="hidden" name="postId" value="${p.blogId}">

                                                                <input type="hidden" name="userId" value="<%=userID%>">
                                                                <input type="hidden" name="cmtID" value="${o.commentId}">

                                                                <button type="submit" class="" value="submit" style="
                                                                        text-transform: none;

                                                                        width: auto;    
                                                                        justify-content: center;
                                                                        font-size: 14px; /* Changed font size to make the icon more visible */
                                                                        text-align: start;
                                                                        background-color: white;
                                                                        border: none;
                                                                        justify-content: flex-start;
                                                                        margin: unset;
                                                                        padding: unset;
                                                                        padding-left:25px;
                                                                        ">
                                                                    <i class="fas fa-trash-alt"></i>delete
                                                                </button>
                                                            </form>
                                                        </c:if>








                                                    </div>
                                                </div>




                                            </div>



                                        </c:forEach>

                                    </li> <!-- end comment level 1 -->



                                </ol>

                                <!-- END commentlist -->

                            </div> <!-- end comments -->

                            <div class="comment-respond">

                                <!-- START respond -->
                                <div id="respond">

                                    <h3>Add Comment <span>Your email address will not be published</span></h3>

                                    <form name="contactForm" id="contactForm" method="post" action="BlogsComment" autocomplete="off">
                                        <fieldset>



                                            <div class="message form-field">
                                                <textarea name="cMessage" id="cMessage" class="h-full-width" placeholder="Your Message"></textarea>
                                                <input type="hidden" name="postId" value="${p.blogId}">

                                                <input type="hidden" name="userId" value="<%=userID%>">
                                            </div>

                                            <input name="submit" id="submit" class="btn btn--primary btn-wide btn--large h-full-width" value="Add Comment" type="submit">

                                        </fieldset>
                                    </form> <!-- end form -->

                                </div>
                                <!-- END respond-->

                            </div> <!-- end comment-respond -->

                        </div> <!-- end comments-wrap -->

                    </div> <!-- end main -->
                </div> 
            </div>

            <div class="col-md-2" style="padding-left: 5%;">
                <div id="sidebar" class="s-content__sidebar">


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

                        <ul class="link-list">
                            <li><a href="createpost">Add new post</a></li>

                        </ul>
                        <ul class="link-list"  style="display: ${ userIDLG==idAuthor  || sessionScope.LOGIN_USER.role  == 0? 'inline' : 'none'} ;">
                            <li><a href="DeletePost?id=${p.blogId}">Delete post</a></li>

                        </ul>
                    </div>

                </div>

            </div> <!-- end sidebar -->

        </div> <!-- end row -->




        <footer style="display: flex;background-color: black;height: 10%;color: aquamarine;flex-direction: column;text-align: center;">




            <span>© Copyright Keep It Simple 2023</span> 
            <span>Design by Twna21</span>


        </footer>




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
        <script src="./BlogsComponent/js/jquery-3.2.1.min.js"></script>
        <script src="./BlogsComponent/js/main.js"></script>



    </body>

</html>