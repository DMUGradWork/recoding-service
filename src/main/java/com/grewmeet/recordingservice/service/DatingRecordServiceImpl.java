package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.domain.DatingLog;
import com.grewmeet.recordingservice.domain.User;
import com.grewmeet.recordingservice.dto.dating.DatingEventLogResponseDto;
import com.grewmeet.recordingservice.dto.dating.DatingEventLoggingRequestDto;
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
                user.getUsername(),
                request.Title(),
                request.Description());

        return DatingEventLogResponseDto.from(datingLogRepository.save(datingLog));
    }

    @Override
    public List<DatingEventLogResponseDto> getDatingLogs(Long userId) {
        return List.of();
    }

    @Override
    public DatingEventLogResponseDto getLogDetail(Long userId, Long logId) {
        return null;
    }
}
