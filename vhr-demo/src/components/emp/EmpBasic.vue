<template>
  <div style="display: flex;justify-content: space-between;width: 95%;margin-top: 16px">
    <div>
      <el-input placeholder="输入用户名搜索用户" prefix-icon="Search" :disabled="showSearch" v-model="EmpName"
                size="small"
                style="width: 300px;margin-right: 15px" @keydown.enter.native="Search"/>
      <el-button type="primary" size="small" icon="Search" :disabled="showSearch" @click="Search">搜索</el-button>
      <el-button type="primary" size="small" icon="ArrowDownBold" @click="AdvSearch">高级搜索</el-button>
    </div>
    <div style="display:inline-flex;">
      <!--      文件上传失败的钩子--><!--      文件上传成功的钩子-->
      <el-upload action="http://localhost:8080//api/employee/basic/upload"
                 :on-error="error"
                 :on-success="success" :show-file-list="false"
                 multiple>
        <el-button style="margin-right: 35px" type="primary" icon="Download" size="small">导入数据</el-button>
      </el-upload>
      <el-button style="margin-right: 20px" type="success" size="small" icon="Upload" @click="Download">导出数据
      </el-button>
      <el-button type="primary" size="small" icon="Plus" @click="addWorker">添加用户</el-button>
    </div>
  </div>
  <div style="display: flex;justify-content: space-between;width: 95%;margin-top: 16px;border: 1px solid black;"
       v-show="AdvancedSearch">
    <el-form status-icon :model="emp" :rules="rules">
      <el-row :gutter="2">
        <el-col :span="6">
          <el-form-item label="政治面貌" style="width: 80%;" prop="politicId">
            <el-select v-model="emp.politicId" size="small" placeholder="请输入政治面貌">
              <el-option
                  v-for="item in politicsStatusData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="民族" style="width: 80%;" prop="nationId">
            <el-select v-model="emp.nationId" placeholder="请选择民族" size="small">
              <el-option
                  v-for="item in nations"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="职位" style="width: 80%;" prop="posId">
            <el-select v-model="emp.posId" placeholder="请选择职位" size="small">
              <el-option
                  v-for="item in positions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="职称" style="width: 80%;" prop="jobLevelId">
            <el-select v-model="emp.jobLevelId" placeholder="请选择职称"
                       size="small">
              <el-option
                  v-for="item in jObLevels"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="聘用形式" style="width: 80%;" prop="engageForm">
            <el-radio-group v-model="emp.engageForm" size="small">
              <el-radio label="劳动合同">劳动合同</el-radio>
              <el-radio label="劳务合同">劳务合同</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="所属部门" style="width: 80%;" prop="departmentId">
            <el-select v-model="emp.departmentId" placeholder="请选择部门名称"
                       size="small">
              <el-option
                  v-for="item in departments"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="入职日期" style="width: 80%;" prop="begin">
            <el-date-picker
                v-model="StartAndEnd"
                type="daterange"
                start-placeholder="star"
                end-placeholder="end"
                size='small'
            />
          </el-form-item>
        </el-col>
        <el-row>
          <el-form-item>
            <el-button size="small" @click="CancleSearch">取消</el-button>
            <el-button size="small" type="primary" icon="Search" @click="SearchAdvanced">搜索</el-button>
          </el-form-item>
        </el-row>
      </el-row>
    </el-form>
  </div>
  <div style="margin-top: 14px">
    <el-table v-loading="loading" @selection-change="SelectedIds" border stripe style="width: 95%" size="small" :data="EmpTable">
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
          <el-button size="small" style="padding: 3px" @click="editEmp(scope)">编辑</el-button>
          <el-button size="small" type="primary" style="padding: 3px">查看高级资料</el-button>
          <el-button size="small" type="danger" @click="deleteEmp(scope)" style="padding: 3px">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div style=" display: flex;
  justify-content: space-between">
    <div style="margin-top: 15px;width: 15%">
      <el-button @click="deleteIds" type="danger">批量删除</el-button>
    </div>
  <div style="margin-top: 15px;display: flex;justify-content: end;width: 85%">
    <el-pagination
        :page-sizes="[10, 20, 30, 40, 50]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
  </div>
  <div>
    <el-dialog v-model="DialogVisible" :title="dialogTitle" width="75%">
      <el-form status-icon ref="emp" :model="emp" :rules="rules">
        <el-row :gutter="15" :justify="'center'">
          <el-col :span="6">
            <el-row>
              <el-form-item label="姓名" style="width: 80%;" prop="name">
                <el-input v-model="emp.name" size="small" prefix-icon="EditPen"
                          placeholder="请输入员工姓名"/>
              </el-form-item>
            </el-row>
          </el-col>
          <el-col :span="6">
            <el-row>
              <el-form-item label="性别" style="width: 80%;" prop="gender">
                <el-radio-group v-model="emp.gender" size="small">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-row>
          </el-col>
          <el-col :span="6">
            <el-form-item label="出生日期" style="width: 80%;" prop="birthday">
              <el-date-picker v-model="emp.birthday"
                              type="date"
                              placeholder="出生日期"
                              size="small"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="政治面貌" style="width: 80%;" prop="politicId">
              <el-select v-model="emp.politicId" size="small" placeholder="请输入政治面貌">
                <el-option
                    v-for="item in politicsStatusData"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="15" :justify="'center'" style="margin-top: 35px">
          <el-col :span="6">
            <el-form-item label="民族" style="width: 80%;" prop="nationId">
              <el-select v-model="emp.nationId" placeholder="请选择民族" size="small">
                <el-option
                    v-for="item in nations"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="籍贯" style="width: 80%;" prop="nativePlace">
              <el-input v-model="emp.nativePlace" size="small" prefix-icon="EditPen"
                        placeholder="请输入籍贯"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电子邮箱" style="width: 80%;" prop="email">
              <el-input v-model="emp.email" size="small" prefix-icon="EditPen"
                        placeholder="电子邮箱地址"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="联系地址" style="width: 80%;" prop="address">
              <el-input v-model="emp.address" size="small" prefix-icon="EditPen"
                        placeholder="联系地址"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-top: 35px">
          <el-col :span="6">
            <el-form-item label="职位" style="width: 80%;" prop="posId">
              <el-select v-model="emp.posId" placeholder="请选择职位" size="small">
                <el-option
                    v-for="item in positions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="职称" style="width: 80%;" prop="jobLevelId">
              <el-select v-model="emp.jobLevelId" placeholder="请选择职称"
                         size="small">
                <el-option
                    v-for="item in jObLevels"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="所属部门" style="width: 80%;" prop="departmentId">
              <el-select v-model="emp.departmentId" placeholder="请选择部门名称"
                         size="small">
                <el-option
                    v-for="item in departments"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="电话" style="width: 80%;" prop="phone">
              <el-input v-model="emp.phone" size="small" prefix-icon="EditPen"
                        placeholder="电话号码"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-top: 35px">
          <el-col :span="6">
            <el-form-item label="工号" style="width: 80%;">
              <el-input v-model="emp.workId" disabled size="small"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="学历" style="width: 80%;" prop="tipTopdeGree">
              <el-select v-model="emp.tipTopdeGree" placeholder="最高学历"
                         size="small">
                <el-option
                    v-for="item in tiptopDegree"
                    :label="item"
                    :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="毕业院校" style="width: 80%;" prop="school">
              <el-input v-model="emp.school" size="small" prefix-icon="EditPen"
                        placeholder="毕业院校名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="专业名称" style="width: 80%;" prop="specialty">
              <el-input v-model="emp.specialty" size="small" prefix-icon="EditPen"
                        placeholder="专业名称"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-top: 35px">
          <el-col :span="6">
            <el-form-item label="入职日期" style="width: 80%;" prop="begindate">
              <el-date-picker v-model="emp.begindate"
                              type="date"
                              placeholder="入职日期"
                              size="small"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="转正日期" style="width: 80%;" prop="conversionTime">
              <el-date-picker v-model="emp.conversionTime"
                              type="date"
                              placeholder="转正日期"
                              size="small"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同起始日期" style="width: 80%;" prop="beginContract">
              <el-date-picker v-model="emp.beginContract"
                              type="date"
                              placeholder="合同起始日期"
                              size="small"
              />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="合同终止日期" style="width: 80%;" prop="endContract">
              <el-date-picker v-model="emp.endContract"
                              type="date"
                              placeholder="合同终止日期"
                              size="small"
              />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-top: 35px">
          <el-col :span="8">
            <el-form-item label="身份证号码" style="width: 80%;" prop="idCard">
              <el-input v-model="emp.idCard" size="small" prefix-icon="EditPen"
                        placeholder="请输入员工身份证号码"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="聘用形式" style="width: 80%;" prop="engageForm">
              <el-radio-group v-model="emp.engageForm" size="small">
                <el-radio label="劳动合同">劳动合同</el-radio>
                <el-radio label="劳务合同">劳务合同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="婚姻状况" prop="wedlock">
              <el-radio-group v-model="emp.wedlock" size="small">
                <el-radio label="已婚">已婚</el-radio>
                <el-radio label="未婚">未婚</el-radio>
                <el-radio label="离异">离异</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="margin-top: 35px;display: flex;justify-content: flex-end">
          <el-button @click="cancelAddWorker">取消</el-button>
          <el-button type="primary" style="margin-left: 40px" @click="sureAdd">确定</el-button>
        </el-row>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import request from "@/api/request";
