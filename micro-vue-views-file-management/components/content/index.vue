<template>
  <div>
    <Row :gutter="20">
      <Col :span="4">
        <Input v-model="page.name" :maxlength="10" clearable show-word-limit placeholder="文件名" size="large" />
      </Col>
      <Col :span="2">
        <Button type="primary" shape="circle" icon="ios-search" size="large" @click="initData">搜 索</Button>
      </Col>
      <Col :span="2" :offset="14">
        <Button type="primary" shape="circle" size="large" @click="openUploadModal">上传文件</Button>
      </Col>
      <Col :span="2">
        <Button type="error" shape="circle" size="large" @click="openDeleteConfirmModal">删除选中</Button>
      </Col>
    </Row>
    <Row :gutter="40">
      {{selections}}
      <Col :span="4" v-for="(item, key) in tableData" :key="key">
        <span @click="chooseItem(item)" >
          <Card dis-hover style="margin: 32px 0;">
            <Poptip trigger="hover" width="400" placement="right-start">
              <template #content >
                <img :src="item.url" width="100%" height="250px">
              </template>
              <img :src="item.url" width="100%" height="60px">
            </Poptip>
            <h3 :style="getColor(item)">{{getImageName(item.url)}}</h3>
          </Card>
        </span>
      </Col>
    </Row>
    <!--    分页组件-->
    <div style="margin: 10px;overflow: hidden">
      <div style="float: right;">
        <Page :total="page.total" :current="page.current"
              :page-size="page.pageSize" :page-size-opts="page.pageSizeOpts"
              show-elevator show-sizer show-total
              @on-change="currentPageChange" @on-page-size-change="pageSizeChange"/>
      </div>
    </div>

    <Modal
      v-model="deleteModal"
      title="删除数据"
      @on-cancel="deleteModal = false">
      <p>确定要删除选中的数据吗？</p>
      <div slot="footer">
        <Button type="error" size="large" long @click="deleteChoose">确定删除</Button>
      </div>
    </Modal>

    <Modal
      v-model="uploadModal"
      title="上传">
      <div class="demo-upload-list" v-for="item in uploadList">
        <template v-if="item.status === 'finished'">
          <img :src="item.url">
          <div class="demo-upload-list-cover">
            <Icon type="ios-eye-outline" @click.native="handleView(item.name)"></Icon>
            <Icon type="ios-trash-outline" @click.native="handleRemove(item)"></Icon>
          </div>
        </template>
        <template v-else>
          <Progress v-if="item.showProgress" :percent="item.percentage" hide-info></Progress>
        </template>
      </div>
      <Upload
        ref="upload"
        :show-upload-list="false"
        :default-file-list="uploadList"
        :on-success="handleSuccess"
        :format="['jpg','jpeg','png']"
        :max-size="2048"
        :on-format-error="handleFormatError"
        :on-exceeded-size="handleMaxSize"
        :before-upload="handleBeforeUpload"
        :action="fileUpload"
        multiple
        type="drag">
        <div style="padding: 20px 0">
          <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
          <p>Click or drag files here to upload</p>
        </div>
      </Upload>
    </Modal>
  </div>
