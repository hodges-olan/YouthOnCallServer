/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Control;

import Server.App.YouthOnCallServer;
import Server.App.yocLogger;
import Server.Model.Jobs;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author co075oh
 */
public class JobsControl {
    
    public Integer createJob(Jobs job) {
        Integer jobID = null;
        SessionFactory sessionFactory = YouthOnCallServer.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            jobID = (Integer) session.save(job);
            session.getTransaction().commit();
        } catch (Exception ex) {
            yocLogger.log("createJob", ex.getMessage(), "ERR");
        }
        return jobID;
    }
    
    public void updateJob(Jobs job) {
        SessionFactory sessionFactory = YouthOnCallServer.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(job);
            session.getTransaction().commit();
        } catch (Exception ex) {
            yocLogger.log("updateJob", ex.getMessage(), "ERR");
        }
    }
    
    public Jobs retrieveJob(Integer search) {
        Jobs job = null;
        SessionFactory sessionFactory = YouthOnCallServer.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            job = session.get(Jobs.class, search);
        } catch (Exception ex) {
            yocLogger.log("retrieveJob", ex.getMessage(), "ERR");
        }
        return job;
    }
    
    public List<Jobs> retrieveAllJobs() {
        List<Jobs> jobs = null;
        SessionFactory sessionFactory = YouthOnCallServer.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            jobs = session.createCriteria(Jobs.class).list();
        } catch (Exception ex) {
            yocLogger.log("retrieveAllJobs", ex.getMessage(), "ERR");
        }
        return jobs;
    }
    
}
