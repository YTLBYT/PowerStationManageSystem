<template>
  <div >
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>首页</el-breadcrumb-item>
    </el-breadcrumb>
    <div style="margin-top: 30px">
      <el-row :gutter="20">
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :precision="2" :value="value2" :title="title"></el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic  title="男女比">
              <template slot="formatter">
                456/2
              </template>

            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
              <template slot="suffix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>

        </el-col>
        <el-col :span="6">
          <div>
            <el-statistic :value="like ? 521 : 520" title="Feedback">
              <template slot="suffix">
            <span @click="like = !like" class="like">
              <i class="el-icon-star-on" style="color:red" v-show="!!like"></i>
              <i class="el-icon-star-off"  v-show="!like"></i>
            </span> </template>
            </el-statistic>
          </div>
        </el-col>
      </el-row>
    </div>
    <div id="main" style="margin-top: 100px; height: 800px; width: 2400px"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'Home',
  data() {
    return {
      like: true,
      value1: 4154.564,
      value2: 2222,
      title: "今年的增长",
      input: "Hello Element UI!",
    }
  },
  mounted() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      darkMode: "auto",
      colorBy: "series",
      title:{
        text: '一周换电人数',
        subtext: '折线统计图',
        left: "center"
      },
      xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']

      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [150, 230, 224, 218, 135, 147, 260],
          type: 'line',
          name: "一周换电统计"
        }
      ]
    };
    myChart.setOption(option);
  }
}
</script>
