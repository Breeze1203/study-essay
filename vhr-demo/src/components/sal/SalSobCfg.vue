<template>
  <div>
    <el-table border stripe style="width: 95%" size="small" :data="empDataWithSalary">
      <el-table-column type="selection" prop="id" fixed width="55"/>
      <el-table-column prop="name" label="姓名" width="100" align="center"/>
      <el-table-column prop="workId" label="工号" width="100" align="center"/>
      <el-table-column prop="gender" label="性别" width="100" align="center"/>
      <el-table-column prop="birthday" label="出生日期" width="100" align="center"/>
      <el-table-column prop="idCard" label="身份证号码" width="170" align="center"/>
      <el-table-column prop="wedlock" label="婚姻状况" width="100" align="center"/>
      <el-table-column prop="nation.name" label="民族" width="100" align="center"/>
      <el-table-column prop="nativePlace" label="籍贯" width="100" align="center"/>
      <el-table-column prop="politicsstatus.name" label="政治面貌" width="100" align="center"/>
      <el-table-column prop="email" label="电子邮件" width="150" align="center"/>
      <el-table-column prop="phone" label="电话号码" width="100" align="center"/>
      <el-table-column prop="address" label="联系地址" width="180" align="center"/>
      <el-table-column label="工资套账名称" width="180" align="center">
        <template #default="scope">
          <el-tag hit="true" type="success" v-if="scope.row.salary!==null">
            {{ scope.row.salary.name }}
          </el-tag>
          <el-tag hit="true" type="danger" v-else>
            暂无设置工资套账
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="department.name" label="所属部门" width="100" align="center"/>
      <el-table-column prop="position.name" label="职位" width="100" align="center"/>
      <el-table-column prop="jObLevel.name" label="职称" width="100" align="center"/>
      <el-table-column prop="engageForm" label="聘用形式" width="100" align="center"/>
      <el-table-column prop="begindate" label="入职日期" width="100" align="center"/>
      <el-table-column prop="conversionTime" label="转正日期" width="100" align="center"/>
      <el-table-column prop="beginContract" label="合同起始时间" width="100" align="center"/>
      <el-table-column prop="endContract" label="合同截止日期" width="100" align="center"/>
      <el-table-column prop="contractTerm" label="合同期限" width="100" align="center"/>
      <el-table-column prop="tipTopdeGree" label="最高学历" width="100" align="center"/>
      <el-table-column prop="school" label="学校" width="150" align="center"/>
      <el-table-column prop="specialty" label="专业" width="150" align="center"/>
      <el-table-column label="操作" fixed="right" width="200" align="center">
        <template #default="scope">
          <el-button size="small" type="primary" style="padding: 3px">修改工资套账</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import request from "@/api/request";
export default {
  data(){
    return{
      empDataWithSalary:[],
      // 分页查询的默认数据
      page:1,
      size:15,
    }
  },
  methods:{
    initEmpWithSalary(){
      request.initEmpWithSalary(this.size,this.page).then(resp=>{
        this.empDataWithSalary=resp.data.employeeList;
      })
    }
  },
  mounted() {
    this.initEmpWithSalary();
  }
}
</script>
<style>

</style>