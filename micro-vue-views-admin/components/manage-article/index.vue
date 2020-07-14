<template>
  <a-table :row-selection="rowSelection" :columns="columns" :data-source="data" >
    <span slot="age" slot-scope="age">
<!--      <span style="background-color: #3b8070"> {{ age }}</span>-->
      <span> {{ age }}</span>
<!--      <a-tag-->
<!--        v-for="tag in tags"-->
<!--        :key="tag"-->
<!--        :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"-->
<!--      >-->
<!--        {{ tag.toUpperCase() }}-->
<!--      </a-tag>-->
    </span>

    <p slot="expandedRowRender" slot-scope="record" style="margin: 0">
      {{ record.address }}
    </p>
  </a-table>
</template>
<script>
  const columns = [
    {
      title: '文章标题',
      dataIndex: 'articleTitle',
    },
    {
      title: '发布时间',
      dataIndex: 'publishTime',
      scopedSlots: { customRender: 'age' },
    },
    // {
    //   title: 'Address',
    //   dataIndex: 'address',
    // },
  ];

  const data = [];
  for (let i = 0; i < 46; i++) {
    data.push({
      key: i,
      articleTitle: `Edward King ${i}`,
      age: 32,
      address: `London, Park Lane no. ${i}`,
      publishTime: new Date(),
    });
  }

  export default {
    data() {
      return {
        data,
        columns,
        selectedRowKeys: [], // Check here to configure the default column
      };
    },
    computed: {
      rowSelection() {
        const { selectedRowKeys } = this;
        return {
          selectedRowKeys,
          onChange: this.onSelectChange,
          hideDefaultSelections: true,
          selections: [
            {
              key: 'all-data',
              text: 'Select All Data',
              onSelect: () => {
                this.selectedRowKeys = [...Array(46).keys()]; // 0...45
              },
            },
            {
              key: 'odd',
              text: 'Select Odd Row',
              onSelect: changableRowKeys => {
                let newSelectedRowKeys = [];
                newSelectedRowKeys = changableRowKeys.filter((key, index) => {
                  return index % 2 === 0;

                });
                this.selectedRowKeys = newSelectedRowKeys;
              },
            },
            {
              key: 'even',
              text: 'Select Even Row',
              onSelect: changableRowKeys => {
                let newSelectedRowKeys = [];
                newSelectedRowKeys = changableRowKeys.filter((key, index) => {
                  return index % 2 !== 0;
                });
                this.selectedRowKeys = newSelectedRowKeys;
              },
            },
          ],
          onSelection: this.onSelection,
        };
      },
    },
    methods: {
      onSelectChange(selectedRowKeys) {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.selectedRowKeys = selectedRowKeys;
      },
    },
  };
</script>
