<template>
  <div>
    <el-input v-model="this.roleEN" style="width: 35%" placeholder="请输入角色英文名">
      <template #prepend>ROLE_</template>
    </el-input>
    <el-input v-model="this.roleCN" placeholder="请输入角色中文名"
              style="width: 35%;margin-left: 7px"></el-input>
    <el-button type="primary" style="margin-left: 7px" icon="Plus">添加角色</el-button>
  </div>
  <div style="margin-top: 15px">
    <!-- 这里是遍历所有角色 里面有各种角色信息-->
    <el-collapse accordion v-for="(r,index) in this.roles" :key="index" @change="changeItem">
      <el-collapse-item :title="r.nameZh" :name="r.id">
        <div>
          <el-card>
            <template #header>
              <div>
                <span>可访问的资源</span>
                <el-button size="large" style="margin-left:85% ;color: #ff1515" type="text">
                  <el-icon size="20">
                    <Delete/>
                  </el-icon>
                </el-button>
              </div>
            </template>
            <div>
              <el-tree show-checkbox node-key="id"
                       :default-checked-keys="this.menusByRole" :data="this.menuData"
                       :props="this.defaultProps"/>
            </div>
          </el-card>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import request from "@/api/request";

export default {
  name: "PosMana",
  data() {
    return {
      roleEN: '',
      roleCN: '',
      roles: [],
      // 这里是所有路由
      menuData: [],
      menusByRole: [],
      defaultProps: {
        children: 'children',
        label: 'name',
      }
    }
  },
  mounted() {
    request.getAllRoles().then(res => {
      if (res) {
        this.roles = res.data;
      }
    });
  }
  ,
  methods: {
    changeItem(mid) {
      if (mid) {
        console.log(mid);
        this.menusByRole = [];
        request.getAllMenus().then(res => {
          if (res) {
            this.menuData = res.data;
          }
        });
        request.getMenusByRole(mid).then(res => {
          if (res) {
            this.menusByRole = res.data;
          }
        });
      }
    }
  }
}
</script>

<style scoped>

</style>