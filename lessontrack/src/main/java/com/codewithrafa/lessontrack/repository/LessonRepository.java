package com.codewithrafa.lessontrack.repository;

import com.codewithrafa.lessontrack.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    long countByStatus(String status);

    List<Lesson> findByLessonDateBetween(LocalDate startDate, LocalDate endDate);

}