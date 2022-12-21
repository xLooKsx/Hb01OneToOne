package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.demo.entity.Instructor;
import br.pessoal.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.Objects;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

            Session session = sessionFactory.getCurrentSession();
        try (sessionFactory; session) {


            session.beginTransaction();

            int theId = 123;

            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

            System.out.println("The instructor detail: " + instructorDetail);
            System.out.println("The associated instructor: " + instructorDetail.getInstructor());

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
