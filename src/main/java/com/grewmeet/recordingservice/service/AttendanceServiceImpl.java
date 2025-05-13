package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.domain.Attendance;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.attendance.AttendanceResponseDto;
import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import com.grewmeet.recordingservice.exception.AlreadyCheckedInException;
import com.grewmeet.recordingservice.exception.AttendanceNotFoundException;
import com.grewmeet.recordingservice.exception.UserNotFoundException;
import com.grewmeet.recordingservice.repository.AttendanceRepository;
import com.grewmeet.recordingservice.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public CheckInResponseDto recordAttendance(Long userId) {
        User user = getUser(userId);

        if(isAlreadyAttending(user)) {
            throw new AlreadyCheckedInException();
        }

        Attendance checkInRecord = attendanceRepository.save(Attendance.of(user));
        return CheckInResponseDto.from(checkInRecord);
    }

    private boolean isAlreadyAttending(User user) {
        List<Attendance> attendances = attendanceRepository.findAllByUserId(user.getId());
        LocalDate today = LocalDate.now();
        return attendances.stream()
                .anyMatch(attendance -> attendance.getWhen().toLocalDate().isEqual(today));
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceLogs(Long userId) {
        User user = getUser(userId);
        List<Attendance> attendances = attendanceRepository.findAllByUserId(user.getId());

        return attendances.stream()
                .map(AttendanceResponseDto::from)
                .toList();
    }

    @Override
    public AttendanceResponseDto getLogDetail(Long userId, Long logId) {
        User user = getUser(userId);
        Attendance attendance = attendanceRepository.findById(logId)
                .orElseThrow(()-> new AttendanceNotFoundException("user id " + userId + "'s " + "attendance Log " + logId + "is not found"));

        return AttendanceResponseDto.from(attendance);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user id " + userId + " is not found"));
    }
}
