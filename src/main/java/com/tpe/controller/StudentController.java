package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller//gelen request(istek)leri bu classta karşılanacak ve ilgili methodlarla maplenerek cevaplayacak
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
public class StudentController {

    @Autowired
    private IStudentService service;

    //NOT: controller response methodlar geriye sadece mav veya string döndürebilir.

    //http:localhost:8080/SpringMvc/students/hi + get--okuma
    //http:localhost:8080/SpringMvc/students/hi + post--kayıt
    //http:localhost:8080/SpringMvc/students/hi + delete--silme


    //@RequestMapping("/hi")
    //Method: sadece ilgili methoda bu url uzerinden istek(request) gelecegini soyler
    //Class : class icinde bulunan tüm methodlara bu url üzerinden istek(request) gelecegini soyler
    @GetMapping("/hi")
    public ModelAndView sayHi(){
        //response u hazırlayacak
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi");
        mav.addObject("messagebody","Ben Öğrenci Yönetim sistemiyim");
        mav.setViewName("hi");
        return mav;
    }

    //1-Tüm öğrencileri listeleme
    //http:localhost:8080/SpringMvc/students/ + get
    @GetMapping
    public ModelAndView getStudent(){
        List<Student>allStudent=service.listAllStudents();
        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudent);
        mav.setViewName("students");
        return mav;
    }

    //2-öğrenciyi kaydetme
    //request: http:localhost:8080/SpringMvc/students/new + get
    //response: form göstermek
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }

    //modelattribute anatasyonu view katmanı ile controller arasında
    //modelin transferini sağlar


    //2-b: öğrenciyi kaydetme
    //request: http:localhost:8080/SpringMvc/students/saveStudent + POST
    //response: Db'e öğrenciyi ekleme ve liste yonledirelim
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "studentForm";
        }

        service.addOrUpdateStudent(student);

        return "redirect:/students";//http:localhost:8080/SpringMvc/students/ + GET
    }

    //3. öğrenciyi güncelleme
    //request : http://localhost:8080/SpringMvc/students/update?id=3 + GET
    //response: update icin verilen id'deki bilgileri ile birlikte formu gösterme
    //idsi verilen öğrenciyi bulmalıyız
    @GetMapping("update")
    public ModelAndView sendFormUpdate(@RequestParam("id") Long identity){

        Student foundStudent=service.findStudentById(identity);

        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }

    //4- öğrenciyi silme
    //request : http://localhost:8080/SpringMvc/students/delete/5 + GET
    //response: ögrenci silinecek kalan ogrencıler listelenecek
    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable ("id") Long identity){

        service.deleteStudent(identity);

        return "redirect:/students";

    }
     //kullanıcıdan bılgı;
    //1-form/body(JSON)
    //2-query param : /query?id=5&name=Ali
    //3-path param  :/5/Ali
    //query param ve path param sadece 1 tane ise isim belirtmek opsiyonel



    //@ExceptionHandler:try-catch bloğunun mantığıyla benzer çalışır

    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message",ex.getMessage());
        modelAndView.setViewName("notFound");
        return modelAndView;

    }

}
