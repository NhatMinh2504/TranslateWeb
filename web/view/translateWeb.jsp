<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Translate Page</title>
        <!-- Add Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background: linear-gradient(to right, #e0f7fa, #e0f2f1);
            }
            .translate-section, .Vietnamese {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .translate-section h1, .Vietnamese h1 {
                color: #5e35b1; /* Deep lavender for headings */
                font-weight: bold;
            }
            .form-group label {
                font-size: 1.1rem;
                color: #6a1b9a; /* Medium purple for labels */
                font-weight: 600;
            }
            .btn {
                font-size: 1rem;
                font-weight: bold;
            }
            .table {
                margin-top: 20px;
            }
            .table th {
                background-color: #7e57c2; /* Lavender background for header */
                color: white;
                text-align: center;
                font-weight: bold;
                font-size: 1.1rem;
            }
            .table td {
                background-color: #f3e5f5; /* Light lavender background for cells */
                text-align: center;
                border-top: 1px solid #d1c4e9; /* Light purple border between rows */
                color: #4a148c; /* Deep purple text */
                font-size: 1rem;
                padding: 10px;
            }
            .alert-success {
                font-size: 1rem;
                font-weight: 500;
                color: #2e7d32; /* Green for success messages */
            }
            /* Modal styling */
            .modal-title {
                color: #5e35b1;
                font-weight: bold;
            }
            .modal-body label {
                color: #6a1b9a;
                font-weight: 600;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <form action="${pageContext.request.contextPath}/authen?action=signout" method="POST" class="mb-3">
                <button type="submit" class="btn btn-outline-primary">Sign out</button>
            </form>

            <c:if test="${sessionScope.roleId == 1}">
                <form action="${pageContext.request.contextPath}/dashboard?action=myvocab" method="POST" class="mb-3">
                    <button type="submit" class="btn btn-outline-info">My Vocab</button>
                </form>
                <form action="${pageContext.request.contextPath}/dashboard?action=pendingVocab" method="POST" class="mb-3">
                    <button type="submit" class="btn btn-outline-info">Peding vocab</button>
                </form>
            </c:if>

            <c:if test="${sessionScope.roleId == 2}">
                <!-- Button mở modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addVocabModal">
                    Contribute Vocab
                </button>

                <!-- Modal cho form "Add Vocab" -->
                <div class="modal fade" id="addVocabModal" tabindex="-1" aria-labelledby="addVocabModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addVocabModalLabel">Contribute Vocabulary</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="${pageContext.request.contextPath}/vocab?action=add" method="POST">
                                <div class="modal-body">
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
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                    <input type="submit" value="Add" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty message}">
                <div class="alert alert-success mt-3">${message}</div>
            </c:if>

            <!-- Translate Section -->
            <div class="translate-section mt-4">
                <h1>Translate Page</h1>
                <form action="${pageContext.request.contextPath}/translate" method="POST" class="mt-3">
                    <div class="form-group">
                        <label for="englishWord">Enter an English word:</label>
                        <input type="text" class="form-control" id="englishWord" name="englishWord" required />
                    </div>
                    <button type="submit" class="btn btn-primary">Translate</button>
                </form>
            </div>

            <!-- Display Results Table -->
            <div class="Vietnamese mt-4">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Vietnamese Meaning</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty vietnameseMeaning}">
                                        ${vietnameseMeaning}
                                    </c:when>
                                    <c:otherwise>
                                        Please enter a word to see its meaning.
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${type}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

        </div>

        <!-- Add Bootstrap and jQuery JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!-- Validation for Translate Form -->
        <script>
            document.querySelector('form[action$="translate"]').addEventListener('submit', function (event) {
                const englishWord = document.getElementById('englishWord').value;
                if (!englishWord) {
                    alert('Please enter a word to translate.');
                    event.preventDefault();
                }
            });
        </script>
    </body>
</html>
