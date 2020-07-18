<template>
  <div v-html="getArticle">
  </div>
</template>

<script>
  import { getIndexArticle } from '~/utils/api'
  import marked from 'marked'
  let query = {"current":1,"total":100,"pageSize":6,"pageSizeOpts":[10,20,30,40,50],"id":4};
  export default {
    name: "test",
    async asyncData () {
      // return await axios.get(`https://my-api/posts/${params.id}`)
      //   .then((res) => {
      //     return { title: res.data.title }
      //   })
      // return await get(query)
      //   .then((resp) => {
      //     // let queryData = resp.data;
      //     return {resp};
      //     // return { title: res.data.title }
      //   })
      let beginTime = new Date().getMilliseconds();
      let [resp] = await Promise.all([
        getIndexArticle()
      ])
      let endTime = new Date().getMilliseconds();
      console.log(endTime - beginTime)
      return {resp};
    },
    computed:{
      getArticle(){
        // let render = mavonEditor.getMarkdownIt();
        // console.log(this.content)
        // console.log(this.resp)
        return marked(this.content);
      }
    },
    mounted() {
      console.log(this.resp)
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
