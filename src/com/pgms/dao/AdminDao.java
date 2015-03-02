package com.pgms.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.pgms.bean.Admin;
import com.pgms.util.HibernateSessionFactory;

public class AdminDao {
	
	public void add(Admin admin){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(admin);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}
		finally{
			HibernateSessionFactory.closeSession();
		}
		
	}
	
	public void delete(Admin admin){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.delete(admin);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void update(Admin admin){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.update(admin);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Admin findById(Long id){
		try{
			Session session = HibernateSessionFactory.getSession();
			Admin admin = (Admin) session.get("com.pgms.bean.Admin", id);
			return admin;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List<Admin> findByUsername_Password(String username,String password){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Admin as a where a.username=:username and a.password=:password";
			Query query = session.createQuery(queryString) ;
			query.setParameter("username", username);
			query.setParameter("password", password);
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
		
	}
}
