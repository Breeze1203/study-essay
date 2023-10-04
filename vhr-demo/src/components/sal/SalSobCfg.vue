<template>
  <div>
    <el-table v-loading="loading" border stripe style="width: 95%" size="small" :data="empDataWithSalary">
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
          <el-tooltip placement="right" effect="light" trigger="click">
            <template #content>
              <div>
                <tr>
                  <td>交通补助</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.trafficSalary }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>午餐补助</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.lunchSalary }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>奖金</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.bonus }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>启用时间</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.createDate }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>养老金比率</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.pensionPer }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>养老金基数</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.pensionBase }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>医疗保险比率</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.medicalPer }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>医疗保险基数</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.medicalBase }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>公积金比率</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.accumulationFundPer }}</td>
                  <td v-else>无</td>
                </tr>
                <tr>
                  <td>公积金基数</td>
                  <td v-if="scope.row.salary!==null">{{ scope.row.salary.accumulationFundBase }}</td>
                  <td v-else>无</td>
                </tr>
              </div>
            </template>
            <el-tag hit="true" v-if="scope.row.salary!==null" type="success">{{ scope.row.salary.name }}</el-tag>
            <el-tag hit="true" v-else type="warning">暂无设置工资套账</el-tag>
          </el-tooltip>
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
          <!--          触发方式为点击-->
          <el-popover placement="right" :width="300" trigger="click" @hide="hide(scope.row.salary)">
            <template #reference>
              <el-button style="margin-right: 16px" type="primary">修改工资套账</el-button>
            </template>
            <el-form size="small">
              <el-form-item label="工资套账名称:" style="height: 10px">
                <el-input v-model="salary.name" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.name"/>
                <el-input v-model="salary.name" v-else size="small" style="width: 180px;" placeholder="请输入工资套账名称"/>
              </el-form-item>
              <el-form-item label="交通补助:" style="height: 10px">
                <el-input v-model="salary.trafficSalary" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.trafficSalary"/>
                <el-input v-model="salary.trafficSalary" v-else size="small" style="width: 180px;" placeholder="请输入交通补助"/>
              </el-form-item>
              <el-form-item label="午餐补助:" style="height: 10px">
                <el-input v-model="salary.lunchSalary" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.lunchSalary"/>
                <el-input v-model="salary.lunchSalary" v-else size="small" style="width: 180px;" placeholder="请输入午餐补助"/>
              </el-form-item>
              <el-form-item label="奖金:" style="height: 10px">
                <el-input v-model="salary.bonus" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.bonus"/>
                <el-input v-model="salary.bonus" v-else size="small" style="width: 180px;" placeholder="请输入奖金"/>
              </el-form-item>
              <el-form-item label="启用时间:" style="height: 10px">
                <el-date-picker v-model="salary.createDate" v-if="scope.row.salary!==null" style="width: 180px;"
                                type="date"
                                :placeholder="scope.row.salary.createDate"
                                size="small"
                />
                <el-date-picker v-model="salary.createDate" v-else style="width: 180px;"
                                type="date"
                                placeholder="请选择启用时间"
                                size="small"
                />
              </el-form-item>
              <el-form-item label="基本工资:" style="height: 10px">
                <el-input v-model="salary.basicSalary" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.basicSalary"/>
                <el-input v-model="salary.basicSalary" v-else size="small" style="width: 180px;" placeholder="请输入基本工资"/>
              </el-form-item>
              <el-form-item label="养老金比率:" style="height: 10px">
                <el-input v-model="salary.pensionPer" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.pensionPer"/>
                <el-input v-model="salary.pensionPer" v-else size="small" style="width: 180px;" placeholder="请输入养老金比率"/>
              </el-form-item>
              <el-form-item label="养老金基数:" style="height: 10px">
                <el-input v-model="salary.pensionBase" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.pensionBase"/>
                <el-input v-model="salary.pensionBase" v-else size="small" style="width: 180px;" placeholder="请输入养老金基数"/>
              </el-form-item>
              <el-form-item label="医疗保险比率:" style="height: 10px">
                <el-input v-model="salary.medicalPer" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.medicalPer"/>
                <el-input v-model="salary.medicalPer" v-else size="small" style="width: 180px;" placeholder="请输入医疗保险比率"/>
              </el-form-item>
              <el-form-item label="医疗保险基数:" style="height: 10px">
                <el-input v-model="salary.medicalBase" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.medicalBase"/>
                <el-input v-model="salary.medicalBase" v-else size="small" style="width: 180px;" placeholder="请输入医疗保险基数"/>
              </el-form-item>
              <el-form-item label="公积金比率:" style="height: 10px">
                <el-input v-model="salary.accumulationFundPer" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.accumulationFundPer"/>
                <el-input v-model="salary.accumulationFundPer"  v-else size="small" style="width: 180px;" placeholder="请输入公积金比率"/>
              </el-form-item>
              <el-form-item label="公积金基数:" style="height: 10px">
                <el-input v-model="salary.accumulationFundBase" v-if="scope.row.salary!==null" size="small" style="width: 180px;"
                          :placeholder="scope.row.salary.accumulationFundBase"/>
                <el-input v-model="salary.accumulationFundBase" v-else size="small" style="width: 180px;" placeholder="请输入公积金基数"/>
              </el-form-item>
            </el-form>
          </el-popover>
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
      empDataWithSalary: [],
      // 加载中
      loading:true,
      // 分页查询的默认数据
      page: 1,
      size: 15,
      salary: {
        name: null,
        trafficSalary: null,
        lunchSalary: null,
        bonus: null,
        createDate: null,
        basicSalary: null,
        pensionPer: null,
        pensionBase: null,
        medicalPer: null,
        medicalBase: null,
        accumulationFundPer: null,
        accumulationFundBase: null,
      }
    }
  },
  methods: {
    initEmpWithSalary() {
      request.initEmpWithSalary(this.size, this.page).then(resp => {
        this.empDataWithSalary = resp.data.employeeList;
      })
    },
    // 隐藏时触发
    hide(data) {
      // 值不为空则根据id修改
      if (data) {

      } else {
        // 值为空则插入

      }
    }
  },
  mounted() {
    this.initEmpWithSalary();
    this.loading=false;
  }
}
</script>
<style>

</style>