package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAddress(String email);
    User findByName(String name);

}
