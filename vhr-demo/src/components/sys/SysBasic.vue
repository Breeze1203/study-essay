<template>
    <div>
      <el-tabs v-model="activity">
        <el-tab-pane label="部门管理" name="first"><DepMana></DepMana></el-tab-pane>
        <el-tab-pane label="职位管理" name="two"><EcMana></EcMana></el-tab-pane>
        <el-tab-pane label="职称管理" name="three"><jobLeveLMana></jobLeveLMana></el-tab-pane>
        <el-tab-pane label="奖惩规则" name="fourth"><PermissMana></PermissMana></el-tab-pane>
        <el-tab-pane label="权限组" name="five"><PosMana></PosMana></el-tab-pane>
      </el-tabs>
    </div>
</template>
<script>
// 引入组件 部分管理等e
import DepMana from "../sys/basic/DepMana.vue"
import EcMana from "../sys/basic/EcMana.vue"
import jobMana from "../sys/basic/JobMana.vue"
import PermissMana from "../sys/basic/PermissMana.vue"
import PosMana from "../sys/basic/PosMana.vue"


export default {
  data(){
    return{
      activity:'first'
    }
  },
  components:{
    "DepMana":DepMana,
    "EcMana":EcMana,
    "jobLeveLMana":jobMana,
    "PermissMana":PermissMana,
    "PosMana":PosMana
  }
}

</script>