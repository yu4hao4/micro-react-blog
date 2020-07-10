import React, {Component} from 'react';
import {Layout, Row, Col, Menu, Dropdown, Avatar,Select } from "antd";
import { DownOutlined } from "@ant-design/icons";
const { Header } = Layout;
const { Option } = Select;
import './index.css'

class HeaderIndex extends Component {
  state = {
    visible: false,
  };

  handleMenuClick = e => {
    if (e.key === '3') {
      this.setState({ visible: false });
    }
  };

  handleVisibleChange = flag => {
    this.setState({ visible: flag });
  };

  render() {
    const menu = (
      <Menu onClick={this.handleMenuClick}>
        <Menu.Item key="1">Clicking me will not close the menu.</Menu.Item>
        <Menu.Item key="2">Clicking me will not close the menu also.</Menu.Item>
        <Menu.Item key="3">Clicking me will close the menu.</Menu.Item>
      </Menu>
    );

    return (
      <Header className="site-layout-background" style={{ padding: 0 }} >
        <Row style={{textAlign:"center"}}>
          <Col span={2} offset={22}>
            <Select defaultValue={[<Avatar size={35} src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />,<DownOutlined />]}
                    style={{ width: 80,height:40 }} bordered={false}>
              <Option value="jack">个人资料</Option>
              <Option value="lucy">修改密码</Option>
              <Option value="Yiminghe">退出登录</Option>
            </Select>
          </Col>
          {/*<Col span={2} offset={22}>*/}
          {/*  <Dropdown*/}
          {/*    overlay={menu}*/}
          {/*    onVisibleChange={this.handleVisibleChange}*/}
          {/*    visible={this.state.visible}*/}
          {/*  >*/}
          {/*    <a className="ant-dropdown-link" onClick={e => e.preventDefault()}>*/}

          {/*    </a>*/}
          {/*  </Dropdown>*/}
          {/*</Col>*/}
        </Row>
      </Header>
    );
  }
}

export default HeaderIndex;
