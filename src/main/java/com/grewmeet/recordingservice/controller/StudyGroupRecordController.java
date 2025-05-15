package com.grewmeet.recordingservice.controller;

import com.grewmeet.recordingservice.dto.studygroup.StudyEventLogResponseDto;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLoggingRequestDto;
import com.grewmeet.recordingservice.repository.UserRepository;
import com.grewmeet.recordingservice.service.StudyGroupRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study-record")
public class StudyGroupRecordController {

    private final UserRepository userRepository;
    private final StudyGroupRecordServiceImpl studyGroupRecordService;

    // 스터디 그룹 세션 참여 내역 기록
    @PostMapping
    public ResponseEntity<?> recordStudyGroupEventParticipation(
            @RequestBody StudyEventLoggingRequestDto request) {
        userRepository.findById(request.userId())
                .orElseThrow(()-> new RuntimeException("User with id " + request.userId() + " not found"));

        StudyEventLogResponseDto studyEventLog =  studyGroupRecordService.recordAttendance(request);
        return ResponseEntity.ok(studyEventLog);
    }

    // 스터디 그룹 세션 참여 내역 조회

    // 스터디 그룹 세션 참여 내역 상세 조회
}
