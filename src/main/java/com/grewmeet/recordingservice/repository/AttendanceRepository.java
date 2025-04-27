package com.grewmeet.recordingservice.repository;

import com.grewmeet.recordingservice.domain.Attendance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByUserId(Long userId);
}
