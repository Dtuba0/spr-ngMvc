package com.tpe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller//gelen request(istek)leri bu classta karşılanacak ve ilgili methodlarla maplenerek cevaplayacak
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
public class StudentController {

    //NOT : controller response methodlar gerıye sadece mav veya strıng dondurur

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
        mav.addObject("messagebody","I'm a Student Managment System");
        mav.setViewName("hi");
        return mav;
    }

    //1- tum ogrencılerı listeleme
    //http:localhost:8080/SpringMvc/students + get
    @GetMapping
    public ModelAndView getStudent(){
        ModelAndView mav = new ModelAndView();
        //todo db den liste ile bilgilere gelicek
        mav.setViewName("students");
        return mav ;
    }

}
