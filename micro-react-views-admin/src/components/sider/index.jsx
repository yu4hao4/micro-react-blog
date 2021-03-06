import {Layout, Menu} from "antd";
import {
    SettingOutlined, FileOutlined, DiffOutlined, TeamOutlined, UserOutlined,
    BarChartOutlined
} from "@ant-design/icons";

const { Sider } = Layout;
const { SubMenu } = Menu;

class IndexSider extends React.Component {
    state = {
        collapsed: false,
    };

    onCollapse = collapsed => {
        console.log(collapsed);
        this.setState({ collapsed });
    };
    render() {
        return (
          <Sider collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
              <div className="logo" />
              <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline">
                  <Menu.Item key="1" icon={<BarChartOutlined />}>
                      数据分析
                  </Menu.Item>
                  <Menu.Item key="2" icon={<DiffOutlined />}>
                      添加文章
                  </Menu.Item>
                  <Menu.Item key="3" icon={<SettingOutlined />}>
                      文章管理
                  </Menu.Item>
                  <SubMenu key="sub1" icon={<UserOutlined />} title="User">
                      <Menu.Item key="3">Tom</Menu.Item>
                      <Menu.Item key="4">Bill</Menu.Item>
                      <Menu.Item key="5">Alex</Menu.Item>
                  </SubMenu>
                  <SubMenu key="sub2" icon={<TeamOutlined />} title="Team">
                      <Menu.Item key="6">Team 1</Menu.Item>
                      <Menu.Item key="8">Team 2</Menu.Item>
                  </SubMenu>
                  <Menu.Item key="9" icon={<FileOutlined />} />
              </Menu>
          </Sider>
        )
    }
}


export default IndexSider;
