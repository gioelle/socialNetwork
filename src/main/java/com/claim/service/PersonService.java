package com.claim.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claim.entity.Person;
import com.claim.repository.PersonRepository;

@Service
public class PersonService {
	
	@PersistenceContext
	private EntityManager entityManager;
		//this is native sql
	
	@Autowired
	private PersonRepository personRepository;
	
	private static final String loginSql= "select P.* from person P where P.email=:email and P.password=:password";
//	private static final String signupSql= "slect P.* from person P where P.first_name=:firstName, P.last_name=:lastName, P.email=:email, P.age=age, P.profile_pic=:profilePic and P.password=:password";
//	@Transactional
//	public Person save(String firstName, String lastName, String email, int age, String profilePic, String password) {
//				this.personRepository.save(arg0)
//				.setParameter("firstName", firstName)
//				.setParameter("lastName", lastName)
//				.setParameter("email", email)
//				.setParameter("age", age)
//				.setParameter("profilePic", profilePic)
//				.setParameter("password", password)
//				.getSingleResult();
//				return entityManager.persist(p);
//	}
	@Transactional
	public void save(Person user) {
		this.personRepository.save(user);
	}
	@Transactional
	public void save2(Person user) {
		this.entityManager.persist(user);
	}
	
	@Transactional
	public Person login(String email, String password) {
		return (Person) entityManager.createNativeQuery(loginSql, Person.class)
					.setParameter("email", email)
					.setParameter("password", password)
					.getSingleResult();
	}
	
	@Transactional
	public Person login2(String email, String password) {
		return this.personRepository.login(email,password);
	}
	
}
