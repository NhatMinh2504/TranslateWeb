<%-- 
    Author     : 4USER-FPT
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Modal -->
<div class="modal fade" id="editVocabModal" tabindex="-1" role="dialog" aria-labelledby="editVocabModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editVocabModalLabel">Edit Vocabulary</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editVocabForm" action="vocab?action=edit" method="POST">
                    <input type="hidden" id="vocabId" name="id">

                    <div class="form-group">
                        <label for="englishWordEditInput">English Word:</label>
                        <input type="text" class="form-control" id="englishWordEditInput" name="englishWord" required>
                    </div>

                    <div class="form-group">
                        <label for="vietnameseWordEditInput">Vietnamese Word:</label>
                        <input type="text" class="form-control" id="vietnameseWordEditInput" name="vietnameseWord" required>
                    </div>

                    <div class="form-group">
                        <label for="typeEditInput">Type:</label>
                        <input type="text" class="form-control" id="typeEditInput" name="type" required>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

