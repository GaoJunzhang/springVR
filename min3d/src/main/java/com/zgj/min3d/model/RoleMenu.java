package com.zgj.min3d.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by user on 2017/12/12.
 */
@Entity
@Table(name = "role_menu", catalog = "vrm_web")
public class RoleMenu implements java.io.Serializable{
    private Long id;
    private Role role;
    private Menu menu;
    public RoleMenu(){}
    public RoleMenu(Long id,Role role,Menu menu){
        this.id=id;
        this.role=role;
        this.menu=menu;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id",nullable = false)
    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
