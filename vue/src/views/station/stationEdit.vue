<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/info' }">电站管理</el-breadcrumb-item>
        <el-breadcrumb-item>电站修改</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" label-width="100px">
        <el-form-item label="换电站编号">
          <el-input v-model="form.stationNumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="换电站名称">
          <el-input v-model="form.stationName" placeholder="请输入换电站名称"></el-input>
        </el-form-item>
        <el-form-item label="换电站地址">
          <el-input v-model="form.stationAddress" placeholder="请输入换电站地址"></el-input>
        </el-form-item>
        <el-form-item label="可用电池数量">
          <el-input v-model="form.chargeNumber" placeholder="请输入可用电池数量"></el-input>
        </el-form-item>
        <el-form-item label="总电池数量">
          <el-input v-model="form.chargeTotal" placeholder="请输入总电池数量"></el-input>
        </el-form-item>
        <el-form-item label="视频流地址">
          <el-input v-model="form.videoUrl" placeholder="请输入视频流地址"></el-input>
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
  name: 'EditStation',
  data() {
    return {
      form: {}
    }
  },
  created() {
    const stationNumber = this.$route.query.stationNumber
    request.get("/station/" + stationNumber).then(res => {
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

