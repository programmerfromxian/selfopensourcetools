<template>
  <div id='sideTree'>
    <el-tree :data="connectResult" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
  </div>
</template>

<script>
import bus from '@/assets/js/eventBus'
export default {
  name: 'SideTree',
  props: ['connectResult'],
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'label',
        nodeType: '',
        parentNodeLabel: ''
      }
    }
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
      if (data.nodeType == 'table') {
        bus.$emit('selectTable', data)
      }
    }
  }
}
</script>

<style scoped>
</style>
