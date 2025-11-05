package tn.esprit.tpcafeziedsnoussi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpcafeziedsnoussi.repositories.ClientRepository;
import tn.esprit.tpcafeziedsnoussi.services.interfaces.IClientService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TpCafeZiedSnoussiApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void contextLoads() {
        // Verify that the application context loads successfully
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void shouldLoadAllBeans() {
        // Verify that essential beans are loaded
        assertThat(clientService).isNotNull();
        assertThat(clientRepository).isNotNull();
    }

    @Test
    void shouldHaveActiveTestProfile() {
        // Verify that the test profile is active
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
        assertThat(activeProfiles).contains("test");
    }
}
