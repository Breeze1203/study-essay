import instace from "../utils/http";


const request={

    login(username,password){
        return instace({
            method:'post',
            url:'/api/doLogin',
            contentType:'"application/json"',
            data:{
                username:username,
                password:password
            }
        })
    },
    logout(){
        return instace({
            method:'post',
            url:'/api/logout',
            contentType:'"application/json"'
        })
    },
    menuinit(){
        return instace({
            method:'post',
            url:'/api/menu',
            contentType:'"application/json"'
        })
    },
    positionInit(){
        return instace({
            method:'get',
            url:'/api/sys/basic/pos',
            contentType:'"application/json"'
        })
    },
    // 删除职位
    deletePositionById(id){
        return instace({
            method:'post',
            url:'/api/sys/basic/deletePositionById',
            contentType:'"application/json"',
            data:{
                id:id
            }
        })
    },
    // 添加职位
    addPosition(name) {
      return instace({
          method:'post',
          url:'/api/sys/basic/addPosition',
          contentType:'"application/json"',
          data:{
              name:name
          }
      })
    },
    // 修改职位
    update(id,name) {
        return instace({
            method:'post',
            url:'/api/sys/basic/updatePosition',
            contentType:'"application/json"',
            data:{
                id:id,
                name:name
            }
        })
    }
}

export default request;