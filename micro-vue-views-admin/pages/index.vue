<template>
  <div>
    <a-layout id="components-layout-demo-fixed">
      <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
        <a-row>
<!--          <a-col :span="16" :offset="4">-->
<!--            <span style="font-size: 25px;color: white">喻浩的实习准备</span>-->
<!--          </a-col>-->
        </a-row>
      </a-layout-header>
      <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
        <a-row>
          <a-col :span="16" :offset="4">
            <div  v-if="content === ''" :style="{ background: '#fff', padding: '24px', minHeight: '480px' }">
              <a-spin :style="{ marginLeft: '45%', marginTop: '15%' }" size="large" />
            </div>
            <mavon-editor v-else :ishljs="true" fontSize="16px" :editable="false" :toolbarsFlag="false"
                          defaultOpen="preview" :subfield="false" :value="this.content"
            />
          </a-col>
        </a-row>
      </a-layout-content>
      <a-layout-footer :style="{ textAlign: 'center' }">
          yuhao5 ©2020 Created by AntD
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
  import { getIndexArticle } from '~/utils/api'
  // import marked from 'marked'
  let query = {"current":1,"total":100,"pageSize":6,"pageSizeOpts":[10,20,30,40,50],"id":4};
  export default {
    name: "test",
    // async asyncData () {
    //   const resp = await getIndexArticle().then(resp => {
    //     return resp;
    //   })
    //   console.log(111)
    //   console.log(resp)
    //   return {resp};
    // },
    beforeCreate() {
      this.$router.push("/admin")
    },
    mounted() {
      // this.getIndex();
    },
    computed:{
      getArticle(){
        // let render = mavonEditor.getMarkdownIt();
        // console.log(this.content)
        // console.log(this.resp.data.obj.list[0].articleContent)
        // let indexContent = this.resp.data.obj.list[0].articleContent;
        // return marked(this.content);
        // console.log(this.content)
        // return marked(this.content);
      }
    },
    head () {
      return {
        title: "首页",
        meta: [
          { hid: 'description', name: 'description', content: 'My custom description' }
        ]
      }
    },
    data(){
      return{
        content:"",
      }
    },
    methods:{
      async getIndex(){
        await getIndexArticle().then(resp => {
          this.content = resp.data.obj.list[0].articleContent
        })
      },
    }
  }
</script>

<style scoped>
  #components-layout-demo-fixed .logo {
    width: 120px;
    height: 31px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px 24px 16px 0;
    float: left;
  }
</style>
