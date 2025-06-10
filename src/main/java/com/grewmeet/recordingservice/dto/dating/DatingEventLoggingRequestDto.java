package com.grewmeet.recordingservice.dto.dating;

public record DatingEventLoggingRequestDto(Long userId,
                                           String Title,
                                           String Description) {
}
