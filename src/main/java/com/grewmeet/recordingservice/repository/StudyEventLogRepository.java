package com.grewmeet.recordingservice.repository;

import com.grewmeet.recordingservice.domain.StudyEventLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyEventLogRepository extends JpaRepository<StudyEventLog, Long> {
    List<StudyEventLog> findByUserId(Long userId);
}
