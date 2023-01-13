package com.example.studentmanagement.student;

// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// * the class below acts as the api layer for student

// creating a class to create a controller for student

// using @RestController annotation to make the class below as a Rest controller class

// using the @RequestMapping annotation and passing the '/api/v1/student' route as 
// input to create '/student' as parent route/mapping for this controller

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    // creating an instance of the StudentService class 
    private final StudentService studentService;

    // creating a constructor to initialize the StudentService instance below

    // using the @Autowired annotation to do dependency injection using contructor
    // injection to inject the StudentService class/dependency in this file
    // for using the method inside it

    // using the @Qualifier annotation with the studentService class and passing 
    // the name of the class / bean / component of the studentService class
    // so as to let the spring know that to which class the StudentService is 
    // associated with

    @Autowired
    public StudentController(@Qualifier("studentService") StudentService service){
        this.studentService = service;
    }
    
    // the method below is used to return the list of students when the user
    // goes to the '/all' route

    // using the @GetMapping annotation and passing the '/all' as input to create
    // a get mapping for '/api/v1/student/all' route

    @GetMapping("/all")
    public List<Student> getStudents(){
        // using the getAllStudents() method from the studentService class to get 
        // the list of all the students
        return studentService.getAllStudents();

        // using the of() method from the list class and passing the instances 
        // of Student class to return the list of students
        // return List.of(
             // using the of() method from the LocalDate class to create a date object
             
             // passing L with 1 since the type of id is Long
        //     new Student(1L,"Chad","chad@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22),
        //     new Student(1L,"Trisha","trish@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22),
        //     new Student(1L,"Helen","helen@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22)
        // );
    }

    // using the @PostMapping annotation to create a method to save the details of the student to the database
    // when the student goes to the "/api/v1/student/register" route

    // the method below takes the instance of Student as input

    // using the @RequestBody annotation to get the details of the Student from
    // the object passed by the user in the request body and then mapping that
    // request body internally to the Student instance

    @PostMapping("/register")
    public void registerStudent(@RequestBody Student student){
        // using the addStudent() method from the StudentService class and passing
        // the instance of the Student as input to save the student to the database
        studentService.addStudent(student);
    }

    // using the @DeleteMapping to delete the details of the student from the database
    // by using the id of the student

    // the @DeleteMapping annotation takes the path as the input and the path also contains the 
    // studentId in between the curley braces to get the id of the particular student 
    // whose details we want to delete

    // passing the id of the student as input to the method to delete the details of that student

    // using the @PathVariable and passing the studentId as input and this name should be 
    // same as that of the one we are passing in the path since we are using this to map 
    // the id that the user has passed in the input
    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        // using the deleteStudentById() method to delete the details of the 
        // student according to the id of the studnet

        // the deleteStudentById() method takes the id of the student as input
        studentService.deleteStudentById(id);
    }

    // using the @PutMapping annotation to create a method to update the email and the name of the student
    // based on the id of the Student


    // passing the id of the student as input to the method to update the details of that student

    // using the @PathVariable and passing the studentId as input and this name should be 
    // same as that of the one we are passing in the path since we are using this to map 
    // the id that the user has passed in the input

    // using @RequestParam to get the name and email of the student as the query whose details we
    // want to update in the db 

    // we have passed the required field as false for @RequestParam since the email and the name of the
    // student is not required

    // here studentName and studentEmail are the keys of the query 
    @PutMapping("/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studId, @RequestParam(required = false) String studentName, @RequestParam(required = false) String studentEmail){
       
        // using the updateDetailsOfStudent() method from the Student service to update the
        // details of the student in the database
        studentService.updateDetailsOfStudent(studId,studentName,studentEmail);
    }
    
}
