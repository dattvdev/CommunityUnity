/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */




// Xử lý khi nút "Xét Duyệt" được nhấp
function approveEvent(eventID, thisItem, rejectButton) {
    thisItem.innerHTML = 'Đã xét duyệt';
    rejectButton.style.display = 'none';
    console.log("1");
    $.ajax({
        type: "POST",
        url: "ApproveControl", // Điều hướng đến servlet xử lý xét duyệt
        data: {eId: eventID, check: "1"},
        success: function (data) {
            // Xử lý thành công (nếu cần)
            console.log("data: "+data);
        },
        error: function (error) {
            // Xử lý lỗi (nếu cần)
            alert("Đã xảy ra lỗi khi xét duyệt hoạt động.");
        },
    });
}


// Xử lý khi nút "Từ Chối" được nhấp
function rejectEvent(eventID, thisItem, approveButton) {
    thisItem.innerHTML = 'Đã từ chối'; // Thay đổi nội dung của list item
    approveButton.style.display = 'none';
    console.log("2");
    $.ajax({
        type: "POST",
        url: "ApproveControl", // Điều hướng đến servlet xử lý từ chối
        data: {eId: eventID, check: "2"}, // 
        success: function (data) {
            // Xử lý thành công (nếu cần)
             console.log(data);
        },
        error: function (error) {
            // Xử lý lỗi (nếu cần)
            alert("Đã xảy ra lỗi khi từ chối hoạt động.");
        },
    });
}
