package com.codewithrafa.lessontrack.repository;

import com.codewithrafa.lessontrack.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    long countByStatus(String status);

    List<Assessment> findByDueDateAfterOrderByDueDateAsc(LocalDate date);
}