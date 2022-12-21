package br.pessoal.oneToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try(sessionFactory; session){
            session.beginTransaction();

            //get the instructor from db
            int theId = 1;
            Instructor instructor = session.get(Instructor.class, theId);

            //create some courses
            Course course = new Course("Air Guitar - the Ultimate Guide");
            Course course2 = new Course("The Pinball masterclass");

            //add courses to instructor
            instructor.add(course);
            instructor.add(course2);

            //save the courses
            session.save(course);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
