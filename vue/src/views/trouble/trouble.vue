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
      <el-input style="width: 240px" placeholder="请输入图书名称" v-model="params.bookName"></el-input>
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入图书标准码" v-model="params.bookNo"></el-input>
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入用户名称" v-model="params.userName"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
    </div>

    <el-table :data="tableData" stripe row-key="id"  default-expand-all>
      <el-table-column prop="id" label="故障编号" width="80"></el-table-column>
      <el-table-column prop="stationId" label="换电站编号"></el-table-column>
      <el-table-column prop="troubleDescribe" label="故障描述"></el-table-column>
      <el-table-column prop="stationName" label="换电站名称"></el-table-column>
      <el-table-column prop="createTime" label="上报日期"></el-table-column>
      <el-table-column prop="resolveDate" label="处理日期"></el-table-column>
      <el-table-column prop="note" label="故障状态">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.note === '已处理'">{{ scope.row.note }}</el-tag>
          <el-tag type="danger" v-if="scope.row.note === '未处理'">{{ scope.row.note }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="管理">
        <template v-slot="scope">
          <el-button type="primary" @click="returnBooks(scope.row)" v-if="scope.row.note === '未处理'">排除</el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
      <el-table-column label="操作">
        <template v-slot="scope">
          <!--          scope.row 就是当前行数据-->
          <!--          <el-button type="primary" @click="$router.push('/editBorrow?id=' + scope.row.id)">编辑</el-button>-->
          <el-popconfirm
              style="margin-left: 5px"
              title="您确定删除这行数据吗？"
              @confirm="del(scope.row.id)"
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
      tableData: [{
        id: 1,
        stationId: 734672,
        troubleDescribe: '线路故障',
        stationName: '易电石油大学站',
        createTime: '2023-2-8',
        resolveDate: '2023-2-9',
        note: '已处理'
      },
        {
          id: 2,
          stationId: 734672,
          troubleDescribe: '停电',
          stationName: '易电石油大学站',
          createTime: '2023-2-8',
          note: '未处理'
        }],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        name: '',
        bookNo: ''
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
    del(id) {
      request.delete("/trouble/delete/" + id).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    returnBooks(row) {
      request.post("/trouble/saveRetur", row).then(res => {
        if (res.code === '200') {
          this.$notify.success('还书成功')
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
