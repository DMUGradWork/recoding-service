package com.grewmeet.recordingservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class StudyEventLog {

    // 검증 완료 후 생성되는 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String studyGroupName;
    private String studyEventTitle;
    private LocalDateTime eventDateTime;

    private StudyEventLog(String username,
                          String studyGroupName,
                          String studyEventTitle,
                          LocalDateTime eventDateTime) {
        this.username = username;
        this.studyGroupName = studyGroupName;
        this.studyEventTitle = studyEventTitle;
        this.eventDateTime = eventDateTime;
    }

    public static StudyEventLog of(String username,
                                   String studyGroupName,
                                   String studyEventTitle,
                                   LocalDateTime eventDateTime) {
        return new StudyEventLog(username, studyGroupName, studyEventTitle, eventDateTime);
    }

}
