<template>
  <div>
<!--    <a-row>-->
<!--      <a-col :span="2" :offset="22">-->
<!--        <a-button type="primary" icon="upload" size="large" @click="save">-->
<!--          保存-->
<!--        </a-button>-->
<!--      </a-col>-->
<!--    </a-row>-->
    <MarkDownEditor ref="markdown" :content='indexArticle.articleContent'
                    @changeContent="changeContent"
                    @saveContent="save"/>
  </div>
</template>

<script>
  import MarkDownEditor from '~/components/markdown-editor'
  import { getIndexArticle, updateArticle } from '~/utils/api'
  export default {
    name: "index",
    components:{
      MarkDownEditor
    },
    data(){
      return {
        indexArticle:{
          articleContent:""
        }
      }
    },
    mounted() {
      this.getIndex();
    },
    methods:{
      async getIndex(){
        await getIndexArticle().then(resp => {
          this.indexArticle = resp.data.obj.list[0]
        })
      },
      changeContent(val){
        this.indexArticle.articleContent = val;
      },
      save(){
        updateArticle(this.indexArticle)
          .then(resp => {
            console.log(resp)
          })
      }
    }
  }
</script>

<style scoped>

</style>
