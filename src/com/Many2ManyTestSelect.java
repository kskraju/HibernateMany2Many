package com;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
 
public class Many2ManyTestSelect {
 
    public static void main(String[] args) {
        // loads configuration and mappings
        Configuration configuration = new Configuration().configure();
        ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
 
        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);
 
        // obtains the session
        Session session = sessionFactory.openSession();
        session.beginTransaction();
         
/*        Groups groupAdmin = new Groups("Administrator Group");
        Groups groupGuest = new Groups("Guest Group");
         
        Users user1 = new Users("Tom", "tomcat", "tom@codejava.net");
        Users user2 = new Users("Mary", "mary", "mary@codejava.net");
         
        groupAdmin.addUser(user1);
        groupAdmin.addUser(user2);
         
        groupGuest.addUser(user1);
         
        user1.addGroup(groupAdmin);
        user2.addGroup(groupAdmin);
        user1.addGroup(groupGuest);*/
         
        //session.save(groupAdmin);
        //session.save(groupGuest);
        
        Criteria cr = session.createCriteria(Users.class);
        List results = cr.list();
        
        Criteria crGr = session.createCriteria(Groups.class);
        List resultsGr = crGr.list();
        
/*        String hql = "Select * FROM sys.Users";
        Query query = session.createCriteria(Users.class);
        List results = query.list();*/
        System.out.println(" List of Users   :: "+results.size());
        System.out.println(" List of Groups  :: "+resultsGr.size());
        
        session.getTransaction().commit();
        session.close();       
    }
 
}
