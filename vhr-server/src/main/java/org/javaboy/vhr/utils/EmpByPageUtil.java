package org.javaboy.vhr.utils;

import org.javaboy.vhr.bean.Employee;

/**
 * @projectName: vhr-server
 * @package: org.javaboy.vhr.utils
 * @className: EmpByPageUtil
 * @author: pt3548297839
 * @description: 进行分页查询的util
 * @date: 2023/9/5 11:14
 * @version: 1.0
 */
public class EmpByPageUtil {
    public String keyword;
    public Integer page;
    public Integer size;
    public Employee emp;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "EmpByPageUtil{" +
                "keyword='" + keyword + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", emp=" + emp +
                '}';
    }
}
