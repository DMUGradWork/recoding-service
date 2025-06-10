package com.grewmeet.recordingservice.controller;


import com.grewmeet.recordingservice.dto.attendance.AttendanceResponseDto;
import com.grewmeet.recordingservice.service.AttendanceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    // 출석 체크하기
    @PostMapping("/{userId}/check-in")
    public ResponseEntity<AttendanceResponseDto> checkIn(
            @PathVariable Long userId) {
        AttendanceResponseDto checkInLog = attendanceService.recordAttendance(userId);
        return ResponseEntity.ok(checkInLog);
    }

    // 출석 기록들 확인하기
    @GetMapping("/{userId}/logs")
    public ResponseEntity<List<AttendanceResponseDto>> getAttendanceLogs(
            @PathVariable Long userId) {
        return ResponseEntity.ok(attendanceService.getAttendanceLogs(userId));
    }

    // 출석 기록 상세 확인하기
    @GetMapping("/{userId}/logs/{logId}")
    public ResponseEntity<AttendanceResponseDto> getAttendanceLogDetail(
            @PathVariable Long userId,
            @PathVariable Long logId) {
        return ResponseEntity.ok(attendanceService.getLogDetail(logId));
    }

}
