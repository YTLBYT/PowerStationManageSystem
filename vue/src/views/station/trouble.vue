<template>
  <div style="width: 80%">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电站管理</el-breadcrumb-item>
      <el-breadcrumb-item>故障上报</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 50px" :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="电站id" prop="username">
        <el-input v-model="form.username" placeholder="请输入电站ID"></el-input>
      </el-form-item>
      <el-form-item label="故障描述" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入故障描述"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'AddAdmin',
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入电站ID', trigger: 'blur'},
          { min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/admin/save', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
  }
}

</script>

