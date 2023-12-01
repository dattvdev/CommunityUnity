<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>View History</title>

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


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            // Hàm để hiển thị nội dung cho danh mục cụ thể
            function showContent(category) {
                // Ẩn tất cả nội dung của các danh mục
                document.getElementById("activitiesJoined").style.display = "none";
                document.getElementById("yourActivities").style.display = "none";
                document.getElementById("donationHistory").style.display = "none";

                // Hiển thị nội dung cho danh mục được chọn
                document.getElementById(category).style.display = "block";
            }
            function deleteActivity(actID,deleteButton,next) {
                next.innerHTML = 'Đã xoá';
                deleteButton.style.display = 'none';
                $.ajax({
                    type: "POST",
                    url: "DeleteActivityControl", // Điều hướng đến servlet xử lý từ chối
                    data: {id: actID}, // Truyền userId cho servlet
                    success: function (data) {
                    },
                    error: function (error) {
                        // Xử lý lỗi (nếu cần)
                        alert("Đã xảy ra lỗi khi từ chối thành viên.");
                    },
                });
            }

        </script>
    </head>
    <body>
        <div class="container-fluid position-relative p-0">
            <%@include file="./components/header.jsp" %>
        </div>
        <div class="container-fluid py-4">

            <div class="row">
                <!-- Cột bên trái chứa các danh mục -->
                <div class="col-md-3">
                    <div class="list-group">
                        <!-- Sử dụng Bootstrap để tạo danh sách danh mục -->
                        <a href="#" class="list-group-item list-group-item-action" onclick="showContent('activitiesJoined')">Các hoạt động đã tham gia</a>
                        <hr/>
                        <a href="#" class="list-group-item list-group-item-action" onclick="showContent('yourActivities')">Các hoạt động của bạn</a>
                        <hr/>
                        <a href="#" class="list-group-item list-group-item-action" onclick="showContent('donationHistory')">Lịch sử donate</a>
                    </div>
                </div>
                <!-- Cột bên phải chứa nội dung lịch sử hoạt động -->
                <div class="col-md-9">
                    <div id="activitiesJoined" style="display: none">
                        <h5>Hoạt động đã tham gia: ${fn:length(listAct)}</h5>
                        <c:if test="${not empty listAct}">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên hoạt động</th>
                                        <th>Địa điểm</th>
                                        <th>Người tạo</th>
                                        <th>Ngày tham gia</th>
                                        <th>Tình trạng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listAct}" var="act" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>                                
                                            <td><a href="./EventDetailControl?id=${act.activity.activityId}">${act.activity.activityName}</a></td>
                                            <td>${act.activity.location}</td>
                                            <td>${act.oName}</td>
                                            <td><fmt:formatDate value="${act.participationDate}" pattern="yyyy-MM-dd" /></td>
                                            <td>${act.status}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

                    </div>

                    <div id="yourActivities" style="display: none">
                        <c:choose>
                            <c:when test="${sessionScope.LOGIN_USER.role == 2 || sessionScope.LOGIN_USER.role == 0}">
                                <h5>Hoạt động đã tạo: ${fn:length(listOAct)}</h5>
                                <c:if test="${not empty listOAct}">            
                                    <table class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Tên hoạt động</th>
                                                <th>Ngày Tạo</th>
                                                <th>Ngày Cập Nhật</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listOAct}" var="act" varStatus="loop">
                                                <tr>
                                                    <td>${loop.index + 1}</td>
                                                    <td><a href="./EventDetailControl?id=${act.activityId}">${act.activityName}</a></td>
                                                    <td><fmt:formatDate value="${act.createdDate}" pattern="yyyy-MM-dd" /></td>
                                                    <td><fmt:formatDate value="${act.updatedDate}" pattern="yyyy-MM-dd" /></td>
                                                    <td>
                                                        <i class="fa-solid fa-trash-can" onclick="deleteActivity(${act.activityId},this,this.nextElementSibling)"></i>
                                                        <p value=""></p>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>    
                                    </table>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <h5>Bạn không phải Ognaizer.</h5>
                            </c:otherwise>
                        </c:choose>

                    </div>

                    <div id="donationHistory" style="display: none">
                        <h5>Số lần ủng hộ: ${fn:length(listThu)}</h5>
                        <h5>Tổng tiền ủng hộ: ${tongDonate}VNĐ</h5>
                        <c:if test="${not empty listThu}">
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên hoạt động</th>
                                        <th>Ngày gửi</th>
                                        <th>Số tiền</th>
                                        <th>Nội dung</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listThu}" var="act" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>                                
                                            <td><a href="./EventDetailControl?id=${act.hoatdong.activityId}">${act.hoatdong.activityName}</a></td>
                                            <td><fmt:formatDate value="${act.ngayGui}" pattern="yyyy-MM-dd" /></td>
                                            <td>${act.soTien}VNĐ</td>
                                            <td>${act.noiDung}</td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
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
