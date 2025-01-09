package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;

    //1-c
    @Override
    public List<Student> listAllStudents() {
        return repository.findAll();
    }

    //2-c
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
    }
    //3-b
    @Override
    public Student findStudentById(Long id) {
        Student student=repository.findById(id).
                orElseThrow(()->new StudentNotFoundException("Student not Found By Id : "+id));
        //findById methodu geriye optinal döndürür
        //student varsa student değişkeni döndürür
        //optinal objesi boş ise orElseThrow bize rastgele bir exception döndürür
        //get():NoSuchElementException.
        return student;
    }

    //4-B
    @Override
    public void deleteStudent(Long id) {
        Student student =findStudentById(id);
        repository.delete(student);



    }
}