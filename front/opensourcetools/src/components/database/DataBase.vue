<template>
  <el-container>
    <el-aside>
      <el-scrollbar style="height: 800px;">
      <side-tree :connectResult='connectResult'></side-tree>
      </el-scrollbar>
    </el-aside>
    <el-main>
      <record></record>
    </el-main>
  </el-container>
</template>

<script>
import SideTree from './SideTree'
import Record from './Record'
export default {
  components: {
    'side-tree': SideTree,
    'record': Record
  },
  name: 'DataBase',
  props: ['connectResult'],
  data() {
    return {
      currentTable: ''
    }
  },
  methods: {
  }
}
</script>
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
<style scoped>
</style>
