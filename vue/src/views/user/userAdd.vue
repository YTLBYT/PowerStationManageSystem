<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户新增</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="150px">
        <el-form-item label="用户名称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名称"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="telephone">
          <el-input v-model="form.telephone" placeholder="请输入手机号"></el-input>
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
  name: 'AddUser',
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error('请输入合法的手机号'));
      }
      callback()
    };
    return {
      stations:[],
      form: {},
      rules: {
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur'},
          { min: 2, max: 10, message: '长度在2-10个字符', trigger: 'blur'}
        ],
        telephone: [
          { validator: checkPhone, required: true,trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/user/add', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    },
    selectStation(){
      request.get("/station/page", {
        params: this.params
      }).then(res => {
        this.stations = res.data.list
      })
    }
  }
}

</script>

