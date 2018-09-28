package com.example.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where" +
            " lower(u.name) like concat('%', lower(:name), '%')" +
            " and (:role is null or u.role = :role)")
    Set<User> find(@Param("name") String name, @Param("role") Role role);

    User findByEmailIgnoreCaseAndPassword(String email, String password);

}