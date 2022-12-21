package br.pessoal.oneToOne;

import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
