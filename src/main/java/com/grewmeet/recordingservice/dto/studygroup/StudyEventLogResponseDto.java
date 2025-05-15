package com.grewmeet.recordingservice.dto.studygroup;

import com.grewmeet.recordingservice.domain.StudyEventLog;
import java.time.LocalDateTime;

public record StudyEventLogResponseDto(String username,
                                       String studyGroupName,
                                       String studyEventTitle,
                                       LocalDateTime studyEventParticipation) {

    public static StudyEventLogResponseDto from(StudyEventLog studyEventLog) {
        return new StudyEventLogResponseDto(
                studyEventLog.getUsername(),
                studyEventLog.getStudyGroupName(),
                studyEventLog.getStudyEventTitle(),
                studyEventLog.getEventDateTime()
        );
    }
}
