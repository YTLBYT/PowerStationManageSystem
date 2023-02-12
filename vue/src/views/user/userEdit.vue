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
      <el-form :inline="true" :model="form" label-width="150px">
        <el-form-item label="用户名">
          <el-input v-model="form.userName" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.telephone"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-select v-model="form.roleId" placeholder="角色" @change="selectStation">
            <el-option label="管理员" value="1"></el-option>
            <el-option label="换电站管理员" value="2"></el-option>
            <el-option label="用户" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属换电站编号" prop="stationNumber" v-show="form.roleId === '2'">
          <el-select v-model="form.stationNumber" placeholder="所属换电站编号">
            <el-option  v-for="station in stations" :key="station.stationNumber" :label="station.stationNumber" :value="station.stationNumber"></el-option>
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
      stations:[],
      form: {}
    }
  },
  created() {
    const userId = this.$route.query.userId
    request.get("/user/" + userId).then(res => {
      this.form = res.data
      if (this.form.roleId === 1){
        this.form.roleId = "管理员"
      }
      else if (this.form.roleId === 2){
        this.form.roleId = "换电站管理员"
      }
      else if (this.form.roleId === 3){
        this.form.roleId = "用户"
      }
    })
  },
  methods: {
    save() {
      if (this.form.roleId === "管理员"){
        this.form.roleId = 1
      }
      else if (this.form.roleId === "换电站管理员"){
        this.form.roleId = 2
      }
      else if (this.form.roleId === "用户"){
        this.form.roleId = 3
      }
      request.put('/user/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/user")
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    selectStation(){
      request.get("/station/page").then(res => {
        this.stations = res.data.list
      })
    }
  }
}

</script>

