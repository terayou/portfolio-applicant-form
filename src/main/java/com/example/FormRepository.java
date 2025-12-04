package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FormRepository extends JpaRepository<FormEntity, Long> {
	Optional<FormEntity> findByUsername(String username);
	
	Optional<FormEntity> findByUsernameAndPassword(String username, String password);
	
	List<FormEntity> findByNameContaining(String keyword);

}
