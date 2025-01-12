<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Batch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Edit Batch</h1>
        
        <form action="batch" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${batch.id}">
            
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${batch.name}" required>
            </div>
            
            <div class="mb-3">
                <label for="timeSlot" class="form-label">Time Slot:</label>
                <input type="text" class="form-control" id="timeSlot" name="timeSlot" value="${batch.timeSlot}" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Update Batch</button>
            <a href="batch?action=list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>