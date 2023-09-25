<template>
  <div style="margin-top: 25px">
    <el-button icon="Plus" type="primary" style="width: 150px;">添加工资套账</el-button>
    <el-button icon="Refresh" style="width: 130px;margin-left: 40px" type="success">刷新</el-button>
  </div>
  <div style="margin-top: 20px">
    <el-table :data="salaryData" border size="small" style="width: 85%">
      <el-table-column prop="name" label="账套名称" width="130" align="center"/>
      <el-table-column prop="basicSalary" label="基本工资" width="100" align="center"/>
      <el-table-column prop="trafficSalary" label="交通补助" width="100" align="center"/>
      <el-table-column prop="lunchSalary" label="午餐补助" width="100" align="center"/>
      <el-table-column prop="bouns" label="奖金" width="100" align="center"/>
      <el-table-column prop="createDate" label="启用时间" width="100" align="center"/>
      <el-table-column prop="basicSalary" label="基本工资" width="100" align="center"/>
      <el-table-column label="养老金" width="100" align="center">
        <el-table-column prop="pensionPer" label="比率" width="100" align="center"/>
        <el-table-column prop="pensionBase" label="基数" width="100" align="center"/>
      </el-table-column>
      <el-table-column label="医疗保险" width="100" align="center">
        <el-table-column prop="medicalPer" label="比率" width="100" align="center"/>
        <el-table-column prop="medicalBase" label="基数" width="100" align="center"/>
      </el-table-column>
      <el-table-column label="公积金" width="100" align="center">
        <el-table-column prop="accumulationFundPer" label="比率" width="100" align="center"/>
        <el-table-column prop="accumulationFundBase" label="基数" width="100" align="center"/>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template #default="scope">
          <el-button size="small">编辑</el-button>
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import request from "@/api/request";
export default {
  data() {
    return {
      salaryData: []
    }
  },
  methods:{
    // 工资套装账单表格数据初始化
    initSalary(){
      request.initSalary().then(resp=>{
        console.log(resp.data);
        this.salaryData=resp.data;
      })
    }
  },
  mounted() {
    this.initSalary();
  }
}
</script>