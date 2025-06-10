package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.domain.StudyEventLog;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLogResponseDto;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLoggingRequestDto;
import com.grewmeet.recordingservice.exception.StudyEventLogNotFoundException;
import com.grewmeet.recordingservice.exception.UserNotFoundException;
import com.grewmeet.recordingservice.repository.StudyEventLogRepository;
import com.grewmeet.recordingservice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyGroupRecordServiceImpl implements StudyGroupRecordService {

    private final UserRepository userRepository;
    private final StudyEventLogRepository studyEventLogRepository;

    @Override
    public StudyEventLogResponseDto recordAttendance(StudyEventLoggingRequestDto request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.userId() + " not found"));

        StudyEventLog studyEventLog = StudyEventLog.of(
                user.getUsername(),
                request.studyGroupName(),
                request.studyEventTitle(),
                request.studyParticipantAt()
        );

        return StudyEventLogResponseDto.from(studyEventLogRepository.save(studyEventLog));
    }

    @Override
    public List<StudyEventLogResponseDto> getStudyEventLogs(Long userId) {
        List<StudyEventLog> studyEventLogs = studyEventLogRepository.findByUserId(userId);
        return studyEventLogs.stream()
                .map(StudyEventLogResponseDto::from)
                .toList();
    }

    @Override
    public StudyEventLogResponseDto getLogDetail(Long logId) {
        StudyEventLog studyEventLog = studyEventLogRepository.findById(logId)
                .orElseThrow(() -> new StudyEventLogNotFoundException("StudyEventLog with id " + logId + " not found"));
        return StudyEventLogResponseDto.from(studyEventLog);
    }
}
