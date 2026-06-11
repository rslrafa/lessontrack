package com.codewithrafa.lessontrack.repository;

import com.codewithrafa.lessontrack.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}