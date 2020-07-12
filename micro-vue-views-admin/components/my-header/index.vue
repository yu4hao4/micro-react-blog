<template>
  <a-layout-header style="background: #fff; padding: 0" >
    <a-row type="flex" justify="center">
      <a-col :span="2" :offset="22">
        <a-dropdown :trigger="['click']">
          <a-menu slot="overlay">
            <a-menu-item key="0">
              <a href="http://www.alipay.com/">个人资料</a>
            </a-menu-item>
            <a-menu-item key="1">
              <a href="http://www.taobao.com/">修改密码</a>
            </a-menu-item>
            <a-menu-divider />
            <a-menu-item key="3">
              退出登录
            </a-menu-item>
          </a-menu>
          <a style="font-size: 20px" class="ant-dropdown-link">
            <a-avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" style="height: 35px;margin-bottom: 10px;"/>
            张三
            <a-icon type="down" style="font-size: 1em"/>
          </a>
        </a-dropdown>
      </a-col>
    </a-row>
  </a-layout-header>
</template>

<script>
  export default {
    name: "my-header"
  }
</script>

<style scoped>

</style>
