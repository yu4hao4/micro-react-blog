<template>

</template>

<script>
  export default {
    name: "dynamic",
    mounted() {
      console.log(this.$route.fullPath)
      console.log(this.$route.params)
    }
  }
</script>

<style scoped>

</style>
