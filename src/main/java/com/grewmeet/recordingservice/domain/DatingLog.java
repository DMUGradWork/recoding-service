package com.grewmeet.recordingservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DatingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String userName;
    private String title;
    private String description;
    private LocalDateTime dateTime;

    private DatingLog (Long userId, String userName, String title, String description) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public static DatingLog of(Long userId, String userName, String title, String description) {
        return new DatingLog(userId,userName,title,description);
    }

}
