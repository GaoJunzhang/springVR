package com.zgj.min3d.dao;

import com.zgj.min3d.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by user on 2017/12/12.
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value = " SELECT re.id,re.name,re.parentId,re.resUrl,re.icon FROM menu re LEFT JOIN role_menu rr  ON re.id = rr.resourcesId LEFT JOIN user_role ur ON rr.roleId =ur.roleId WHERE ur.userId=:userId   GROUP BY re.id ORDER BY re.sort ASC", nativeQuery = true)
    public List<Menu> findByUserId(@Param("userId") Integer userId);
}
