package br.pessoal.oneToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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

            //Hibernate query with HQL
            Query<Instructor> query = session.createQuery("SELECT i FROM Instructor i " +
                                                                "JOIN FETCH i.courseList " +
                                                                "WHERE i.id=:theInstructorId", Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId", theId);

            //execute query and get instructor
            Instructor instructor = query.getSingleResult();

            System.out.println("Instructor: "+instructor);

            //get course for the instructor
            System.out.println("Courses: "+instructor.getCourseList());

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
