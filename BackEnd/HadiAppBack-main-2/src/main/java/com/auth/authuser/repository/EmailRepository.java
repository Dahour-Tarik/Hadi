package com.auth.authuser.repository;

import com.auth.authuser.model.EmailHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailHistory, Long> {
}
