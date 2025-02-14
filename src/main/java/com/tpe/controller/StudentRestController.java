package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController//bu classta restful serviceler geliştirilecek
@RequestMapping("/api")
public class StudentRestController {

    private final IStudentService service;

    @Autowired
    public StudentRestController(IStudentService service) {
        this.service = service;
    }


    //1- Tüm ögrencileri listeleme
    //https://localhost:8080/api/all + GET
    @GetMapping("/all")
    public List<Student>allStudent(){
       return service.listAllStudents();//jackson tarafından JSON formatına donusturulur
    }

    //client : fronend , dıger bır yazılım








}
