package com.funfit.servlets;

import com.funfit.dao.BatchDAO;
import com.funfit.dao.ParticipantDAO;
import com.funfit.dao.ParticipantDAOImpl;
import com.funfit.dao.BatchDAOImpl;
import com.funfit.model.Batch;
import com.funfit.model.Participant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ParticipantServlet extends HttpServlet {
    private ParticipantDAO participantDAO;
    private BatchDAO batchDAO; // Add BatchDAO to fetch batches

    @Override
    public void init() {
        participantDAO = new ParticipantDAOImpl();
        batchDAO = new BatchDAOImpl(); // Initialize BatchDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listParticipants(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteParticipant(request, response);
                    break;
                default:
                    listParticipants(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant participant = participantDAO.getParticipant(id);
        List<Batch> batches = batchDAO.getAllBatches();
        
        request.setAttribute("participant", participant);
        request.setAttribute("batches", batches);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-participant.jsp");
        dispatcher.forward(request, response);
    }

    private void listParticipants(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Batch> batches = batchDAO.getAllBatches();
        System.out.println("Number of batches: " + batches.size()); // Debug line
        
        String batchIdParam = request.getParameter("batchId");
        List<Participant> participants;
        
        if ("all".equals(batchIdParam) || batchIdParam == null) {
            participants = participantDAO.getAllParticipants();
            System.out.println("All participants: " + participants.size()); // Debug line
        } else {
            int batchId = Integer.parseInt(batchIdParam);
            participants = participantDAO.getParticipantsByBatch(batchId);
            System.out.println("Participants in batch " + batchId + ": " + participants.size()); // Debug line
        }

        request.setAttribute("batches", batches);
        request.setAttribute("participants", participants);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("view-participants.jsp");
        dispatcher.forward(request, response);
    }


    private void viewParticipant(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Participant participant = participantDAO.getParticipant(id);
        if (participant != null) {
            request.setAttribute("participant", participant);
            RequestDispatcher dispatcher = request.getRequestDispatcher("participant-details.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Participant not found");
        }
    }

    private void deleteParticipant(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (participantDAO.deleteParticipant(id)) {
            response.sendRedirect("participant?action=list");
        } else {
            request.setAttribute("error", "Failed to delete participant");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String email = request.getParameter("email");
            int batchId = Integer.parseInt(request.getParameter("batchId"));

            Participant participant = new Participant(name, age, email, batchId);
            
            if (participantDAO.addParticipant(participant)) {
                response.sendRedirect("participant?action=list");
            } else {
                request.setAttribute("error", "Failed to add participant");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format in form data");
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }
    }
}
