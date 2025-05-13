package com.grewmeet.recordingservice.dto.attendance;

import com.grewmeet.recordingservice.domain.Attendance;
import java.time.LocalDateTime;

public record AttendanceResponseDto(String username,
                                    LocalDateTime when
) {
    public static AttendanceResponseDto from(Attendance attendance) {
        return new AttendanceResponseDto(
                attendance.getUserName(),
                attendance.getWhen()
        );
    }
}
