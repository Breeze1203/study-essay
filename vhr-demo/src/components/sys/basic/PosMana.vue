<template>
  <div>
    <el-input v-model="roleEN" style="width: 35%" placeholder="请输入角色英文名">
      <template #prepend>ROLE_</template>
    </el-input>
    <el-input v-model="roleCN" placeholder="请输入角色中文名" style="width: 35%;margin-left: 7px"></el-input>
    <el-button type="primary" style="margin-left: 7px" icon="Plus" @click="addRole">添加角色</el-button>
  </div>
  <div style="margin-top: 15px">
    <el-collapse accordion v-for="(r,index) in roles" :key="index">
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
            <div></div>
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
      roles: []
    }
  },
  mounted() {
    request.getAllRoles().then(res => {
      if (res) {
        this.roles = res.data;
      }
    })
  },
  methods: {
    addRole() {
    }
  }
}
</script>

<style scoped>

</style>