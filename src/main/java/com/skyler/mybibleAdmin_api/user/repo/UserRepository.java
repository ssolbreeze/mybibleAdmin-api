package com.skyler.mybibleAdmin_api.user.repo;

import com.skyler.mybibleAdmin_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
