package com.grewmeet.recordingservice.controller;

import com.grewmeet.recordingservice.dto.dating.DatingEventLogResponseDto;
import com.grewmeet.recordingservice.dto.dating.DatingEventLoggingRequestDto;
import com.grewmeet.recordingservice.service.DatingRecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dating-record")
public class DatingRecordController {

    private final DatingRecordService datingRecordService;

    // 데이팅 참여 내역 기록하기
    @PostMapping("/{userId}/logs")
    public ResponseEntity<DatingEventLogResponseDto> recordDatingEventParticipation(
            @RequestBody DatingEventLoggingRequestDto request) {
        DatingEventLogResponseDto datingLog = datingRecordService.recordAttendance(request);
        return ResponseEntity.ok(datingLog);
    }

    // 데이팅 참여 내역 조회
    @GetMapping("/{userId}/logs")
    public ResponseEntity<List<DatingEventLogResponseDto>> getAllDatingEventRecords(
            @PathVariable Long userId) {
        return ResponseEntity.ok(datingRecordService.getDatingLogs(userId));
    }

    // 데이팅 참여 내역 상세 조회
    @GetMapping("/{userId}/logs/{logId}")
    public ResponseEntity<DatingEventLogResponseDto> getDatingEventRecord(
            @PathVariable Long userId,
            @PathVariable Long logId) {
        return ResponseEntity.ok(datingRecordService.getLogDetail(logId));
    }
}
