package com.zgj.min3d.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by user on 2017/12/12.
 */
@Entity
@Table(name = "user", catalog = "vrm_web")
public class User implements java.io.Serializable {
    private Integer id;
    private Short userType;
    private String nickname;
    private String name;
    private String password;
    private Short sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private String remark;
    private Short isDelete;
    private Timestamp updateTime;
    private Timestamp deleteTime;
    private Timestamp createTime;

    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    private User() {
    }

    private User(Short isDelete, Timestamp createTime) {
        this.isDelete = isDelete;
        this.createTime = createTime;
    }

    /**
     * full constructor
     */
    public User(Short userType, String nickname, Short sex, String language, String city, String province, String country, String headimgurl, String unionid,
                String remark, Short isDelete, Timestamp updateTime, Timestamp deleteTime, Timestamp createTime, String password, String name, Set<UserRole> userRoles) {
        this.userType = userType;
        this.nickname = nickname;
        this.sex = sex;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.unionid = unionid;
        this.remark = remark;
        this.isDelete = isDelete;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.createTime = createTime;
        this.password = password;
        this.userRoles = userRoles;
        this.name = name;
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

    @Column(name = "user_type")
    public Short getUserType() {
        return this.userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    @Column(name = "nickname", length = 32)
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "sex")
    public Short getSex() {
        return this.sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    @Column(name = "language", length = 32)
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "city", length = 32)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "province", length = 32)
    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "country", length = 32)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "headimgurl", length = 256)
    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }


    @Column(name = "unionid", length = 32)
    public String getUnionid() {
        return this.unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Lob
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "is_delete", nullable = false)
    public Short getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(Short isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "update_time", length = 19)
    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "delete_time", length = 19)
    public Timestamp getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "password", nullable = false, length = 64)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Column(name = "name",nullable = false,length = 32)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
