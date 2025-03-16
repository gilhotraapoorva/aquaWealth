
package com.aquawealth.repository;

import com.aquawealth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(TRIM(u.governmentId)) = LOWER(TRIM(:governmentId))")
    Optional<User> findByGovernmentId(@Param("governmentId") String governmentId);
    Optional<User> findByEmail(String email);

}

