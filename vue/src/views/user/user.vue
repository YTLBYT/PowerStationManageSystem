<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-main>
      <div style="padding: 10px 0;">
        <el-input
            placeholder="请输入用户名"
            suffix-icon="el-icon-search"
            v-model="params.userName"
            style="width: 200px; margin-top: 0px">
        </el-input>
        <el-input
            placeholder="请输入手机号"
            suffix-icon="el-icon-search"
            v-model="params.telephone"
            style="width: 200px; margin-top: 0px">
        </el-input>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd()">新增
        </el-button>
      </div>
      <el-table :data="tableData"
                :cell-style="rowStyle"
                stripe row-key="userId"
                default-expand-all>
        <el-table-column prop="userId" label="序号" width="100px" align="center"></el-table-column>
        <el-table-column prop="userName" label="用户名" width="100px" align="center"></el-table-column>
        <el-table-column prop="telephone" label="手机号" align="center"></el-table-column>
        <el-table-column label="角色" align="center">
          <template slot-scope="scope">
          <span v-if="scope.row.roleId === 1">
            管理员
          </span>
          <span v-if="scope.row.roleId === 2">
              换电站管理员
          </span>
          <span v-if="scope.row.roleId === 3">
            用户
          </span>
          </template>
        </el-table-column>
        <el-table-column prop="stationNumber" label="所属换电站编号" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" align="center">
          <template v-slot="scope">
            <el-switch
                v-model= 'scope.row.status'
                @change="changeStatus(scope.row)"
                active-color="#13ce66"
                inactive-color="#ff4949"
                :active-value = "1"
                :inactive-value="0">
            </el-switch>
          </template>
        </el-table-column>
        <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
        <el-table-column label="操作" align="center" width="300px">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button type="primary" @click="$router.push('/userEdit?userId=' + scope.row.userId)">编辑</el-button>
            <el-button type="warning" @click="resetUserPassword(scope.row.userId)">重置密码</el-button>
            <el-popconfirm
                style="margin-left: 5px"
                title="您确定删除这行数据吗？"
                @confirm="del(scope.row.userId)"
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
    </el-main>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'Video',
  data() {
    return {
      admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        userName: '',
        telephone: ''
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
      request.get('/user/page', {
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
     * @param userId
     */
    del(userId) {
      request.delete("/user/delete/" + userId).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    resetUserPassword(userId){
      request.put("/user/resetUserPassword/" + userId).then(res => {
        if (res.code === '200') {
          this.$notify.success('重置密码成功')
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
      request.put('/user/update', row).then(res => {
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
      this.$router.push("/userAdd");
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