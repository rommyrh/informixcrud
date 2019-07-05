package com.armos.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.armos.usermanagement.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	




    
}