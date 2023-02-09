<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/info' }">电站管理</el-breadcrumb-item>
        <el-breadcrumb-item>电站新增</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="换电站名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入换电站名称"></el-input>
        </el-form-item>
        <el-form-item label="换电站地址" prop="stationAddress">
          <el-input v-model="form.stationAddress" placeholder="请输入换电站地址"></el-input>
        </el-form-item>
        <el-form-item label="总电池数量" prop="chargeNumber">
          <el-input v-model="form.chargeTotal" placeholder="请输入总电池数量"></el-input>
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
  name: 'AddStation',
  data() {
    return {
      form: {},
      rules: {
        stationName: [
          { required: true, message: '请输入换电站地址', trigger: 'blur'},
          { min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ],
        stationAddress: [
          { required:true, message: '请输入换电站地址', trigger: 'blur' }
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

