import instace from "../utils/http";
import base from "./path";


const request={

    login(username,password){
        return instace({
            method:'post',
            url:base.baseUrl+'/doLogin',
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
            url:base.baseUrl+'/logout',
            contentType:'"application/json"'
        })
    },
    menuinit(){
        return instace({
            method:'post',
            url:base.baseUrl+'/menu',
            contentType:'"application/json"'
        })
    }
}

export default request;