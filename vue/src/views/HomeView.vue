<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
    <el-aside :width="asidewidth + 'px'" style="background-color: rgb(238, 241, 246);height:100%;">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden;"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height:80px; background-color: #5282FF; text-align: center; height: 80px">
          <img src="../assets/power.png" alt="" width="30px" style="margin-right: 5px; position: relative; top: 8px">
          <b style="color: white;" v-show="!isCollapse">电站管理系统</b>
        </div>
        <el-menu-item index="1">
          <i class="el-icon-house"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-s-platform"></i>
          <span slot="title">视频管理</span>
        </el-menu-item>
        <el-submenu index="3">
          <template slot="title">
            <img src="../assets/powerstation.png" alt="" width="18px" style="margin-left: 4px">
            <span style="margin-left: 8px">电站管理</span>
          </template>
          <el-menu-item index="3-1">
            <span slot="title">电站信息</span>
          </el-menu-item>
          <el-menu-item index="3-2">
            <span slot="title">故障上报</span>
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="4">
          <i class="el-icon-s-release"></i>
          <span slot="title">故障管理</span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="6">
          <img src="../assets/role.png" alt="" width="18px" style="margin-left: 4px">
          <span slot="title" style="margin-left: 8px">角色管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 80px; display: flex; height: 80px">
        <div style="flex: 1; font-size: 25px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
          <el-breadcrumb-item>活动详情</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="padding: 10px 0;">
          <el-input
              placeholder="请输入内容"
              suffix-icon="el-icon-search"
              v-model="input"
              style="width: 200px; margin-top: 0px">
          </el-input>
          <el-button type="primary" style="margin-left: 5px">搜索</el-button>
          <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd()">新增
          </el-button>
        </div>
        <el-table :data="tableData"
                  :cell-style="rowStyle"
                  style="width: 100%;">
          <el-table-column label="日期" width="180px" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.date }}</span>
            </template>
          </el-table-column>
          <el-table-column label="姓名" width="180px" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column label="地址" width="300px" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.address }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" fixed="right">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit" type="success" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button icon="el-icon-remove-outline" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" style="margin: 5px">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="100"
              layout="total, sizes, prev, pager, next, jumper"
              :total="400">
          </el-pagination>
        </div>

      </el-main>
    </el-container>

  </el-container>
</template>


<script>
export default {
  name: 'HomeView',
  data() {
    return {
      input: '',
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      asidewidth: 200
    }
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.asidewidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
      } else {
        this.asidewidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
      }
    },
    rowStyle() {
      return "text-align:center";
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    handleAdd() {

    }
  }
}
</script>

<style>
.el-header {
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
