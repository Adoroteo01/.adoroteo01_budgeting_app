package persistence;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Budget;
import model.Budgeter;
import model.Tracker;
import model.TrackerEntry;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;
import model.exceptions.InvalidBudgetEntryException;

public class JsonHandlerTest {
    Budget b1;
    Budget b2;
    List<Budget> listOfB;

    Budgeter btr1;
    BudgetEntry be1;
    BudgetEntry be2;
    List<BudgetEntry> listOfBE;

    Budgeter btr2;
    BudgetEntry be3;
    BudgetEntry be4;

    Tracker tr1;
    TrackerEntry te1;
    TrackerEntry te2;
    List<TrackerEntry> listOfTE;

    Tracker tr2;
    TrackerEntry te3;
    TrackerEntry te4;

    JSONArray jsonBudgets;
    JSONObject jsonBudget1;
    JSONObject jsonBudget2;

    JsonHandler jh;

    @BeforeEach
    void setup() {

        init();
    }

    private void init() {
        initBudgetEntries();
        initBudgeter();
        initTrackerEntries();
        initTracker();
        initBudget();
        initJsonBudgets();
        jsonBudget1 = jsonBudgets.getJSONObject(0);
        jsonBudget2 = jsonBudgets.getJSONObject(1);

        jh = new JsonHandler();
    }

    @SuppressWarnings("methodlength") // cannot split up into more methods
    private void initJsonBudgets() {
        jsonBudgets = new JSONArray(
                "[{\"primitives\":{\"endDate\":\"Feb 1\",\"name\":\"Jan\",\"startDate\":\"Jan 1"
                        +
                        "\"},\"budgetEntries\":[{\"budgetAmount\":400,\"actualAmount\":56.99,\"name\":\""
                        +
                        "Grocery\",\"id\":\"1000\"},{\"budgetAmount\":200,\"actualAmount\":40.5,\"name\":"
                        +
                        "\"Gas\",\"id\":\"1001\"}],\"tracker\":{\"entries\":[{\"date\":\"Jan 2\",\"amount"
                        +
                        "\":56.99,\"budgetEntry\":{\"id\":\"1000\"}},{\"date\":\"Jan 2\",\"amount\":40.5,"
                        +
                        "\"budgetEntry\":{\"id\":\"1001\"}}]},\"budgeter\":{\"budgetEntries\":[{\"id\":\""
                        +
                        "1000\"},{\"id\":\"1001\"}]}},{\"primitives\":{\"endDate\":\"Mar 1\",\"name\":\""
                        +
                        "Feb\",\"startDate\":\"Feb 1\"},\"budgetEntries\":[{\"budgetAmount\":33.44,\""
                        +
                        "actualAmount\":98,\"name\":\"Grocery\",\"id\":\"1001\"},{\"budgetAmount\":66.99,"
                        +
                        "\"actualAmount\":50,\"name\":\"Gas\",\"id\":\"1000\"}],\"tracker\":{\"entries\":[{"
                        +
                        "\"date\":\"Feb 3\",\"amount\":50,\"budgetEntry\":{\"id\":\"1000\"}},{\"date\":\""
                        +
                        "Feb 4\",\"amount\":98,\"budgetEntry\":{\"id\":\"1001\"}}]},\"budgeter\":{\""
                        +
                        "budgetEntries\":[{\"id\":\"1001\"},{\"id\":\"1000\"}]}}]\r\n"
                        +
                        "");
    }

    private void initBudget() {
        b1 = new Budget("B1", "Oct 1", "Nov 1");
        b1.addBudgetEntry(be1);
        b1.addBudgetEntry(be2);
        try {
            b1.addTrackerEntry("Jan 1", "Grocery", 0);
            b1.addTrackerEntry("Jan 2", "Gas", 0);
        } catch (InvalidBudgetEntryException e) {
            System.out.println("Could not add Tracker Entries");
        }

        b2 = new Budget("B2", "Sep 1", "Oct 1");

        listOfB = new ArrayList<Budget>();
        listOfB.add(b1);
        listOfB.add(b2);
    }

    private void initTracker() {
        tr1 = new Tracker();
        tr1.addEntry(te1);
        tr1.addEntry(te2);

        tr2 = new Tracker();
        tr2.addEntry(te3);
        tr2.addEntry(te4);
    }

