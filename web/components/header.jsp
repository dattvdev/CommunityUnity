<%@page import="java.util.ArrayList"%>
<%@page import="entity.Notification"%>
<%@page import="dao.NotificateDAO"%>
<%@page import="dao.AccountDAO"%>
<%@page import="entity.Account"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg custom-navbar">
    <a href="" class="navbar-brand p-0">
        <a href ="home"><h1 class="text-primary m-0" ><i class="fa fa-map-marker-alt me-3"></i>CommunityUnity</h1></a>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="fa fa-bars"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto py-0">
            <a href="home" class="nav-item nav-link active">Home</a>
            <a href="about.jsp" class="nav-item nav-link">About</a>
            <a href="blogs" class="nav-item nav-link">Blogs</a>
            <c:if test="${sessionScope.LOGIN_USER.role  == 2 || sessionScope.LOGIN_USER.role  == 0}">
                        <a href="./CreateActivity.jsp" class="nav-item nav-link">Create Activity</a>
                   

            </c:if>
            <div id="notificationIconContainer">
                
                <i id="notificationIcon" class="fa-solid fa-bell"></i>

                <%
                    NotificateDAO notify = new NotificateDAO();
                    Account loginUser = null;
                    ArrayList<Notification> listNotidisplay = new ArrayList<>();
                    try {
                        loginUser = (Account) session.getAttribute("LOGIN_USER");
                        for (Notification noti : notify.getAllNotifications()) {
                            if (noti.getUser_id() == loginUser.getAccId()) {
                                listNotidisplay.add(noti);
                            }
                        }
                    } catch (Exception e) {
                    }
                %>
                <div class="notifications">
                    <% for (Notification notifies : listNotidisplay) {%>
                    <div id="notification" class="hidden notifications" style="margin-right:900px;">

                        <div class="notification-content">
                            <a href="<%= notifies.getLink()%>" style="color: black;">
                                <p><%=notifies.getContent()%></p>
                            </a>
                        </div>

                        <div class="notification-time" style="margin-left : 30px;"><%=notifies.getDate()%></div>
                    </div>

                    <%}%>
                </div>

            </div>
            <script>
                const notificationIcon = document.getElementById("notificationIcon");
                const notification = document.getElementsByClassName('notifications');

                let isNotificationVisible = false;

                notificationIcon.addEventListener("click", () => {
                    if (!isNotificationVisible) {
                        // Hi?n th? th ng b o n?u n  kh ng hi?n th?
                        for (var i = 0; i < notification.length; i++) {
                            notification[i].style.display = "block"; // ho?c s? d?ng c c l?p CSS
                            notification[i].style.top = i * 7 + "0px";
                        }

                        isNotificationVisible = true;
                    } else {
                        // ?n th ng b o n?u n  ?ang hi?n th?
                        for (var i = 0; i < notification.length; i++) {
                            notification[i].style.display = "none";
                        }
                        //// ho?c s? d?ng c c l?p CSS
                        isNotificationVisible = false;
                    }
                });

            </script>


            <style>
                #notification {
                    display: none;
                    position: fixed;
                    background-color: white;
                    border: 1px solid #ccc;
                    width: 320px; /* ?i?u ch?nh k ch th??c theo mong mu?n */
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
                    z-index: 1000;
                    border-radius: 4px;
                    margin-top: 53px;
                    margin-left: -80px;
                }

                .notification-header {
                    padding: 10px;
                    border-bottom: 1px solid #ccc;
                }

                .notification-header h3 {
                    margin: 0;
                    font-size: 20px;
                    font-weight: 700;
                }

                .notification-content {
                    font-size:19px; 
                    font-weight: 500;
                    padding: 10px;
                    display: flex;
                    align-items: center;
                }

                .notification-content img {
                    width: 40px;
                    height: 40px;
                    border-radius: 50%;
                    margin-right: 10px;
                }

                .notification-content p {
                    margin: 0;
                    font-size: 14px;
                }
                .notification-time {
                    margin-top: -10px; /* Di chuy?n l n tr n 10px */
                    margin-left: 57px; /* Di chuy?n sang ph?i 20px */
                    font-size: 12px;
                    color: #1877f2;
                }

                #notificationIconContainer {
                    position: relative;
                    display: inline-block;
                }

                #notificationIcon {
                    padding-top:7px;
                    padding-left: 5px;
                    font-size: 24px; /* Thay ??i k ch th??c bi?u t??ng theo mong mu?n */
                }

                #notificationCount {
                    position: absolute;
                    margin-top: -8px;
                    margin-left: 7px;
                    top: 0;
                    right: 0;
                    background-color: red; /* M u n?n cho s? th ng b o */
                    color: white; /* M u ch? cho s? th ng b o */
                    border-radius: 50%; /* K ch th??c g c bo tr n */
                    padding: 4px 8px; /* Kho?ng c ch gi?a s? v  bi?u t??ng */
                    font-size: 12px; /* K ch th??c c?a s? th ng b o */
                }


            </style>
        </div>

        <c:choose>
            <c:when test="${sessionScope.LOGIN_USER == null}">
                <li class="nav-item">
                    <a href="login.jsp"><i class="fa fa-user"></i></a>
                </li>
            </c:when>
            <c:otherwise>

                <div class="nav-item dropdown"> 
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" data-bs-toggle="dropdown">
                        <img class="rounded-circle-perfect" src="${sessionScope.LOGIN_USER.photo}" alt="">
                        <label class="font-weight-bold text-primary">${sessionScope.LOGIN_USER.userName}</label>
                    </a>

                    <div class="dropdown-menu m-0">
                        <a class="dropdown-item" href="changepass">Reset Password</a>
                        <a class="dropdown-item" href="./Profile.jsp">View Profile</a>
                        <a class="dropdown-item" href="./HistoryControl">View History</a>
                        <c:if test="${ sessionScope.LOGIN_USER.role  == 0}">
                            <a class="dropdown-item" href="admin">Admin</a>
                        </c:if>

                        <a class="dropdown-item" href="logout">Log Out</a>    
                    </div>
                </c:otherwise>
            </c:choose>


        </div>
</nav>
<style>
    .rounded-circle-perfect {
        width: 35px;
        height: 35px;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 5px
    }
    .custom-navbar {
        background-color: #333;
        color: #fff; 
        padding-right: 1%;
        padding-left:1%;
    }

    .custom-navbar .navbar-brand {
        color: #fff;
    }

    .custom-navbar .navbar-nav .nav-link {
        color: #fff; 
    }
</style>
