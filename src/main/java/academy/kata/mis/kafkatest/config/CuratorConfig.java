//package academy.kata.mis.kafkatest.config;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CuratorConfig {
//    @Value("${zookeeper.connection}")
//    private String connection;
//
//    @Bean
//    public CuratorFramework curatorFramework() {
//        CuratorFramework curatorFramework = CuratorFrameworkFactory
//                .newClient(connection, new ExponentialBackoffRetry(1000, 3));
//        curatorFramework.start();
//        return curatorFramework;
//    }
//
//}
