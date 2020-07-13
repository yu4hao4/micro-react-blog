<template>
  <a-layout id="components-layout-demo-side" style="min-height: 100vh">
    <a-layout-sider v-model="collapsed" :collapsible="true">
      <div class="logo" />
      <a-menu theme="dark" :default-selected-keys="[ getChooseMenuKeyName ]" mode="inline">
        <a-menu-item v-for="(item,index) in menus" :key="item.key" @click="chooseMenu = menus[index]">
          <a-icon :type="item.iconType" />
          <span>{{ item.name }}</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <MyHeader/>
      <a-layout-content style="margin: 0 16px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>主页</a-breadcrumb-item>
          <a-breadcrumb-item>{{ chooseMenu.name }}</a-breadcrumb-item>
        </a-breadcrumb>
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <DataStatistics v-if="chooseMenu.key === 'data-statistics'"/>
          <AddArticle v-if="chooseMenu.key === 'add-article'"/>
          <ManageArticle v-if="chooseMenu.key === 'manage-article'"/>
        </div>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        Ant Design ©2018 Created by Ant UED
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script>
  import MyHeader from '~/components/my-header'
  import DataStatistics from '~/components/data-statistics'
  import AddArticle from '~/components/add-article'
  import ManageArticle from '~/components/manage-article'
  export default {
    components:{
      MyHeader,
      DataStatistics,
      AddArticle,
      ManageArticle
    },
    data() {
      return {
        collapsed: false,
        menus:[
          {key:'data-statistics',iconType:'pie-chart',name:'数据统计'},
          {key:'add-article',iconType:'file',name:'添加文章'},
          {key:'manage-article',iconType:'setting',name:'文章管理'},
        ],
        chooseMenu:{key:'data-statistics',iconType:'pie-chart',name:'数据统计'}
      };
    },
    computed:{
      getChooseMenuKeyName(){
        return this.chooseMenu.key
      }
    },
    methods:{
    }
  };
</script>

<style>
  #components-layout-demo-side .logo {
    height: 32px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px;
  }
</style>
