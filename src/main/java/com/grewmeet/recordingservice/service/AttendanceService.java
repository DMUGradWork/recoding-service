package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.dto.attendance.AttendanceResponseDto;
import java.util.List;

public interface AttendanceService {
    AttendanceResponseDto recordAttendance(Long userId);
    List<AttendanceResponseDto> getAttendanceLogs(Long userId);
    AttendanceResponseDto getLogDetail(Long logId);
}
