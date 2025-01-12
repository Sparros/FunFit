<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Participant</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Edit Participant</h1>

    <form action="participant" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${participant.id}">

        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" class="form-control" id="name" name="name" value="${participant.name}" required>
        </div>

        <div class="mb-3">
            <label for="age" class="form-label">Age:</label>
            <input type="number" class="form-control" id="age" name="age" value="${participant.age}" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${participant.email}" required>
        </div>

        <div class="mb-3">
            <label for="batchId" class="form-label">Batch:</label>
            <select class="form-control" id="batchId" name="batchId" required>
                <c:forEach var="batch" items="${batches}">
                    <option value="${batch.id}" ${batch.id eq participant.batchId ? 'selected' : ''}>${batch.name}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Update Participant</button>
        <a href="participant?action=list" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>