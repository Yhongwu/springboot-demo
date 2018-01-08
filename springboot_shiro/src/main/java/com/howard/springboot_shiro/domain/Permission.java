package com.howard.springboot_shiro.domain;

import java.io.Serializable;
import java.util.Set;

public class Permission implements Serializable {

    private static final long serialVersionUID = 3241757478188988088L;

    private Integer pid;
    private String pname;
    private Set<Role> roles;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", roles=" + roles +
                '}';
    }
}
