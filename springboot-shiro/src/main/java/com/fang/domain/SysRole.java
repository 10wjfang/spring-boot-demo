package com.fang.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: fwj
 * @Description:
 * @Date: Created in 2018/11/6 13:55
 * @Modified by:
 */
@Entity
public class SysRole implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<UserInfo> userInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

}
