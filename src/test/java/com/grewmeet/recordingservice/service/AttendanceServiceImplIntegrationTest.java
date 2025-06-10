package com.grewmeet.recordingservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.checkin.CheckInResponseDto;
import com.grewmeet.recordingservice.exception.AlreadyCheckedInException;
import com.grewmeet.recordingservice.repository.AttendanceRepository;
import com.grewmeet.recordingservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class AttendanceServiceImplIntegrationTest {
//
//    @Autowired
//    private AttendanceService attendanceService;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    @DisplayName("회원이 정상적으로 출석할 수 있다")
//    void recordAttendance_success() {
//        User user = User.of("TestUser");
//        userRepository.save(user);
//
//        CheckInResponseDto result = attendanceService.recordAttendance(user.getId());
//
//        assertThat(result).isNotNull();
//        assertThat(result.username()).isEqualTo("TestUser");
//        assertThat(result.when()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("오늘 이미 출석한 회원은 다시 출석할 수 없다")
//    void recordAttendance_alreadyCheckedIn() {
//        User user = User.of("TestUser");
//        userRepository.save(user);
//        attendanceService.recordAttendance(user.getId()); // 첫 출석
//
//        assertThrows(
//                AlreadyCheckedInException.class,
//                () -> attendanceService.recordAttendance(user.getId())
//        );
//    }
//
//    @Test
//    @DisplayName("회원의 출석 내역을 불러올 수 있다")
//    void getAttendances_success() {
//        User user = User.of("TestUser");
//        userRepository.save(user);
//        attendanceService.recordAttendance(user.getId());
//
//        List<CheckInResponseDto> attendances = attendanceService.getAttendances(user.getId());
//
//        assertThat(attendances).hasSize(1);
//        assertThat(attendances.get(0).username()).isEqualTo("TestUser");
//    }
}
