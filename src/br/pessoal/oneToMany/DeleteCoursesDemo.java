package br.pessoal.oneToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCoursesDemo {
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

            //get a course
            int theId = 10;
            Course course = session.get(Course.class, theId );

            //delete a course
            session.delete(course);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
