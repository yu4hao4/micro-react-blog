import java.io.File;

/**
 * @author yuhao5
 * @date 2020-07-25
 */
public class Test {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir")+File.separator+"images";
        File file = new File(path);
        String[] length = file.list();
        System.out.println(length.length);
        File[] files = file.listFiles();
        for (File file1 : files) {
            file1.getName();
        }
        file.delete();
        for (String s : length) {
            System.out.println(s);
        }
    }
}
