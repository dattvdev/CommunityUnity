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
        <link href="https://fonts.googleapis.com/icon?family=Material+Symbols+Outlined" rel="stylesheet">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/admin.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

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
                        <span class="material-symbols-outlined">home</span>
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
            <main class="main-container">
                <div class="main-title">
                    <p class="font-weight-bold">DASHBOARD</p>
                </div>

                <div class="main-cards">

                    <div class="card">
                        <div class="card-inner">
                            <p class="text-primary">TOTAL ACTIVITY</p>

                        </div>

                        <span class="text-primary font-weight-bold">${total}</span>


                    </div>

                    <div class="card">
                        <div class="card-inner">
                            <p class="text-primary">UPCOMING</p>

                        </div>
                        <span class="text-primary font-weight-bold">${upcoming}</span>
                    </div>

                    <div class="card">
                        <div class="card-inner">
                            <p class="text-primary">IN PROGRESS</p>

                        </div>
                        <span class="text-primary font-weight-bold">${inprogress}</span>
                    </div>

                    <div class="card">
                        <div class="card-inner">
                            <p class="text-primary">FINISHED</p>
                        </div>
                        <span class="text-primary font-weight-bold">${finished}</span>
                    </div>

                </div>

                <div class="container">
                    <canvas id="myChart"></canvas>

                </div>


            </main>
            <!-- End Main -->

        </div>
        <script>
            let myChart = document.getElementById('myChart').getContext('2d');
            // Global Options
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = '#777';
            let massPopChart = new Chart(myChart, {
                type: 'horizontalBar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
                data: {
                    labels: ['Total', 'Upcoming', 'In progress', 'Finished'],
                    datasets: [{
                            label: 'Population',
                            data: [
            ${total},
            ${upcoming},
            ${inprogress},
            ${finished},
                            ],
                            //backgroundColor:'green',
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.6)',
                                'rgba(54, 162, 235, 0.6)',
                                'rgba(255, 206, 86, 0.6)',
                                'rgba(75, 192, 192, 0.6)',
                                'rgba(153, 102, 255, 0.6)'
                            ],
                            borderWidth: 1,
                            borderColor: '#777',
                            hoverBorderWidth: 3,
                            hoverBorderColor: '#000'
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Activity',
                        fontSize: 25
                    },
                    legend: {
                        display: true,
                        position: 'right',
                        labels: {
                            fontColor: '#000'
                        }
                    },
                    layout: {
                        padding: {
                            left: 50,
                            right: 0,
                            bottom: 0,
                            top: 0
                        }
                    },
                    tooltips: {
                        enabled: true
                    }
                }
            });
        </script>

        <!-- Scripts -->
        <!-- ApexCharts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/apexcharts/3.35.3/apexcharts.min.js"></script>
        <!-- Custom JS -->
        <script src="./js/admin.js"></script>
    </body>
</html>