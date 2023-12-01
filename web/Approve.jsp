<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Pending Events</title>
        <!-- Import Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Import Font Awesome for icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
        <c:choose>
            <c:when test="${sessionScope.LOGIN_USER == null}">
                <c:redirect url="login.jsp"/>
            </c:when>
            <c:when test="${sessionScope.LOGIN_USER.getRole() !=0}">
                <%
                    String message = "Bạn Không Có Quyền Truy Cập Admin Panel!";
                    request.setAttribute("ms", message);
                %>
                <c:redirect url="errormanage.jsp"/>

            </c:when>
        </c:choose>
    </head>
    <body>
        <%@include file="./components/header.jsp" %>
        <div class="container mt-5">
            <h4>Xét duyệt các hoạt động:</h4>
            <hr/><br>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">STT</th>
                        <th scope="col">Tên hoạt động</th>
                        <th scope="col">Địa điểm</th>
                        <th scope="col">Người tổ chức</th>
                        <th scope="col">Chi tiết</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pendingEvents}" var="event" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td> 
                            <td>${event.activityName}</td>
                            <td>${event.location}</td>
                            <td>${event.organizerId}</td>
                            <td>
                                <button class="btn btn-link detail-button" data-toggle="modal" data-target="#eventModal-${event.activityId}">Detail</button>
                            </td>
                            <td>
                                <button class="btn btn-danger" onclick="rejectEvent(${event.activityId}, this, this.nextElementSibling)">Từ chối</button>
                                <button class="btn btn-success" onclick="approveEvent(${event.activityId}, this, this.previousElementSibling)">Xét duyệt</button>
                            </td>
                        </tr>
                    <div class="modal fade" id="eventModal-${event.activityId}" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="eventModalLabel">Thông Tin Chi Tiết Sự Kiện</h5>
                                </div>
                                <div class="modal-body">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h4>Tên Sự Kiện:</h4>
                                                <p id="event-name">${event.activityName}</p>

                                                <h4>Mô Tả:</h4>
                                                <p id="event-description">${event.description}</p>

                                                <h4>Ngày Bắt Đầu:</h4>
                                                <p id="event-start-date">${event.startDate}</p>

                                                <h4>Ngày Kết Thúc:</h4>
                                                <p id="event-end-date">${event.endDate}</p>
                                            </div>
                                            <div class="col-md-6">
                                                <h4>Số Lượng:</h4>
                                                <p id="modal-memberLimit">${event.numberMember}</p>

                                                <h4>Địa Điểm:</h4>
                                                <p id="modal-location">${event.location}</p>
                                                <h4>Hình ảnh:</h4>

                                                <img style="max-width: 200px;
                                                     max-height: 100%;
                                                     width: auto;" src="${event.photo}" alt="images/uer.png">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </tbody>
            </table>

        </div>




        <script src="./js/location.js"></script> 
        <script src="./js/approveEvent.js"></script>    
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
