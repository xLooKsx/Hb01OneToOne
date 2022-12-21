package br.pessoal.manyToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import br.pessoal.entity.Review;
import br.pessoal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try(sessionFactory; session){
            session.beginTransaction();

            //create a course
            Course course = new Course("Pacman - How to score One million points");

            //save the course
            System.out.println("Saving the course ");
            session.save(course);
            System.out.println("Course saved");

            //create the students
            Student student = new Student("John", "Doe", "john.doe@undead.com");
            Student student2 = new Student("Mary", "Public", "mary.public@undead.com");

            //add students to the course
            course.addStudent(student);
            course.addStudent(student2);

            //save the students
            System.out.println("Saving the students ");
            session.save(student);
            session.save(student2);
            System.out.println("Saved students: "+course.getStudents());

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
