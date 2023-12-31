package com.mamun.crud.controller;

import com.mamun.crud.Entity.Student;
import com.mamun.crud.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model){
       model.addAttribute("students",this.studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        Student student=new Student();
        model.addAttribute("student",student);
        return  "new_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student")Student student){
        this.studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/student/update/{id}")
    public String updateStudentForm(@PathVariable int id, Model model){
        model.addAttribute("student",studentService.getStudentById(id));
        return "update_student";

    }

    @PostMapping("/students/{id}")
    public String updateStudentDetails(@PathVariable int id, @ModelAttribute ("student") Student student, Model model){

       Student existingStudent= this.studentService.getStudentById(id);
       existingStudent.setId(id);
       existingStudent.setFirstName(student.getFirstName());
       existingStudent.setLastName(student.getLastName());
       existingStudent.setEmail(student.getEmail());
      Student updated=this.studentService.updateStudent(existingStudent);
      return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id){
         this.studentService.deleteStudentById(id);
        return "redirect:/students";
    }




}
