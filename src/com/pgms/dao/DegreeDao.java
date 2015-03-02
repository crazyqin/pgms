package com.pgms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.util.HibernateSessionFactory;

public class DegreeDao {
	
	public void add(Degree degree){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(degree);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void delete(Degree degree){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.delete(degree);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void update(Degree degree){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.update(degree);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Degree findByDegreeCode(String degree_code){
		try{
			Session session = HibernateSessionFactory.getSession();
			Degree degree = (Degree) session.get("com.pgms.bean.Degree", degree_code);
			return degree;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Degree findByDegreeType(String degree_type){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Degree as d where d.degree_type=:degree_type";
			Query query = session.createQuery(queryString) ;
			query.setParameter("degree_type", degree_type);
			if (query.list().size()>0){
				return (Degree) query.list().get(0);
			}
			return null;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void addorupdate(Degree degree){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.merge(degree);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List viewAllDegree(){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Degree";
			Query query = session.createQuery(queryString) ;
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}

	public List<String> viewAllUniqueDegree() {
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "SELECT distinct degree_type FROM degree";
			Query query = session.createSQLQuery(queryString);
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}

}
