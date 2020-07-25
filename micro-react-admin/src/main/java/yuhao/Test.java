package yuhao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

/**
 * @author yuhao5
 * @date 2020-07-25
 */
public class Test {
    public static void main(String[] args) {
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://121.36.49.252:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        PreparedStatement pst =  null;
        long beginTime = 0;
        long endTime = 0;
        try {
            Class.forName(driver);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);
            if(conn != null) {
                System.out.println("获取连接成功");
                beginTime = new Date().getTime();//开始计时
                String sqlPrefix = "INSERT INTO `test`.`test_page_memory` (`id`,`username`, `password`, `create_time`, `update_time`) values ";
                // 保存sql后缀
                StringBuilder suffix = new StringBuilder();
                // 设置事务为非自动提交
                conn.setAutoCommit(false);
                // 比起st，pst会更好些
                pst = conn.prepareStatement("");//准备执行语句
                // 外层循环，总提交事务次数
                int index = 1;
                for (int i = 1; i <= 100; i++) {
                    suffix = new StringBuilder();
                    // 第j次提交步长
                    for (int j = 1; j <= 6000; j++) {
                        // 构建SQL后缀
                        suffix.append("('")
                                .append(600000+index)
                                .append("','").append(UUID.randomUUID().toString().substring(0, 20))
                                .append("','").append(UUID.randomUUID().toString())
                                .append("','").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .append("','").append(LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .append("'),");
                        index++;
                    }
                    // 构建完整SQL
                    String sql = sqlPrefix + suffix.substring(0, suffix.length() - 1);
//                    System.out.println(sql);
                    // 添加执行SQL
                    pst.addBatch(sql);
                    // 执行操作
                    pst.executeBatch();
                    // 提交事务
                    conn.commit();
                    // 清空上一次添加的数据
                    suffix = new StringBuilder("");
                    endTime = new Date().getTime();
                    System.out.println("第 "+i+" 次事务提交成功，用时: "+(endTime-beginTime));
                }
                endTime = new Date().getTime();//开始计时
            }else {
                System.out.println("数据库连接失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("com.mysql.jdbc.Driver驱动类没有找到");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库地址错误");
        }finally {//释放资源
            System.out.println("插入成功，所有时间："+ (endTime-beginTime));
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
