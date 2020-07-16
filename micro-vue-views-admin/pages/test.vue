<template>
  <div v-html="getArticle">
  </div>
</template>

<script>
  import { mavonEditor } from 'mavon-editor'
  import marked from 'marked'
  export default {
    name: "test",
    computed:{
      getArticle(){
        // let render = mavonEditor.getMarkdownIt();
        console.log(this.content)
        return marked(this.content);
      }
    },
    data(){
      return{
        content:"# how to use mavonEditor in nuxt.js\n" +
          "# 1232131231\n" +
          "<h4><a id=\"how_to_use_mavonEditor_in_nuxtjs_0\"></a>how to use mavonEditor in nuxt.js</h4> <h1><a id=\"1232131231_1\"></a>1232131231</h1>\n"
      }
    }
  }
</script>

<style scoped>

</style>
