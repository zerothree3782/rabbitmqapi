package com.hhi.rabbitmqapi;

import com.hhi.rabbitmqapi.service.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {

    private final RabbitService rabbitService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String username = "5d35809c8e54fe64097d7053";
        String password = "fqEkBtaKLOsyNSbbEdRHoMNSwodZEaUg";

        log.info(rabbitService.addAccount(username,password,"monitoring"));
        log.info(rabbitService.addVhostPermission(username));
        log.info(rabbitService.addTopicPermission(username));
    }
}
