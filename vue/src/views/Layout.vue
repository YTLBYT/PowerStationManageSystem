<template>
  <el-container style="height: 100vh; border: 1px solid #eee">
<!--    侧边栏-->
    <el-aside :width="asidewidth + 'px'" style="background-color: rgb(238, 241, 246);height:100%;">
      <el-menu style="min-height: 100%; overflow-x: hidden;"
               :collapse-transition="false"
               :collapse="isCollapse"
               :default-active="$route.path" router
      >
        <div style="height: 60px; line-height:80px; background-color: #5282FF; text-align: center; height: 80px">
          <img src="../assets/power.png" alt="" width="30px" style="margin-right: 5px; position: relative; top: 8px">
          <b style="color: white;" v-show="!isCollapse">电站管理系统</b>
        </div>
        <el-menu-item index="home">
          <i class="el-icon-house"></i>
          <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item index="video">
          <i class="el-icon-s-platform"></i>
          <span slot="title">视频管理</span>
        </el-menu-item>
        <el-submenu index="powerStation">
          <template slot="title">
            <img src="../assets/powerstation.png" alt="" width="18px" style="margin-left: 4px">
            <span style="margin-left: 8px">电站管理</span>
          </template>
          <el-menu-item index="/info">
            <span slot="title">电站信息</span>
          </el-menu-item>
          <el-menu-item index="/troubleAdd">
            <span slot="title">故障上报</span>
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="trouble">
          <i class="el-icon-s-release"></i>
          <span slot="title">故障管理</span>
        </el-menu-item>
        <el-menu-item index="user">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="role">
          <img src="../assets/role.png" alt="" width="18px" style="margin-left: 4px">
          <span slot="title" style="margin-left: 8px">角色管理</span>
        </el-menu-item>
        <el-menu-item index="car">
          <img src="../assets/car-fill.png" alt="" width="18px" style="margin-left: 4px">
          <span slot="title" style="margin-left: 8px">车辆管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
<!--      头部-->
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 80px; display: flex; height: 80px">
        <div style="flex: 1; font-size: 25px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>{{admin.userName}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><div @click="$router.push('/person')">个人信息</div></el-dropdown-item>
            <el-dropdown-item><div @click="logout">退出</div></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

<!--      主体-->
      <el-main tyle="flex: 1; width: 0; background-color: white; padding: 10px">
        <router-view/>
      </el-main>
    </el-container>

  </el-container>
</template>




<script>
import Cookies from 'js-cookie'

export default {
  name: 'Layout.vue',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      input: '',
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
    logout() {
      // 清除浏览器用户数据
      Cookies.remove('admin')
      this.$router.push('/login')
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
