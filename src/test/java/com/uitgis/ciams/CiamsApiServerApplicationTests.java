package com.uitgis.ciams;

import com.uitgis.ciams.config.CiamsScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CiamsApiServerApplicationTests {

    @Autowired
    private CiamsScheduler ciamsScheduler;

    @Test
    void scheduleJob() {
        ciamsScheduler.renewalKrasData();
    }

}
