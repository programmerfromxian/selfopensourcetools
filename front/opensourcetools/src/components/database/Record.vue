<template>
  <div id='record'>
    <el-table
      v-loading="loading"
      element-loading-text="加载中"
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      :data="recordResult.recordList"
      style="width: 100%;height: 650px">
      <el-table-column v-for='(column, key) of recordResult.columnList'
        :prop="column"
        :label="column"
        :key="key"
        width="180">
        <template slot-scope='scope'>
          <span>{{scope.row[key]}}</span>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @current-change='handleChangePage'
      layout="prev, pager, next"
      :total="page.totalCount">
    </el-pagination>
  </div>
</template>

<script>
import bus from '@/assets/js/eventBus'
import axios from 'axios'
export default {
  name: 'Record',
  data() {
    return {
      recordList: {},
      connectInfo: {
      },
      recordResult: {
        columnList: [],
        recordList: []
      },
      page: {
        pageNumber: 1,
        pageSize: 10,
        totalPage: 10,
        totalCount: 100
      },
      parentNodeLabel: '',
      label: '',
      loading: false
    }
  },
  mounted() {
    var _this = this
    bus.$on('selectTable', data => {
      console.log('Select table ' + data.parentNodeLabel + '.' + data.label)
      _this.parentNodeLabel = data.parentNodeLabel
      _this.label = data.label
      axios({
        method: 'post',
        url: 'http://localhost:10000/opensource/database/record',
        params: {
          tableName: data.parentNodeLabel + '.' + data.label,
          pageNumber: _this.page.pageNumber,
          pageSize: _this.page.pageSize
        },
        data: this.connectInfo
      }).then(res => {
        console.log("Get record success " + res.data)
        _this.recordResult = res.data.data
        _this.page.totalCount = res.data.data.totalCount
      }).catch(response => {
      })
    }),
    bus.$on('transferConnectInfo', data => {
      _this.connectInfo = data
    })
  },
  methods: {
    handleChangePage: function(val) {
      console.log('Current page is ' + val);
      this.page.pageNumber = val
      axios({
        method: 'post',
        url: 'http://localhost:10000/opensource/database/record',
        params: {
          tableName: this.parentNodeLabel + '.' + this.label,
          pageNumber: this.page.pageNumber,
          pageSize: this.page.pageSize
        },
        data: this.connectInfo
      }).then(res => {
        console.log("Get record success " + res.data)
        this.recordResult = res.data.data
        this.page.totalCount = res.data.data.totalCount
      }).catch(response => {
      })
    }
  }
}
</script>

<style scoped>
</style>
