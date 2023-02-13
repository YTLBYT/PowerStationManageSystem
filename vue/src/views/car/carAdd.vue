<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/car' }">汽车管理</el-breadcrumb-item>
        <el-breadcrumb-item>汽车新增</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="150px">
        <el-form-item label="汽车品牌" prop="userName">
          <el-input v-model="form.carBrand" placeholder="请输入汽车品牌"></el-input>
        </el-form-item>
        <el-form-item label="汽车型号" prop="telephone">
          <el-input v-model="form.carType" placeholder="请输入汽车型号"></el-input>
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
  name: 'AddCar',
  data() {
    return {
      form: {},
      rules: {
      }
    }
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/car/add', this.form).then(res => {
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

