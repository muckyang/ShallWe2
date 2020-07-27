
package com.web.blog.dao;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, String> {
    User getUserById(String id);
    User getUserByEmail(String email);
    User getUserByNickname(String nickname);
    Optional<User> findUserByIdAndPassword(String id, String password);
}
