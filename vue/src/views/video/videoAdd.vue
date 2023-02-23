<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/video' }">视频管理</el-breadcrumb-item>
        <el-breadcrumb-item>视频新增</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="150px">
        <el-form-item label="视频流地址" prop="videoUrl">
          <el-input v-model="form.videoUrl" placeholder="请输入视频流地址"></el-input>
        </el-form-item>
        <el-form-item label="来源" prop="origin">
          <el-input v-model="form.origin" placeholder="请输入来源"></el-input>
        </el-form-item>
        <el-form-item label="流传输协议" prop="agreement">
          <el-select v-model="form.agreement" placeholder="流传输协议">
            <el-option label="RTMP" value="RTMP"></el-option>
            <el-option label="RTSP" value="RTSP"></el-option>
            <el-option label="HLS" value="HLS"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入口带宽" prop="bandWidth">
          <el-input v-model="form.bandWidth" placeholder="请输入带宽"></el-input>
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
import Cookies from "js-cookie";

export default {
  name: 'AddVideo',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      form: {},
      rules: {
      }
    }
  },
  methods: {
    save() {
      this.form.stationNumber = this.admin.stationNumber;
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/video/add', this.form).then(res => {
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

