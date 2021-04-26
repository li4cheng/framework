package com.my.framework.web.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

@Transactional
@RestController
@RequestMapping("/api/blocking-queue")
@Api(tags = "阻塞队列测试")
public class BlockingQueueResource {

    private static final Logger log = LoggerFactory.getLogger(BlockingQueueResource.class);
    private static int i = 1;
    private static final LinkedBlockingQueue<HashMap<String, List<Long>>> queue = new LinkedBlockingQueue<>(5);

    @GetMapping("/test")
    @ApiModelProperty(value = "阻塞队列测试")
    public ResponseEntity<Object> test(@RequestParam List<Long> ids) throws InterruptedException {

        new Thread(new Put(String.valueOf(i++), ids)).start();
        new Thread(new Take()).start();

        return ResponseEntity.noContent().build();
    }

    public static class Take implements Runnable {

        @Override
        public void run() {
            try {
                //模拟逻辑处理
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.take().forEach((latAndLng, warehouseIdList) -> {
                    log.info("get:[{}]", latAndLng);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Put implements Runnable {

        private final String s;
        private List<Long> ids;

        public Put(String s, List<Long> ids) {
            this.s = s;
            this.ids = ids;
        }

        @Override
        public void run() {
            HashMap<String, List<Long>> hashMap = new HashMap<>();
            hashMap.put(s, ids);
            try {
                queue.put(hashMap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("set:[{}]", s);
        }
    }
}