import {ElMessage} from "element-plus";


export default {
  data() {
    return {
      EmpTable: [],
      EmpName: '',
      dialogTitle: '',
      loading: true,
      page: 1,
      size: 10,
      total: '',
      // 高级搜索的时间选择
      StartAndEnd: '',
      showSearch: false,
      // 高级搜索的属性
      AdvancedSearch: false,
      DialogVisible: false,
      tiptopDegree: ["博士", "硕士", "本科", "大专", "高中", "初中", "小学", "其它"],
      politicsStatusData: [],
      nations: [],
      positions: [],
      jObLevels: [],
      departments: [],
      // 批量删除员工的id使用
      empIds:[],
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        idCard: [{
          required: true,
          message: '请输入正确的身份证号',
          trigger: 'blur'
        }, {
          pattern: '^[1-9]\\d{5}(19\\d{2}|20[0-2]\\d)(0[1-9]|1[0-2])(0[1-9]|[1-2]\\d|3[0-1])\\d{3}[0-9Xx]$',
          message: '身份证号码格式错误',
          trigger: "blur"
        }],
        gender: [{required: true, message: '请选择性别', trigger: 'blur'}],
        birthday: [{required: true, message: '请选择出生日期', trigger: 'blur'}],
        wedlock: [{required: true, message: '请选择婚姻状况', trigger: 'blur'}],
        nationId: [{required: true, message: '请选择名族', trigger: 'blur'}],
        nativePlace: [{required: true, message: '请输入籍贯', trigger: 'blur'}],
        politicId: [{required: true, message: '请输入政治面貌', trigger: 'blur'}],
        email: [{required: true, message: '请输入邮件地址', trigger: 'blur'}, {
          type: "email",
          message: "邮箱格式不正确",
          trigger: 'blur'
        }],
        phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}],
        address: [{required: true, message: '请输入联系地址', trigger: 'blur'}],
        departmentId: [{required: true, message: '请输入部门', trigger: 'blur'}],
        jobLevelId: [{required: true, message: '请选择职称', trigger: 'blur'}],
        posId: [{required: true, message: '请选择职位', trigger: 'blur'}],
        engageForm: [{required: true, message: '请选择合同形式', trigger: 'blur'}],
        tipTopdeGree: [{required: true, message: '请选择最高学历', trigger: 'blur'}],
        specialty: [{required: true, message: '请输入专业名称', trigger: 'blur'}],
        school: [{required: true, message: '请输入毕业院校', trigger: 'blur'}],
        begindate: [{required: true, message: '请输入入职时间', trigger: 'blur'}],
        conversionTime: [{required: true, message: '请输入转正日期', trigger: 'blur'}],
        beginContract: [{required: true, message: '请输入合同起始日期', trigger: 'blur'}],
        endContract: [{required: true, message: '请输入合同结束日期', trigger: 'blur'}]
      },
      emp: {
        id: null,
        name: null,
        gender: null,
        birthday: null,
        idCard: null,
        wedlock: null,
        nationId: null,
        nativePlace: null,
        politicId: null,
        email: null,
        phone: null,
        address: null,
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: null,
        tipTopdeGree: null,
        specialty: null,
        school: null,
        begindate: null,
        workState: '在职',
        workId: null,
        contractTerm: null,
        conversionTime: null,
        notworkdate: null,
        beginContract: null,
        endContract: null,
        workAge: null,
        nation: null,
        politicsstatus: null,
        position: null,
        department: null,
        jobLevel: null,
      }
    }
  },
  mounted() {
    this.initEmp();
  },
  watch: {
    EmpName: 'ClearEmpName'
  }
  ,
  methods: {
    // 高级搜索
    AdvSearch() {
      // 展示高级搜索时，普通搜索按钮变得不可用
      this.showSearch = true;
      this.AdvancedSearch = true;
    },
    // 取消高级搜索
    CancleSearch() {
      this.showSearch = false;
      this.AdvancedSearch = false;
      this.StartAndEnd = null;
      // 给里面的数据变为空
      this.emptyEmp();
      this.initEmp();
    },
    // 进行高级搜索
    SearchAdvanced() {
      request.getEmpByPageAdvanch(this.size, this.page, this.emp).then(resp => {
        if (resp.data.employeeList !== null) {
          this.EmpTable = resp.data.employeeList;
          this.total = resp.data.total;
          this.politicsStatusData = resp.data.politicsStatus;
          this.nations = resp.data.nations;
          this.positions = resp.data.positions;
          this.jObLevels = resp.data.jObLevels;
          this.departments = resp.data.departments;
        }
      })
    },
    //下载数据
    Download() {
      window.open("/api/employee/basic/downExcel", '_parent')
    },
    // 给emp里面的内容变为空
    emptyEmp() {
      this.emp = {
        id: null,
        name: null,
        gender: null,
        birthday: null,
        idCard: null,
        wedlock: null,
        nationId: null,
        nativePlace: null,
        politicId: null,
        email: null,
        phone: null,
        address: null,
        departmentId: null,
        jobLevelId: null,
        posId: null,
        engageForm: null,
        tipTopdeGree: null,
        specialty: null,
        school: null,
        begindate: null,
        workState: '在职',
        workId: null,
        contractTerm: null,
        conversionTime: null,
        notworkdate: null,
        beginContract: null,
        endContract: null,
        workAge: null,
      }
    },
    success(response, file, fileLis) {
      // 处理成功上传后的逻辑
      if (response.message === '导入成功') {
        this.$message({
          message: response.message,
          type: 'success',
          duration: 1000 // 设置关闭时间为 3 秒
        });
        // 插入成功后，刷新表
        this.initEmp();
      } else {
        ElMessage.error(response.message)
      }
    },
    error(errorResponse, file, files) {
      ElMessage.error("上传失败，请稍后再试")
    },
    // 修改用户
    editEmp(data) {
      this.dialogTitle = "修改用户";
      this.emp = data.row;
      this.DialogVisible = true;
    },
    // 删除员工
    deleteEmp(data) {
      request.deleteEmp(data.row.id).then(res => {
        if (res.data.message === '删除成功') {
          this.initEmp();
        }
      })
    }
    ,
    // 获取批量删除员工的id
    SelectedIds(value){
      // 因为之前选过，所以再次使用时将其变为一个空数组
      this.empIds=[];
      for (let i = 0; i < value.length; i++) {
        this.empIds.push(value[i].id);
      }
    },
    // 批量删除员工
    deleteIds(){
      if(this.empIds.length===0){
        ElMessage.error("请选择要删除的员工");
      }else {
        request.deleteEmployeeByIds(this.empIds).then(resp=>{
          if(resp.data.message==='删除成功'){
            console.log("删除成功");
            this.empIds=[];
            this.initEmp();
          }
        })
      }
    },
    // size发生给改时
    handleSizeChange(number) {
      this.size = number;
      if (this.showSearch === false) {
        this.initEmp();
      }else {
        this.SearchAdvanced();
      }
    }
    ,
    // page发生更改时
    handleCurrentChange(number) {
      this.page = number;
      if (this.showSearch === false) {
        this.initEmp();
      }else {
        this.SearchAdvanced();
      }
    }
    ,
    // 根据用户名搜索用户
    Search() {
      if (this.EmpName === '') {
        ElMessage.error("请输入搜索的用户名");
        return;
      }
      this.initEmp();
    }
    ,
    // 当搜索框里面的值为空时
    ClearEmpName(value) {
      if (value === '') {
        this.initEmp();
      }
    }
    ,
    // 添加用户
    addWorker() {
      this.emptyEmp();
      this.dialogTitle = "添加用户";
      this.DialogVisible = true;
      // 随机生成1-1000的数字
      let workId = Math.floor(Math.random() * 1000) + 1;
      // 给数字前面补0,直至8位
      this.emp.workId = workId.toString().padStart(8, '0');
    }
    ,
    // 取消添加用户
    cancelAddWorker() {
      this.DialogVisible = false;
    }
    ,
    // 确定添加用户
    sureAdd() {
      this.$refs.emp.validate((valid) => {
        if (valid) {
          if (this.emp.id === null) {
            let diffInMilliseconds = Math.abs(this.emp.endContract.getTime() - this.emp.beginContract.getTime());
            this.emp.contractTerm = Math.ceil(diffInMilliseconds / (1000 * 3600 * 24 * 365)); // 四舍五入到最接近的整数天
            request.addEmp(this.emp).then(res => {
              if (res.data.message === '添加成功') {
                this.initEmp();
                this.DialogVisible = false;
              }
            })
          } else {
            request.updateEmp(this.emp).then(res => {
              if (res.data.message === '修改成功') {
                this.initEmp();
                this.DialogVisible = false;
              }
            })
          }
        }
      })
    }
    ,
    initEmp() {
      console.log('-----');
      request.initAllEmp(this.page, this.size, this.EmpName).then(res => {
        if (res.data.employeeList !== null) {
          this.EmpTable = res.data.employeeList;
          this.total = res.data.total;
          this.politicsStatusData = res.data.politicsStatus;
          this.nations = res.data.nations;
          this.positions = res.data.positions;
          this.jObLevels = res.data.jObLevels;
          this.departments = res.data.departments;
          this.loading = false;
        }
      })
    }
  }
}
</script>
<style scoped>

</style>