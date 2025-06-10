package com.grewmeet.recordingservice.controller;

import com.grewmeet.recordingservice.dto.dating.DatingEventLogResponseDto;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLogResponseDto;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLoggingRequestDto;
import com.grewmeet.recordingservice.repository.UserRepository;
import com.grewmeet.recordingservice.service.StudyGroupRecordServiceImpl;
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
@RequestMapping("/study-record")
public class StudyGroupRecordController {


    private final StudyGroupRecordServiceImpl studyGroupRecordService;

    // 스터디 그룹 세션 참여 내역 기록하기
    @PostMapping("/{userId}/logs")
    public ResponseEntity<?> recordStudyGroupEventParticipation(
            @RequestBody StudyEventLoggingRequestDto request) {
        StudyEventLogResponseDto studyEventLog =  studyGroupRecordService.recordAttendance(request);
        return ResponseEntity.ok(studyEventLog);
    }

    // 스터디 그룹 세션 참여 내역 조회
    @GetMapping("/{userId}/logs")
    public ResponseEntity<List<StudyEventLogResponseDto>> getStudyGroupEventRecords(
            @PathVariable Long userId){
        return ResponseEntity.ok(studyGroupRecordService.getStudyEventLogs(userId));
    }

    // 스터디 그룹 세션 참여 내역 상세 조회
    @GetMapping("/{userId}/logs/{logId}")
    public ResponseEntity<StudyEventLogResponseDto> getStudyGroupEventRecord(
            @PathVariable Long userId,
            @PathVariable Long logId) {
        return ResponseEntity.ok(studyGroupRecordService.getLogDetail(logId));
    }
}
