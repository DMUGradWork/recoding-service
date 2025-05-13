package com.grewmeet.recordingservice.dto.dating;

import com.grewmeet.recordingservice.domain.DatingLog;
import java.time.LocalDateTime;

public record DatingEventLogResponseDto(String userName, String title, String description, LocalDateTime dateTime) {

    public static DatingEventLogResponseDto from(DatingLog datingLog) {
        return new DatingEventLogResponseDto(
                datingLog.getUserName(),
                datingLog.getTitle(),
                datingLog.getDescription(),
                datingLog.getDateTime()
        );
    }
}
