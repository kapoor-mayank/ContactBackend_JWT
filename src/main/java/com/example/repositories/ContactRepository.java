package com.example.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	List<Contact> findByPhoneNumber(String phoneNumber);
	List<Contact> findByNameContainingIgnoreCase(String name);
	List<Contact> findByPhoneNumberIsLike(String phoneNumber);
	@Query("SELECT c FROM Contact c WHERE c.name = :name AND c.phoneNumber = :phoneNumber AND c.user.id = :userId")
    Contact findContactsByNameAndPhoneNumberAndUserId(
        @Param("name") String name,
        @Param("phoneNumber") String phoneNumber,
        @Param("userId") Long userId
    );
	
	//Java Persistence Query Language used in above query as the requirement of checking whether searcher is a contact of searched
	//to be able to see e-mail, required a custom query, instead of the ones provided by Java Persistence API
}
