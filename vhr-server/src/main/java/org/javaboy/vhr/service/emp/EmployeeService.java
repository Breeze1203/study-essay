package org.javaboy.vhr.service.emp;

import jakarta.annotation.Resource;
import org.javaboy.vhr.bean.*;
import org.javaboy.vhr.mapper.*;
import org.javaboy.vhr.utils.EmpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "EmployeeService")
public class EmployeeService {
    @Resource(name = "EmployeeMapper")
    EmployeeMapper employeeMapper;

    // 这个是政治面貌
    @Resource(name = "PoliticsStatusMapper")
    PoliticsstatusMapper politicsstatusMapper;

    // 所属名族
    @Resource(name = "NationMapper")
    NationMapper nationMapper;

    // 查询所有职位
    @Resource(name = "PositionMapper")
    PositionMapper positionMapper;

    // 所有职称
    @Resource(name = "JobLevelMapper")
    JObLevelMapper jObLevelMapper;

    // 所有部门
    @Resource(name = "DepartmentMapper")
    DepartmentMapper departmentMapper;

    // 分页查询
    public EmpUtil getEmployeeByPage(Integer size, Integer page, String keyword) {
        if (size != null && page != null) {
            page = (page - 1) * size;
        }
        // 分页查询
        List<Employee> employees = employeeMapper.SelectEmpByPage(size, page, keyword);
        // 查询总条数
        long empCount = employeeMapper.getEmpCount(keyword);
        // 查询所有政治面貌
        List<Politicsstatus> politicsstatuses = politicsstatusMapper.AllPoliticsStatus();
        // 查询所有民族
        List<Nation> nations = nationMapper.AllNation();
        // 查询所有职位
        List<Position> allPosition = positionMapper.getAllPosition();
        // 查询所有职称
        List<JObLevel> allJobLevel = jObLevelMapper.getAllJobLevel();
        // 查询所有部门
        List<Department> departments = departmentMapper.allDepartment();
        EmpUtil empUtil = new EmpUtil();
        empUtil.setEmployeeList(employees);
        empUtil.setTotal(empCount);
        empUtil.setPoliticsStatus(politicsstatuses);
        empUtil.setPositions(allPosition);
        empUtil.setNations(nations);
        empUtil.setjObLevels(allJobLevel);
        empUtil.setDepartments(departments);
        return empUtil;
    }

    // 添加员工
    public Integer addEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    // 删除员工
    public Integer deleteEmp(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    // 修改员工
    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }

    //    这个是先查询出所有的politicsstatuses,nations,allPosition,allJobLevel,departments
    public EmpUtil getAllUtil() {
        // 查询所有政治面貌
        List<Politicsstatus> politicsstatuses = politicsstatusMapper.AllPoliticsStatus();
        // 查询所有民族
        List<Nation> nations = nationMapper.AllNation();
        // 查询所有职位
        List<Position> allPosition = positionMapper.getAllPosition();
        // 查询所有职称
        List<JObLevel> allJobLevel = jObLevelMapper.getAllJobLevel();
        // 查询所有部门
        List<Department> departments = departmentMapper.allDepartment();
        EmpUtil empUtil = new EmpUtil();
        empUtil.setPoliticsStatus(politicsstatuses);
        empUtil.setPositions(allPosition);
        empUtil.setNations(nations);
        empUtil.setjObLevels(allJobLevel);
        empUtil.setDepartments(departments);
        return empUtil;
    }

    // 集合插入
    public int insertByList(List<Employee> employees) {
        return employeeMapper.insertByList(employees);
    }


    // 高级搜索
    public EmpUtil getEmployeeByPageAdvanch(Integer size, Integer page,Employee employee) {
        if (size != null && page != null) {
            page = (page - 1) * size;
        }
        // 分页查询
        List<Employee> employees = employeeMapper.SelectEmpByPageAdvanced(size,page,employee);
        // 查询总条数
        long empCount = employeeMapper.getEmpCountAdvanch(employee);
        // 查询所有政治面貌
        List<Politicsstatus> politicsstatuses = politicsstatusMapper.AllPoliticsStatus();
        // 查询所有民族
        List<Nation> nations = nationMapper.AllNation();
        // 查询所有职位
        List<Position> allPosition = positionMapper.getAllPosition();
        // 查询所有职称
        List<JObLevel> allJobLevel = jObLevelMapper.getAllJobLevel();
        // 查询所有部门
        List<Department> departments = departmentMapper.allDepartment();
        EmpUtil empUtil = new EmpUtil();
        empUtil.setEmployeeList(employees);
        empUtil.setTotal(empCount);
        empUtil.setPoliticsStatus(politicsstatuses);
        empUtil.setPositions(allPosition);
        empUtil.setNations(nations);
        empUtil.setjObLevels(allJobLevel);
        empUtil.setDepartments(departments);
        return empUtil;
    }

    // 根据employee的id进行批量删除

    public Long deleteEmployeeByIds(Integer[] ids){
        return employeeMapper.deleteEmployeeByIds(ids);
    }
}
