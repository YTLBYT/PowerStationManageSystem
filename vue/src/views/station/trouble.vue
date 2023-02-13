<template>
  <div style="width: 80%">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电站管理</el-breadcrumb-item>
      <el-breadcrumb-item>故障上报</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form style="margin-top: 50px" :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
      <el-form-item label="电站编号" prop="stationNumber">
        <el-select v-model="form.stationNumber"  filterable placeholder="请选择电站编号" @change="selectStationByNumber">
          <el-option
              v-for="item in stations"
              :key="item.stationId"
              :label="item.stationNumber"
              :value="item.stationNumber">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电站名称" prop="stationName">
        <el-input v-model="form.stationName" disabled></el-input>
      </el-form-item>
      <el-form-item label="电站地址" prop="stationAddress">
        <el-input v-model="form.stationAddress" disabled></el-input>
      </el-form-item>
      <el-form-item label="故障描述" prop="troubleDescribe">
        <el-input
            style="width: 700px"
            type="textarea"
            :rows="2"
            placeholder="请输入故障描述"
            v-model="form.troubleDescribe">
        </el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: 'AddTrouble',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      form: {},
      stations:[],
      rules: {
        stationNumber: [
          { required: true, message: '请输入电站编号', trigger: 'blur'},
        ],
        troubleDescribe:[
          { required: true, message: '请输入故障描述', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    request.get('/station/page').then(res => {
      this.stations = res.data.list
    })
  },
  methods: {
    save() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/trouble/add', this.form).then(res => {
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
    selectStationByNumber(){
      const station = this.stations.find(v => v.stationNumber === this.form.stationNumber)
      request.get('/station/' + station.stationNumber).then(res =>{
        this.$set(this.form, 'stationName', res.data.stationName)
        this.form.stationAddress = res.data.stationAddress
        this.form.stationId = res.data.stationId
      })
    }
  }
}

</script>

