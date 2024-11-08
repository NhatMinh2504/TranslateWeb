<%-- 
    Author     : 4USER-FPT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add Vocabulary</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="vocab?action=add" method="POST">
                    <div class="form-group">
                        <label for="englishWordInput">English Word:</label>
                        <input type="text" class="form-control" name="englishWord" required>
                    </div>
                    <div class="form-group">
                        <label for="vietnameseWordInput">Vietnamese Word:</label>
                        <input type="text" class="form-control" name="vietnameseWord" required>
                    </div>
                    <div class="form-group">
                        <label for="typeInput">Type:</label>
                        <select class="form-control" id="typeInput" name="type" required>
                            <option value="" disabled selected>Choose type</option>
                            <option value="n">Noun</option>
                            <option value="v">Verb</option>
                            <option value="a">Adjective</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <input type="submit" value="Add" class="btn btn-primary">
                    </div>
                </form>

                <!-- Hiển thị lỗi nếu có -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
            </div>
        </div>
    </div>
</div>


