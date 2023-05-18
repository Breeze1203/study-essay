import instace from "../utils/http";
import {formToJSON} from "axios";


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
    addJobLevel(name,titleLevel,enabled) {
        return instace({
                method: 'post',
                url: '/api/sys/basic/addJobLevel',
                contentType: 'application/json',
                data:{
                    name:name,
                    titleLevel:titleLevel,
                    enabled:enabled,
                }
            }
        )
    },
    // 删除职称
    deleteJobLevel(id){
        return instace({
            method:'post',
            url:'/api/sys/basic/deleteJobLevel',
            contentType: 'application/json',
            data:{
                id:id
            }
        })
    },
    // 更新职称
    updateJobLevel(id,name,titleLevel,enabled){
        return instace({
            method:'post',
            url:'/api/sys/basic/updateJobLevel',
            contentType: 'application/json',
            data:{
                id:id,
                name:name,
                titleLevel:titleLevel,
                enabled:enabled,
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
    }
}

export default request;