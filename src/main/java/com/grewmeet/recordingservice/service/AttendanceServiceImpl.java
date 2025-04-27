package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.domain.Attendance;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import com.grewmeet.recordingservice.exception.AlreadyCheckedInException;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user id " + userId + " is not found"));

        if(isAlreadyAttending(user)) {
            throw new AlreadyCheckedInException();
        }

        Attendance checkInRecord = attendanceRepository.save(Attendance.of(user));
        return CheckInResponseDto.from(checkInRecord);
    }

    @Override
    public List<CheckInResponseDto> getAttendances(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user id " + userId + " is not found"));

        List<Attendance> attendances = attendanceRepository.findAllByUserId(user.getId());

        return attendances.stream()
                .map(CheckInResponseDto::from)
                .toList();
    }

    private boolean isAlreadyAttending(User user) {
        List<Attendance> attendances = attendanceRepository.findAllByUserId(user.getId());
        LocalDate today = LocalDate.now();
        return attendances.stream()
                .anyMatch(attendance -> attendance.getWhen().toLocalDate().isEqual(today));
    }
}
