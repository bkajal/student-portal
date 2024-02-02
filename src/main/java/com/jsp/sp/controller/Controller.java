package com.jsp.sp.controller;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.hibernate.exception.ConstraintViolationException;

import com.jsp.sp.model.Admin;
import com.jsp.sp.model.Student;

@WebServlet(loadOnStartup = 1)
public class Controller extends HttpServlet{
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	static EntityTransaction entityTransaction = entityManager.getTransaction();
	
	//Fetch first admin in admin_table
	public Admin firstAdmin() {
		return entityManager.find(Admin.class, 1);
	}
	//Add admin to admin_table
	public boolean addAdmin(Admin admin) {
		if (admin!=null) {
			admin.setUsername("Admin");
			admin.setPassword("admin01");
			entityTransaction.begin();
			entityManager.persist(admin);
			entityTransaction.commit();
			return true;
		}
		return false;
	}
	//Fetch existing admin in admin_table using credentials
	public Admin fetchAdmin(String... loginparam) {
		try {
			entityTransaction.begin();
			String jpql = "SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("username", loginparam[0]);
			query.setParameter("password", loginparam[1]);
			Admin admin = (Admin) query.getSingleResult();
			entityTransaction.commit();
			return admin;	
		} catch (NoResultException e) {
			entityTransaction.rollback();
			System.out.println("ERROR: --NO DATA IN DATABASE--");
		}
		return null;
	}
	//Fetch student by student_enroll_id from student_table
	public Student findStudent(int studentId) {
		return entityManager.find(Student.class, studentId);
	}
	//add student to student_table
	public boolean addStudent(Admin admin, Student student) {
		try {
			if (student!=null) {
				entityTransaction.begin();
				entityManager.persist(student);
				entityManager.merge(admin);
				entityTransaction.commit();
				return true;
			}
		} catch (ConstraintViolationException e) {
			entityTransaction.rollback();
			System.out.println("ERROR:--(Primary Key) ID Repeated-- ");
		}
		return false;
	}
	//Fetch existing student list from student_table
	public List<Student> fetchStudents() {
		try {
			entityTransaction.begin();
			String jpql = "SELECT s FROM Student s";
			TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
			List<Student> studentList = query.getResultList();
			entityTransaction.commit();
			return studentList;	
		} catch (Exception e) {
			entityTransaction.rollback();
			System.out.println("ERROR: --NO DATA IN DATABASE--");
		}
		return null;
	}
	//Fetch existing student list from student_table except 1st entry
	public List<Student> getexistingStudents() {
		try {
			entityTransaction.begin();
			String jpql = "SELECT s FROM Student s WHERE s.enroll_id > 1";
			TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);
			List<Student> studentList = query.getResultList();
			entityTransaction.commit();
			return studentList;
		} catch (Exception e) {
			entityTransaction.rollback();
			System.out.println("ERROR: --DATA NOT FOUND--");
		}
		return null;
	}
	//Remove Student by student_enroll_id from student_table 
	public int removeStudent(Admin curr_admin, Student studentToRemove) {
		if (curr_admin!=null && studentToRemove!=null) {
			List<Student> a_student_list = curr_admin.getStudents();
			List<Student> existingStudents = getexistingStudents();
			for (Student student : existingStudents) {
				if (student.getEnroll_id()==studentToRemove.getEnroll_id()) {
					studentToRemove = student;
					int enroll_id = studentToRemove.getEnroll_id();
					entityTransaction.begin();
					a_student_list.remove(studentToRemove);
					entityTransaction.commit();
					entityTransaction.begin();
					entityManager.remove(studentToRemove);
					entityManager.merge(curr_admin);
					entityTransaction.commit();
					return enroll_id;
				} 
			}
		}
		return -1;
	}
	//update Student Details through Admin
	public Student updateStudent(Admin curr_admin, Student studentToUpdate) {
		if (curr_admin!=null && studentToUpdate!=null) {
			List<Student> a_student_list = curr_admin.getStudents();
			for (Student student : a_student_list) {
				if(student.getEnroll_id()==studentToUpdate.getEnroll_id()) {
					entityTransaction.begin();
					entityManager.merge(studentToUpdate);
					entityTransaction.commit();
				}
			}
			curr_admin.setStudents(a_student_list);
			entityTransaction.begin();
			entityManager.merge(curr_admin);
			entityTransaction.commit();
			return studentToUpdate;
		}
		return null;
	}
	//update Student Details through Student
	public int updateStudent(Student studentToUpdate) {
		if (studentToUpdate!=null) {
			entityTransaction.begin();
			entityManager.merge(studentToUpdate);
			entityTransaction.commit();
		}
		return studentToUpdate.getEnroll_id();
	}
}
