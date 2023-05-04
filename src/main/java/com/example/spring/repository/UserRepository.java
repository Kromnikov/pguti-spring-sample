package com.example.spring.repository;

import com.example.spring.user.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository  extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);;
}
