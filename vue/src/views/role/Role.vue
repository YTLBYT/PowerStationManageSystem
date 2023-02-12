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
            placeholder="请输入名称"
            suffix-icon="el-icon-search"
            v-model="params.roleName"
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
        <el-table-column prop="roleId" label="序号" width="80" align="center"></el-table-column>
        <el-table-column prop="roleName" label="角色名" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template v-slot="scope">
            <!--          scope.row 就是当前行数据-->
            <el-button type="info" @click="selectMenu(scope.row)">分配菜单 <i class="el-icon-menu"></i></el-button>
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

    <el-dialog title="菜单分配" :visible.sync="menuDialogVis" width="30%">
      <el-tree
          :props="props"
          :data="menuData"
          show-checkbox
          node-key="id"
          ref="tree"
          :default-expanded-keys="expends"
          :default-checked-keys="checks">
         <span class="custom-tree-node" slot-scope="{ node, data }">
            <span><i :class="data.icon"></i> {{ data.name }}</span>
         </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="menuDialogVis = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: 'Video',
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 10,
        roleName: '',
        telephone: ''
      },
      name: "",
      form: {},
      dialogFormVisible: false,
      menuDialogVis: false,
      multipleSelection: [],
      menuData: [],
      props: {
        label: 'name',
      },
      expends: [],
      checks: [],
      roleId: 0,
      roleFlag: '',
      ids: []
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
      request.get('/role/page', {
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
    //风格函数，使文字居中
    rowStyle() {
      return "text-align:center";
    },
  }
}
</script>

<style scoped>

</style>