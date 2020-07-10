import { Layout, Breadcrumb } from 'antd';
import IndexSider from "../components/sider";
import HeaderIndex from "../components/header";
import ContentIndex from "../components/content";
import FooterIndex from "../components/footer";
import './index.css'

class IndexLayout extends React.Component {
    render() {
        return (
          <Layout style={{ minHeight: '100vh' }}>
              <IndexSider/>
              <Layout className="site-layout">
                <HeaderIndex/>
                <ContentIndex/>
                <FooterIndex/>
              </Layout>
          </Layout>
        );
    }
}

export default IndexLayout;
