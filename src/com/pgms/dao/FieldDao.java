package com.pgms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.util.HibernateSessionFactory;

public class FieldDao {
	
	public void add(Field field){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(field);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void delete(Field field){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.delete(field);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void update(Field field){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.update(field);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Field findByFieldCode(String field_code){
		try{
			Session session = HibernateSessionFactory.getSession();
			Field field = (Field) session.get("com.pgms.bean.Field", field_code);
			return field;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Field findByFieldName(String field_name){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Field as f where f.field_name=:field_name";
			Query query = session.createQuery(queryString) ;
			query.setParameter("field_name", field_name);
			if (query.list().size()>0){
				return (Field) query.list().get(0);
			}
			return null;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void addorupdate(Field field){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.merge(field);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List viewAllField(){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Field";
			Query query = session.createQuery(queryString) ;
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}

}
