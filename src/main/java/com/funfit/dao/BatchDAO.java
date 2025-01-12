package com.funfit.dao;

import com.funfit.model.Batch;
import java.util.List;

public interface BatchDAO {
    boolean addBatch(Batch batch);
    boolean updateBatch(Batch batch);
    boolean deleteBatch(int id);
    Batch getBatch(int id);
    List<Batch> getAllBatches();
}
