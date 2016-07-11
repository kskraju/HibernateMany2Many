package com;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
 
/**
 * A program that demonstrates using Hibernate framework to manage
 * a bidirectional many-to-many association in relational database.
 *
 */
public class Many2ManyTest {
 
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
         
        Groups groupAdmin = new Groups("Admin Group");
        Groups groupGuest = new Groups("User Group");
         
        Users user1 = new Users("TomHarry", "tomharry", "tomharry@abc.net");
        Users user2 = new Users("AdamMary", "amary", "amary@cba.net");
         
        groupAdmin.addUser(user1);
        groupAdmin.addUser(user2);
         
        groupGuest.addUser(user1);
         
        user1.addGroup(groupAdmin);
        user2.addGroup(groupAdmin);
        user1.addGroup(groupGuest);
         
        session.save(groupAdmin);
        session.save(groupGuest);
         
        session.getTransaction().commit();
        session.close();       
    }
 
}
