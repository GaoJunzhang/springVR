package com.zgj.min3d.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by user on 2017/12/12.
 */
@Entity
@Table(name = "meun", catalog = "vrm_web")
public class Menu implements java.io.Serializable{
    private Integer id;
    private String name;
    private String menuUrl;
    private Short type;
    private Integer parentId;
    private Integer sort;
    private Short enable;
    private String icon;

    private Set<RoleMenu> roleMenu = new HashSet<RoleMenu>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name",length = 32,nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "menu_url",length = 256,nullable = false)
    public String getMenuUrl() {
        return this.menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    @Column(name = "type")
    public Short getType() {
        return this.type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Column(name = "parent_id",nullable = false)
    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "enable")
    public Short getEnable() {
        return this.enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    @Column(name = "icon",length = 256)
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu")
    public Set<RoleMenu> getRoleMenu() {
        return this.roleMenu;
    }

    public void setRoleMenu(Set<RoleMenu> roleMenu) {
        this.roleMenu = roleMenu;
    }
}
