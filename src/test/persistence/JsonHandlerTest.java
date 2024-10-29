package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Budget;
import model.Budgeter;
import model.Tracker;
import model.TrackerEntry;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

public class JsonHandlerTest {
    Budget b1;

    Budgeter btr1;
    BudgetEntry be1;
    BudgetEntry be2;
    List<BudgetEntry> listOfBE;

    Tracker tr1;
    TrackerEntry te1;
    TrackerEntry te2;
    List<TrackerEntry> listOfTE;

    JsonHandler jh;

    @BeforeEach
    void setup() {
        b1 = new Budget("B1", "Oct 1", "Nov 1");

        be1 = new Expense("1000", "BE1", 200);
        be2 = new Expense("1001", "BE2", 500);

        btr1 = new Budgeter();
        btr1.addEntry(be1);
        btr1.addEntry(be2);

        listOfBE = new ArrayList<BudgetEntry>();
        listOfBE.add(be1);
        listOfBE.add(be2);

        te1 = new TrackerEntry("Jan 1", be1, 12.99);
        te2 = new TrackerEntry("Jan 2", be2, 1.5);

        tr1 = new Tracker();
        tr1.addEntry(te1);
        tr1.addEntry(te2);

        listOfTE = new ArrayList<TrackerEntry>();
        listOfTE.add(te1);
        listOfTE.add(te2);

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

        assertEquals(4, jsonBudgetEntry.keySet().size());

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

        assertEquals(2, jsonBudgetEntries.length());

        assertEquals(4, jsonBudgetEntry2.keySet().size());
        assertEquals(be1.getId(), jsonId1);
        assertEquals(be1.getName(), jsonName1);
        assertEquals(be1.getBudgetAmount(), jsonBudgetAmount1);
        assertEquals(be1.getActualAmount(), jsonActualAmount1);

        String jsonId2 = jsonBudgetEntry2.optString("id");
        String jsonName2 = jsonBudgetEntry2.optString("name");
        Double jsonBudgetAmount2 = jsonBudgetEntry2.optDouble("budgetAmount");
        Double jsonActualAmount2 = jsonBudgetEntry2.optDouble("actualAmount");

        assertEquals(4, jsonBudgetEntry1.keySet().size());
        assertEquals(be2.getId(), jsonId2);
        assertEquals(be2.getName(), jsonName2);
        assertEquals(be2.getBudgetAmount(), jsonBudgetAmount2);
        assertEquals(be2.getActualAmount(), jsonActualAmount2);

    }

    @Test
    void testBudgetEntryIdToJson() {
        JSONObject jsonBudgetEntry = jh.budgetEntryIdToJson(be1);
        String jsonId = jsonBudgetEntry.optString("id");

        assertEquals(1, jsonBudgetEntry.keySet().size());
        assertEquals(be1.getId(), jsonId);

    }

    @Test
    void testBudgetEntriesIdToJson() {
        JSONArray jsonBudgetEntries = jh.budgetEntriesIdToJson(listOfBE);
        JSONObject jsonBudgetEntry1 = jsonBudgetEntries.optJSONObject(0);
        JSONObject jsonBudgetEntry2 = jsonBudgetEntries.optJSONObject(1);

        assertEquals(2, jsonBudgetEntries.length());

        String jsonId1 = jsonBudgetEntry1.optString("id");
        assertEquals(be1.getId(), jsonId1);
        assertEquals(1, jsonBudgetEntry1.keySet().size());

        String jsonId2 = jsonBudgetEntry2.optString("id");
        assertEquals(be2.getId(), jsonId2);
        assertEquals(1, jsonBudgetEntry2.keySet().size());

    }

    @Test
    void testBudgeterToJson() {
        JSONObject jsonBudgeter = jh.budgeterToJson(btr1);

        JSONArray jsonBudgetEntries = jsonBudgeter.optJSONArray("budgetEntries");

        JSONObject jsonbudgetEntry1 = jsonBudgetEntries.optJSONObject(0);
        JSONObject jsonbudgetEntry2 = jsonBudgetEntries.optJSONObject(1);

        String jsonbudgetEntryId1 = jsonbudgetEntry1.optString("id");
        String jsonbudgetEntryId2 = jsonbudgetEntry2.optString("id");

        List<String> listOfId = new ArrayList<String>();
        listOfId.add(jsonbudgetEntryId1);
        listOfId.add(jsonbudgetEntryId2);

        assertEquals(2, jsonBudgetEntries.length());

        assertTrue(listOfId.contains(be1.getId()));
        assertTrue(listOfId.contains(be2.getId()));

    }

    @Test
    void testTrackerEntryToJson() {

        JSONObject jsonTrackerEntry = jh.trackerEntryToJson(te1);
        JSONObject jsonTrackerEntryBudgetEntry = jsonTrackerEntry.optJSONObject("budgetEntry");

        assertEquals(3, jsonTrackerEntry.keySet().size());
        assertEquals(1, jsonTrackerEntryBudgetEntry.keySet().size());

        assertEquals(te1.getDate(), jsonTrackerEntry.opt("date"));
        assertEquals(te1.getBudgetEntryId(), jsonTrackerEntryBudgetEntry.opt("id"));
        assertEquals(te1.getAmount(), jsonTrackerEntry.opt("amount"));

    }

    @Test
    void testTrackerEntriesToJson() {
        JSONArray jsonTrackerEntries = jh.trackerEntriesToJson(listOfTE);

        JSONObject jsonTrackerEntry1 = jsonTrackerEntries.getJSONObject(0);
        JSONObject jsonTrackerEntry2 = jsonTrackerEntries.getJSONObject(1);

        JSONObject jsonTrackerEntryBudgetEntry1 = jsonTrackerEntry1.optJSONObject("budgetEntry");
        JSONObject jsonTrackerEntryBudgetEntry2 = jsonTrackerEntry2.optJSONObject("budgetEntry");

        assertEquals(1, jsonTrackerEntryBudgetEntry1.keySet().size());
        assertEquals(1, jsonTrackerEntryBudgetEntry2.keySet().size());

        assertEquals(2, jsonTrackerEntries.length());
        assertEquals(3, jsonTrackerEntry1.keySet().size());
        assertEquals(3, jsonTrackerEntry2.keySet().size());

        assertEquals(te1.getDate(), jsonTrackerEntry1.opt("date"));
        assertEquals(te1.getBudgetEntryId(), jsonTrackerEntryBudgetEntry1.opt("id"));
        assertEquals(te1.getAmount(), jsonTrackerEntry1.opt("amount"));

        assertEquals(te2.getDate(), jsonTrackerEntry2.opt("date"));
        assertEquals(te2.getBudgetEntryId(), jsonTrackerEntryBudgetEntry2.opt("id"));
        assertEquals(te2.getAmount(), jsonTrackerEntry2.opt("amount"));

    }

}
