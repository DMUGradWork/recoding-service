package com.grewmeet.recordingservice.dto.studygroup;

import java.time.LocalDateTime;

public record StudyEventLoggingRequestDto(Long userId,
                                          String studyGroupName,
                                          String studyEventTitle,
                                          LocalDateTime studyParticipantAt) {
}
