<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Tạo Sự Kiện</title>
        <!-- Sử dụng Bootstrap CSS -->
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
        <style>
            /* Định dạng phần tử form */
            form {
                max-width: 60%;
                margin: 0 auto;
                padding: 20px;
                background-color: #f7f7f7;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            /* Định dạng phần tử input */
            input[type="text"],
            input[type="file"],
            input[type="date"],
            textarea {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            /* Định dạng nút submit */
            button[type="submit"] {
                background-color: #007bff;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            /* Định dạng nút submit khi rê chuột lên */
            button[type="submit"]:hover {
                background-color: #0056b3;
            }
            h1.display-4 {
                text-align: center; /* Căn giữa nội dung theo chiều ngang */
                margin-top: 20px; /* Tạo khoảng cách từ phía trên */
                margin-bottom: 20px; /* Tạo khoảng cách từ phía dưới */
            }
            select option {
                background-color: #f2f2f2; /* Màu nền */
                color: #333; /* Màu văn bản */
                padding: 5px; /* Khoảng cách bên trong option */
                border: 1px solid #ccc; /* Đường viền */
                border-radius: 4px; /* Góc bo tròn */
            }

            /* Tùy chỉnh màu nền và văn bản cho option khi hover */
            select option:hover {
                background-color: #e0e0e0;
                color: #000;
            }
        </style>

    </head>
    <body>
        <%@include file="./components/header.jsp" %>
        <div class="container mt-5 ">
            <h1 class="display-4">Tạo Sự Kiện Mới</h1>

            <form action="ActivityPendingControl" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                <div class="mb-3">
                    <label for="image" class="form-label">Hình ảnh:</label>
                    <input type="file" class="form-control" id="image" name="image">
                </div>
                <br>
                <div class="mb-3">
                    <label for="activityName" class="form-label">Tên Hoạt Động:</label>
                    <input type="text" class="form-control" id="activityName" name="activityName" required>
                </div>
                <br>
                <div class="mb-3">
                    <label for="startDate" class="form-label">Ngày Bắt Đầu:</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" min="2023-09-01" required>
                </div>
                <br>
                <div class="mb-3">
                    <label for="endDate" class="form-label">Ngày Kết Thúc:</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" min="2023-09-01" required>
                </div>
                <br>
                <div class="mb-3">
                    <label  class="form-label">Số Lượng Thành Viên:</label>
                    <input type="number" class="form-control" id="memberLimit" name="memberLimit" min="1"  required>
                </div>

                <br>
                <div class="mb-3">
                    <label for="province" class="form-label" >Tỉnh/Thành Phố:</label>
                    <select id="province" class="form-select" name="province">
                        <option value="">Chọn tỉnh/thành phố</option>
                    </select>
                </div>
                <br>
                <!-- Dropdown quận/huyện -->
                <div class="mb-3">
                    <label for="district" class="form-label" >Quận/Huyện:    </label>
                    <select id="district" class="form-select" name="district">
                        <option value="">Chọn quận/huyện</option>
                    </select>
                </div>
                <br>
                <!-- Dropdown xã/ward -->
                <div class="mb-3">
                    <label for="ward" class="form-label">Xã/Phường:</label>
                    <select id="ward" class="form-select" name="ward">
                        <option value="">Chọn xã/phường</option>
                    </select>
                </div>

                <br>
                <div class="mb-3">
                    <label for="description" class="form-label">Mô Tả:</label>
                    <textarea class="form-control" id="description" name="description"></textarea>
                </div>

                <!-- Các trường dữ liệu khác -->

                <button type="submit" class="btn btn-primary">Tạo Sự Kiện</button>
            </form>
        </div>

        <script src="./js/location.js"></script> 

        <script>
                function validateForm() {
                    var activityName = document.getElementById("activityName").value;
                    var startDate = new Date(document.getElementById("startDate").value);
                    var endDate = new Date(document.getElementById("endDate").value);
                    var currentDate = new Date();
                    currentDate.setHours(0, 0, 0, 0);
                    startDate.setHours(0, 0, 0, 0);
                    endDate.setHours(0, 0, 0, 0);
                    // Kiểm tra tên hoạt động
                    if (activityName.length > 70) {
                        alert("Tên hoạt động không được quá 70 ký tự.");
                        return false;
                    }

                    // Kiểm tra ngày bắt đầu và ngày kết thúc
                    if (startDate < currentDate) {
                        alert("Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.");
                        return false;
                    }

                    if (endDate <= startDate) {
                        alert("Ngày kết thúc phải lớn hơn ngày bắt đầu.");
                        return false;
                    }

                    return true;
                }

        </script>
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
