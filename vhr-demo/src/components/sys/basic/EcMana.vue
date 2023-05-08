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
    <el-table :data="pos.tableData" border stripe style="width: 80%">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="id" label="编号" width="180"/>
      <el-table-column prop="name" label="职位" width="180"/>
      <el-table-column prop="createDate" label="创建时间"/>
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
        tableData: []
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
    addPosition(){
      if(this.pos.name){
        request.addPosition(this.pos.name).then(res=>{
          if(res){
            request.positionInit().then(res => {
              if (res) {
                this.pos.tableData = res.data;
              }
            })
          }
        })
        this.pos.name='';
      }else {
        ElMessage({
          showClose: true,
          message: '请输入要添加的职位',
          type: 'error',
        })
      }
    }
    ,
    handleDelete(index,data){
      ElMessageBox.confirm(
          '此操作将删除职位'+data.name+',是否继续?',
          {
            distinguishCancelAndClose: true,
            confirmButtonText: '确定',
            cancelButtonText: '取消',
          }
      )
          .then(() => {
            request.deletePositionById(data.id).then(res=>{
              if(res){
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
              message:'已取消操作'
            })
          })
    }
}
}
</script>

<style scoped>

</style>