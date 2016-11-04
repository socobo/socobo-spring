package com.socobo.userManagement.repository;

import com.socobo.userManagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by patrick on 05.11.16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
}
