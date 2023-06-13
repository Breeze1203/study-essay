<template>
  <div style="display: flex;justify-content: center;margin-top: 20px">
    <el-input v-model="HrName" placeholder="通过用户名搜索用户" prefix-icon="Search"
              size="small" style="width: 400px;margin-right: 8px" @keydown.enter.native="selectHr"></el-input>
    <el-button icon="Search" size="small" type="primary" @click="selectHr">搜索</el-button>
  </div>
  <div style="margin: 20px;display: flex; justify-content: space-around;flex-wrap: wrap">
    <el-card v-for="(hr,index) in HrData" :key="index" style="width: 300px;margin-right: 10px;margin-top: 25px">
      <template #header>
        <div>
          <span>{{ hr.name }}</span>
          <el-button icon="Delete" style="float: right;padding:3px 0;color: #ff0000" type="text"
                     @click="deleteHr(hr)"></el-button>
        </div>
      </template>
      <div style="text-align: center">
        <el-image :src="hr.userface" style="width: 80px; height: 80px;margin: 0 auto;"/>
      </div>
      <div class="UserInformation">
        <el-row>
          <span>用户名:{{ hr.name }}</span>
        </el-row>
        <el-row>
          <span>手机号码:{{ hr.phone }}</span>
        </el-row>
        <el-row>
          <span>电话号码:{{ hr.telephone }}</span>
        </el-row>
        <el-row>
          <span>地址:{{ hr.address }}</span>
        </el-row>
        <el-row>
          <span>用户状态:<el-switch
              v-model="hr.enabled"
              active-text="启用"
              inactive-text="禁用"
              size="small"
              style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
              @change="updateEnable(hr)"
          /></span>
        </el-row>
        <el-row>
          <span>用户角色:
            <el-tag v-for="(role,index) in hr.roles" :key="index" style="margin-right: 6px">{{ role.nameZh }}</el-tag>
            <el-button icon="MoreFilled" type="text" @click="UpdateHrRole(hr)"/>
          </span>
        </el-row>
        <el-row>
          <span>备注:{{ hr.remark }}</span>
        </el-row>
      </div>
    </el-card>
  </div>
  <div>
    <el-dialog
        v-model="dialogVisible"
        title="修改用户角色"
        width="25%"
    >
      <span>
      <el-select
          v-model="this.selectRole"
          multiple
          placeholder="请选择用户角色"
          size="small"
          style="width: 240px"
      >
        <el-option
            v-for="(role,index) in this.allRoles"
            :key="index"
            :label="role.nameZh"
            :value="role.id"
        />
      </el-select>
      </span>
      <el-row style="margin-top: 20px">
        <span style="float: right;justify-content: flex-end;">
        <el-button size="small" @click="cancel">取消</el-button>
        <el-button size="small" style="margin-left: 30px" type="primary" @click="sure()">确定</el-button>
      </span>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/api/request";
import {ElMessage, ElMessageBox} from "element-plus";


export default {
  data() {
    return {
      HrName: '',
      HrId: '',
      HrData: [],
      searchHr: [],
      allRoles: [],
      selectRole: [],
      dialogVisible: false
    }
  },
  mounted() {
    this.initHrData();
    this.initAllRoles();
  },
  watch: {
    HrName: 'filterHrName'
  },
  methods: {
    initHrData() {
      request.getAllHr(this.HrName).then(res => {
        if (res) {
          this.HrData = res.data;
        }
      })
    }
    ,
    initAllRoles() {
      request.getAllRoles().then(res => {
        if (res) {
          this.allRoles = res.data;
        }
      })
    }
    ,
    selectHr() {
      if (this.HrName) {
        request.getAllHr(this.HrName).then(res => {
          if (res.data) {
            this.HrData = res.data;
          }
        })
      } else {
        ElMessage.error("请输入要搜索的用户名");
      }
    },
    updateEnable(hr) {
      request.updateEnabled(hr.enabled, hr.id).then(res => {
        if (res.data.message === '更新成功') {
          this.initHrData();
        }
      })
    },
    // 修改用户角色
    UpdateHrRole(hr) {
      this.HrId = hr.id;
      // 将之前选中的角色清除掉
      this.selectRole = [];
      this.dialogVisible = true;
      // 已有的角色名称加入其中
      hr.roles.forEach(r => {
        this.selectRole.push(r.id);
      });
    },
    // hrid是用户id,rid是角色id
    sure() {
      request.updateHrRole(this.HrId, this.selectRole).then(res => {
        if (res.data.message === '修改成功') {
          this.initHrData();
          this.dialogVisible = false;
        }
      })
    },
    // 删除用户
    deleteHr(hr) {
      ElMessageBox.confirm(
          '此操作将删除【' + hr.name + '】用户',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
                request.deleteHr(hr.id).then(res => {
                  if (res.data.message === '删除成功') {
                    this.initHrData();
                  }
                })
              }
          )
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '删除失败',
            })
          })
    },
    cancel() {
      this.dialogVisible = false;
    }
    ,
    filterHrName(value) {
      if (!value) {
        this.initHrData();
      }
    }
  }
}
</script>

<style scoped>
.UserInformation {
  font-size: 13px;
  color: #1bd5ff;
}
</style>