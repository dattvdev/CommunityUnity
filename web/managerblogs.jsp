<%-- 
Document   : ManagerProduct
Created on : Dec 28, 2020, 5:19:02 PM
Author     : trinh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <title>Admin Dashboard</title>

        <!-- Montserrat Font -->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css" integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Symbols+Outlined" rel="stylesheet">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/manager.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <div class="grid-container">

            <!-- Header -->
            <header class="header">
                <div class="menu-icon" onclick="openSidebar()">
                    <span class="material-icons-outlined">menu</span>
                </div>
                <div class="header-left">
                    <a href="home">
                        <span class="material-symbols-outlined" style="color: black">home</span>
                    </a>   
                </div>
                <div class="header-right">
                    <c:if test="${sessionScope.account.getRole() == 1}">
                        <h5>Xin Chào ${sessionScope.account.username}</h5>
                    </c:if>
                </div>
                <span class="material-icons-outlined">account_circle</span>
            </header>
            <!-- End Header -->

           <!-- Sidebar -->
            <aside id="sidebar">
                <div class="sidebar-title">
                    <div class="sidebar-brand">
                        <span class="material-symbols-outlined">
                            admin_panel_settings
                        </span> Admin Panel
                    </div>
                    <span class="material-icons-outlined" onclick="closeSidebar()">close</span>
                </div>

                <ul class="sidebar-list">
                    <li class="sidebar-list-item">
                        <a href="admin" target="">
                            <span class="material-icons-outlined">dashboard</span> Dashboard
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="BlogsManagerControl" target="">
                            <span class="material-icons-outlined">inventory_2</span> Blogs
                        </a>
                    </li>
                    <li class="sidebar-list-item">

                        <a href="./ApproveControl" target="">
                            <span class="material-icons-outlined">fact_check</span> Approve Event
                        </a>
                    </li>
                    <li class="sidebar-list-item">

                        <a href="./ApproveReport" target="">
                            <span class="material-icons-outlined">warning</span> Report
                        </a>
                    </li>    
                    <li class="sidebar-list-item">
                        <a href="manageraccount" target="">
                            <span class="material-icons-outlined">manage_accounts</span> Account Manager
                        </a>
                    </li>
                    <li class="sidebar-list-item">
                        <a href="https://dashboard.tawk.to/#/dashboard/" target="_blank">
                            <span class="material-icons-outlined">chat</span> Chat
                        </a> 
                    </li>
                    <li class="sidebar-list-item">
                        <a href="logout" target="">
                            <span class="material-icons-outlined">logout</span> Log Out
                        </a> 
                    </li>
                </ul>
            </aside>
            <!-- End Sidebar -->

            <!-- Main -->
            <div class="containerr">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="menu">
                            <div >
                                <h2 style="width: 100%;">Manage <b>Blogs</b></h2>
                            </div>

                        </div>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Image</th>
                                <th>Short Content</th>
                                <th>Author</th>
                                <th>Pending</th>
                                <th>Delete</th>
                                <th>Approve</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${blogs}" var="o">
                                <tr>

                                    <td> <a href="./blogsdetail?id=${o.blogId}">${o.blogId}</a></td>
                                    <td>${o.title}</td>

                                    <td> <a href="./blogsdetail?id=${o.blogId}"><img width="150px" src="${o.photo}"></a></td> 

                                    <td>
                                        ${o.shortContent}
                                    </td>
                                    <td>
                                        ${o.author}
                                    </td>
                                    <td style="color: ${o.pending == 1 ? 'green' : 'red'}">
                                        ${o.pending == 1 ? 'Has been approved' : 'Pending approval'}
                                    </td>

                                    <td>

                                        <a href="DeleteBlogs?id=${o.blogId}" class="delete" data-toggle="modal"><span class="material-symbols-outlined" title="delete">delete</a>
                                    </td>
                                    <td>

                                        <a href="ApproveBlogs?id=${o.blogId}" class="add" data-toggle="modal"><img src="./images/approved-icon-7.jpg" title="delete" style="display: ${o.pending == 0 ? 'none' : 'inline'} ;height: 28px;padding-bottom: 7px; padding-left:20px;  "></a>
                                        <a href="ApproveBlogs?id=${o.blogId}" class="add" data-toggle="modal"><img src="./images/noapprove.jpg"  title="delete" style="display: ${o.pending == 1 ? 'none' : 'inline'} ;height: 28px;padding-bottom: 7px; padding-left:20px;  "></a>

                                    </td>

                                </tr>




                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">



                    </div>
                    <!-- Edit Modal HTML -->




                </div>
            </div>
        </div>
        <!-- Scripts -->
        <!-- ApexCharts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.3/apexcharts.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <!-- Custom JS -->
        <script src="./js/manager.js"></script>


    </body>
</html>