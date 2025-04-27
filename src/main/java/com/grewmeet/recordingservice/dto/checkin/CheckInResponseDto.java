package com.grewmeet.recordingservice.dto.checkin;

import com.grewmeet.recordingservice.domain.Attendance;
import java.time.LocalDateTime;

public record CheckInResponseDto(String username,
                                 LocalDateTime when
) {
    public static CheckInResponseDto from(Attendance attendance) {
        return new CheckInResponseDto(
                attendance.getUserName(),
                attendance.getWhen()
        );
    }
}
