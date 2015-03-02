package com.pgms.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.pgms.bean.Admin;
import com.pgms.bean.College;
import com.pgms.bean.Degree;
import com.pgms.bean.Field;
import com.pgms.bean.Student;
import com.pgms.util.HibernateSessionFactory;

public class StudentDao {
	
	public void add(Student stu){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(stu);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void delete(Student stu){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.delete(stu);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public void update(Student stu){
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.update(stu);
			session.getTransaction().commit();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public Student findById(Long id){
		try{
			Session session = HibernateSessionFactory.getSession();
			Student stu = (Student) session.get("com.pgms.bean.Student", id);
			return stu;
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List findByCollegeName(String college_name){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Student as s where s.college.college_name=:college_name";
			Query query = session.createQuery(queryString) ;
			query.setParameter("college_name", college_name);
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List findByDegreeType(String degree_type){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Student as s where s.degree.degree_type=:degree_type";
			Query query = session.createQuery(queryString) ;
			query.setParameter("degree_type", degree_type);
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}
	
	public List<Student> findByFieldCode(String field_code){
		try{
			Session session = HibernateSessionFactory.getSession();
			String queryString = "from Student as s where s.field.field_code=:field_code";
			Query query = session.createQuery(queryString) ;
			query.setParameter("field_code", field_code);
			return query.list();
		}catch(RuntimeException re){
			throw re;
		}finally{
			HibernateSessionFactory.closeSession();

		}
	}

	public List searchStu(int firstResult, int maxResults, String hql) {

		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql) ;
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		List l = query.list();
		HibernateSessionFactory.closeSession();
		return l;
	}

	public int countSearchStu(String hql) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql) ;
		int c = query.list().size();
		HibernateSessionFactory.closeSession();
		return c;
	}


	
	
}
