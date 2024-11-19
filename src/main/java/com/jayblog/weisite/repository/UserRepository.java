package com.jayblog.weisite.repository;

import com.jayblog.weisite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
