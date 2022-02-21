<template>
  <div>
    <div id='connectInfo'>
      <el-form ref="form" :model="connectInfo"  :inline='true' >
        <el-form-item label="type">
          <el-input size='mini' v-model="connectInfo.type" style='width: 100px'></el-input>
        </el-form-item>
        <el-form-item label="ip">
          <el-input size='mini' v-model="connectInfo.host" style='width: 100px'></el-input>
        </el-form-item>
        <el-form-item label="port">
          <el-input size='mini' v-model="connectInfo.port" style='width: 100px'></el-input>
        </el-form-item>
        <el-form-item label="username">
          <el-input size='mini' v-model="connectInfo.username" style='width: 100px'></el-input>
        </el-form-item>
        <el-form-item label="password">
          <el-input size='mini' type='password' v-model="connectInfo.password" style='width: 100px'></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size='mini' type="primary" @click="connectTest">测试连接</el-button>
        </el-form-item>
        <el-form-item>
          <el-button v-show='testConnectSuccess' size='mini' type="primary" @click="connect">连接</el-button>
        </el-form-item>
      </el-form>
    </div>
    <database :connectResult='connectResult'></database>
    <!--<router-view/>-->
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'
import DataBase from './components/database/DataBase'
import axios from 'axios'
import bus from '@/assets/js/eventBus'
export default {
  components: {
    'hello-world': HelloWorld,
    'database': DataBase
  },
  data: function() {
    return {
      inputValue: '',
      list: [],
      connectInfo: {
        type: 'mysql',
        host: '',
        port: 3306,
        username: 'root',
        password: ''
      },
      testConnectMsg: '',
      testConnectSuccess: false,
      connectResult: []
    }
  },
  methods: {
    handleSubmit: function() {
      this.list.push(this.inputValue)
      this.inputValue = ''
    },
    handleDelete: function(index) {
      alert(index)
      this.list.splice(index, 1)
    },
    connectTest: function() {
      console.log('Start test connect ip ' + this.connectInfo.host)
      axios({
        method: 'post',
        data: this.connectInfo,
        url: 'http://localhost:10000/opensource/database/test'
      }).then(res => {
        var success = res.data.success
        this.testConnectSuccess = success
        console.log('connect test result is ' + success)
        if (!success) {
          this.testConnectMsg = res.data.errMsg
        } else {
          this.testConnectMsg = res.data.data
        }
      }).catch(response => {
        console.log('Connect test error, response is ' + response)
      })
    },
    connect: function() {
      console.log('Start connect to ip ' + this.connectInfo.host)
      axios({
        method: 'post',
        data: this.connectInfo,
        url: 'http://localhost:10000/opensource/database/connect'
      }).then(res => {
        var success = res.data.success
        console.log('connect test result is ' + success)
        if (!success) {
          alert(res.data.errMsg)
        } else {
          console.log('Connect result is ' + JSON.toString(res.data.data))
          this.connectResult = res.data.data
          bus.$emit('transferConnectInfo', this.connectInfo)
        }
      }).catch(response => {
        console.log('Connect test error, response is ' + response)
      })
    }
  }
}
</script>

<style scoped>
  #connectInfo {
    width: 1000px;
  }
</style>
