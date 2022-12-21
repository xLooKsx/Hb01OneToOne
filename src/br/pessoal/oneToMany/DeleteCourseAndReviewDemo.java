package br.pessoal.oneToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import br.pessoal.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try(sessionFactory; session){
            session.beginTransaction();

            //get the course
            int courseId = 10;
            Course course = session.get(Course.class, courseId);

            //print the course
            System.out.println(course);

            //prin the course reviews
            System.out.println(course.getReviews());

            //delete the course
            session.delete(course);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
