package com.codewithrafa.lessontrack.controller;

import com.codewithrafa.lessontrack.entity.Subject;
import com.codewithrafa.lessontrack.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }

    @GetMapping("/subjects")
    public String listSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @GetMapping("/subjects/new")
    public String showCreateForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject-form";
    }

    @PostMapping("/subjects")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "subject-form";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}