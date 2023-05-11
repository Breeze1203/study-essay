<template>
  <div>
    <el-input placeholder="添加职位" v-model="name.jobLeveL.name" prefix-icon="Plus" style="width: 350px"></el-input>
    <!--这里el-select必须有v-model属性，可以这样想选中的el-option后将它的值复制给v-model-->
    <el-select v-model="name.jobLeveL.titleLevel" placeholder="请选择职位等级" size="default"
               style="margin-left: 12px;width:15%">
      <el-option
          v-for="item in options"
          :key="item"
          :label="item"
          :value="item"
      />
    </el-select>
    <el-button size="default" type="primary" style="margin-left: 12px;width: 8%" @click="addJobLevel">添加</el-button>
  </div>
  <div style="margin-top: 20px">
    <el-table
        :data="name.tableData"
        style="width: 80%"
        border
        stripe
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column prop="name" label="职称名称" width="130"/>
      <el-table-column prop="titleLevel" label="职称等级" width="100"/>
      <el-table-column prop="createDate" label="创建时间" width="200"/>
      <el-table-column label="状态" width="100">
        <template #default="scope">
          <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
          <el-tag type="danger" v-else="scope.row.enabled">已禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
          >Edit
          </el-button
          >
          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
          >Delete
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div>
    <el-dialog
        v-model="this.name.dialogVisible"
        title="修改职称"
        width="30%"
    >
      <span>
        <el-row>
        <el-tag size="large" style="width: 80px">职称名</el-tag>
        <el-input size="default" v-model="name.inputJobLevel" style="width: 50%;margin-left: 20px"></el-input>
        </el-row>
        <el-row style="margin-top: 10px">
          <el-tag size="large" style="width: 80px">职称等级</el-tag>
          <el-select v-model="name.jobLeveL.title" placeholder="请选择职位等级" style="margin-left: 20px;width: 50%"
                     size="default">
    <el-option
        v-for="item in options"
        :key="item"
        :label="item"
        :value="item"
    />
          </el-select>
          <el-row style="margin-top: 10px">
            <el-tag size="large" style="width: 80px">是否禁用</el-tag>
            <el-switch v-model="value" style="margin-left: 20px" active-text="开启" inactive-text="禁用"/>
          </el-row>
        </el-row>
      </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="sure">确定</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
  <div style="margin-top: 10px">
    <!--    :disabled="this.disabled" 给按钮设置状态-->
    <el-button @click="deleteIds" type="danger">批量删除</el-button>
  </div>
</template>

<script>
import request from "@/api/request";
import {ElMessage, ElMessageBox} from 'element-plus'

export default {
  name: "JobMana",
  data() {
    return {
      name: {
        tableData: [],
        jobLeveL: {
          name: '',
          titleLevel: '',
          enabled: true
        },
        dialogVisible: false,
        inputJobLevel: ''
      },
      options: [
        '正高级',
        '副高级',
        '中级',
        '初级',
        '员级'],
      value: true,
      id: '',
      ids: [],
    }
  },
  mounted() {
    request.getAllJobLevel().then(res => {
      if (res) {
        this.name.tableData = res.data;
      }
    })
  },
  methods: {
    handleEdit(index, data) {
      this.name.dialogVisible = true;
      this.name.inputJobLevel = data.name;
      this.name.jobLeveL.title = data.titleLevel;
      this.id = data.id
    },
    addJobLevel() {
      if (this.name.jobLeveL.name === '' || this.name.jobLeveL.titleLevel === '') {
        ElMessage.error('请选择要添加的职位或等级');
      } else {
        request.addJobLevel(this.name.jobLeveL.name, this.name.jobLeveL.titleLevel, this.name.jobLeveL.enabled).then(res => {
          if (res) {
            request.getAllJobLevel().then(res => {
              if (res) {
                this.name.tableData = res.data;
              }
            })
            this.name.jobLeveL.name = '';
            this.name.jobLeveL.titleLevel = '';
          }
        })
      }
    },
    handleDelete(index, data) {
      ElMessageBox.confirm(
          '是否删除【' + data.name + '】这个职称',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
            request.deleteJobLevel(data.id).then(res => {
              if (res) {
                request.getAllJobLevel().then(res => {
                  if (res) {
                    this.name.tableData = res.data;
                  }
                })
              }
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '操作取消',
            })
          })
    },
    cancel() {
      this.name.dialogVisible = false;
    },
    sure() {
      // 跟新职称
      request.updateJobLevel(this.id, this.name.inputJobLevel, this.name.jobLeveL.title, this.value).then(res => {
        if (res) {
          this.name.dialogVisible = false;
          request.getAllJobLevel().then(res => {
            if (res) {
              this.name.tableData = res.data;
            }
          })
        }
      })
    },
    // 批量删除 这个value是选中的ids
    handleSelectionChange(value) {
      this.ids = [];
      for (let i = 0; i < value.length; i++) {
        this.ids.push(value[i].id);
      }
    },
    deleteIds() {
      if (this.ids.length === 0) {
        ElMessage.error('请选择要删除的职位');
      } else {
        request.deleteByJobLevelIds(this.ids).then(res => {
          if (res) {
            this.ids=[];
            request.getAllJobLevel().then(res => {
              if (res) {
                this.name.tableData = res.data;
              }
            })
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>