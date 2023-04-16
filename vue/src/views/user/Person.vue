<template>
  <div style="width: 500px">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>个人信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="admin" label-width="150px">
        <el-form-item label="用户名">
          <el-input v-model="admin.userName"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="admin.telephone"></el-input>
        </el-form-item>
        <el-form-item label="请输入密码">
          <el-input v-model="admin.password" type="password"></el-input>
        </el-form-item>
      </el-form>
    </div>

    <div style="text-align: center; margin-top: 30px;">
      <el-button type="primary" @click="save" size="medium">修改</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'Person',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
    }
  },
  created() {
  },
  methods: {
    save() {
        request.put('/user/person', this.admin).then(res => {
          if (res.code === '200') {
            this.$notify.success('更新成功,请重新登录')
            // 清除浏览器用户数据
            Cookies.remove('admin')
            this.$router.push('/login')
          } else {
            this.$notify.error(res.msg)
          }
        })
    },
  }
}

</script>


