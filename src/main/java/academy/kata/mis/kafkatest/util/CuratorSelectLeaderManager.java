//package academy.kata.mis.kafkatest.util;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.leader.LeaderLatch;
//import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class CuratorSelectLeaderManager {
//
//    private final CuratorFramework curatorFramework;
//    private final LeaderLatch leaderLatch;
//
//    public CuratorSelectLeaderManager(CuratorFramework curatorFramework) {
//        this.curatorFramework = curatorFramework;
//        this.leaderLatch = new LeaderLatch(curatorFramework, "/leader1");
//        leaderLatch.addListener(new LeaderLatchListener() {
//            @Override
//            public void isLeader() {
//                log.info("Current instance leader");
//            }
//
//            @Override
//            public void notLeader() {
//                log.info("Current instance not leader");
//            }
//        });
//    }
//
//    @PostConstruct
//    public void start() throws Exception {
//        log.info("Starting CuratorSelectLeaderManager");
//        leaderLatch.start();
//    }
//
//    @PreDestroy
//    public void close() {
//        log.info("Stopping CuratorSelectLeaderManager");
//        try {
//            leaderLatch.close();
//        } catch (Exception e) {
//            log.error("Error while closing leaderLatch", e);
//        }
//        try {
//            curatorFramework.close();
//        } catch (Exception e) {
//            log.error("Error while closing curatorFramework", e);
//        }
//    }
//
//    public boolean isLeader() {
//        return leaderLatch.hasLeadership();
//    }
//}
