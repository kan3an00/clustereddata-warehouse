package progressoft.warehouse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import progressoft.warehouse.model.DealsDTO;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DealsResourceTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createDeal() throws Exception {

        // Valid deal creation - expecting success
        String uri = "/api/deals";
        DealsDTO deal = new DealsDTO();
        deal.setFromCurrency("USD");
        deal.setToCurrency("JOD");
        deal.setAmount(BigDecimal.valueOf(120.00));
        String inputJson = super.mapToJson(deal);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        // Duplicate deal creation - expecting failure
        MvcResult duplicateResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int duplicateStatus = duplicateResult.getResponse().getStatus();
        assertEquals(500, duplicateStatus); // Assuming a specific error code for duplicate creation

        // Missing fields - expecting failure
        DealsDTO incompleteDeal = new DealsDTO();
        incompleteDeal.setFromCurrency("USD");
        String incompleteJson = super.mapToJson(incompleteDeal);
        MvcResult incompleteResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(incompleteJson)).andReturn();

        int incompleteStatus = incompleteResult.getResponse().getStatus();
        assertEquals(400, incompleteStatus); // Assuming a specific error code for incomplete data
    }

    @Test
    public void getDealsList() throws Exception {
        String uri = "/api/deals";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        DealsDTO[] dealList = super.mapFromJson(content, DealsDTO[].class);
        assertTrue(dealList.length > 0);
    }
}
