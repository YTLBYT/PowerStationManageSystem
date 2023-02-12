<template>
  <div style="width: 80%">
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item :to="{ path: '/car' }">汽车管理</el-breadcrumb-item>
        <el-breadcrumb-item>汽车修改</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin-top: 30px">
      <el-form :inline="true" :model="form" label-width="150px">
        <el-form-item label="汽车品牌">
          <el-input v-model="form.carBrand"></el-input>
        </el-form-item>
        <el-form-item label="汽车型号">
          <el-input v-model="form.carType"></el-input>
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
  name: 'Editcar',
  data() {
    return {
      stations:[],
      form: {}
    }
  },
  created() {
    const carId = this.$route.query.carId
    request.get("/car/" + carId).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/car/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/car")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>

