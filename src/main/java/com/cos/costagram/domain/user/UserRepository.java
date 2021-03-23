package com.cos.costagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	//SELECT * FROM user WHERE username = 1?
	User findByUsername(String username);
}
