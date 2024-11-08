<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor-admin/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="${pageContext.request.contextPath}/vendor-admin/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin.css" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/colReorder-bootstrap4.css">

        <style>
            .error {
                color: red;
            }
        </style>
    </head>

    <body id="page-top">
        <!--Navbar-->
        <jsp:include page="../common/admin/navbar.jsp"></jsp:include>

            <div id="wrapper">
                <!-- Sidebar -->
            <jsp:include page="../common/admin/sideBar.jsp"></jsp:include>

                <div id="content-wrapper">
                    <div class="container-fluid">
                        <!-- Breadcrumbs-->
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="#">Dashboard</a>
                            </li>
                            <li class="breadcrumb-item active">Overview</li>
                            <li class="breadcrumb-item ml-auto">
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addModal">
                                    Add Vocabulary
                                </button>
                            </li>
                        </ol>

                        <!-- DataTables Example -->
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fas fa-table"></i>
                                Vocabulary
                            <c:if test="${not empty message}">
                                <div>${message}</div>
                            </c:if>
                            <c:if test="${not empty error}">
                                <div style="color:red;">${error}</div>
                            </c:if>
                        </div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>English Word</th>
                                            <th>Vietnamese Word</th>
                                            <th>Type</th>
                                                
                                                <th>Action</th>
                                               
                                            <th>Status</th>
                                            <th>Date</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listV}" var="p">
                                            <tr>
                                                <td>${p.id}</td>
                                                <td>${p.englishWord}</td>
                                                <td>${p.vietnameseWord}</td>
                                                <td>${p.type}</td>
                                                <td>
                                                    <c:if test="${sessionScope.roleId == 1}">
                                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                                data-target="#editVocabModal"
                                                                data-id="${p.id}"
                                                                data-englishword="${p.englishWord}"
                                                                data-vietnameseword="${p.vietnameseWord}"
                                                                data-type="${p.type}">
                                                            Edit
                                                        </button>
                                                        <form action="vocab?action=delete" method="POST" style="display:inline;">
                                                            <input type="hidden" name="id" value="${p.id}"/>
                                                            <button type="submit" class="btn btn-danger">Delete</button>
                                                        </form>
                                                        <c:if test="${p.status == 'pending'}">
                                                            <form action="vocab?action=accept" method="POST" style="display:inline;">
                                                                <input type="hidden" name="id" value="${p.id}"/>
                                                                <button type="submit" class="btn btn-success">Accept</button>
                                                            </form>
                                                        </c:if>
                                                    </c:if>

                                                    <c:if test="${sessionScope.roleId == 2}">
                                                        <button type="button" class="btn btn-primary" disabled
                                                                data-toggle="modal"
                                                                data-target="#editVocabModal"
                                                                data-id="${p.id}"
                                                                data-englishword="${p.englishWord}"
                                                                data-vietnameseword="${p.vietnameseWord}"
                                                                data-type="${p.type}">
                                                            Edit
                                                        </button>
                                                        <form action="vocab?action=delete" method="POST" style="display:inline;">
                                                            <input type="hidden" name="id" value="${p.id}"/>
                                                            <button type="submit" class="btn btn-danger" disabled>Delete</button>
                                                        </form>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                   
                                                        <c:when test="${p.status == 'accepted'}">
                                                            Accepted
                                                        </c:when>
                                                                 <c:when test="${p.status == 'pending'}">
                                                            Pending
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>${p.date}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
                    </div>
                </div>
                <!-- /.container-fluid -->

                <!-- Sticky Footer -->
                <jsp:include page="../common/admin/footer.jsp"></jsp:include>
                </div>
                <!-- /.content-wrapper -->
            </div>
            <!-- /#wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

        <jsp:include page="addVocabModal.jsp"></jsp:include>
        <jsp:include page="editVocabModal.jsp"></jsp:include>

            <!-- Bootstrap core JavaScript-->
            <script src="${pageContext.request.contextPath}/vendor-admin/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor-admin/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor-admin/jquery-easing/jquery.easing.min.js"></script>

        <!-- Page level plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor-admin/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/vendor-admin/datatables/dataTables.bootstrap4.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/admin/sb-admin.min.js"></script>

        <script>
            $(document).ready(function () {
                // Khởi tạo DataTables
                $('#dataTable').DataTable({
                    "pageLength": 12, // Số lượng bản ghi trên mỗi trang
                    "lengthChange": false // Tắt tùy chọn thay đổi số lượng bản ghi
                });

                // Xác thực form khi nhấn nút "Add"
                $('#addModal form').on('submit', function (event) {
                    let englishWord = $('input[name="englishWord"]').val();
                    let vietnameseWord = $('input[name="vietnameseWord"]').val();
                    let type = $('select[name="type"]').val();

                    // Kiểm tra dữ liệu
                    if (!englishWord || !vietnameseWord || !type) {
                        alert("Please fill in all fields.");
                        event.preventDefault(); // Ngăn không cho form được gửi
                    }
                });
            });

            // Nạp dữ liệu vào trang modal
            function loadVocabData(id, englishWord, vietnameseWord, type) {
                document.getElementById("vocabId").value = id;
                document.getElementById("englishWordEditInput").value = englishWord;
                document.getElementById("vietnameseWordEditInput").value = vietnameseWord;
                document.getElementById("typeEditInput").value = type;
            }

            $('#editVocabModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget); // Button mà người dùng đã nhấn

                // Lấy dữ liệu từ thuộc tính data-* của button
                var id = button.data('id');
                var englishWord = button.data('englishword');
                var vietnameseWord = button.data('vietnameseword');
                var type = button.data('type');

                // Tìm các trường trong modal và điền dữ liệu vào
                var modal = $(this);
                modal.find('#vocabId').val(id);
                modal.find('#englishWordEditInput').val(englishWord);
                modal.find('#vietnameseWordEditInput').val(vietnameseWord);
                modal.find('#typeEditInput').val(type);
            });
        </script>
    </body>

</html>
