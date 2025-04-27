package com.grewmeet.recordingservice.exception;

public class AlreadyCheckedInException extends RuntimeException {
    public AlreadyCheckedInException() {
        super("오늘은 이미 출석하셨습니다.");
    }

    public AlreadyCheckedInException(String message) {
        super(message);
    }
}
