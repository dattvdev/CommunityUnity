<%@page import="entity.VolunteerActivity" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <link
            href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap"
            rel="stylesheet">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
              rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
              rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <style>
            .navbar-light {
                background-color: black;
            }

            .py-5 {
                padding-top: 5rem !important;
            }

            .donation-box .title {
                background-color: #86B817;
            }

            .donation-box .donate-button {
                background-color: #86B817;
            }

            .donation-box .amount .button {
                background-color: #86B817;
                text-transform: lowercase;

                position: unset;
            }

            .icon-hover:hover {
                border-color: #3b71ca !important;
                background-color: white !important;
                color: #3b71ca !important;
            }

            .icon-hover:hover i {
                color: #3b71ca !important;
            }

            .options-menu {
                position: absolute;
                top: 0px;
                right: 20px;
                /* Điều chỉnh khoảng cách từ phía phải */
            }

            .ellipsis-icon {
                cursor: pointer;
                font-size: 24px;
                /* Tùy chỉnh kích thước biểu tượng */
                color: #333;
            }

            .options {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                /* Bo tròn góc */
                padding: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                /* Đổ bóng */
                position: absolute;
                top: 5px;
                right: 10px;
                z-index: 1;
                width: 250px;
                display: none;
            }

            .options a {
                display: block;
                padding: 5px;
                text-decoration: none;
                color: #333;
                /* Màu sắc văn bản */
                transition: background-color 0.3s;
                /* Hiệu ứng khi di chuột qua */
            }

            .options a:hover {
                background-color: #f0f0f0;
                /* Màu nền khi di chuột qua */
            }
        </style>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
    </head>

    <body>


        <%@include file="./components/header.jsp" %>

        <!-- content -->
        <div class="container mt-5">

            <h1 class="display-4" style="
                padding-top: 5%;
                ">Chi Tiết Hoạt Động</h1>

            <c:if test="${not empty detail}">
                <section class="py-5">
                    <div class="container" style="position: relative;">

                        <div class="row gx-5">
                            <aside class="col-lg-6">
                                <div
                                    class="border rounded-4 mb-3 d-flex justify-content-center">
                                    <a data-fslightbox="mygalley" class="rounded-4"
                                       target="_blank" data-type="image">
                                        <img style="max-width: 100%; max-height: 100vh; margin: auto;"
                                             class="rounded-4 fit" src="${detail.photo}" />
                                    </a>
                                </div>
                            </aside>
                            <main class="col-lg-6">
                                <div class="ps-lg-3">
                                    <h4 class="title text-dark">
                                        ${detail.activityName}
                                    </h4>
                                    <h6 class="title text-dark">
                                        ${status}
                                    </h6>

                                    <div class="d-flex flex-row my-3">
                                        <span class="text-muted"></i>Người tổ chức:
                                            ${oname}</span>
                                    </div>
                                    <div class="row">
                                        <dt class="col-3">Ngày Bắt Đầu:</dt>
                                        <dd class="col-3">
                                            <fmt:formatDate value="${detail.startDate}"
                                                            pattern="yyyy-MM-dd" />
                                        </dd>
                                        <dt class="col-3">Ngày Kết Thúc:</dt>
                                        <dd class="col-3">
                                            <fmt:formatDate value="${detail.endDate}"
                                                            pattern="yyyy-MM-dd" />
                                        </dd>
                                        <dt class="col-3">Ngày Tạo:</dt>
                                        <dd class="col-3">
                                            <fmt:formatDate value="${detail.createdDate}"
                                                            pattern="yyyy-MM-dd" />
                                        </dd>
                                        <dt class="col-3">Ngày Cập Nhật:</dt>
                                        <dd class="col-3">
                                            <fmt:formatDate value="${detail.updatedDate}"
                                                            pattern="yyyy-MM-dd" />
                                        </dd>
                                    </div>
                                    <hr />

                                    <p style="word-wrap: break-word;">
                                        ${detail.description}
                                    </p>
                                    <hr />
                                    <div class="row">

                                        <dt class="col-1 fa-solid fa-location-dot text-primary"></dt>
                                        <dd class="col-11"> ${detail.location}</dd>
                                        <dt class="col-1 fa fa-user text-primary"></dt>   
                                        <dd class="col-11">${member}/${detail.numberMember}</dd


                                        <hr />
                                        <div class="text-center mt-4">
                                            <div class="grouptbt"
                                                 style="display: flex; justify-content: space-between; align-items: center;">
                                                <c:if
                                                    test="${detail.organizerId == userID}">
                                                    <button id="approveButton"
                                                            class="btn btn-primary btn-lg"
                                                            data-toggle="modal"
                                                            data-target="#myModal">Xét
                                                        duyệt</button>
                                                    </c:if>
                                                <form action="donationevent" method="post">
                                                    <input type="hidden"
                                                           value="${detail.organizerId}"
                                                           name="id">
                                                    <button id="donation"
                                                            class="btn btn-primary btn-lg">Donation</button>
                                                    <input type="hidden" name="activityId"
                                                           value="${detail.activityId}">
                                                    <input type="hidden" name="userID"
                                                           value="${userID}">
                                                </form>

                                                <div class="options-menu">
                                                    <div class="ellipsis-icon"
                                                         style="font-size: 30px;">&#8942;
                                                    </div>
                                                    <div class="options">
                                                        <a href="#" data-toggle="modal"
                                                           data-target="#donateModal">Xem
                                                            thu chi</a>
                                                        <a href="#" data-toggle="modal" data-target="#memberModal">Xem người tham gia</a>  
                                                        <c:if
                                                            test="${detail.organizerId == userID}">
                                                            <a href="#" data-toggle="modal"
                                                               data-target="#chiModal">Chi
                                                                tiền</a>
                                                            </c:if>
                                                            <c:if
                                                                test="${detail.organizerId == userID}">
                                                            <a href="#" data-toggle="modal"
                                                               data-target="#editModal">Edit</a>
                                                        </c:if>
                                                        <c:if
                                                            test="${detail.organizerId == userID || sessionScope.LOGIN_USER.role  == 0}">
                                                            <a
                                                                href="DeleteActivityControl?id=${detail.activityId}">Xoá</a>
                                                        </c:if>

                                                        <c:if
                                                            test="${detail.organizerId != userID}">

                                                            <a 
                                                                href="#"
                                                                data-toggle="modal"
                                                                data-target="#reportModal">Report</a>

                                                        </c:if>
                                                    </div>
                                                </div>


                                                <c:if test="${detail.organizerId != userID && check==0 && member<detail.numberMember}">
                                                    <form action="PendingUser" method="POST">
                                                        <input type="hidden" name="activityId" value="${detail.activityId}">
                                                        <input type="hidden" name="userID" value="${userID}">
                                                        <button class="btn btn-primary btn-lg">Tham gia</button>
                                                    </form>
                                                </c:if>
                                                <c:if test="${detail.organizerId != userID && check==1}">
                                                    <box class="btn btn-primary btn-lg">Đợi xét duyệt</box>
                                                    </c:if>
                                                    <c:if test="${detail.organizerId != userID && check==2}">
                                                    <box class="btn btn-primary btn-lg">Đã Tham gia</box>
                                                    </c:if> 
                                            </div>
                                        </div>

                                    </div>
                            </main>
                        </div>
                    </div>
                </section>
            </c:if>
        </div>


        <%@include file="./components/footer.jsp" %>
        <div class="modal fade" id="donateModal">
            <div class="modal-dialog" style="max-width: 60%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Thông tin Donate</h4>
                        <button type="button" class="close"
                                data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <h5>Tổng số tiền thu: ${tongThu}VNĐ</h5>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>người gửi</th>
                                    <th>Người nhận</th>
                                    <th>Ngày gửi</th>
                                    <th>Số tiền</th>
                                    <th>Nội dung</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${donateThu}" var="a"
                                           varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${a.tenNguoiGui}</td>
                                        <td>${a.nguoiNhan}</td>
                                        <td>
                                            <fmt:formatDate value="${a.ngayGui}"
                                                            pattern="yyyy-MM-dd" />
                                        </td>
                                        <td>${a.soTien}VNĐ</td>
                                        <td>${a.noiDung}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h5>Tổng số tiền chi: ${tongChi}VNĐ</h5>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Số tiền</th>
                                    <th>Nội dung</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${donateChi}" var="a"
                                           varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${a.money}vnđ</td>
                                        <td>${a.text}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger"
                                data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="memberModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Thành viên tham gia</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <!-- Hiển thị danh sách thành viên và các nút từ chối/xét duyệt tại đây -->
                        <ul class="list-group">
                            <c:forEach var="us" items="${memberlist}" varStatus="status">    
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <div>
                                        <img class="rounded-circle-perfect" src="${us.photo}" alt="">
                                        <label>${us.getUserName()}</label>
                                    </div>
                                </li>
                            </c:forEach>   
                        </ul>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Xét duyệt thành viên</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <!-- Hiển thị danh sách thành viên và các nút từ chối/xét duyệt tại đây -->
                        <ul class="list-group">
                            <c:forEach var="us" items="${pendinglist}" varStatus="status">    
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <div>
                                        <img class="rounded-circle-perfect" src="${us.photo}" alt="">
                                        <label>${us.getUserName()}</label>
                                    </div>
                                    <div class="btn-group" role="group">
                                        <button style="margin-right: 10px;" class="btn btn-danger rounded-pill" onclick="rejectMember(${us.getId()}, ${detail.activityId}, this, this.nextElementSibling)">Từ chối</button>
                                        <button class="btn btn-success rounded-pill" onclick="approveMember(${us.getId()}, ${detail.activityId}, this, this.previousElementSibling)">Đồng Ý</button>
                                    </div>
                                </li>
                            </c:forEach>   
                        </ul>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="chiModal">
            <div class="modal-dialog" style="max-width: 50%;">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Chi tiền</h4>
                        <button type="button" class="close"
                                data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <input type="hidden" id="hoatdongnhan" name="hoatdongnhan"
                                   class="form-control" value="${detail.activityId}">
                        </div>
                        <div class="mb-3">
                            <label for="NDCK">Nội dung:</label>
                            <input type="text" id="NDCK" name="NDCK"
                                   class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="money">Số tiền:</label>
                            <input type="number" id="money" name="money"
                                   class="form-control" min="1" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger"
                                data-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary"
                                data-dismiss="modal" onclick="addChi()">Thêm</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="max-width: 50%;" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Activity</h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- Nội dung của modal -->
                        <div class="mb-3">
                            <input type="hidden" id="activityId" name="activityId"
                                   class="form-control" value="${detail.activityId}">
                        </div>
                        <div class="mb-3">
                            <label for="activityName">Activity Name:</label>
                            <input type="text" id="activityName" name="activityName"
                                   class="form-control" required
                                   value="${detail.activityName}">
                        </div>
                        <div class="mb-3">
                            <label for="description">Description:</label>
                            <textarea id="description" name="description"
                                      class="form-control"
                                      value="${detail.description}">${detail.description}</textarea>
                        </div>

                        <c:choose>
                            <c:when test="${status == 'Đang diễn ra'}">
                                <div class="mb-3">
                                    <label for="endDateStr">End Date:</label>
                                    <input type="date" id="endDateStr" name="endDateStr"
                                           class="form-control" required>
                                    <input type="hidden" name="startDateStr"
                                           id="startDateStr" class="form-control"
                                           value="${detail.startDate}">
                                </div>
                            </c:when>
                            <c:when test="${status == 'Sắp diễn ra'}">
                                <div class="mb-3">
                                    <label for="startDateStr">Start Date:</label>
                                    <input type="date" id="startDateStr"
                                           name="startDateStr" class="form-control" value="${detail.startDate}"
                                           required >
                                </div>
                                <div class="mb-3">
                                    <label for="endDateStr">End Date:</label>
                                    <input type="date" id="endDateStr" name="endDateStr"
                                           class="form-control" required value="${detail.endDate}">
                                </div>
                            </c:when>
                            <c:otherwise>                                
                                <input type="hidden" id="startDateStr"
                                       name="startDateStr" class="form-control"
                                       value="${detail.startDate}" >

                                <input type="hidden" id="endDateStr" name="endDateStr"
                                       class="form-control" value="${detail.endDate}">

                            </c:otherwise>
                        </c:choose>


                        <div class="mb-3">
                            <label for="province" class="form-label">Tỉnh/Thành
                                Phố:</label>
                            <select id="province" class="form-select" name="province" >
                                <option value="">Chọn tỉnh/thành phố</option>
                            </select>
                        </div>
                        <!-- Dropdown quận/huyện -->
                        <div class="mb-3">
                            <label for="district" class="form-label">Quận/Huyện:
                            </label>
                            <select id="district" class="form-select" name="district">
                                <option value="">Chọn quận/huyện</option>
                            </select>
                        </div>
                        <!-- Dropdown xã/ward -->
                        <div class="mb-3">
                            <label for="ward" class="form-label">Xã/Phường:</label>
                            <select id="ward" class="form-select" name="ward">
                                <option value="">Chọn xã/phường</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="memberLimit">Member Limit:</label>
                            <input type="number" id="memberLimit" name="memberLimit"
                                   class="form-control" min="1" required
                                   value="${detail.numberMember}">
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary"
                                onclick="updateActivity()">Save</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            var optionsMenu = document.querySelector('.options-menu');
            var optionsList = document.querySelector('.options');

            optionsMenu.addEventListener('click', function (event) {
                if (optionsList.style.display === 'block') {
                    optionsList.style.display = 'none';
                    return;
                }
                optionsList.style.display = 'block';
            });

            document.addEventListener('click', function (event) {
                if (!optionsMenu.contains(event.target)) {
                    optionsList.style.display = 'none';
                }
            });
        </script>

    </div>
