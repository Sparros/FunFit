package com.funfit.dao;

import com.funfit.model.Participant;
import java.util.List;

public interface ParticipantDAO {
    boolean addParticipant(Participant participant);
    boolean updateParticipant(Participant participant);
    boolean deleteParticipant(int id);
    Participant getParticipant(int id);
    List<Participant> getAllParticipants();
    List<Participant> getParticipantsByBatch(int batchId); 
}

