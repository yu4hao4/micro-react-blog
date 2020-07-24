<template>
  <div>
    {{ path }}
  </div>
</template>

<script>
  export default {
    name: "dynamic",
    mounted() {
      console.log(this.$route.fullPath)
      console.log(this.$route.params)
      this.path = this.$route.params.pathMatch;
    },
    head () {
      return {
        title: this.path,
        meta: [
          { hid: 'description', name: 'description', content: 'My custom description' }
        ]
      }
    },
    data(){
      return {
        path:""
      }
    }
  }
</script>

<style scoped>

</style>
