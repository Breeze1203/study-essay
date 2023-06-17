<template>
  <div>
    <el-input v-model="this.roleEN" style="width: 35%" placeholder="请输入角色英文名">
      <template #prepend>ROLE_</template>
    </el-input>
    <el-input v-model="this.roleCN" placeholder="请输入角色中文名"
              style="width: 35%;margin-left: 7px"></el-input>
    <el-button type="primary" style="margin-left: 7px" icon="Plus" @click="addRole">添加角色</el-button>
  </div>
  <div style="margin-top: 15px">
    <!-- 这里是遍历所有角色 里面有各种角色信息-->
    <el-collapse accordion v-for="(r,index) in this.roles" :key="index" v-model="activeNames" @change="changeItem">
      <el-collapse-item :title="r.nameZh" :name="r.id">
        <div>
          <el-card>
            <template #header>
              <div>
                <span>可访问的资源</span>
                <el-button size="large" style="margin-left:85% ;color: #ff1515" type="text" @click="deleteRoleById(r)">
                  <el-icon size="20">
                    <Delete/>
                  </el-icon>
                </el-button>
              </div>
            </template>
            <div>
              <el-tree show-checkbox node-key="id" ref="tree"
                       :default-checked-keys="this.menusByRole" :data="this.menuData"
                       :props="this.defaultProps"/>
            </div>
            <div class="button-group">
              <el-button class="button" @click="cancel">取消修改</el-button>
              <el-button class="button" type="primary" @click="changeRolesMenus(r.id,index)">确认修改</el-button>
            </div>
          </el-card>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import request from "@/api/request";
import {ElMessage, ElMessageBox} from "element-plus";

export default {
  name: "PosMana",
  data() {
    return {
      roleEN: '',
      roleCN: '',
      roles: [],
      activeNames: -1,
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
    this.initRoles();
  }
  ,
  methods: {
    initRoles() {
      request.getAllRoles().then(res => {
        if (res) {
          this.roles = res.data;
        }
      });
    }
    ,
    changeItem(mid) {
      request.getAllMenus().then(res => {
        if (res) {
          this.menuData = res.data;
        }
      });
      if (mid) {
        this.menusByRole = [];
        request.getMenusByRole(mid).then(res => {
          if (res) {
            this.menusByRole = res.data;
          }
        });
      }
    },
    changeRolesMenus(rid, index) {
      if (this.$refs.tree[index].getCheckedKeys().length === 0) {
        ElMessage.error('请选择可访问的资源')
        return;
      }
      //通过ref属性获取到这个对象,通过这个对象调用他的方法，然而这个tree有多个,类似与数组，所以要有下标
      // getCheckedKeys(true)参数为true获取子节点数
      request.updateMenusByRole(rid, this.$refs.tree[index].getCheckedKeys(true)).then(res => {
        if (res) {
          this.activeNames = -1;
        }
      })
    }
    ,
    cancel() {
      this.activeNames = -1;
    },
    addRole() {
      if (this.roleCN.trim() !== '' && this.roleEN.trim() !== '') {
        request.addRole(this.roleCN.trim(), this.roleEN.trim()).then(res => {
          if (res) {
            this.initRoles();
            this.roleCN = '';
            this.roleEN = '';
          }
        })
      } else {
        ElMessage.error('请输入要添加的角色');
      }
    },
    deleteRoleById(r) {
      ElMessageBox.confirm(
          '此操作将删除【'+r.nameZh+'】职位,是否继续',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            request.deleteRoleById(r.id).then(res=>{
              if(res){
                this.initRoles();
              }
            });
            console.log(r.id);
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '操作取消',
            })
          })
    },
  }
}
</script>

<style scoped>
.button-group {
  display: flex;
  justify-content: flex-end;
}

.button {
  margin-left: 10px;
}
</style>