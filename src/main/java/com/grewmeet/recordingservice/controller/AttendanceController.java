package com.grewmeet.recordingservice.controller;


import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import com.grewmeet.recordingservice.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<CheckInResponseDto> checkIn(
            @PathVariable Long userId) {
        CheckInResponseDto checkInLog = attendanceService .recordAttendance(userId);
        return ResponseEntity.ok().build();
    }

}
