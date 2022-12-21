package br.pessoal.oneToOne;

import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class DeleteInstructorDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try(sessionFactory){

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            //get instructor by primary key
            int id = 2;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Found instructor: "+instructor);

            if (Objects.nonNull(instructor)){
                System.out.println("Deleting: "+instructor);

                /*
                 * Will Also delete associated "details" object because CascadeType.ALL
                 * */
                session.delete(instructor);
            }


            session.getTransaction().commit();
        }
    }
}
