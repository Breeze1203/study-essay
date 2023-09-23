package org.javaboy.vhr.utils;

import org.javaboy.vhr.bean.*;

import java.util.List;

public class EmpUtil {
    private List<Employee> employeeList;
    private long Total;

    // 政治面貌
    private List<Politicsstatus> politicsStatus;

    // 职位
    private List<Position> positions;

    // 职称
    private List<JObLevel> jObLevels;

    private List<Department> departments;

    public List<Nation> nations;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<JObLevel> getjObLevels() {
        return jObLevels;
    }

    public void setjObLevels(List<JObLevel> jObLevels) {
        this.jObLevels = jObLevels;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    // 所有民族

    public List<Nation> getNations() {
        return nations;
    }

    public void setNations(List<Nation> nations) {
        this.nations = nations;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public long getTotal() {
        return Total;
    }

    public void setTotal(long total) {
        Total = total;
    }

    public List<Politicsstatus> getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(List<Politicsstatus> politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    @Override
    public String toString() {
        return "EmpUtil{" +
                "employeeList=" + employeeList +
                ", Total=" + Total +
                ", politicsStatus=" + politicsStatus +
                ", positions=" + positions +
                ", jObLevels=" + jObLevels +
                ", departments=" + departments +
                ", nations=" + nations +
                '}';
    }
}
