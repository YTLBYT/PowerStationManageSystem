<template>
  <div>
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>故障管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!--    搜索表单-->
    <div style="margin-bottom: 20px; margin-top: 30px">
      <el-input style="width: 240px" placeholder="请输入换电站编号" v-model="params.stationNumber"></el-input>
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入换电站地址" v-model="params.stationAddress"></el-input>
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入上报日期" v-model="params.createTime"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
    </div>

    <el-table :data="tableData" stripe row-key="troubleId"  default-expand-all>
      <el-table-column prop="troubleId" label="故障编号" width="80"></el-table-column>
      <el-table-column prop="stationNumber" label="换电站编号"></el-table-column>
      <el-table-column prop="troubleDescribe" label="故障描述"></el-table-column>
      <el-table-column prop="stationAddress" label="换电站地址"></el-table-column>
      <el-table-column prop="createTime" label="上报日期"></el-table-column>
      <el-table-column prop="resolveTime" label="处理日期"></el-table-column>
      <el-table-column prop="userName" label="处理人姓名"></el-table-column>
      <el-table-column prop="telephone" label="处理人手机号"></el-table-column>
      <el-table-column prop="status" label="故障提示">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.status === -1">已处理</el-tag>
          <el-tag type="warning" v-if="scope.row.status === 0">正在处理</el-tag>
          <el-tag type="danger" v-if="scope.row.status === 1">未处理</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="管理">
        <template v-slot="scope">
          <el-button type="primary" @click="resolve(scope.row)" v-if="scope.row.status === 1">处理</el-button>
          <el-button type="success" @click="resolve(scope.row)" v-if="scope.row.status === 0">处理完成</el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
      <el-table-column label="操作">
        <template v-slot="scope">
          <!--          scope.row 就是当前行数据-->
          <!--          <el-button type="primary" @click="$router.push('/editBorrow?troubleId=' + scope.row.troubleId)">编辑</el-button>-->
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定删除这行数据吗？"
              @confirm="del(scope.row.troubleId)"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <div style="margin-top: 20px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'TroubleList',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        stationNumber: '',
        stationAddress: '',
        createTime:''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/trouble/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        bookName: '',
        bookNo: '',
        userName: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      // 点击分页按钮触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(troubleId) {
      request.delete("/trouble/delete/" + troubleId).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    resolve(row) {
      row.userName = this.admin.userName
      row.telephone = this.admin.telephone
      if (row.status === 1){
        row.status = 0
      }
      else if (row.status === 0){
        row.status = -1
      }
      request.put("/trouble/update", row).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新故障状态成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
