// package com.example.studentmanagement.student;

// import java.time.LocalDate;
// import java.util.List;

// // the code below is used to do a static import so as to get access to the months directly
// // without using Month.
// import static java.time.Month.*;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// // creating a Configuration class for the Student

// // using the @Configuration annotation to let the spring know that the class
// // below is a configuration class

// @Configuration
// public class StudentConfig {
//     // using the @Bean annotation to let the sping know that the method below is a bean

//     // The method below is a method of type CommandLineRunner and this method 
//     // will be called when the spring boot application will start running

//     // providing the instance of StudentRepository as input to the method below
//     // to get access to all the methods to do CRUD operations with the database
//     @Bean
//     CommandLineRunner commandLineRunner(StudentRepository repository){
//         return args -> {
//             // the code below is used to create three new instance of the student class
//             Student chad =  new Student("Chad","chad@jetbrains.com",LocalDate.of(2000, JANUARY, 5)/*, 22 */);
//             Student trisha = new Student("Trisha","trish@jetbrains.com",LocalDate.of(2000, JANUARY, 5) /*, 22 */);
//             Student helen = new Student("Helen","helen@jetbrains.com",LocalDate.of(2000, JANUARY, 5)/*, 22 */);
            
//             // using the repository instance of StudentRepository to get access to the
//             // saveAll() method from the JPARepository and save all the three instances
//             // i.e. chad,helen and trisha to the database

//             // the saveAll() method takes a list as input so using the List.of()
//             // method to create the list of all the three instance (chad,trisha and helen)
//             // and save them to the database
//             repository.saveAll(List.of(chad,trisha,helen));
//         };
//     }
// }
