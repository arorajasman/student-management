package com.example.studentmanagement.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


// model class to create the properties for a student and the name of the class
// will be the name of the database table

// the class below will also be used to map the student table to our project

// using the @Entity annotation to create the class below as Entity class

// using the @Table annotation to map the Student class below to the postgres table
// named student

@Entity
@Table(name = "student")   // here we are giving the name to the table as student if we do not add this value then by default the name of the table in postgres db will be same as the name of the class
public class Student {

    // property to get id of the student

    // using the @Id annotation to create the id property as primary key in the student table

    // using the @SequenceGenerator annotation to create a sequence of the id

    // using the name attribute to give a name to the SequenceGenerator

    // using the allocationSize to set the amount to increment by when allocating sequence numbers from the sequence

    // using the @GeneratedValue annotation and providing the value of strategy 
    // attribute as GenerationType.SEQUENCE to define the strategy to generate 
    // the id value

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;


    // property to get the name of the student
    private String name;

    // property to get the email of the student
    private String email;
    // property to get the dob of the student 

    // the data type of the property below is of LocalDate type
    private LocalDate dob;

    // property to get the age of the student

    // since we can easily calculate the age of the student from the dob so 
    // we will not add the age column to the database 

    // using the @Transient annotation to let the spring know that we do not need  
    // to create the age column in the db

    @Transient
    private Integer age;

    
    // creating a no-argument constructor for the class
    public Student(){
        System.out.println("Student: Inside no-args constructor");
    }

    // creating a constructor to initialize all the properties of the student class
    public Student(Long id, String name, String email, LocalDate dob /* , Integer age */) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        // this.age = age;
    }

    // creating a constructor without the id field since this will be used for database
    // purpose and the database will autogenerate the id
    public Student(String name, String email, LocalDate dob /* , Integer age */ ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        // this.age = age;
    }

    // getter for id
    public Long getId() {
        return id;
    }

    // setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // getter for name
    public String getName() {
        return name;
    }

    // setter for name
    public void setName(String name) {
        this.name = name;
    }

    // getter for email
    public String getEmail() {
        return email;
    }

    // setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // getter for dob
    public LocalDate getDob() {
        return dob;
    }

    // setter for dob
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // getter for age
    public Integer getAge() {
        // return age;

        // using the Period.between() method to get the age of the users by passing 
        // the dob of the user and the current date time

        // using the getYears() method to get the age in years
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    // setter for age
    public void setAge(Integer age) {
        this.age = age;
    }

    // overriding the toString() method for debugging purpose
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
    }

}
