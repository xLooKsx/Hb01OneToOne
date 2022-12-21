package br.pessoal.manyToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import br.pessoal.entity.Review;
import br.pessoal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteMaryDemo {
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

            //get the student mary from the database
            int id=2;
            Student student = session.get(Student.class, id);

            System.out.println("Loaded student: "+student);
            System.out.println("Courses : "+student.getCourses());

            //delete student
            System.out.println("Deleting student: "+student);
            session.delete(student);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
