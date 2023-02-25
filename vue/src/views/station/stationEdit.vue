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
        <el-form-item label="支持的汽车">
          <div class="block">
            <el-cascader
                 v-if="isSelected"
                 @change="modeChange"
                :options="options"
                :props="{ multiple: true}"
                v-model="this.defaultCars"
                clearable></el-cascader>
          </div>
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
      options:[],
      isSelected: true,
      defaultCars:[],
      form: {}
    }
  },
  created() {
    this.getCarList()
    const stationNumber = this.$route.query.stationNumber
    const stationId = this.$route.query.stationId
    request.get("/station/car/" + stationId).then(res => {
      let carsId = res.data
      for (let i=0; i<=carsId.length; i++){
        let list = [0]
        list.push(carsId[i])
        this.defaultCars.push(list)
      }
    })
    request.get("/station/" + stationNumber).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      this.form.carIdList = []
      for (let i = 0; i < this.defaultCars.length; i++){
        this.form.carIdList.push(this.defaultCars[i][1])
      }
      request.put('/station/update', this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.$router.push("/info")
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    getCarList(){
      request.get('station/carTreeList').then(res =>{
        if (res.code === '200') {
          this.options = res.data
        }
      })
    },
    modeChange(val){
      this.isSelected = false
      this.defaultCars = val
      this.isSelected = true
    }

  }
}

</script>

