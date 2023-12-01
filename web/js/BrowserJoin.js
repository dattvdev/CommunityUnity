/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// JavaScript code to handle Approve button
function approveMember(userId, eventID, listItem, approveButton) {
    // Thay đổi giao diện ngay lập tức
    listItem.innerHTML = 'Đã xét duyệt'; // Thay đổi nội dung của list item
    approveButton.style.display = 'none';
    // Gửi yêu cầu xét duyệt thành viên bằng AJAX (nếu cần)
    $.ajax({
        type: "POST",
        url: "ApproveMemberServlet", // Điều hướng đến servlet xử lý xét duyệt
        data: {userId: userId, eventID: eventID},
        // Truyền userId, eventID cho servlet
        success: function (data) {
            // Xử lý thành công (nếu cần)
        // Hiển thị thông báo từ servlet (nếu có)
        },
        error: function (error) {
            // Xử lý lỗi (nếu cần)
            alert("Đã xảy ra lỗi khi xét duyệt thành viên.");
        },
    });
}

// JavaScript code to handle Reject button
function rejectMember(userId, eventID, listItem, approveButton) {
    // Thay đổi giao diện ngay lập tức
    listItem.innerHTML = 'Đã từ chối'; // Thay đổi nội dung của list item
    approveButton.style.display = 'none';
    // Gửi yêu cầu từ chối thành viên bằng AJAX (nếu cần)
    $.ajax({
        type: "POST",
        url: "RejectMemberServlet", // Điều hướng đến servlet xử lý từ chối
        data: {userId: userId, eventID: eventID}, // Truyền userId cho servlet
        success: function (data) {
            // Xử lý thành công (nếu cần)
       // Hiển thị thông báo từ servlet (nếu có)
        },
        error: function (error) {
            // Xử lý lỗi (nếu cần)
            alert("Đã xảy ra lỗi khi từ chối thành viên.");
        },
    });
}

$(document).ready(function () {
    $("#myModal").on("click", "button[data-action='reject']", function () {
        // Xử lý từ chối thành viên
        // Đóng modal sau khi xử lý
        $("#myModal").modal("hide");
    });

    $("#myModal").on("click", "button[data-action='approve']", function () {
        // Xử lý xét duyệt thành viên
        // Đóng modal sau khi xử lý
        $("#myModal").modal("hide");
    });
});

//---------------------------------------------------------------------------


function updateActivity()    {
    console.log("he");
    if (validateForm()) {
        console.log("hi");
        var activityId = $("#activityId").val();
        console.log("hi");
        var activityName = $("#activityName").val();
        var description = $("#description").val();
        var startDate = $("#startDateStr").val();
        var endDate = $("#endDateStr").val();
        var province = document.getElementById("province").value;
        var district = document.getElementById("district").value;
        var ward = document.getElementById("ward").value;
        var memberLimit = $("#memberLimit").val();

        var data = {
            activityId: activityId,
            activityName: activityName,
            description: description,
            startDate: startDate,
            endDate: endDate,
            province: province, 
            district: district, 
            ward: ward,
            memberLimit: memberLimit
                    // Thêm dữ liệu từ các trường khác
        };

        $.ajax({
            type: "POST",
            url: "UpdateActivityControl", //URL của servlet 
            data: data,
            success: function (response) {
                console.log(data);
                alert("Cập nhật thành công.");
            },
            error: function () {
                data: data,
                alert("Đã xảy ra lỗi khi gửi yêu cầu.");
            }
        });
    }
}
document.getElementById("editButton").addEventListener("click", function () {
    // Hiển thị form chỉnh sửa bằng cách thay đổi thuộc tính style.display
    document.getElementById("editForm").style.display = "block";
});
function cancelEdit() {
    // Ẩn form chỉnh sửa bằng cách thay đổi thuộc tính style.display
    document.getElementById("editForm").style.display = "none";
}
//----------------------------------------------------------------
function validateForm() {
    var activityName = document.getElementById("activityName").value;
    var startDate = new Date(document.getElementById("startDateStr").value);
    var endDate = new Date(document.getElementById("endDateStr").value);
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


    if (endDate <= startDate && startDate != null) {
        alert("Ngày kết thúc phải lớn hơn ngày bắt đầu.");
        return false;
    }

    return true;
}

//------------------------------
function addChi() {
    console.log("1");
    var hoatdongnhan = $("#hoatdongnhan").val();
    var NDCK = $("#NDCK").val();
    var money = $("#money").val();

    // Tạo một đối tượng chứa dữ liệu để gửi đi
    var data = {
        hoatdongnhan: hoatdongnhan,
        NDCK: NDCK,
        money: money
    };

    // Sử dụng AJAX để gửi dữ liệu đến servlet
    $.ajax({
        type: "POST", // Hoặc "GET" tùy vào phương thức của servlet
        url: "AddUsePaymentControl", // Thay "ten-servlet" bằng URL của servlet
        data: data,
        success: function (response) {
            console.log(data);
            alert("Thêm dữ liệu thành công!");
        },
        error: function (xhr, status, error) {
            // Xử lý lỗi nếu có
            // Ví dụ: Hiển thị thông báo lỗi
            alert("Lỗi: " + error);
        }
    });

    // Đóng modal sau khi gửi dữ liệu
    $('#chiModal').modal('hide');
}

