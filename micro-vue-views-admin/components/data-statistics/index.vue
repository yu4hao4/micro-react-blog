<template>
  <a-row :gutter="16">
    <a-col :span="12">
      <a-statistic title="浏览量" :value="1128" style="margin-right: 50px">
        <template #suffix>
          <a-icon type="like" />
        </template>
      </a-statistic>
    </a-col>
    <a-col :span="12">
      <a-statistic title="Unmerged" :value="93" class="demo-class">
          <span slot="suffix"> / 100</span>
      </a-statistic>
    </a-col>
  </a-row>
</template>
