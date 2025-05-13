package com.grewmeet.recordingservice.repository;

import com.grewmeet.recordingservice.domain.DatingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatingLogRepository extends JpaRepository<DatingLog, Long> {
}