    private void initTrackerEntries() {
        te1 = new TrackerEntry("Jan 2", be1, 56.99);
        te2 = new TrackerEntry("Jan 2", be2, 40.5);
        listOfTE = new ArrayList<TrackerEntry>();
        listOfTE.add(te1);
        listOfTE.add(te2);

        te3 = new TrackerEntry("Feb 3", be4, 50);
        te4 = new TrackerEntry("Feb 4", be3, 98);
    }

    private void initBudgeter() {
        btr1 = new Budgeter();
        btr1.addEntry(be1);
        btr1.addEntry(be2);

        btr2 = new Budgeter();
        btr2.addEntry(be3);
        btr2.addEntry(be4);
    }

    private void initBudgetEntries() {
        be1 = new Expense("1000", "Grocery", 400, 56.99);
        be2 = new Expense("1001", "Gas", 200, 40.5);
        listOfBE = new ArrayList<BudgetEntry>();
        listOfBE.add(be1);
        listOfBE.add(be2);

        be3 = new Expense("1001", "Grocery", 33.44, 98);
        be4 = new Expense("1000", "Gas", 66.99, 50);
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

        JSONObject jsonTrackerEntry1 = jsonTrackerEntries.optJSONObject(0);
        JSONObject jsonTrackerEntry2 = jsonTrackerEntries.optJSONObject(1);

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

    @Test
    void testTrackerToJson() {

        JSONObject jsonTracker = jh.trackerToJson(tr1);

        JSONArray jsonTrackerEntries = jsonTracker.optJSONArray("entries");

        JSONObject jsonTrackerEntry1 = jsonTrackerEntries.optJSONObject(0);
        JSONObject jsonTrackerEntry2 = jsonTrackerEntries.optJSONObject(1);

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

    @Test
    void testSaveBudgetPrimitives() {
        JSONObject jsonBudget = jh.saveBudget(b1);

        JSONObject jsonPrimitives = jsonBudget.optJSONObject("primitives");

        assertEquals(4, jsonBudget.keySet().size());
        assertEquals(3, jsonPrimitives.keySet().size());

        // Checking primitives
        assertEquals(b1.getName(), jsonPrimitives.opt("name"));
        assertEquals(b1.getStartDate(), jsonPrimitives.opt("startDate"));
        assertEquals(b1.getEndDate(), jsonPrimitives.opt("endDate"));
    }

    @Test
    void testSaveBudgetPrimitivesBudgetEntries() {
        JSONObject jsonBudget = jh.saveBudget(b1);

        JSONArray jsonBudgetEntries = jsonBudget.optJSONArray("budgetEntries");

        assertEquals(4, jsonBudget.keySet().size());
        assertEquals(2, jsonBudgetEntries.length());

        // Checking Budget Entry Look Up Data
        assertEquals(2, jsonBudgetEntries.length());

        JSONObject jsonBE1 = jsonBudgetEntries.optJSONObject(0);
        JSONObject jsonBE2 = jsonBudgetEntries.optJSONObject(1);

        assertEquals(4, jsonBE1.keySet().size());
        assertEquals(4, jsonBE2.keySet().size());

        // Checking string because BudgetEntries were generated from a Map
        // thus the objects don't have an expected order
        String jsonString = jsonBudgetEntries.toString();
        assertTrue(jsonString.contains("\"id\":\"1001\""));
        assertTrue(jsonString.contains("\"name\":\"Gas\""));
        assertTrue(jsonString.contains("\"budgetAmount\":200"));
        assertTrue(jsonString.contains("\"actualAmount\":40.5"));
        assertTrue(jsonString.contains("\"id\":\"1000\""));
        assertTrue(jsonString.contains("\"name\":\"Grocery\""));
        assertTrue(jsonString.contains("\"budgetAmount\":400"));
        assertTrue(jsonString.contains("\"actualAmount\":56.99"));
    }

    @Test
    void testSaveBudgetBudgeter() {
        JSONObject jsonBudget = jh.saveBudget(b1);

        JSONObject jsonBudgeter = jsonBudget.optJSONObject("budgeter");

        assertEquals(4, jsonBudget.keySet().size());

        // Checking Budgeter
        JSONArray jsonBudgeterBudgetEntries = jsonBudgeter.optJSONArray("budgetEntries");
        assertEquals(2, jsonBudgeterBudgetEntries.length());

        String jsonString = jsonBudgeterBudgetEntries.toString();
        assertTrue(jsonString.contains("\"id\":\"1001\""));
        assertTrue(jsonString.contains("\"id\":\"1000\""));

    }

    @Test
    void testSaveBudgetTracker() {
        JSONObject jsonBudget = jh.saveBudget(b1);

        JSONObject jsonTracker = jsonBudget.optJSONObject("tracker");
        assertEquals(4, jsonBudget.keySet().size());

        // Checking Tracker
        JSONArray jsonTrackerEntries = jsonTracker.optJSONArray("entries");
        JSONObject jsonTE1 = jsonTrackerEntries.optJSONObject(0);
        JSONObject jsonTE2 = jsonTrackerEntries.optJSONObject(1);

        List<TrackerEntry> listOfTE = b1.getTracker().getEntries();
        TrackerEntry te1 = listOfTE.get(0);
        TrackerEntry te2 = listOfTE.get(1);

        assertEquals(2, jsonTrackerEntries.length());
        assertEquals(3, jsonTE1.keySet().size());
        assertEquals(3, jsonTE2.keySet().size());

        assertEquals(te1.getDate(), jsonTE1.optString("date"));
        assertEquals(be1.getId(), jsonTE1.optJSONObject("budgetEntry").opt("id"));
        assertEquals(te1.getAmount(), jsonTE1.optDouble("amount"));

        assertEquals(te2.getDate(), jsonTE2.optString("date"));
        assertEquals(be2.getId(), jsonTE2.optJSONObject("budgetEntry").opt("id"));
        assertEquals(te2.getAmount(), jsonTE2.optDouble("amount"));
    }

    @Test
    void testSaveBudgets() {
        JSONArray jsonBudgets = jh.saveBudgets(listOfB);

        assertEquals(2, jsonBudgets.length());
    }

    @Test
    void testWriteBudgetsToFile() {

        String path = "data/";
        String name = "writeBudgetsToFileTest";

        try {
            jh.writeBudgetsToFile(listOfB, path, name);
        } catch (FileNotFoundException e) {
            fail();
        }

        Path testFile = Paths.get(path, name + ".json");

        if (Files.exists(testFile)) {
            // expected
        } else {
            fail();
        }
    }

    @Test
    void testLoadBudgetsFromFile() {
        String path = "data/loadBudgetsTest.json";
        List<Budget> expectedBudgets = new ArrayList<Budget>();
        try {
            List<Budget> budgets = jh.loadBudgetsFromFile(path);

            Budget expectedBudget1 = new Budget("Jan", "Jan 1", "Feb 1", btr1, tr1);
            Budget expectedBudget2 = new Budget("Feb", "Feb 1", "Mar 1", btr2, tr2);
            expectedBudgets.add(expectedBudget1);
            expectedBudgets.add(expectedBudget2);

            assertTrue(budgets.equals(expectedBudgets));

        } catch (IOException e) {
            fail();
        }
    }

    @SuppressWarnings("methodlength") // cannot split up into more methods
    @Test
    void testReadJsonSave() {
        String path = "data/loadBudgetsTest.json";
        try {
            String budgetsString = jh.readJsonSave(path);
            String expectedString = "[\n"
                    + "    {\n"
                    + "        \"primitives\": {\n"
                    + "            \"endDate\": \"Feb 1\",\n"
                    + "            \"name\": \"Jan\",\n"
                    + "            \"startDate\": \"Jan 1\"\n"
                    + "        },\n"
                    + "        \"budgetEntries\": [\n"
                    + "            {\n"
                    + "                \"budgetAmount\": 400,\n"
                    + "                \"actualAmount\": 56.99,\n"
                    + "                \"name\": \"Grocery\",\n"
                    + "                \"id\": \"1000\"\n"
                    + "            },\n"
                    + "            {\n"
                    + "                \"budgetAmount\": 200,\n"
                    + "                \"actualAmount\": 40.5,\n"
                    + "                \"name\": \"Gas\",\n"
                    + "                \"id\": \"1001\"\n"
                    + "            }\n"
                    + "        ],\n"
                    + "        \"tracker\": {\n"
                    + "            \"entries\": [\n"
                    + "                {\n"
                    + "                    \"date\": \"Jan 2\",\n"
                    + "                    \"amount\": 56.99,\n"
                    + "                    \"budgetEntry\": {\n"
                    + "                        \"id\": \"1000\"\n"
                    + "                    }\n"
                    + "                },\n"
                    + "                {\n"
                    + "                    \"date\": \"Jan 2\",\n"
                    + "                    \"amount\": 40.5,\n"
                    + "                    \"budgetEntry\": {\n"
                    + "                        \"id\": \"1001\"\n"
                    + "                    }\n"
                    + "                }\n"
                    + "            ]\n"
                    + "        },\n"
                    + "        \"budgeter\": {\n"
                    + "            \"budgetEntries\": [\n"
                    + "                {\n"
                    + "                    \"id\": \"1000\"\n"
                    + "                },\n"
                    + "                {\n"
                    + "                    \"id\": \"1001\"\n"
                    + "                }\n"
                    + "            ]\n"
                    + "        }\n"
                    + "    },\n"
                    + "    {\n"
                    + "        \"primitives\": {\n"
                    + "            \"endDate\": \"Mar 1\",\n"
                    + "            \"name\": \"Feb\",\n"
                    + "            \"startDate\": \"Feb 1\"\n"
                    + "        },\n"
                    + "        \"budgetEntries\": [\n"
                    + "            {\n"
                    + "                \"budgetAmount\": 33.44,\n"
                    + "                \"actualAmount\": 98,\n"
                    + "                \"name\": \"Grocery\",\n"
                    + "                \"id\": \"1001\"\n"
                    + "            },\n"
                    + "            {\n"
                    + "                \"budgetAmount\": 66.99,\n"
                    + "                \"actualAmount\": 50,\n"
                    + "                \"name\": \"Gas\",\n"
                    + "                \"id\": \"1000\"\n"
                    + "            }\n"
                    + "        ],\n"
                    + "        \"tracker\": {\n"
                    + "            \"entries\": [\n"
                    + "                {\n"
                    + "                    \"date\": \"Feb 3\",\n"
                    + "                    \"amount\": 50,\n"
                    + "                    \"budgetEntry\": {\n"
                    + "                        \"id\": \"1000\"\n"
                    + "                    }\n"
                    + "                },\n"
                    + "                {\n"
                    + "                    \"date\": \"Feb 4\",\n"
                    + "                    \"amount\": 98,\n"
                    + "                    \"budgetEntry\": {\n"
                    + "                        \"id\": \"1001\"\n"
                    + "                    }\n"
                    + "                }\n"
                    + "            ]\n"
                    + "        },\n"
                    + "        \"budgeter\": {\n"
                    + "            \"budgetEntries\": [\n"
                    + "                {\n"
                    + "                    \"id\": \"1001\"\n"
                    + "                },\n"
                    + "                {\n"
                    + "                    \"id\": \"1000\"\n"
                    + "                }\n"
                    + "            ]\n"
                    + "        }\n"
                    + "    }\n"
                    + "]";

            assertEquals(expectedString.replaceAll("\\s+", ""), budgetsString.replaceAll("\\s+", ""));

        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testLoadBudget() {
        Budget budget1 = jh.loadBudget(jsonBudget1);
        Budget budget2 = jh.loadBudget(jsonBudget2);

        Budget expectedBudget1 = new Budget("Jan", "Jan 1", "Feb 1", btr1, tr1);
        Budget expectedBudget2 = new Budget("Feb", "Feb 1", "Mar 1", btr2, tr2);

        assertTrue(budget1.equals(expectedBudget1));
        assertTrue(budget2.equals(expectedBudget2));
    }

    @Test
    void testLoadBudgeter() {
        Budgeter budgeter = jh.loadBudgeter(jsonBudget1, listOfBE);

        assertEquals(btr1, budgeter);
    }

    @Test
    void testLoadBudgeterNotFound() {
        listOfBE.remove(0);
        Budgeter budgeter = jh.loadBudgeter(jsonBudget1, listOfBE);

        assertNull(budgeter);
    }

    @Test
    void testLoadBudgetEntries() {

        JSONArray jsonBudgetEntries = jsonBudget1.optJSONArray("budgetEntries");

        List<BudgetEntry> budgetEntries = jh.loadBudgetEntries(jsonBudgetEntries);

        assertTrue(budgetEntries.equals(listOfBE));

    }

    @Test
    void testLoadTracker() {

        JSONObject jsonTracker = jsonBudget1.optJSONObject("tracker");
        Tracker tracker = jh.loadTracker(jsonTracker, listOfBE);

        assertTrue(tracker.equals(tr1));

    }

    @Test
    void testLoadBudgetPrimitives() {

        JSONObject jsonPrimitives = jsonBudget1.optJSONObject("primitives");
        List<String> primitives = jh.loadBudgetPrimitives(jsonPrimitives);

        List<String> expectedPrimitives = new ArrayList<String>();
        expectedPrimitives.add("Feb 1");
        expectedPrimitives.add("Jan");
        expectedPrimitives.add("Jan 1");

        assertTrue(primitives.equals(expectedPrimitives));

    }
}
