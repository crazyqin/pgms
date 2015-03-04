package com.pgms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pgms.bean.Admin;
import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.util.HibernateSessionFactory;

public class CollegeDao {
	
	public void add(College college){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(college);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void delete(College college){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.delete(college);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void update(College college){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.update(college);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public College findById(Long id){
		try{
			Session session = HibernateSessionFactory.getSession();
			College college = (College) session.get("com.pgms.bean.College", id);
			return college;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public College findByCollegeName(String college_name){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from College as c where c.college_name=:college_name";
			Query query = session.createQuery(queryString) ;
			query.setParameter("college_name", college_name);
			if (query.list().size()>0){
				return (College) query.list().get(0);
			}
			return null;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void addorupdate(College college){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.merge(college);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List viewAllCollege(){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from College";
			Query query = session.createQuery(queryString) ;
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	
	public List searchCollege(int firstResult, int maxResults, String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql) ;
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List l = query.list();
		HibernateSessionFactory.closeSession();
		return l;
	}

	public int countSearchCollege(String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql) ;
		int c = query.list().size();
		HibernateSessionFactory.closeSession();
		return c;
	}
	
	

}
