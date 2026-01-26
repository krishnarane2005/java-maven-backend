package com.FestOrg.FestOrg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.FestOrg.FestOrg.repository.AdminRepository;

@SpringBootTest
@ActiveProfiles("test")
class FestOrgApplicationTests {

    @MockBean
    private AdminRepository adminRepository;

    @Test
    void contextLoads() {
    }
}
