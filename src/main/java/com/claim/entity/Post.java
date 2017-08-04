package com.claim.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="post")
//@Data
public class Post {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id", updatable=false, nullable=false)
	public long id;
	//This post has a relationship or "join" with this table. 
	//Post has a join reference to the person table using a foreign key reference by email address
	
	
	//insertable false because we're not going to use the new post to insert a new user
	//but if you want to insert an address when you insert a new user, it'd be true
	//updatable person.address update address - would want to make them true
	@OneToOne
	@JoinColumn(name="email", insertable=false, updatable=false)
	private Person person;
	
	@Column (name="email")
	public String email;

	@Column(name="message")
	private String message;

	@Column(name="picture")
	private String picture;

	@CreationTimestamp
	@Column(name="created_date")
	private Timestamp createdDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}