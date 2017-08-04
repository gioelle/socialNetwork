package com.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claim.entity.Person;
//Spring JPA uses the class name and object names vs native query uses the table and column names.
@Repository
public interface PersonRepository extends JpaRepository<Person,String> {
						//Person is the object, the name is P
	@Query("Select P from Person P where P.email=:email and P.password=:password")
	public Person login(@Param("email")String email, @Param("password")String password);
	
	
}
