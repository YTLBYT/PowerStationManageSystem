<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>视频管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-main>
      <div style="padding: 10px 0;">
        <el-input
            placeholder="请输入内容"
            suffix-icon="el-icon-search"
            v-model="input"
            style="width: 200px; margin-top: 0px">
        </el-input>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd()">新增
        </el-button>
      </div>
      <el-table :data="tableData"
                :cell-style="rowStyle"
                stripe row-key="videoId"
                default-expand-all>
        <el-table-column prop="videoId" label="编号" width="80" align="center"></el-table-column>
        <el-table-column prop="videoUrl" label="视频流地址" align="center"></el-table-column>
        <el-table-column prop="origin" label="来源" align="center"></el-table-column>
        <el-table-column prop="agreement" label="流传输协议" align="center"></el-table-column>
        <el-table-column prop="bandWidth" label="入口带宽" align="center"></el-table-column>
        <el-table-column prop="carsNumber" label="实时排队车辆结果" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" align="center">
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.status === '上线'">{{ scope.row.status }}</el-tag>
            <el-tag type="danger" v-if="scope.row.status === '下线'">{{ scope.row.status }}</el-tag>
          </template>
        </el-table-column>
        <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button type="primary" @click="$router.push('/editBorrow?videoId=' + scope.row.videoId)">编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px"
                title="您确定删除这行数据吗？"
                @confirm="del(scope.row.videoId)"
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
      tableData: [{
        videoId: 1,
        videoUrl: 'https://ServeIp/live/stream.m3u8',
        origin: '推流',
        agreement: 'HLS',
        bandWidth: '300Mkbps',
        carsNumber: 5,
        status: '上线'
      },
        {
          videoId: 2,
          videoUrl: 'https://ServeIp/live/stream.m3u8',
          origin: '推流',
          agreement: 'RTSP',
          bandWidth: '300Mkbps',
          carsNumber: 2,
          status: '上线'
        },
        {
          videoId: 3,
          videoUrl: 'https://ServeIp/live/stream.m3u8',
          origin: '推流',
          agreement: 'HLS',
          bandWidth: '300Mkbps',
          carsNumber: 3,
          status: '上线'
        },
        {
          videoId: 4,
          videoUrl: 'https://ServeIp/live/stream.m3u8',
          origin: '推流',
          agreement: 'HLS',
          bandWidth: '300Mkbps',
          status: '下线'
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
      request.get('/borrow/page', {
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
    del(videoId) {
      request.delete("/borrow/delete/" + videoId).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handleAdd(){

    },
    rowStyle() {
      return "text-align:center";
    },
  }
}
</script>

<style scoped>

</style>