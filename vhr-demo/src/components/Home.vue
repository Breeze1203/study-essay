<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">
        <div class="title">微人事</div>
        <el-dropdown class="dropdown" @command="commandHander">
          <span>{{ user.name }} <img :src="user.userface" class="image"/></span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
              <el-dropdown-item command="setting">设置</el-dropdown-item>
              <el-dropdown-item divided command="logout">注销登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu @select="handleOpen" v-for="(item,index) in routes">
            <el-sub-menu index="1">
              <template #title>
                <i :class="item.iconCls"></i>
                <span>{{ item.name }}</span>
              </template>
              <el-menu-item :index="child.path" v-for="(child,indexj) in item.children">{{ child.name }}</el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb v-if="this.$router.currentRoute.value.path!='/Home'">
            <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.value.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import {ElMessage, ElMessageBox} from 'element-plus';
import request from '@/api/request';
import { ArrowRight } from '@element-plus/icons-vue';

export default {
  name: 'Home',
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem('user')),
    }
  },
    computed: {
      routes() {
       return this.$store.state.routes
     }
   },
  methods: {
    handleOpen(index) {
      this.$router.push(index);
    },
    // 这里的参数是command选中的
    commandHander(message) {
      if (message == 'logout') {
        ElMessageBox.confirm(
            "此操作将注销登录，是否继续",
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning',
            }
        )
            .then(() => {
              // 注销登录 删除session 跳转到登录界面
              window.sessionStorage.removeItem("user");
              // 同时删除store里面的数据 初始化为一个空数组
              this.$store.commit('menu', [])
              request.logout().then(request => {
                console.log(request.data);
              });
              this.$router.replace("/");
            })
            .catch(() => {
              ElMessage({
                type: 'info',
                message: '已取消操作',
              })
            })
      }
    }
  }
}
</script>

<style>
.header {
  background-color: rgba(56, 150, 230, 0.87);
  display: flex;
  align-items: center;
  box-sizing: border-box;
  padding: 0px 15px;
  justify-content: space-between;
}

.title {
  font-size: 20px;
  font-family: Arial, Helvetica, sans-serif;
  color: #ffff;
}

.dropdown {
  cursor: pointer;
}

.image {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
}
</style>