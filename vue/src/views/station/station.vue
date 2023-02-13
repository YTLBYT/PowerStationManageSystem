<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>电站管理</el-breadcrumb-item>
      <el-breadcrumb-item>电站信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-main>
      <div style="padding: 10px 0;" v-show="admin.roleId === 1">
        <el-input placeholder="请输入换电站名称" suffix-icon="el-icon-search" v-model="params.stationName" style="width: 200px; margin-top: 0px"></el-input>
        <el-input placeholder="请输入换电站编号" suffix-icon="el-icon-search" v-model="params.stationNumber" style="width: 200px; margin-top: 0px"></el-input>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd()">新增
        </el-button>
      </div>
      <el-table :data="tableData"
                :cell-style="rowStyle"
                stripe row-key="stationId"
                default-expand-all>
        <el-table-column prop="stationId" label="序号" width="80" align="center"></el-table-column>
        <el-table-column prop="stationNumber" label="换电站编号" align="center"></el-table-column>
        <el-table-column prop="stationName" label="换电站名称" align="center"></el-table-column>
        <el-table-column prop="stationAddress" label="换电站地址" align="center"></el-table-column>
        <el-table-column prop="chargeNumber" label="可用电池数量" align="center"></el-table-column>
        <el-table-column prop="chargeTotal" label="总电池数量" align="center"></el-table-column>
        <el-table-column prop="videoUrl" label="视频流地址" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" align="center">
          <template v-slot="scope">
            <el-switch
                v-model= 'scope.row.status'
                @change="changeStatus(scope.row)"
                active-color="#13ce66"
                inactive-color="#ff4949"
                :active-value = 1
                :inactive-value = 0>
            </el-switch>
          </template>
        </el-table-column>
        <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button type="primary" @click="$router.push('/editStation?stationNumber=' + scope.row.stationNumber + '&stationId=' + scope.row.stationId)">编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px"
                title="您确定删除这行数据吗？"
                @confirm="del(scope.row.stationId)"
            >
              <el-button type="danger" slot="reference" v-show="admin.roleId === 1">删除</el-button>
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
    </el-main>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'Station',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        stationName: '',
        stationNumber: '',
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    /**
     * 默认加载全部
     */
    load() {
      if (this.admin.roleId === 2){
        this.params.stationNumber = this.admin.stationNumber
      }
      request.get('/station/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    /**
     * 点击重置按钮恢复全部查询
     */
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

    /**
     * 删除当前行
     * @param stationId
     */
    del(stationId) {
      request.delete("/station/delete/" + stationId).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    /**
     * 改变状态
     * @param row
     */
    changeStatus(row){
      request.put('/station/update', row).then(res => {
        if (res.code === '200') {
          this.$notify.success('操作成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },

    //添加函数，跳转到添加列表
    handleAdd(){
      this.$router.push("/addStation");
    },

    //风格函数，使文字居中
    rowStyle() {
      return "text-align:center";
    },
  }
}
</script>

<style scoped>

</style>