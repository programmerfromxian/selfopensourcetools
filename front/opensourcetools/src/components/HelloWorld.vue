<template>
  <li @click='handleDelete'>{{name}}</li>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: ['name', 'index'],
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    handleDelete: function() {
      this.$emit('delete', this.index)
    }
  }
}
</script>
