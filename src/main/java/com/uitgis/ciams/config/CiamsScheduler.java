package com.uitgis.ciams.config;

import com.uitgis.ciams.service.CiamsKrasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
@Profile("prod")
public class CiamsScheduler {

    @Value("${scheduler.use}")
    private boolean useKrasScheduler;

    private final CiamsKrasService ciamsKrasService;

    @Scheduled(cron = "${scheduler.cron}", zone = "Asia/Seoul")
    public void renewalKrasData() {
        try {
            if (!useKrasScheduler) return;
            log.info("Renewal Kras Data Start :: " + new Date());
            ciamsKrasService.renewalKrasData();
            log.info("Renewal Kras Data End :: " + new Date());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
