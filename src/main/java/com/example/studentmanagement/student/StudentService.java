package com.example.studentmanagement.student;

// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

// * the class below acts as a service layer for the student

// using the @Component annotation to make the class below as a component or a spring
// bean

// * we can pass a unique name to the @Component annotation to give the class below
// * a unique name with which we can identify the class below but if we not pass
// * the name then the unique name of the class will be same as the name of the class
// * but with first letter in lower case so in our case the name of the class below
// * will be studentService

// * the @Component annotation below will make the class below as a component class
// * but we want the class below to be a component but with some more things so using
// * the @Service annotation rather than @Component annotation to make the class
// * below as a service class and let the spring know that the class below is a 
// * service class

// @Component
@Service
public class StudentService {

    // the method below is used to get the list of all the students
    // public List<Student> getAllStudents(){

    //     // using the of() method from the list class and passing the instances 
    //     // of Student class to return the list of students
    //     return List.of(
    //         // using the of() method from the LocalDate class to create a date object

    //         // passing L with 1 since the type of id is Long
    //         new Student(1L,"Chad","chad@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22),
    //         new Student(1L,"Trisha","trish@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22),
    //         new Student(1L,"Helen","helen@jetbrains.com",LocalDate.of(2000, Month.JANUARY, 5), 22)
    //     );
    // }



    // the code below is used to create an instance of StudentRepository for doing
    // CRUD operations with the database using dependency injection
    private final StudentRepository studentRepository;

    // creating a constructor and passing the instance of StudentReository to it 
    // as input for initializing the instance of StudentRepository

    // using the @Autowired annotation to do dependency injection using constructor injection
    // to get access to the methods inside the StudentRepository to do CRUD operations
    // with the database

    // using the @Qualifier annotation and passing the name of the interface as input
    // to let the spring now which bean are we referring to for doing dependency injection
    @Autowired
    public StudentService(@Qualifier("studentRepository") StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    // the method below is used to return the list of all the Students that we get
    // from the database
    public List<Student> getAllStudents(){
        // using the findAll() method provided by the jpaRepository that we are 
        // using inside the StudentRepository to get the list of all the Students
        // from the database
        return studentRepository.findAll();
    } 

    // the method below is used to add a new student to the database 

    // the method below takes an instance of Student as input
    public void addStudent(Student student){

        // below line of code is for debugging purpose
        System.out.println("The details of the student are: ");
        System.out.println(student);


        // the code below is used to get the details of the student using the email address provided by
        // the student using the findByEmail() method defined by us in the StudentRepository interface and 
        // then passing the email of the student as input by using the getEmail() getter from the Student class
        Optional<Student> studentDetailsByEmail = studentRepository.findByEmail(student.getEmail());

        // using the isPresent() method on the StudentDetailsByEmail property to check that
        // if the details of the Student are present or not 

        // if the details of the Student are present then throwing the exception that
        // email already taken
        if(studentDetailsByEmail.isPresent()){

            // using the IllegalStateException to throw the exception to the user
            // that email is already taken
            throw new IllegalStateException("Email already taken");

        }

        // using the save() method from the jpaRepository using the studentRepository 
        // to save the details of the student in the database
        studentRepository.save(student);
    }

    // the method below is used to delete the details of the student based on the
    // id of the student
    public void deleteStudentById(Long studentId){
        // using the existsById() method from the StudentRepository and then 
        // passing the id of the student as input to check that whether the student 
        // exists in the db or not
        boolean studentExists = studentRepository.existsById(studentId);

        if(!studentExists){
            throw new IllegalStateException("Student with id "+studentId+" does not exists");
        }
        
        // using the deleteById method from the studentRepository to delete the 
        // details of the student by id

        // the deleteById() method takes the id of the student as input
        studentRepository.deleteById(studentId);
    }

    // the method below is used to update the details of the student based on the
    // id of the student

    // using the @Transactional annotation to start a transaction to update the
    // details of the student in the db when this method is called
    @Transactional
    public void updateDetailsOfStudent(Long studentId,String name, String email){
        // the method below is used to get the details of the student by id and if the 
        // details not there that means the student does not exists then throwing the error
        // that the student with the id does not exists

        // using the findById() method to get the details of the student by using the id 
        // of the student else throwing the error that the student with the id does not exists
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id "+studentId+" does not exists"));
        

        // the code below is used to check that if the name is not equal to null and name is not empty and the
        // name entered by the user is not as the same as we currently have in the db then 
        // updating the name of the student
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            // using the setName() setter from the Student class to update the name of the student
            student.setName(name);
        }

        // the code below is used to check that if the email is not equal to null and email is not empty and the
        // email entered by the user is not as the same as we currently have in the db then 
        // updating the email of the student
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            // the code below is to use the findByEmail() method and then passing the email of the
            // student as input to check that whether there is already a student that exists with the 
            // email id or not
            Optional<Student> studentData = studentRepository.findByEmail(email);

            // using isPresent() method on the studentData to check that whether the student exists or not
            if(studentData.isPresent()){
                throw new IllegalStateException("Student with email id "+ email + " already exists");
            }

            // using the setEmail() setter from the Student class to update the email of the Student
            student.setEmail(email);
        }
    }

}
