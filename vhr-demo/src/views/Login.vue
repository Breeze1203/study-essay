<template>
    <div>
        <!-- 通过ref可以获取到整个表单的对象 -->
        <el-form :rules="rules" ref="LoginForm" :model="LoginForm" class="Logincontainer">
            <h1 class="LoginTitle">系统登录</h1>
            <el-form-item prop="username">
                <!-- auto-complete="off" 是否自动补全 -->
                <el-input type="text" v-model="LoginForm.username" auto-complete="off" placeholder="请输入用户名">
                </el-input>
            </el-form-item>
            <el-form-item prop="password"><el-input type="password" v-model="LoginForm.password" auto-complete="off"
                    placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item class="loginremember">
                <el-checkbox label="记住密码" v-model="check"></el-checkbox>
            </el-form-item>
            <el-form-item><el-button type="primary" class="loginbutton" @click="submit">登录</el-button></el-form-item>
        </el-form>
    </div>
</template>

<script>
import request from '@/api/request';

export default {
    name: 'login',
    data() {
        return {
            LoginForm: {
                username: 'admin',
                password: 123
            },
            check: true,
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            }

        }
    },
    methods: {
        submit() {
            this.$refs.LoginForm.validate((valid) => {
                if (valid) {
                   request.login(this.LoginForm.username,this.LoginForm.password).then(success=>{
                    if(success){
                        console.log(success.data);
                        // 登录成功将用户信息存到session中 将对象类型转为字符串
                        window.sessionStorage.setItem("user",JSON.stringify(success.data.object));
                        this.$router.replace('/Home')

                    }
                   })
                } else {
                    this.$message.error('请输入所有字段')
                    return false;
                }
            });
        },
    }
}
</script>

<style scoped>
.Logincontainer {
    border-radius: 1px;
    background: rgba(235, 227, 227, 0.292);
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    border: 1px solid rgb(172, 159, 159);
    box-shadow: 0 0 25px #efefef;
}

.LoginTitle {
    color: rgb(169, 145, 145);
    text-align: center;
    margin: 16px auto 20px auto;
}

.loginremember {
    text-align: left;
    margin: 0 0 15px 0;
}

.loginbutton {
    width: 100%;
}
</style>