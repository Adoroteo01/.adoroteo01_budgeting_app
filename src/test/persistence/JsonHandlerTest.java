package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Budget;

public class JsonHandlerTest {
    Budget b1;
    JsonHandler j;

    @BeforeEach
    void setup() {
        b1 = new Budget("B1", "Oct 1", "Nov 1");
        j = new JsonHandler();
    }


    @Test
    void testbudgetPrimitiveFieldsToJson() {
        JSONObject jsonObject = j.budgetPrimitiveFieldsToJson(b1);
        String jsonName = jsonObject.optString("name");
        String jsonStartDate = jsonObject.optString("startDate");
        String jsonEndDate = jsonObject.optString("endDate");
        
        assertEquals(b1.getName(), jsonName);
        assertEquals(b1.getStartDate(), jsonStartDate);
        assertEquals(b1.getEndDate(), jsonEndDate);

    }
}