</div>

</div>
</main>
</div>
</div>
</section>
</div>







<div class="modal fade" id="reportModal" tabindex="-1" role="dialog"
     aria-labelledby="reportModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportModalLabel">Report This
                    Activity</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Bạn muốn tố cáo hoạt động trên vì lý do gì?</p>
                <form id="reportForm" action="ReportNotification" method="post">
                    <div class="form-group">
                        <label for="reason">Lý do tố cáo:</label>
                        <select class="form-control" id="reason"
                                name="reason">
                            <option value="Hoạt động này không đúng
                                    như những gì đã mô tả">Hoạt động này không đúng
                                như những gì đã mô tả</option>
                            <option value="Hoạt động này không phù
                                    hợp với tổ chức tình nguyện">Hoạt động này không phù
                                hợp với tổ chức tình nguyện</option>
                            <option value="Hoạt động này mang ý
                                    nghĩa xấu với cộng đồng">Hoạt động này mang ý
                                nghĩa xấu với cộng đồng</option>
                            <option value="Lý do khác">Lý do khác</option>

                            <!-- Thêm các lý do tố cáo khác -->
                        </select>
                    </div>

                    <input type="hidden" name="activityId"
                           value="${detail.activityId}">
                    <input type="hidden" name="organizerId"
                           value="${detail.organizerId}">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary"
                                data-dismiss="modal">No</button>


                        <button type="submit" class="btn btn-danger"
                                id="yes">Yes</button>
                    </div>
                </form>
            </div>


            <script>
                $(document).ready(function () {
                    $('#reportSubmittedModal').on('shown.bs.modal', function () {
                        setTimeout(function () {
                            $('#reportForm').submit(); // Tự động gửi form sau 3 giây
                        }, 3000);
                    });
                });
            </script>

        </div>

    </div>
</div>
</div>

<div class="modal fade" id="reportSubmittedModal" tabindex="-1"
     role="dialog" aria-labelledby="reportSubmittedModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportSubmittedModalLabel">Thông
                    báo</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Cảm ơn bạn đã tố cáo. Quản trị viên sẽ xem xét ý kiến của
                    bạn.</p>
            </div>
        </div>
    </div>
</div>
<script src="./js/location.js"></script>
<script src="./js/BrowserJoin.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/tempusdominus/js/moment.min.js"></script>
<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
<script
src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>

</html>