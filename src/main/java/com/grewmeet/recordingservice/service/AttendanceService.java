package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.dto.attendance.AttendanceResponseDto;
import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import java.util.List;

public interface AttendanceService {
    CheckInResponseDto recordAttendance(Long userId);
    AttendanceResponseDto getLogDetail(Long userId, Long logId);
    List<AttendanceResponseDto> getAttendanceLogs(Long userId);
}
