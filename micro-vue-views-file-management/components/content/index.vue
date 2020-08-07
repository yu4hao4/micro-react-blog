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
        <Button type="primary" shape="circle" size="large" @click="openAddModal">上传文件</Button>
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
        tableData: [],
        selections:[],
        detailModal:false,
        addModal:false,
        deleteModal:false,
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
      getColor(item){
        if (this.selections.length > 0){
          return "background-color: #2d8cf0";
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
      openAddModal(){
        this.addModal = true;
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
      //如果文件超过设置大小，执行的方法
      processingPictureTooLarge(){
        this.$Message.error("体积太大,请上传小于2M的图片")
      },
      //上传图片成功
      uploadPictureSuccess(response, file, fileList){
        this.showDetail.qualityUrl=response.obj;
      },
      //上传图片失败
      uploadPictureFail(error, file, fileList){
        this.$Message.error('图片上传失败，请稍后再试')
      },
      confirm(){
        let request = { ...this.addDetail }
        request.qualityUrl = request.qualityUrl.substring(request.qualityUrl.indexOf("image/")+6);
      }
    }
  }
</script>
