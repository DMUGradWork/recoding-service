package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.domain.DatingLog;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.dating.DatingEventLogResponseDto;
import com.grewmeet.recordingservice.dto.dating.DatingEventLoggingRequestDto;
import com.grewmeet.recordingservice.exception.DatingLogNotFoundException;
import com.grewmeet.recordingservice.exception.UserNotFoundException;
import com.grewmeet.recordingservice.repository.DatingLogRepository;
import com.grewmeet.recordingservice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatingRecordServiceImpl implements DatingRecordService {

    private final UserRepository userRepository;
    private final DatingLogRepository datingLogRepository;

    @Override
    public DatingEventLogResponseDto recordAttendance(DatingEventLoggingRequestDto request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.userId() + " not found"));

        DatingLog datingLog = DatingLog.of(
                user.getId(),
                user.getUsername(),
                request.Title(),
                request.Description());

        return DatingEventLogResponseDto.from(datingLogRepository.save(datingLog));
    }

    @Override
    public List<DatingEventLogResponseDto> getDatingLogs(Long userId) {
        List<DatingLog> datingLogs = datingLogRepository.findByUserId(userId);
        return datingLogs.stream()
                .map(DatingEventLogResponseDto::from)
                .toList();
    }

    @Override
    public DatingEventLogResponseDto getLogDetail(Long logId) {
        DatingLog datingLog = datingLogRepository.findById(logId)
                .orElseThrow(() -> new DatingLogNotFoundException("Log with id " + logId + " not found"));

        return DatingEventLogResponseDto.from(datingLog);
    }
}
