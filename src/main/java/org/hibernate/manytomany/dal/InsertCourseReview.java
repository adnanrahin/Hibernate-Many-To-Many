package org.hibernate.manytomany.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.manytomany.model.Course;
import org.hibernate.manytomany.model.Instructor;
import org.hibernate.manytomany.model.InstructorDetail;
import org.hibernate.manytomany.model.Review;

import java.util.ArrayList;
import java.util.List;

public class InsertCourseReview {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Review.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            /*session.beginTransaction();

            Review review1 = new Review("One of Kind Deep Learning Course");
            Review review2 = new Review("Awesome Course");
            Review review3 = new Review("Best Data Science Course.");

            List<Review> deepLearning = new ArrayList<>();
            deepLearning.add(review1);
            deepLearning.add(review2);

            List<Review> dataScience = new ArrayList<>();
            dataScience.add(review3);

            Course dlCourse = session.get(Course.class, 10);
            Course ds = session.get(Course.class, 11);

            dlCourse.setReviews(deepLearning);
            ds.setReviews(dataScience);

            session.save(dlCourse);
            session.save(ds);

            session.getTransaction().commit();*/

            session.beginTransaction();

            Course tempCourse = session.get(Course.class, 11);

            // add some reviews
            tempCourse.addReview(new Review("I love Deep Learning"));
            tempCourse.addReview(new Review("Best Deep Learning Course"));
            tempCourse.addReview(new Review("What a Deep Learning Course"));

            // save the course ... and leverage the cascade all :-)
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.save(tempCourse);

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
