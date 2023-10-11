package com.cms.app.repository;

import com.cms.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // Since email is unique, we'll find users by email
    Optional<User> findByEmail(String email);
}
