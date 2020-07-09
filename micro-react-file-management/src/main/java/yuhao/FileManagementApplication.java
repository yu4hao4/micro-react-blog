package yuhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuhao5
 * @date 2020-07-09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FileManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManagementApplication.class, args);
    }

}
