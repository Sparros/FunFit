<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Participant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Add Participant</h1>
        
        <form action="/Funfit/participant" method="post">
            <div class="mb-3">
                <label for="name" class="form-label">Participant Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>

            <div class="mb-3">
                <label for="age" class="form-label">Age:</label>
                <input type="number" class="form-control" id="age" name="age" required min="0">
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email Address:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="batchId" class="form-label">Batch:</label>
                <select class="form-control" id="batchId" name="batchId" required>
                    <option value="" disabled selected>Select a Batch</option>
                    <c:if test="${empty batches}">
                        <option disabled>No available batches</option>
                    </c:if>
                    <c:forEach var="batch" items="${batches}">
                        <option value="${batch.id}">${batch.name}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Add Participant</button>
            <a href="home.html" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>
