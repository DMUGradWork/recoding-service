package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.dto.studygroup.StudyEventLogResponseDto;
import com.grewmeet.recordingservice.dto.studygroup.StudyEventLoggingRequestDto;
import java.util.List;

public interface StudyGroupRecordService {
    StudyEventLogResponseDto recordAttendance(StudyEventLoggingRequestDto request);
    List<StudyEventLogResponseDto> getStudyEventLogs(Long userId);
    StudyEventLogResponseDto getLogDetail(Long logId);
}
