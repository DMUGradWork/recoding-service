package com.grewmeet.recordingservice.service;

import com.grewmeet.recordingservice.dto.dating.DatingEventLogResponseDto;
import com.grewmeet.recordingservice.dto.dating.DatingEventLoggingRequestDto;
import java.util.List;

public interface DatingRecordService {
    DatingEventLogResponseDto recordAttendance(DatingEventLoggingRequestDto request);
    List<DatingEventLogResponseDto> getDatingLogs(Long userId);
    DatingEventLogResponseDto getLogDetail(Long logId);
}
