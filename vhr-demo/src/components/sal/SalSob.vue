<template>
  <div style="margin-top: 25px">
    <el-input prefix-icon="Search" style="width: 250px" v-model="salaryName" placeholder="请输入套账名称"></el-input>
    <el-button type="primary" icon="Search" @click="Search">搜索</el-button>
    <el-button icon="Plus" type="primary" style="width: 150px;margin-left: 40px" @click="addSalary">添加工资套账</el-button>
    <el-button icon="Refresh" style="width: 130px;margin-left: 40px" type="success" @click="refresh">刷新</el-button>
  </div>
  <div style="margin-top: 20px">
    <el-table :data="salaryData" border size="small" style="width: 85%">
      <el-table-column prop="name" label="账套名称" fixed width="130" align="center"/>
      <el-table-column prop="trafficSalary" label="交通补助" width="100" align="center"/>
      <el-table-column prop="lunchSalary" label="午餐补助" width="100" align="center"/>
      <el-table-column prop="bonus" label="奖金" width="100" align="center"/>
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
          <el-button size="small" @click="updateSal(scope)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteSal(scope)">删除</el-button>
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
  <el-dialog style="margin-top: 15%;" v-model="this.upadteSalDialog"  width="60%" title="修改工资套账">
    <el-form status-icon>
      <el-row :gutter="12" :justify="'center'">
        <el-col :span="8">
          <el-row>
            <el-form-item label="账套名称" style="width: 80%;" >
              <el-input v-model="SalData.name" placeholder="请输入账套名称" size="small"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="交通补助" style="width: 80%;">
              <el-input size="small" v-model="SalData.trafficSalary" placeholder="请输入交通补助"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="午餐补助" style="width: 80%;">
              <el-input size="small" v-model="SalData.lunchSalary" placeholder="请输入午餐补助"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
      </el-row>
      <el-row :gutter="12" :justify="'center'">
        <el-col :span="8">
          <el-row>
            <el-form-item label="奖金金额" style="width: 80%;" >
              <el-input placeholder="请输入奖金金额" v-model="SalData.bonus" size="small"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="启用时间" style="width: 80%;">
              <el-date-picker type="date" v-model="createDate" placeholder="启用日期" size="small"
              />
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="基本工资" style="width: 80%;">
              <el-input size="small" v-model="SalData.basicSalary" placeholder="请输入基本工资"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
      </el-row>
      <el-row :gutter="12" :justify="'center'">
        <el-col :span="8">
          <el-row>
            <el-form-item label="养老金比率" style="width: 80%;" >
              <el-input v-model="SalData.pensionPer" size="small"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="养老金基数" style="width: 80%;" >
              <el-input size="small" v-model="SalData.pensionBase"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="医疗保险比率" style="width: 80%;">
              <el-input size="small" v-model="SalData.medicalPer"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
      </el-row>
      <el-row :gutter="12" :justify="'center'">
        <el-col :span="8">
          <el-row>
            <el-form-item label="医疗保险基数" style="width: 80%;" >
              <el-input size="small" v-model="SalData.medicalBase"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="公积金比率" style="width: 80%;" >
              <el-input size="small" v-model="SalData.accumulationFundPer"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
        <el-col :span="8">
          <el-row>
            <el-form-item label="公积金基数" style="width: 80%;">
              <el-input size="small" v-model="SalData.accumulationFundBase"></el-input>
            </el-form-item>
          </el-row>
        </el-col>
      </el-row>
      <el-row style="margin-top: 35px;display: flex;justify-content: flex-end">
        <el-button @click="cancelUpdate">取消</el-button>
        <el-button type="primary" style="margin-left: 40px" @click="sureUpdate">确定</el-button>
      </el-row>
    </el-form>
  </el-dialog>
</template>
<script>
import request from "@/api/request";
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      upadteSalDialog:false,
      salaryData: [],
      showSalary: false,
      salary: ["套账名称", "基本工资", "交通补助", "午餐补助", "奖金", "养老金比率", "养老金基数", "医疗保险比率", "医疗保险基数", "公积金比率", "公积金基数"],
      stepNumber: 0,
      nextText: "下一步",
      salaryName:'',
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
      },
      createDate:null
    }
  },
  methods: {
    // 搜索
    Search(){
      if(this.salaryName===null||this.salaryName===''){
        ElMessage.error("请输入搜索的工资套账");
      }else {
        request.searchSalByName(this.salaryName).then(resp=>{
          if(resp.data.length>0){
            this.salaryData = resp.data;
          }else {
            ElMessage.info("无相关部门账套");
          }
        })
      }
    },
    // 刷新
    refresh(){
      this.initSalary();
    },// 修改工资套账
    updateSal(scope){
      this.SalData=scope.row;
      this.upadteSalDialog=true;
},
    //取消修改
    cancelUpdate(){
      this.upadteSalDialog=false;
      this.initData();
    },//取消添加
    cancelAddSal(){
      this.upadteSalDialog=false;
      this.initData();
    },
    sureUpdate(){
      // 之前salary里面没有createDate属性
      if(this.createDate===null){
        this.createDate=new Date();
      }
      this.SalData.createDate=this.createDate;
      request.updateSal(this.SalData).then(resp=>{
        if(resp.data.message==='修改成功'){
          this.initSalary();
          this.initData();
          this.upadteSalDialog=false;
        }
      })
    },
    // 添加工资套装
    addSalary() {
      this.initData();
      this.showSalary = true;
    },
    // 下一步 到达不同步骤条时，更换按钮文本
    nextStep() {
      if (this.nextText === '确定' && this.stepNumber === 10) {
        request.insertSalary(this.SalData).then(resp => {
          if (resp.data.message === '添加成功') {
            this.showSalary = false;
            this.initSalary();
            this.stepNumber=0;
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
    // 删除工资套账
    deleteSal(rowData){
      request.deleteSal(rowData.row.id).then(resp=>{
        if(resp.data.message==='删除成功'){
          this.initSalary();
        }
      })
    },
    // 工资套装账单表格数据初始化
    initSalary() {
      request.initSalary().then(resp => {
        this.salaryData = resp.data;
      })
    },
    watchSalaryName(value){
      if (value === '') {
        this.initSalary();
      }
    },
    initData(){
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
    }
  },
  // 监控某个值
  watch:{
    salaryName:'watchSalaryName'
  }
  ,
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