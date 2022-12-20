package br.pessoal.hibernate.demo;

import br.pessoal.hibernate.demo.entity.Instructor;
import br.pessoal.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        try(sessionFactory){

//            Instructor instructor = new Instructor("Lucas", "Oliveira", "lucas.oliveira@undead.com");
//            InstructorDetail instructorDetail = new InstructorDetail("youtube.com", "Java");
//            instructor.setInstructorDetail(instructorDetail);


            Instructor instructor = new Instructor("Madhu", "Patel", "madhu.patel@undead.com");
            InstructorDetail instructorDetail = new InstructorDetail("youtube.com/MadPatel", "Guitar");
            instructor.setInstructorDetail(instructorDetail);

            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();

            /*
             * This will also save instructorDetail 'cause CascadeType.ALL
             * */
            System.out.println("Saving instructor: "+instructor);
            session.save(instructor);

            session.getTransaction().commit();
        }
    }
}
