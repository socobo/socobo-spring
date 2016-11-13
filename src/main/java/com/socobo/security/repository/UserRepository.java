package com.socobo.security.repository;

import com.socobo.security.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by patrick on 05.11.16.
 */
@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    User findById(String id);

}
