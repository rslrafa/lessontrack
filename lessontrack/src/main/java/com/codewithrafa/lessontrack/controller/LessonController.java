package com.codewithrafa.lessontrack.controller;

import com.codewithrafa.lessontrack.entity.Lesson;
import com.codewithrafa.lessontrack.service.LessonService;
import com.codewithrafa.lessontrack.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LessonController {

    private final LessonService lessonService;
    private final SubjectService subjectService;

    public LessonController(LessonService lessonService, SubjectService subjectService) {
        this.lessonService = lessonService;
        this.subjectService = subjectService;
    }

    @GetMapping("/lessons")
    public String listLessons(Model model) {
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "lessons";
    }

    @GetMapping("/lessons/new")
    public String showCreateForm(Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "lesson-form";
    }

    @PostMapping("/lessons")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.saveLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("lesson", lessonService.getLessonById(id));
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "lesson-form";
    }

    @GetMapping("/lessons/delete/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return "redirect:/lessons";
    }
}