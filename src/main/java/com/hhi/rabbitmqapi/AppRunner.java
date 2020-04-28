package com.hhi.rabbitmqapi;

import com.hhi.rabbitmqapi.service.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {

    private final RabbitService rabbitService;
    private final StringEncryptor stringEncryptor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //설정파일 암복호화
        String encrypt = stringEncryptor.encrypt("password");
        log.info("encrypt : {}",encrypt);
        log.info("decrypt : {}" , stringEncryptor.decrypt(encrypt));


        String username = "5d35809c8e54fe64097d7053";
        String password = "fqEkBtaKLOsyNSbbEdRHoMNSwodZEaUg";
        log.info(rabbitService.addAccount(username,password,"monitoring"));
        log.info(rabbitService.addVhostPermission(username));
        log.info(rabbitService.addTopicPermission(username));
    }
}
