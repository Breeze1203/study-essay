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
    positionInin(){
        return instace({
            method:'get',
            url:'/api/sys/basic/pos',
            contentType:'"application/json"'
        })
    }
}

export default request;