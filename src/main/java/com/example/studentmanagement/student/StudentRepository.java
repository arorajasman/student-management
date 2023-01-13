package com.example.studentmanagement.student;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// the code below is used to create the data access layer for doing CRUD
// operations with the database

// using the JPARepository and passing the data type of the primary key of 
// the Student class i.e. our entity class and also the instance of Student 
// as generic type to the JPARepository

// using the @Repository annotation to create the class below as a Repository class
// to do CRUD operations with the database

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // the method below is used to find the details of a student based on the Email
    // * the naming convention for this type of method is findBy<Name in capital letter>
    // * and it takes user email as input

    // the below method will convert into the following sql query
    // * SELECT * FROM student where email = ?
    // where ? is the email that we have entered


    // we can also use the @Query annotation to find the details of the student
    // by email id using the custom sql query that we can pass as input to the
    // @Query annotation

    // * the query written inside the @Query annotation is JPQL not sql completely

    // @Query("SELECT s FROM Student WHERE s.email = ?1")
    Optional<Student> findByEmail(String email);
}
