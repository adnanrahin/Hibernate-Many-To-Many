package org.hibernate.manytomany.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.manytomany.model.*;

public class InsertStudentInCourse {

    public static void main(String args[]) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class).addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Review.class).
                        addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Course course = session.get(Course.class, 10);

            Student students[] = {new Student("Jack", "Black", "jackblack@Email.com")
                    , new Student("Arafat", "Mamun", "arafatmamun@Email.com"),
                    new Student("GC", "Tushar", "gctushar@Email.com")};

            for (int i = 0; i < students.length; i++) {
                course.addStudents(students[i]);
                session.save(students[i]);
            }

            session.getTransaction().commit();

        } finally {
            session.close();
            sessionFactory.close();
        }

    }

}
