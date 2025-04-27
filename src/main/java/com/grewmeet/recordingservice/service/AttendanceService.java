package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import java.util.List;

public interface AttendanceService {
    CheckInResponseDto recordAttendance(Long userId);

    List<CheckInResponseDto> getAttendances(Long userId);
}
