<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Xét duyệt thành viên</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <!-- Hiển thị danh sách thành viên và các nút từ chối/xét duyệt tại đây -->
                        <ul>
                             
                            <!-- Thêm danh sách thành viên và nút tại đây -->
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <script>
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
        </script>