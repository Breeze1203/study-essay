<template>
  <div>
    <el-input size="default" v-model="department" style="width: 500px" prefix-icon="Search"
              placeholder="输入部门名称搜索部门"/>
  </div>
  <div>
    <el-tree ref="tree" :filter-node-method="filterNode" style="width: 500px;margin-top: 10px" :data="DepData"
             :props="defaultProps" :expand-on-click-node="false" :default-expand-all="true">
      <!--是否在点击节点的时候展开或者收缩节点， 默认值为 true，如果为 false，则只有点箭头图标的时候才会展开或者收缩节点-->
      <!--node指代当前树节点对象，data是你后端返回的数据-->
      <template #default="{ node, data }">
        <div class="department">
          <span>{{ node.label }}</span>
          <span class="buttons">
           <el-button type="primary" size="small" @click="addDep(data)">添加部门</el-button>
            <el-button type="danger" size="small" @click="deleteDep(data)">删除部门</el-button>
          </span>
        </div>
      </template>
    </el-tree>
  </div>
  <el-dialog v-model="show" title="添加部门" style="width: 450px;">
    <el-row>
      <el-space :size="40">
        <span style="color: #ff7c0a">上级部门</span>
        <span>{{ departmentName.name }}</span>
      </el-space>
    </el-row>
    <el-row style="margin-top: 20px">
      <el-space :size="40">
        <span style="color: #ff7c0a">部门名称</span>
        <span><el-input placeholder="输入所添加的部门名称" size="small" v-model="addDepartment"></el-input></span>
      </el-space>
    </el-row>
    <el-row justify="end" style="margin-top: 20px">
      <el-button @click="cancel">取消</el-button>
      <el-button @click="sure">确定</el-button>
    </el-row>
  </el-dialog>
</template>

<script>
import request from "@/api/request";
import {ElMessage} from "element-plus";
import {ElMessageBox} from "element-plus";

export default {
  name: "DepMana",
  data() {
    return {
      department: '',
      DepData: [],
      defaultProps: {
        label: 'name',
        children: 'children'
      },
      show: false,
      departmentName: '',
      addDepartment: ''
    }
  },
  watch: {
    department: 'filterDepartment'
  },
  mounted() {
    this.initDepartment();
  },
  methods: {
    initDepartment() {
      request.getAllDepartmentByParentId().then(res => {
        this.DepData = res.data;
      });
    },

    // 这里是watch监听属性值变化的方法
    filterDepartment(value) {
      // 调用 Tree 实例对象的 filter 方法来过滤树节点。
      // 方法的参数就是过滤关键字。 需要注意的是，此时需要设置 filter-node-method 属性，其值为过滤函数。
      this.$refs.tree.filter(value);
    },
    // 这个方法就是tree节点过滤方法
    // 第一个参数是过滤的值(String),第二个就是tree对象data
    // 通过调用 filter-node-method 方法来判断该节点是否应该被显示。如果返回值为 true，
    // 则该节点会被显示；如果返回值为 false，则该节点会被隐藏。
    filterNode(value, data) {
      // 这里表示如果传过来的value值为空，则不过滤
      if (!value) return true;
      // 这里如果data对象的name属性值包含value,剩下的就过滤
      return data.name.indexOf(value) !== -1;
    },
    // 添加部门
    addDep(data) {
      this.departmentName = data;
      this.show = true;
    },
    //删除部门
    deleteDep(data) {
      if (data.children.length > 0) {
        ElMessage.error("操作失败,该部门下有子部门");
        return;
      }
      ElMessageBox.confirm(
          '此操作将永久删除【' + data.name + '】部门,是否继续',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
          }
      )
          .then(() => {
                request.deleteDepartment(data.id).then(res => {
                  if (res.data.message === '部门删除成功') {
                    this.initDepartment();
                  }
                })
              }
          )
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '操作取消',
            })
          })
    },
    cancel() {
      this.show = false;
    },
    sure() {
      if (!this.addDepartment) {
        ElMessage({
          message: '请输入部门名称',
          type: 'error',
        });
      } else {
        request.addDepartment(this.departmentName.id, this.departmentName.parent, this.addDepartment, this.departmentName.depPath).then(res => {
              this.addDepartment = '';
              this.initDepartment();
              this.show = false;
            }
        )
      }
    }
  }
}
</script>

<style scoped>
.department {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.buttons {
  display: flex;
  gap: 10px; /* 设置两个按钮之间的间距 */
}
</style>