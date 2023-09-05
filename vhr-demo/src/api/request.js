import instace from "../utils/http";


const request = {

    login(username, password) {
        return instace({
            method: 'post',
            url: '/api/doLogin',
            contentType: '"application/json"',
            data: {
                username: username,
                password: password
            }
        })
    },
    logout() {
        return instace({
            method: 'post',
            url: '/api/logout',
            contentType: '"application/json"'
        })
    },
    menuinit() {
        return instace({
            method: 'post',
            url: '/api/menu',
            contentType: '"application/json"'
        })
    },
    positionInit() {
        return instace({
            method: 'get',
            url: '/api/sys/basic/pos',
            contentType: '"application/json"'
        })
    },
    // 删除职位
    deletePositionById(id) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/deletePositionById',
            contentType: '"application/json"',
            data: {
                id: id
            }
        })
    },
    // 添加职位
    addPosition(name) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/addPosition',
            contentType: '"application/json"',
            data: {
                name: name
            }
        })
    },
    // 修改职位
    update(id, name) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/updatePosition',
            contentType: '"application/json"',
            data: {
                id: id,
                name: name
            }
        })
    },
    // 批量删除
    deleteIds(ids) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/deleteByIds',
            contentType: '"application/json"',
            data: {
                ids: ids,
            }
        })
    },
    // 查询所有的职称
    getAllJobLevel() {
        return instace({
                method: 'get',
                url: '/api/sys/basic/getAllJobLevel',
                contentType: '"application/json"',
            }
        )
    },
    // 添加职称
    addJobLevel(name, titleLevel, enabled) {
        return instace({
                method: 'post',
                url: '/api/sys/basic/addJobLevel',
                contentType: 'application/json',
                data: {
                    name: name,
                    titleLevel: titleLevel,
                    enabled: enabled,
                }
            }
        )
    },
    // 删除职称
    deleteJobLevel(id) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/deleteJobLevel',
            contentType: 'application/json',
            data: {
                id: id
            }
        })
    },
    // 更新职称
    updateJobLevel(id, name, titleLevel, enabled) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/updateJobLevel',
            contentType: 'application/json',
            data: {
                id: id,
                name: name,
                titleLevel: titleLevel,
                enabled: enabled,
            }
        })
    },
    // 批量删除
    deleteByJobLevelIds(ids) {
        return instace({
            method: 'post',
            url: '/api/sys/basic/deleteJobLevelByIds',
            contentType: '"application/json"',
            data: {
                ids: ids,
            }
        })
    },
    // 批量删除
    getAllRoles() {
        return instace({
            method: 'get',
            url: '/api/sys/permission/getAllRoles',
            contentType: '"application/json"',
        })
    },
    // 查询所有菜单,PostMana.vue使用
    getAllMenus() {
        return instace({
            method: 'get',
            url: '/api/menus/getAllMenus',
            contentType: '"application/json"',
        })
    },
    // 根据角色查询其可以访问的菜单
    getMenusByRole(ItemId) {
        return instace({
            method: 'post',
            url: '/api/menus/getMenusByRole',
            contentType: '"application/json"',
            data: {
                mid: ItemId,
            }
        })
    },
    // 更改不同角色可访问的menu菜单
    updateMenusByRole(rid, mids) {
        return instace({
            method: 'post',
            url: '/api/menus/updateMenusByRole',
            data: {
                rid: rid,
                mids: mids
            },
            contentType: '"application/json"',
        })
    },
    // 添加角色 PosMana.vue组件使用
    addRole(roleCN, roleEN) {
        return instace({
            method: 'post',
            url: '/api/menus/addRoles',
            data: {
                name: roleEN,
                nameZh: roleCN
            },
            contentType: '"application/json"',
        })
    },
    // 根据id删除角色
    deleteRoleById(id) {
        return instace({
            method: 'post',
            url: '/api/menus/deleteRolesById',
            contentType: '"application/json"',
            data: {
                id: id
            },
        })
    },
    // 部门管理组件获取所有部门
    getAllDepartmentByParentId() {
        return instace({
            method: 'get',
            url: '/api/sys/department/getAllDepartmentByParentId',
            contentType: '"application/json"',
        })
    },
    // 添加部门管理
    addDepartment(id, parent, name, depPath) {
        return instace({
            method: 'post',
            url: '/api/sys/department/addDepartment',
            contentType: '"application/json"',
            data: {
                id: id,
                name: name,
                parent: parent,
                depPath: depPath,
            }
        })
    },
    // 删除部门管理
    deleteDepartment(id) {
        return instace({
            method: 'post',
            url: '/api/sys/department/deleteDepartment',
            contentType: '"application/json"',
            data: {
                id: id,
            }
        })
    },
    // SysHr组件，获取所有的登录用户角色
    getAllHr(keyword) {
        return instace({
            method: 'post',
            url: '/api/sys/hr/getAllHr',
            contentType: '"application/json"',
            data:{
                keyword:keyword
            }
        })
    },
    // 修改hr用户的enabled
    updateEnabled(enabled, id) {
        return instace({
            method: 'post',
            url: '/api/sys/hr/updateEnabled',
            contentType: '"application/json"',
            data: {
                enabled: enabled,
                id: id
            }
        })
    },
    //   修改hr的角色
    updateHrRole(hrid,rids){
        return instace({
            method: 'post',
            url: '/api/sys/hr/updateHrRole',
            contentType: '"application/json"',
            data: {
                hrid:hrid,
                rids:rids
            }
        })
    },
    // 删除hr角色
    deleteHr(HrId){
        return instace({
            method: 'post',
            url: '/api/sys/hr/deleteHr',
            contentType: '"application/json"',
            data:{
                HrId:HrId
            }
        })
    },
    // EmpBasic查询所有员工
    initAllEmp(page,size,keyword){
        return instace({
            method: 'post',
            url: '/api/employee/basic/',
            contentType: '"application/json"',
            data: {
                page:page,
                size:size,
                keyword:keyword,
            }
        })
    },
    // EmpBasic 添加员工
    addEmp(emp){
        return instace({
            method: 'post',
            url: '/api/employee/basic/addEmp',
            headers: {
                'Content-Type': 'application/json'
            },
            data: emp
        })
    },
    // EmpBasic 删除员工
    deleteEmp(id){
        return instace({
            method:"delete",
            url: `/api/employee/basic/${id}`,
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },
    // 修改员工
    updateEmp(emp){
        return instace({
            method:"put",
            url: `/api/employee/basic/updateEmp`,
            headers: {
                'Content-Type': 'application/json'
            },
            data:emp
        })
    },
    // 下载Excel
    downExcel(){
        return instace({
            method:"get",
            url: `/api/employee/basic/downExcel`,
            headers: {
                'Content-Type': 'application/json'
            }
        })
    },
    // 高级搜索
    getEmpByPageAdvanch(size,page,emp){
        return instace({
            method:"post",
            url: `/api/employee/basic/Advanched`,
            headers: {
                'Content-Type': 'application/json'
            },
            data:{
                size:size,
                page:page,
                emp:emp,
            }
        })
    }

}

export default request;