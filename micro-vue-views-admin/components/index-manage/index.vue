<template>
  <div>
    <a-row>
      <a-col :span="2" :offset="22">
        <a-button type="primary" icon="download" size="large" @click="save">
          保存
        </a-button>
      </a-col>
    </a-row>
    <MarkDownEditor ref="markdown" :content='content' @changeContent="changeContent"/>
  </div>
</template>

<script>
  import MarkDownEditor from '~/components/markdown-editor'
  export default {
    name: "index",
    components:{
      MarkDownEditor
    },
    data(){
      return {
        content:"1111saadsdasdddsadsad"
      }
    },
    methods:{
      changeContent(val){
        this.content = val;
      },
      save(){
        this.content;
      }
    }
  }
</script>

<style scoped>

</style>
