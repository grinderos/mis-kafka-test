//package academy.kata.mis.kafkatest.util;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//@EnableScheduling
//public class SchedulerTest {
//    private final CuratorSelectLeaderManager curatorSelectLeaderManager;
//
//    @Scheduled(fixedRateString = "100000")
//    public void scheduled() {
//        System.out.println("------------------------");
//        System.out.println(curatorSelectLeaderManager.isLeader());
//        log.info("SchedulerTest scheduled");
//        System.out.println("------------------------");
//    }
//}
