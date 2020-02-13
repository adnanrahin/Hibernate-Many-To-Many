package org.hibernate.manytomany.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.manytomany.model.Course;
import org.hibernate.manytomany.model.Instructor;
import org.hibernate.manytomany.model.InstructorDetail;
import org.hibernate.manytomany.model.Review;

public class InsertInstructor {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Review.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {

            session.beginTransaction();

            Instructor instructors[] = {new Instructor("Optimus", "Prime", "optimusprime@email.com"),
                    new Instructor("Iron", "Hide", "ironhide@email.com"),
                    new Instructor("Vector", "Prime", "vectorprime@email.com")
            };

            InstructorDetail instructorDetail[] = {
                    new InstructorDetail("youtube.com/Optimus", "Driving"),
                    new InstructorDetail("youtube.com/ironhide", "Flying"),
                    new InstructorDetail("youtube.com/ironhide", "Destroy")
            };

            for (int i = 0; i < instructors.length; i++) {
                instructors[i].setInstructorDetail(instructorDetail[i]);
                session.save(instructors[i]);
            }

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }
}
