<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>车辆管理</el-breadcrumb-item>
      <el-breadcrumb-item>车辆信息</el-breadcrumb-item>
    </el-breadcrumb>
    <el-main>
      <div style="padding: 10px 0;">
        <el-input
            placeholder="请输入汽车品牌"
            suffix-icon="el-icon-search"
            v-model="params.carBrand"
            style="width: 200px; margin-top: 0px">
        </el-input>
        <el-input
            placeholder="请输入汽车型号"
            suffix-icon="el-icon-search"
            v-model="params.carType"
            style="width: 200px; margin-top: 0px">
        </el-input>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i> 搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置</el-button>
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd()">新增
        </el-button>
      </div>
      <el-table :data="tableData"
                :cell-style="rowStyle"
                stripe row-key="carId"
                default-expand-all>
        <el-table-column prop="carId" label="序号" width="80" align="center"></el-table-column>
        <el-table-column prop="carBrand" label="汽车品牌" align="center"></el-table-column>
        <el-table-column prop="carType" label="汽车型号" align="center"></el-table-column>
        <!--      <el-table-column prop="updatetime" label="更新时间"></el-table-column>-->
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button type="primary" @click="$router.push('/carEdit?carId=' + scope.row.carId)">编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px"
                title="您确定删除这行数据吗？"
                @confirm="del(scope.row.carId)"
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
export default {
  name: 'Video',
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        carBrand: '',
        carType: ''
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
      request.get('/car/page', {
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
    del(carId) {
      request.delete("/car/delete/" + carId).then(res => {
        if (res.code === '200') {
          this.$notify.success('删除成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },

    //添加函数，跳转到添加列表
    handleAdd(){
      this.$router.push("/carAdd");
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