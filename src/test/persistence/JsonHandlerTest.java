package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Budget;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

public class JsonHandlerTest {
    Budget b1;
    BudgetEntry be1;
    JsonHandler jh;

    @BeforeEach
    void setup() {
        b1 = new Budget("B1", "Oct 1", "Nov 1");
        be1 = new Expense("1000", "BE1", 200);
        jh = new JsonHandler();
    }

    @Test
    void testBudgetPrimitiveFieldsToJson() {
        JSONObject jsonObject = jh.budgetPrimitiveFieldsToJson(b1);
        String jsonName = jsonObject.optString("name");
        String jsonStartDate = jsonObject.optString("startDate");
        String jsonEndDate = jsonObject.optString("endDate");

        assertEquals(b1.getName(), jsonName);
        assertEquals(b1.getStartDate(), jsonStartDate);
        assertEquals(b1.getEndDate(), jsonEndDate);

    }

    @Test
    void testBudgetEntryToJson() {
        JSONObject jsonObject = jh.budgetEntryToJson(be1);
        String jsonId = jsonObject.optString("id");
        String jsonName = jsonObject.optString("name");
        Double jsonBudgetAmount = jsonObject.optDouble("budgetAmount");
        Double jsonActualAmount = jsonObject.optDouble("actualAmount");

        assertEquals(be1.getId(), jsonId);
        assertEquals(be1.getName(), jsonName);
        assertEquals(be1.getBudgetAmount(), jsonBudgetAmount);
        assertEquals(be1.getActualAmount(), jsonActualAmount);

    }


}
