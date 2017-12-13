package com.zgj.min3d.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by user on 2017/12/12.
 */
@Entity
@Table(name = "role", catalog = "vrm_web")
public class Role implements java.io.Serializable {
    private Integer id;
    private String roleDesc;
    private Set<RoleMenu> roleMenus = new HashSet<RoleMenu>(0);

    public Role() {
    }

    public Role(Integer id, String roleDesc, Set<RoleMenu> roleMenus) {
        this.id = id;
        this.roleDesc = roleDesc;
        this.roleMenus = roleMenus;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "role_desc", nullable = false, length = 32)
    public String getRoleDesc() {
        return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    public Set<RoleMenu> getRoleMenus() {
        return this.roleMenus;
    }

    public void setRoleMenus(Set<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
}
