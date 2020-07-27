package yuhao.aspect;

/**
 * 使用ThreadLocal技术来记录当前线程中的数据源的key
 * @author yuhao5
 * @date 2020-07-27
 */
public class DynamicDataSourceHolder {

    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param key
     */
    public static void putDataSouce(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource() {
        return holder.get();
    }

    /**
     * 清空数据源
     */
    public static void removeDataSource(){
        holder.remove();
    }
}
