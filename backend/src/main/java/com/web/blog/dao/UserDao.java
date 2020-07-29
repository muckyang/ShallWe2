
package com.web.blog.dao;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, String> {

    User getUserByEmail(String email);
    Optional<User> getUserByNickname(String nickname);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
