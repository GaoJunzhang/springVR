package com.zgj.min3d.service;

import com.zgj.min3d.dao.MenuRepository;
import com.zgj.min3d.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017/12/12.
 */
@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public List<Menu> loadUserResources(Integer userId){
        return menuRepository.findByUserId(userId);
    }
}
