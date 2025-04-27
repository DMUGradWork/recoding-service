package com.grewmeet.recordingservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.grewmeet.recordingservice.domain.Attendance;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import com.grewmeet.recordingservice.exception.AlreadyCheckedInException;
import com.grewmeet.recordingservice.reflection.AttendanceTestFactory;
import com.grewmeet.recordingservice.repository.AttendanceRepository;
import com.grewmeet.recordingservice.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Test
    @DisplayName("출석 체크 동작 테스트")
    void attendanceServiceBasicWorkTest() {
        Long userId = 1L;
        User user = User.of("TestUser");
        Attendance attendance = Attendance.of(user);

        // Mockito 문법:  When(호출된 메서드).then(그때 반환할 것)
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(attendanceRepository.save(any(Attendance.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CheckInResponseDto result = attendanceService.recordAttendance(userId);

        assertThat(result).isNotNull();
        assertThat(result.username()).isEqualTo("TestUser");
        assertThat(result.when()).isNotNull();
    }

    @Test
    @DisplayName("출석 내역 호출 테스트")
    void attendancesLoadTest() {
        User user = User.of("TestUser");

        Attendance attendance1 = AttendanceTestFactory.createAttendance(user, LocalDateTime.now());
        Attendance attendance2 = AttendanceTestFactory.createAttendance(user, LocalDateTime.now().plusDays(1));
        Attendance attendance3 = AttendanceTestFactory.createAttendance(user, LocalDateTime.now().minusDays(1));

        assertThat(attendance1.getWhen().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(attendance2.getWhen().toLocalDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(attendance3.getWhen().toLocalDate()).isEqualTo(LocalDate.now().minusDays(1));
    }


    @Test
    @DisplayName("오늘 이미 출석한 회원은 다시 출석할 수 없다")
    void cannotAttendTwiceInOneDay() {
        // Given
        Long userId = 1L;
        User user = User.of("TestUser");
        ReflectionTestUtils.setField(user, "id", 1L);

        // 오늘 이미 출석한 Attendance 생성
        Attendance attendance = Attendance.of(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(attendanceRepository.findAllByUserId(userId)).thenReturn(List.of(attendance));

        // When & Then
        assertThatThrownBy(() -> attendanceService.recordAttendance(userId))
                .isInstanceOf(AlreadyCheckedInException.class)
                .hasMessage("오늘은 이미 출석하셨습니다.");
    }
}