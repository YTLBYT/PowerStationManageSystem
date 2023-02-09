<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户修改</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" label-width="100px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName" placeholder="请输入换电站名称"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.telephone" placeholder="请输入换电站地址"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="form.role" placeholder="角色">
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="换电站管理员" value="换电站管理员"></el-option>
            <el-option label="用户" value="用户"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属换电站id" prop="stationId" v-show="form.role === '换电站管理员'">
          <el-select v-model="form.stationId" placeholder="所属换电站id">
            <el-option  v-for="id in stations" :key="id" :label="id" :value="id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </div>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'EditUser',
  data() {
    return {
      stations:['131231','312343','31233'],
      form: {
        userName: '杨涛',
        telephone: '15378590469',
        role: '用户',
        stationId: '131231'
      }
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/station/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/station/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/info")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>

