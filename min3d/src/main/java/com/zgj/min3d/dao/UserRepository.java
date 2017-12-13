package com.zgj.min3d.dao;

import com.zgj.min3d.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2017/12/12.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Specification<User> specification, Pageable pageable);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User g set g.is_delete = 1, g.delete_time = now() where g.id = :id", nativeQuery = true)
    int deleteUser(@Param("id") Long id);

    @Query(value = "from User u where u.name =:name")
    User findByName(@Param("name") String name);
}
