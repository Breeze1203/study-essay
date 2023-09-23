package org.example.mail;

import java.io.Serializable;

/**
 * @projectName: rocketmq
 * @package: org.example.mainserver
 * @className: User
 * @author: pt3548297839
 * @description: TODO
 * @date: 2023/9/22 12:57
 * @version: 1.0
 */
public class User implements Serializable {
    private String name;

    private String posName;
    private String joblevelName;
    private String departmentName;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String name, String posName, String joblevelName, String departmentName, String email) {
        this.name = name;
        this.posName = posName;
        this.joblevelName = joblevelName;
        this.departmentName = departmentName;
        this.email = email;
    }

    public User(String name, String posName, String joblevelName, String departmentName) {
        this.name = name;
        this.posName = posName;
        this.joblevelName = joblevelName;
        this.departmentName = departmentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getJoblevelName() {
        return joblevelName;
    }

    public void setJoblevelName(String joblevelName) {
        this.joblevelName = joblevelName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", posName='" + posName + '\'' +
                ", joblevelName='" + joblevelName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
