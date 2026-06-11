package com.codewithrafa.lessontrack.service;

import com.codewithrafa.lessontrack.entity.Assessment;
import com.codewithrafa.lessontrack.repository.AssessmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public Assessment getAssessmentById(Long id) {
        return assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));
    }

    public void deleteAssessment(Long id) {
        assessmentRepository.deleteById(id);
    }
}