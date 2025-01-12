package com.funfit.dao;

import com.funfit.model.Participant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAOImpl implements ParticipantDAO {

	@Override
    public boolean addParticipant(Participant participant) {
        Connection con = DBConnection.getConnection();
        System.out.println("add");
        System.out.println(participant);
        try {
            String sql = "INSERT INTO participants (name, age, email, batch_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, participant.getName());
            statement.setInt(2, participant.getAge());
            statement.setString(3, participant.getEmail());
            statement.setInt(4, participant.getBatchId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateParticipant(Participant participant) {
    		
    	Connection con = DBConnection.getConnection();
    	System.out.println("update");
    	System.out.println(participant);
        try {
        	PreparedStatement statement = con.prepareStatement("UPDATE participants SET name=?, age=?, email=?, batch_id=? WHERE id=?");
            statement.setString(1, participant.getName());
            statement.setInt(2, participant.getAge());
            statement.setString(3, participant.getEmail());
            statement.setInt(4, participant.getBatchId());
            statement.setInt(5, participant.getId());
            statement.executeUpdate();
            
            statement.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteParticipant(int id) {
        Connection con = DBConnection.getConnection();
        System.out.println("delete participant with id: " + id);
        try {
            String sql = "DELETE FROM participants WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        try {
            String sql = "SELECT p.*, b.name as batch_name FROM participants p " +
                        "LEFT JOIN batches b ON p.batch_id = b.id";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Participant participant = new Participant();
                participant.setId(resultSet.getInt("id"));
                participant.setName(resultSet.getString("name"));
                participant.setAge(resultSet.getInt("age"));
                participant.setEmail(resultSet.getString("email"));
                participant.setBatchId(resultSet.getInt("batch_id"));
                participant.setBatchName(resultSet.getString("batch_name"));
                participants.add(participant);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public List<Participant> getParticipantsByBatch(int batchId) {
        List<Participant> participants = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        try {
            String sql = "SELECT p.*, b.name as batch_name FROM participants p " +
                        "LEFT JOIN batches b ON p.batch_id = b.id " +
                        "WHERE p.batch_id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, batchId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Participant participant = new Participant();
                participant.setId(resultSet.getInt("id"));
                participant.setName(resultSet.getString("name"));
                participant.setAge(resultSet.getInt("age"));
                participant.setEmail(resultSet.getString("email"));
                participant.setBatchId(resultSet.getInt("batch_id"));
                participant.setBatchName(resultSet.getString("batch_name"));
                participants.add(participant);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public Participant getParticipant(int id) {
        Connection con = DBConnection.getConnection();
        Participant participant = null;
        try {
            String sql = "SELECT p.*, b.name as batch_name FROM participants p " +
                        "LEFT JOIN batches b ON p.batch_id = b.id " +
                        "WHERE p.id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                participant = new Participant();
                participant.setId(resultSet.getInt("id"));
                participant.setName(resultSet.getString("name"));
                participant.setAge(resultSet.getInt("age"));
                participant.setEmail(resultSet.getString("email"));
                participant.setBatchId(resultSet.getInt("batch_id"));
                participant.setBatchName(resultSet.getString("batch_name"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participant;
    }
}
