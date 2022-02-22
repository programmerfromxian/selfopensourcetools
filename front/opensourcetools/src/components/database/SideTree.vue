<template>
  <div id='sideTree'>
    <el-tree
      :data="connectResult"
      :props="defaultProps"
      @node-click="handleNodeClick"
      :highlight-current='true'
      :expand-on-click-node='false'>
    </el-tree>
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
      },
      currentNodeLabel: ''
    }
  },
  methods: {
    handleNodeClick(data) {
      console.log('Click node label is ' + data.label);
      if (this.currentNodeLabel == data.label) {
        console.log('This node is current')
      } else if (data.nodeType == 'table') {
        console.log('Select new table node, query...')
        bus.$emit('selectTable', data)
      } else {
        console.log('Node fold')
      }
      this.currentNodeLabel = data.label
    }
  }
}
</script>

<style scoped>
</style>
