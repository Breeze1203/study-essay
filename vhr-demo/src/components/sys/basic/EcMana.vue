<template>
  <div>
    <el-input
        v-model="pos.name"
        style="width: 350px;margin-right: 10px"
        placeholder="添加职位..."
        prefix-icon="Plus"
        size="default">
    </el-input>
    <el-button type="primary" size="default" icon="Plus" @click="addPosition">添加</el-button>
  </div>
  <div style="margin-top: 15px">
    <el-table :data="pos.tableData" @selection-change="handleSelectionChange" border stripe style="width: 80%">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="编号" width="180"/>
      <el-table-column prop="name" label="职位" width="180"/>
      <el-table-column prop="createDate" label="创建时间"/>
      <!--      理解scope-->
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
  <div style="margin-top: 15px">
    <el-button
        @click="deleteIds"
        size="small"
        type="danger">批量删除
    </el-button>
  </div>
  <div>
    <el-dialog
        v-model="pos.dialogShow"
        title="修改职位"
        width="40%">
      <span>
        <el-tag size="large">修改职位</el-tag>
        <el-input size="default" style="width: 300px;margin-left: 15px" v-model="pos.tagPosition"></el-input>
      </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="CloseDialog">取消</el-button>
        <el-button type="primary" @click="updatePosition">确认</el-button>
      </span>
      </template>
    </el-dialog>
  </div>


</template>

<script>
import request from '@/api/request';
import {ElMessage, ElMessageBox} from 'element-plus';

export default {
  name: "EcMana",
  data() {
    return {
      pos: {
        name: "",
        tableData: [],
        dialogShow: false,
        tagPosition: '',
        id: '',
        // 批量删除的ids
        ids: []
      }
    }
  },
  // 表格里面的数据当组件初始化时就执行，所有在vue生命周期方法mounted里面执行
  mounted() {
    request.positionInit().then(res => {
      if (res) {
        this.pos.tableData = res.data;
      }
    })
  },
  methods: {
    // 添加职位
    addPosition() {
      if (this.pos.name) {
        request.addPosition(this.pos.name).then(res => {
          if (res) {
            request.positionInit().then(res => {
              if (res) {
                this.pos.tableData = res.data;
              }
            })
          }
        })
        this.pos.name = '';
      } else {
        ElMessage({
          showClose: true,
          message: '请输入要添加的职位',
          type: 'error',
        })
      }
    }
    ,
    // 删除职位
    handleDelete(index, data) {
      ElMessageBox.confirm(
          '此操作将删除职位【' + data.name + '】,是否继续?',
          {
            distinguishCancelAndClose: true,
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }
      )
          .then(() => {
            request.deletePositionById(data.id).then(res => {
              if (res) {
                request.positionInit().then(res => {
                  if (res) {
                    this.pos.tableData = res.data;
                  }
                })
              }
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '已取消操作'
            })
          })
    },
    // 修改职位
    handleEdit(index, data) {
      this.pos.tagPosition = data.name;
      this.pos.id = data.id;
      this.pos.dialogShow = true;
    },
    CloseDialog() {
      this.pos.dialogShow = false;
    },
    updatePosition() {
      request.update(this.pos.id, this.pos.tagPosition).then(res => {
        if (res) {
          if (res) {
            request.positionInit().then(res => {
              if (res) {
                this.pos.tableData = res.data;
                this.pos.dialogShow = false;
              }
            })
          }
        }
      })
    },
    // 批量删除 有个回调函数参数值是选中那行表格数据
    handleSelectionChange(val) {
      this.pos.ids = [];
      for (let i = 0; i < val.length; i++) {
        this.pos.ids.push(val[i].id);
      }
    }
    ,
    deleteIds() {
      if (this.pos.ids.length >= 1) {
        ElMessageBox.confirm(
            '此操作将删除' + this.pos.ids.length + "条数据",
            {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning',
            }
        )
            .then(() => {
              request.deleteIds(this.pos.ids).then(res => {
                if (res) {
                  request.positionInit().then(res => {
                    this.pos.tableData = res.data;
                  })
                }
              })
            })
            .catch(() => {
              ElMessage({
                type: 'info',
                message: '取消操作',
              })
            })
      }
    }
  }
}
</script>

<style scoped>

</style>