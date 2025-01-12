package com.funfit.dao;

import com.funfit.model.Batch;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BatchDAOImpl implements BatchDAO {

    @Override
    public boolean addBatch(Batch batch) {
        Connection con = DBConnection.getConnection();
        System.out.println("add");
        System.out.println(batch);
        try {
            String sql = "INSERT INTO batches (name, time_slot) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, batch.getName());
            statement.setString(2, batch.getTimeSlot());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateBatch(Batch batch) {
        Connection con = DBConnection.getConnection();
        try {
        	String sql = "UPDATE batches SET name=?, time_slot=? WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, batch.getName());
            statement.setString(2, batch.getTimeSlot());
            statement.setInt(3, batch.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBatch(int id) {
        Connection con = DBConnection.getConnection();
        System.out.println("delete batch with id: " + id);
        try {
            String sql = "DELETE FROM batches WHERE id=?";
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
    public Batch getBatch(int id) {
        Connection con = DBConnection.getConnection();
        System.out.println("get batch with id: " + id);
        Batch batch = null;
        try {
            String sql = "SELECT * FROM batches WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                batch = new Batch();
                batch.setId(resultSet.getInt("id"));
                batch.setName(resultSet.getString("name"));
                batch.setTimeSlot(resultSet.getString("time_slot"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    @Override
    public List<Batch> getAllBatches() {
        Connection con = DBConnection.getConnection();
        System.out.println("get all batches");
        List<Batch> batches = new ArrayList<>();
        try {
            String sql = "SELECT * FROM batches";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Batch batch = new Batch();
                batch.setId(resultSet.getInt("id"));
                batch.setName(resultSet.getString("name"));
                batch.setTimeSlot(resultSet.getString("time_slot"));
                batches.add(batch);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }
}
