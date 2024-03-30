package listeners;

import java.util.ArrayList;

import models.Course;
// import utils.AppUtility;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListenerImpl implements ServletContextListener {
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("To DEKHO AB MAI KYA KARTA HU KYUKI AB BOHOHT HO CHUKA HAI");
        ArrayList<Course> courses = Course.collectAllCourses();

        ServletContext context = e.getServletContext();
        context.setAttribute("courses", courses);
        System.out.println(courses);
       
        System.out.println("DEKHA AB AYA NA MAZA KHEL KA ");
        
    }

    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("khel khatam checkmate");
    }
}
