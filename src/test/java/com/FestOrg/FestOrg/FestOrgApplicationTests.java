package com.FestOrg.FestOrg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ActiveProfiles("test")
class FestOrgApplicationTests {

    @Test
    void contextLoads() {
    }
}
