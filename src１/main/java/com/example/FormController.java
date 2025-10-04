package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Hello from Spring Boot!";
    }

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/submit")
    public String submitForm(@RequestParam String name,
							@RequestParam int year,
							@RequestParam int month,
							@RequestParam int day,
							@RequestParam int age,
							@RequestParam String email,
							@RequestParam String callnumber,
							@RequestParam String live,
							@RequestParam String finalbackground,
							@RequestParam String skills,
							@RequestParam String job,
							@RequestParam String PR,
							@RequestParam String motivation,	
							Model model) {

        FormEntity entity = new FormEntity();
        entity.setName(name);
        entity.setYear(year);
        entity.setMonth(month);
        entity.setDay(day);
        entity.setAge(age);
        entity.setEmail(email);
        entity.setCallnumber(callnumber);
        entity.setLive(live);
        entity.setFinalbackground(finalbackground);
        entity.setSkills(skills);
        entity.setJob(job);
        entity.setPR(PR);
        entity.setMotivation(motivation);

        formRepository.save(entity);

        // 全体をまとめて1つのオブジェクトで渡す
        model.addAttribute("formData", entity);

        return "index";
    }
}