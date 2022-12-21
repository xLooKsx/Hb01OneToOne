package br.pessoal.oneToMany;

import br.pessoal.entity.Course;
import br.pessoal.entity.Instructor;
import br.pessoal.entity.InstructorDetail;
import br.pessoal.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {
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

            //create a course
            Course course = new Course("Pacman - How to score One million points");

            //add some reviews
            course.addReview(new Review("Great course...love it!"));
            course.addReview(new Review("Cool course, job well done"));
            course.addReview(new Review("What a dumb course, you are a idiot!"));

            //save the course
            System.out.println("Saving the course ");
            System.out.println("Course :"+course);
            System.out.println("reviews: "+course.getReviews());
            session.save(course);

            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
