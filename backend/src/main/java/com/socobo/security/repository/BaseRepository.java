package com.socobo.security.repository;

import com.socobo.security.model.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by patrick on 06.11.16.
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID>{

    T save(T entity);

}
