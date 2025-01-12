<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Participants in Batch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Participants</h1>

    <!-- Dropdown to select batch -->
    <form action="participant" method="get" class="mb-4">
        <input type="hidden" name="action" value="list">
        <div class="form-group">
            <label for="batchId">Select Batch:</label>
            <select id="batchId" name="batchId" class="form-control" onchange="this.form.submit()">
                <option value="all" ${batchId eq 'all' ? 'selected' : ''}>All Batches</option>
                <c:forEach var="batch" items="${batches}">
                    <option value="${batch.id}" ${batch.id eq batchId ? 'selected' : ''}>${batch.name}</option>
                </c:forEach>
            </select>
        </div>
    </form>

    <!-- Show participants for the selected batch -->
    <c:if test="${not empty participants}">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Batch</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="participant" items="${participants}">
                    <tr>
                        <td>${participant.id}</td>
                        <td>${participant.name}</td>
                        <td>${participant.age}</td>
                        <td>${participant.email}</td>
                        <td>${participant.batchName}</td>
                        <td>
                            <a href="participant?action=edit&id=${participant.id}" class="btn btn-info btn-sm">Edit</a>
                            <a href="participant?action=delete&id=${participant.id}" class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete this participant?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty participants}">
        <div class="alert alert-warning">No participants found for the selected batch.</div>
    </c:if>

    <a href="batch?action=add-participant" class="btn btn-primary">Add New Participant</a>
    <a href="home.html" class="btn btn-secondary">Cancel</a>
</div>
</body>
</html>