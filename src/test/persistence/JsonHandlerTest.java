package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Budget;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

public class JsonHandlerTest {
    Budget b1;
    BudgetEntry be1;
    BudgetEntry be2;
    List<BudgetEntry> listOfBE;
    JsonHandler jh;

    @BeforeEach
    void setup() {
        b1 = new Budget("B1", "Oct 1", "Nov 1");
        be1 = new Expense("1000", "BE1", 200);
        be2 = new Expense("1001", "BE2", 500);
        
        listOfBE = new ArrayList<BudgetEntry>();
        listOfBE.add(be1);
        listOfBE.add(be2);
        
        jh = new JsonHandler();
    }

    @Test
    void testBudgetPrimitiveFieldsToJson() {
        JSONObject jsonPrimitiveFields = jh.budgetPrimitiveFieldsToJson(b1);
        String jsonName = jsonPrimitiveFields.optString("name");
        String jsonStartDate = jsonPrimitiveFields.optString("startDate");
        String jsonEndDate = jsonPrimitiveFields.optString("endDate");

        assertEquals(b1.getName(), jsonName);
        assertEquals(b1.getStartDate(), jsonStartDate);
        assertEquals(b1.getEndDate(), jsonEndDate);

    }

    @Test
    void testBudgetEntryToJson() {
        JSONObject jsonBudgetEntry = jh.budgetEntryToJson(be1);
        String jsonId = jsonBudgetEntry.optString("id");
        String jsonName = jsonBudgetEntry.optString("name");
        Double jsonBudgetAmount = jsonBudgetEntry.optDouble("budgetAmount");
        Double jsonActualAmount = jsonBudgetEntry.optDouble("actualAmount");

        assertEquals(be1.getId(), jsonId);
        assertEquals(be1.getName(), jsonName);
        assertEquals(be1.getBudgetAmount(), jsonBudgetAmount);
        assertEquals(be1.getActualAmount(), jsonActualAmount);

    }

    @Test
    void testBudgetEntriesToJson() {
        JSONArray jsonBudgetEntries = jh.budgetEntriesToJson(listOfBE);
        JSONObject jsonBudgetEntry1 = jsonBudgetEntries.optJSONObject(0);
        JSONObject jsonBudgetEntry2 = jsonBudgetEntries.optJSONObject(1);
        
        String jsonId1 = jsonBudgetEntry1.optString("id");
        String jsonName1 = jsonBudgetEntry1.optString("name");
        Double jsonBudgetAmount1 = jsonBudgetEntry1.optDouble("budgetAmount");
        Double jsonActualAmount1 = jsonBudgetEntry1.optDouble("actualAmount");

        assertEquals(be1.getId(), jsonId1);
        assertEquals(be1.getName(), jsonName1);
        assertEquals(be1.getBudgetAmount(), jsonBudgetAmount1);
        assertEquals(be1.getActualAmount(), jsonActualAmount1);

        String jsonId2 = jsonBudgetEntry2.optString("id");
        String jsonName2 = jsonBudgetEntry2.optString("name");
        Double jsonBudgetAmount2 = jsonBudgetEntry2.optDouble("budgetAmount");
        Double jsonActualAmount2 = jsonBudgetEntry2.optDouble("actualAmount");

        assertEquals(be2.getId(), jsonId2);
        assertEquals(be2.getName(), jsonName2);
        assertEquals(be2.getBudgetAmount(), jsonBudgetAmount2);
        assertEquals(be2.getActualAmount(), jsonActualAmount2);

    }


}
