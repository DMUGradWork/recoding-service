package com.grewmeet.recordingservice.repository;

import com.grewmeet.recordingservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
