<template>
  <div style="margin-top: 25px">
    <el-button icon="Plus" type="primary" style="width: 150px;" @click="addSalary">添加工资套账</el-button>
    <el-button icon="Refresh" style="width: 130px;margin-left: 40px" type="success">刷新</el-button>
  </div>
  <div style="margin-top: 20px">
    <el-table :data="salaryData" border size="small" style="width: 85%">
      <el-table-column type="selection" prop="id" fixed width="55"/>
      <el-table-column prop="name" label="账套名称" width="130" align="center"/>
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
      <el-table-column label="操作" align="center" fixed="right" width="200">
        <template #default="scope">
          <el-button size="small">编辑</el-button>
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-dialog v-model="showSalary" style="width: 45%;" title="添加工资套账">
    <div style="display: flex;justify-content: space-around;align-items: center">
      <el-steps direction="vertical" :active="stepNumber">
        <el-step v-for="(item,index) in salary" :key="index" :title="item"/>
      </el-steps>
      <!--  v-for="(item,value,index) in SalData
           遍历对象-->
      <el-input v-model="SalData[value]" style="width: 40%;height: 25px" v-for="(item,value,index) in SalData"
                :key="index" :title="item"
                :placeholder="'请输入'+salary[index]+'...'" v-show="stepNumber===index"/>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="previous">上一步</el-button>
        <el-button type="primary" @click="nextStep">{{ this.nextText }}</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import request from "@/api/request";

export default {
  data() {
    return {
      salaryData: [],
      showSalary: false,
      salary: ["套账名称", "基本工资", "交通补助", "午餐补助", "奖金", "养老金比率", "养老金基数", "医疗保险比率", "医疗保险基数", "公积金比率", "公积金基数"],
      stepNumber: 0,
      nextText: "下一步",
      SalData: {
        name: null,
        trafficSalary: null,
        lunchSalary: null,
        bonus: null,
        basicSalary: null,
        pensionPer: null,
        pensionBase: null,
        medicalPer: null,
        medicalBase: null,
        accumulationFundPer: null,
        accumulationFundBase: null
      }
    }
  },
  methods: {
    // 添加工资套装
    addSalary() {
      this.SalData = {
        name: null,
        trafficSalary: null,
        lunchSalary: null,
        bonus: null,
        basicSalary: null,
        pensionPer: null,
        pensionBase: null,
        medicalPer: null,
        medicalBase: null,
        accumulationFundPer: null,
        accumulationFundBase: null
      }
      this.showSalary = true;
    },
    // 下一步 到达不同步骤条时，更换按钮文本
    nextStep() {
      if (this.nextText === '确定' && this.stepNumber === 10) {
        request.insertSalary(this.SalData).then(resp => {
          if (resp.data.message === '添加成功') {
            this.showSalary = false;
            this.initSalary();
          }
        })
      }
      if (this.stepNumber !== 10) {
        this.stepNumber = this.stepNumber + 1;
        if (this.stepNumber === 10) {
          this.nextText = '确定';
        } else {
          this.nextText = '下一步';
        }
      }
    },
    // 上一步
    previous() {
      if (this.stepNumber !== 0) {
        this.stepNumber = this.stepNumber - 1;
        if (this.stepNumber < 10) {
          this.nextText = '下一步';
        } else {
          this.nextText = '确定';
        }
      }
    },
    // 工资套装账单表格数据初始化
    initSalary() {
      request.initSalary().then(resp => {
        this.salaryData = resp.data;
      })
    },
  },
  mounted() {
    this.initSalary();
  }
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>