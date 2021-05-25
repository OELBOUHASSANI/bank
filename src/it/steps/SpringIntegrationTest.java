package steps;

import com.bank.BankApplication;
import com.bank.entities.Transaction;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(classes = BankApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    RestTemplate restTemplate;
    protected Transaction transactionResponse = null;


    void executePost(Object payload)  {
        String url = "http://localhost:8080/rest/operation";
        restTemplate = new RestTemplate();
        transactionResponse = restTemplate.postForObject(url, payload, Transaction.class);
    }
}