package com.jwt_based.security.Task_3;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {
	Optional<AppUser> findByEmail(String email);

}
