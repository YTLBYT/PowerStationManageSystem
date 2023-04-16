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
        <el-button type="primary" icon="el-icon-circle-plus-outline" style="float: right" @click="handleAdd">新增</el-button>
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
        <el-table-column label="操作" align="center" width="200px">
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

        <el-dialog title="新增电站" :visible.sync="dialogFormVisible" width="700px" center>
          <el-form :inline="true" :model="form" :rules="rules" ref="ruleForm" label-width="100px">
            <el-form-item label="换电站名称" prop="stationName">
              <el-input v-model="form.stationName" placeholder="请输入换电站名称"></el-input>
            </el-form-item>
            <el-form-item label="换电站地址" prop="stationAddress">
              <el-input v-model="form.stationAddress" placeholder="请输入换电站地址"></el-input>
            </el-form-item>
            <el-form-item label="总电池数量" prop="chargeTotal">
              <el-input v-model="form.chargeTotal" placeholder="请输入总电池数量"></el-input>
            </el-form-item>
            <el-form-item label="支持的汽车" prop="carIdList">
              <el-cascader
                  :options="options"
                  :props="{ multiple: true }"
                  v-model="carIds"
                  clearable></el-cascader>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save()">确 定</el-button>
          </div>
        </el-dialog>
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
      },
      dialogFormVisible: false,
      options: [],
      carIds:[],
      form: {},
      rules: {
        stationName: [
          { required: true, message: '请输入换电站名称', trigger: 'blur'},
          { min: 3, max: 10, message: '长度在3-10个字符', trigger: 'blur'}
        ],
        stationAddress: [
          { required:true, message: '请输入换电站地址', trigger: 'blur' }
        ]
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
      request.put('/station/updateStatus', row).then(res => {
        if (res.code === '200') {
          this.$notify.success('操作成功')
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },

    save() {
      this.form.carIdList = []
      for (let i = 0; i < this.carIds.length; i++){
        this.form.carIdList.push(this.carIds[i][1])
      }
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          request.post('/station/add', this.form).then(res => {
            if (res.code === '200') {
              this.$notify.success('新增成功')
              this.$refs['ruleForm'].resetFields()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
      this.dialogFormVisible = false
      this.$emit(this.load())
    },

    //添加函数，跳转到添加列表
    handleAdd(){
      request.get('station/carTreeList').then(res =>{
        if (res.code === '200') {
          this.options = res.data
        }
      })
      this.dialogFormVisible = true
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