package com.zgj.min3d.service;

import com.zgj.min3d.dao.UserRepository;
import com.zgj.min3d.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/12/12.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(String operatorId, String name, int page, int size, String sortType, String sortValue){
        String[] svs = sortValue.split(",");
        String[] sts = sortType.split(",");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        for (int i = 0; i < svs.length; i++) {
            Sort.Order order = new Sort.Order(Sort.Direction.fromString(sts[i]), svs[i]);
            orders.add(order);
        }
        Sort sort = new Sort(orders);
        Pageable pageable = new PageRequest(page, size, sort);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("isDelete"), (short) 0));
                if (name != null && !name.trim().isEmpty()) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + name + "%"));
                }
                return predicate;
            }

        };

        return userRepository.findAll(specification, pageable);
    }

    public User selectByUsername(String username){
        return userRepository.findByName(username);
    }

    public User selectByUserId(Integer userId){
        return userRepository.findOne(userId);
    }

    public Integer delUser(Long userId){
        return userRepository.deleteUser(userId);
    }

}
