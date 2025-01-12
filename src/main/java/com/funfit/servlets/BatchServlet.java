package com.funfit.servlets;

import com.funfit.dao.BatchDAO;
import com.funfit.dao.BatchDAOImpl;
import com.funfit.model.Batch;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BatchServlet extends HttpServlet {
    private BatchDAO batchDAO;

    @Override
    public void init() {
        batchDAO = new BatchDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listBatches(request, response);
                break;
            case "edit":
                editBatch(request, response);
                break;
            case "delete":
                deleteBatch(request, response);
                break;
            case "add-participant":
                showAddParticipantForm(request, response);
                break;
            default:
                listBatches(request, response);
                break;
        }
    }

    private void updateBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String timeSlot = request.getParameter("timeSlot");
            
            Batch batch = new Batch();
            batch.setId(id);
            batch.setName(name);
            batch.setTimeSlot(timeSlot);
            
            boolean success = batchDAO.updateBatch(batch);
            
            response.setContentType("application/json");
            response.getWriter().write("{\"success\":" + success + "}");
        } catch (Exception e) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\":false,\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
    private void listBatches(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Batch> batches = batchDAO.getAllBatches();
            System.out.println("Number of batches retrieved: " + batches.size());
            for (Batch batch : batches) {
                System.out.println("Batch: " + batch.getId() + ", " + batch.getName() + ", " + batch.getTimeSlot());
            }
            request.setAttribute("batches", batches);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-batches.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving batches: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-batches.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void editBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Batch batch = batchDAO.getBatch(id);
        request.setAttribute("batch", batch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-batch.jsp");
        dispatcher.forward(request, response);
    }
    
    private void deleteBatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean success = batchDAO.deleteBatch(id);

            // Redirect to the batch list page
            if (success) {
                response.sendRedirect("batch?action=list");
            } else {
                request.setAttribute("error", "Error deleting the batch.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("view-batches.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid batch ID.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-batches.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String timeSlot = request.getParameter("timeSlot");
            
            Batch batch = new Batch();
            batch.setId(id);
            batch.setName(name);
            batch.setTimeSlot(timeSlot);
            
            batchDAO.updateBatch(batch);
            response.sendRedirect("batch?action=list");
        } else {
            // Existing add batch code
            String name = request.getParameter("name");
            String timeSlot = request.getParameter("timeSlot");
            Batch batch = new Batch(name, timeSlot);
            batchDAO.addBatch(batch);
            response.sendRedirect("batch?action=list");
        }
    }
    
    private void showAddParticipantForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<Batch> batches = batchDAO.getAllBatches();
            
            // Debugging: Check if batches are empty
            if (batches.isEmpty()) {
                System.out.println("No batches available.");
            }
            
            // Add batches to request attributes
            request.setAttribute("batches", batches); 
            
            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-participant.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving batches: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-participant.jsp");
            dispatcher.forward(request, response);
        }
    }

}