</template>
<script>
  import { getImages } from '~/util/api'
  export default {
    components : {
    },
    mounted() {
      this.initData();
    },
    data () {
      return {
        imgName: '',
        visible: false,
        uploadList: [
          {
            'name': 'a42bdcc1178e62b4694c830f028db5c0',
            'url': 'https://res.hc-cdn.com/cnpm-common-resource/2.0.2/base/header/components/images/logo.png'
          },
          {
            'name': 'bc7521e033abdd1e92222d733590f104',
            'url': 'https://res.hc-cdn.com/cnpm-common-resource/2.0.2/base/header/components/images/logo.png'
          }
        ],

        tableData: [],
        selections:[],
        detailModal:false,
        addModal:false,
        deleteModal:false,
        uploadModal:false,
        page:{
          current:1,
          total:100,
          pageSize:2,
          pageSizeOpts:[10, 20, 30, 40, 50],
          name:'',
        }
      }
    },
    methods: {
      // 获得图片名称
      getImageName(name){
        let result = name;
        if (name.indexOf("image")){
          result = name.substring(name.indexOf("image/")+6)
        }
        return result.substr(0,10)+"...";
      },
      // 选中要删除的图片
      chooseItem(data){
        let items = this.selections;
        let flag = true;
        for (let i in items){
          if (items[i].id === data.id){
            console.log(1)
            //包含传入的选择
            items.splice(i,1);
            flag = false;
            break;
          }
        }
        if (flag){
          items.push(data);
        }
      },
      //选中后更改图片名称的颜色
      getColor(data){
        let items = this.selections;
        for (let item of items){
          if (item.id === data.id){
            return "background-color: #2d8cf0";
          }
        }
      },
      //显示modal，同时获得点击的用户数据
      show (index) {
        this.showDetail = this.tableData[index];
        this.detailModal = true;
      },
      //隐藏 modal
      hiddenModal(){
        this.detailModal = false;
        this.addModal = false;
      },
      //添加管理员用户
      openUploadModal(){
        this.uploadModal = true;
      },
      //打开删除确认的模态框
      openDeleteConfirmModal(){
        let choose = this.selections;
        if (choose.length === 0){
          this.$Message.error('请先选择您要删除的数据');
          return
        }
        this.deleteModal = true;
      },
      //删除选中的图片
      deleteChoose(){
        let choose = this.selections;
        this.deleteModal = false;
      },
      updateInfo(){
        let request = { ...this.showDetail }
        request.qualityUrl = request.qualityUrl.substring(request.qualityUrl.indexOf("image/")+6);
      },
      //当前页码发生改变
      currentPageChange(val){
        this.page.current=val;
        this.initData();
      },
      //每页显示的数据数量发生改变
      pageSizeChange(val){
        this.page.pageSize=val;
        this.initData();
      },
      //初始化数据
      initData(){
        getImages(this.page)
          .then(resp => {
            let respData = resp.data;
            if (resp.status === 200 && respData.status === 201){
              this.tableData = respData.obj.list;
              this.page.total = respData.obj.total;
            }
          })
      },
      confirm(){
        let request = { ...this.addDetail }
        request.qualityUrl = request.qualityUrl.substring(request.qualityUrl.indexOf("image/")+6);
      },

      handleView (name) {
        this.imgName = name;
        this.visible = true;
      },
      handleRemove (file) {
        const fileList = this.$refs.upload.fileList;
        this.$refs.upload.fileList.splice(fileList.indexOf(file), 1);
      },
      handleSuccess (res, file) {
        file.url = 'https://file.iviewui.com/dist/7dcf5af41fac2e4728549fa7e73d61c5.svg';
        file.name = '7eb99afb9d5f317c912f08b5212fd69a';
      },
      handleFormatError (file) {
        this.$Message.warning("文件格式不正确");
      },
      handleMaxSize (file) {
        this.$Message.warning("文件太大");
      },
      handleBeforeUpload () {
        // const check = this.uploadList.length < 5;
        // if (!check) {
        //   this.$Notice.warning({
        //     title: 'Up to five pictures can be uploaded.'
        //   });
        // }
        // return check;
      }
    }
  }
</script>

<style>
  .demo-upload-list{
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
    border: 1px solid transparent;
    border-radius: 4px;
    overflow: hidden;
    background: #fff;
    position: relative;
    box-shadow: 0 1px 1px rgba(0,0,0,.2);
    margin-right: 4px;
  }
  .demo-upload-list img{
    width: 100%;
    height: 100%;
  }
  .demo-upload-list-cover{
    display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background: rgba(0,0,0,.6);
  }
  .demo-upload-list:hover .demo-upload-list-cover{
    display: block;
  }
  .demo-upload-list-cover i{
    color: #fff;
    font-size: 20px;
    cursor: pointer;
    margin: 0 2px;
  }
</style>
