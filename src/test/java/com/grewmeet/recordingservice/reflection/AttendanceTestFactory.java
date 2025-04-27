package com.grewmeet.recordingservice.reflection;

import com.grewmeet.recordingservice.domain.Attendance;
import com.grewmeet.recordingservice.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.test.util.ReflectionTestUtils;

public class AttendanceTestFactory {
    public static Attendance createAttendance(User user, LocalDateTime dateTime) {
        Attendance attendance = Attendance.of(user);
        ReflectionTestUtils.setField(attendance, "when", dateTime);
        return attendance;
    }
}
