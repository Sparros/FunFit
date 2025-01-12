<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Batch List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Batch List</h1>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Time Slot</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="batch" items="${batches}">
                <tr>
                    <td>${batch.id}</td>
                    <td>${batch.name}</td>
                    <td>${batch.timeSlot}</td>
                    <td>
                        <a href="batch?action=edit&id=${batch.id}" class="btn btn-info btn-sm">Edit</a>
                        <a href="batch?action=delete&id=${batch.id}" class="btn btn-danger btn-sm"
                           onclick="return confirm('Are you sure you want to delete this batch?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href="add-batch.html" class="btn btn-primary">Add New Batch</a>
    <a href="home.html" class="btn btn-secondary">Cancel</a>
</div>
</body>
</html>