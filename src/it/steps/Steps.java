package steps;

import com.bank.dto.TransactionDto;
import com.bank.enumerated.TransactionTypeEnum;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Steps extends SpringIntegrationTest {

    @When("^the client initiate a (.*) transaction with the following information")
    public void theClientInitiateATransaction(String transactionType, DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        TransactionDto transactionDto = TransactionDto.builder()
                .amount(new BigDecimal(rows.get(0).get("amount")))
                .accountId(Long.valueOf(rows.get(0).get("accountId")))
                .transactionType(TransactionTypeEnum.valueOf(transactionType))
                .build();
        this.executePost(transactionDto);
    }


    @Then("the client receives the transaction info as bellow")
    public void theClientReceivesTheTransactionInfo(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        String expectedTransactionType = rows.get(0).get("transactionType");
        String expectedAmount = rows.get(0).get("amount");
        assertThat(this.transactionResponse.getTransactionType().toString()).isEqualTo(expectedTransactionType);
        assertThat(this.transactionResponse.getAmount()).isEqualTo(new BigDecimal(expectedAmount));
    }
}
