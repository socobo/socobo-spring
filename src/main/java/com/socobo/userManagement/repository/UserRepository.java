package com.socobo.userManagement.repository;

import com.socobo.userManagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by patrick on 05.11.16.
 */
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long>{
}
