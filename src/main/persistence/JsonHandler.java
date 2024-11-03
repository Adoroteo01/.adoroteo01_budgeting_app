package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Budget;
import model.Budgeter;
import model.Tracker;
import model.TrackerEntry;
import model.budgetentries.BudgetEntry;
import model.budgetentries.Expense;

// A handler for saving and loading the state of a Budgeting App
// Saves app states by writing field values in json
// Loads app states by reading saved json files and setting fields to corrisponding values
public class JsonHandler {

    private PrintWriter printWriter;

    // creates new JsonHandler with no printWriter
    public JsonHandler() {
        // no action
    }

    // REQUIRES: path must be a valid path
    // EFFECT: creates file in json format at the location of path.
    // file is named filename.json and is a json data of a list of Budget
    public void writeBudgetsToFile(List<Budget> budgets, String path, String filename) throws FileNotFoundException {

        printWriter = new PrintWriter(new File(path + filename + ".json"));

        String jsonStringSavedBudgets = saveBudgets(budgets).toString();
        printWriter.print(jsonStringSavedBudgets);
        printWriter.close();
    }

    // EFFECT: returns JSONArray containing JSONObjects of Budgets in budgets list
    public JSONArray saveBudgets(List<Budget> budgets) {
        JSONArray jsonBudgets = new JSONArray();

        for (Budget budget : budgets) {
            JSONObject jsonBudget = saveBudget(budget);
            jsonBudgets.put(jsonBudget);

        }

        return jsonBudgets;
    }

    // EFFECTS: writes fields of budget in a JSONObject for saving
    public JSONObject saveBudget(Budget budget) {
        JSONObject jsonBudget = new JSONObject();

        JSONObject jsonPrimitives = budgetPrimitiveFieldsToJson(budget);
        JSONArray jsonBudgetEntries = budgetEntriesToJson(budget.getBudgetEntries());
        JSONObject jsonBudgeter = budgeterToJson(budget.getBudgeter());
        JSONObject jsonTracker = trackerToJson(budget.getTracker());

        jsonBudget.put("primitives", jsonPrimitives);
        jsonBudget.put("budgetEntries", jsonBudgetEntries);
        jsonBudget.put("budgeter", jsonBudgeter);
        jsonBudget.put("tracker", jsonTracker);

        return jsonBudget;
    }

    // EFFECTS: returns JSONObject for primitive fields in budget
    JSONObject budgetPrimitiveFieldsToJson(Budget budget) {
        JSONObject jsonPrimitives = new JSONObject();

        String name = budget.getName();
        String startDate = budget.getStartDate();
        String endDate = budget.getEndDate();

        jsonPrimitives.put("name", name);
        jsonPrimitives.put("startDate", startDate);
        jsonPrimitives.put("endDate", endDate);

        return jsonPrimitives;
    }

    // EFFECT: returns JSONObject for budget's budgeter
    // where each BudgetEntry in budgeter gets it's id saved
    // in no particular order.
    JSONObject budgeterToJson(Budgeter budgeter) {
        JSONObject jsonBudgter = new JSONObject();

        JSONArray jsonBudgetEntries = budgetEntriesIdToJson(budgeter.getbudgetEntries());

        jsonBudgter.put("budgetEntries", jsonBudgetEntries);

        return jsonBudgter;
    }

    // EFFECT: returns JSONArray for the id's of each BudgetEntry in the
    // given list of BudgetEntries
    JSONArray budgetEntriesIdToJson(List<BudgetEntry> budgetEntries) {

        JSONArray jsonBudgetEntries = new JSONArray();

        for (BudgetEntry budgetEntry : budgetEntries) {
            JSONObject jsonBudgetEntry = budgetEntryIdToJson(budgetEntry);
            jsonBudgetEntries.put(jsonBudgetEntry);
        }

        return jsonBudgetEntries;
    }

    // EFFECT: returns JSONObject of the id of given BudgetEntry
    JSONObject budgetEntryIdToJson(BudgetEntry budgetEntry) {

        JSONObject jsonBudgetEntry = new JSONObject();

        String id = budgetEntry.getId();
        jsonBudgetEntry.put("id", id);

        return jsonBudgetEntry;
    }

    // EFFECT: returns JSONArray for given list of BudgetEntries
    JSONArray budgetEntriesToJson(List<BudgetEntry> budgetEntries) {

        JSONArray jsonBudgetEntries = new JSONArray();

        for (BudgetEntry budgetEntry : budgetEntries) {
            JSONObject jsonBudgetEntry = budgetEntryToJson(budgetEntry);
            jsonBudgetEntries.put(jsonBudgetEntry);
        }

        return jsonBudgetEntries;
    }

    // EFFECT: returns JSONObject for given BudgetEntry
    JSONObject budgetEntryToJson(BudgetEntry budgetEntry) {
        JSONObject jsonBudgetEntry = new JSONObject();

        String id = budgetEntry.getId();
        String name = budgetEntry.getName();
        Double budgetAmount = budgetEntry.getBudgetAmount();
        Double actualAmount = budgetEntry.getActualAmount();

        jsonBudgetEntry.put("id", id);
        jsonBudgetEntry.put("name", name);
        jsonBudgetEntry.put("budgetAmount", budgetAmount);
        jsonBudgetEntry.put("actualAmount", actualAmount);

        return jsonBudgetEntry;

    }

    // EFFECT: returns JSONObject for budget's tracker
    JSONObject trackerToJson(Tracker tracker) {

        JSONObject jsonTracker = new JSONObject();
        JSONArray jsonTrackerEntries = trackerEntriesToJson(tracker.getEntries());

        jsonTracker.put("entries", jsonTrackerEntries);

        return jsonTracker;
    }

    // EFFECT: returns JSONObject for tracker's list of TrackerEntries
    JSONArray trackerEntriesToJson(List<TrackerEntry> trackerEntries) {

        JSONArray jsonTrackerEntries = new JSONArray();

        for (TrackerEntry trackerEntry : trackerEntries) {
            JSONObject jsonTrackerEntry = trackerEntryToJson(trackerEntry);
            jsonTrackerEntries.put(jsonTrackerEntry);
        }

        return jsonTrackerEntries;
    }

    // EFFECT: returns JSONObject for a TrackerEntry
    JSONObject trackerEntryToJson(TrackerEntry trackerEntry) {
        JSONObject jsonTrackerEntry = new JSONObject();

        BudgetEntry budgetEntry = trackerEntry.getBudgetEntry();
        JSONObject jsonBudgetEntryId = budgetEntryIdToJson(budgetEntry);

        jsonTrackerEntry.put("date", trackerEntry.getDate());
        jsonTrackerEntry.put("budgetEntry", jsonBudgetEntryId);
        jsonTrackerEntry.put("amount", trackerEntry.getAmount());

        return jsonTrackerEntry;
    }

    // ---------------- LOADING ----------------

    // REQUIRES: - all BudgetEntry in budgetEntries must have actualAmount equal
    // to the sum of all amounts of their associated TrackerEntry(s)
    // EFFECT: returns a list of Budget of the given path to a Budgets save file
    public List<Budget> loadBudgetsFromFile(String path) throws IOException {

        String jsonString = readJsonSave(path);
        JSONArray jsonBudgets = new JSONArray(jsonString);

        List<Budget> budgets = new ArrayList<Budget>();

        for (int index = 0; index < jsonBudgets.length(); index++) {

            JSONObject jsonBudget = jsonBudgets.optJSONObject(index);
            Budget budget = loadBudget(jsonBudget);
            budgets.add(budget);
        }

        return budgets;
    }

    // EFFECT: returns contents of the save file at path as a string
    String readJsonSave(String path) throws IOException {
        // Adapted from: CPSC 210 JsonSerializationDemo, University of British Columbia,
        // 2024W1
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> Stringstream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            Stringstream.forEach(s -> stringBuilder.append(s));
        }

        // ---------------------------------------------

        String jsonString = stringBuilder.toString();
        return jsonString;
    }

    // EFFECT: returns a Budget with data of jsonBudget
    Budget loadBudget(JSONObject jsonBudget) {

        List<String> listOfPrimitives = loadBudgetPrimitives(jsonBudget.optJSONObject("primitives"));

        String budgetEndDate = listOfPrimitives.get(0);
        String budgetName = listOfPrimitives.get(1);
        String budgetStartDate = listOfPrimitives.get(2);

        List<BudgetEntry> budgetEntries = loadBudgetEntries(jsonBudget.optJSONArray("budgetEntries"));

        Tracker tracker = loadTracker(jsonBudget.getJSONObject("tracker"), budgetEntries);
        Budgeter budgeter = loadBudgeter(jsonBudget.getJSONObject("budgeter"), budgetEntries);
        Budget budget = new Budget(budgetName, budgetStartDate, budgetEndDate, budgeter, tracker);

        return budget;
    }

    // REQUIRES: jsonBudgeter contains data for a Budget's Budgeter, budgetEntries
    // have the same ids as
    // budgetEntries in jsonBudgeter
    // EFFECT: returns a Budget's Budgeter with given data. returns null if
    // arguments invalid.
    Budgeter loadBudgeter(JSONObject jsonBudgeter, List<BudgetEntry> budgetEntries) {

        JSONArray jsonBudgetEntries = jsonBudgeter.getJSONArray("budgetEntries");
        List<BudgetEntry> foundBudgetEntries = new ArrayList<BudgetEntry>();

        for (int i = 0; i < jsonBudgetEntries.length(); i++) {
            JSONObject jsonBudgetEntry = jsonBudgetEntries.getJSONObject(i);

            String id = jsonBudgetEntry.getString("id");
            BudgetEntry budgetEntry = lookupBudgetEntry(id, budgetEntries);
            if (budgetEntry == null) {
                return null;
            }
            foundBudgetEntries.add(budgetEntry);
        }

        Budgeter budgeter = new Budgeter(foundBudgetEntries);

        return budgeter;

    }

    // REQUIRES: there is a BudgetEntry with id of id in budgetEntries
    // EFFECT: returns the BudgetEntry in budgetEntries with given id
    private BudgetEntry lookupBudgetEntry(String id, List<BudgetEntry> budgetEntries) {

        for (BudgetEntry budgetEntry : budgetEntries) {
            if (budgetEntry.getId().equals(id)) {
                return budgetEntry;
            }
        }

        return null; // if cannot find corrisponding budget entry
    }

    // REQUIRES: jsonTracker contains data for a Budget's Tracker
    // EFFECT: returns a Budget's Tracker with given data
    Tracker loadTracker(JSONObject jsonTracker, List<BudgetEntry> budgetEntries) {

        JSONArray jsonTrackerEntries = jsonTracker.getJSONArray("entries");
        Tracker tracker = new Tracker();

        for (int i = 0; i < jsonTrackerEntries.length(); i++) {
            JSONObject jsonTrackerEntry = jsonTrackerEntries.getJSONObject(i);

            String date = jsonTrackerEntry.getString("date");
            Double amount = jsonTrackerEntry.getDouble("amount");

            String id = jsonTrackerEntry.getJSONObject("budgetEntry").getString("id");
            BudgetEntry budgetEntry = lookupBudgetEntry(id, budgetEntries);

            TrackerEntry trackerEntry = new TrackerEntry(date, budgetEntry, amount);
            tracker.addEntry(trackerEntry);
        }

        return tracker;
    }

    // REQUIRES: jsonBudgetEntries contains data for a Budget's BudgetEntry(s)
    // EFFECT: returns a list of a Budget's BudgetEntry(s) with given data.
    List<BudgetEntry> loadBudgetEntries(JSONArray jsonBudgetEntries) {

        List<BudgetEntry> budgetEntries = new ArrayList<BudgetEntry>();

        for (int i = 0; i < jsonBudgetEntries.length(); i++) {
            JSONObject jsonBudgetEntry = jsonBudgetEntries.getJSONObject(i);
            Double budgetAmount = jsonBudgetEntry.getDouble("budgetAmount");
            Double actualAmount = jsonBudgetEntry.getDouble("actualAmount");
            String name = jsonBudgetEntry.getString("name");
            String id = jsonBudgetEntry.getString("id");

            BudgetEntry budgetEntry = new Expense(id, name, budgetAmount, actualAmount);
            budgetEntries.add(budgetEntry);
        }

        return budgetEntries;
    }

    // REQUIRES: jsonBudgetPrimitives contains data for a Budget's primitive fields
    // EFFECT: returns a Budget's name, startDate, and endDate in a List in that
    // order with given data.
    List<String> loadBudgetPrimitives(JSONObject jsonBudgetPrimitives) {

        ArrayList<String> primitives = new ArrayList<String>();

        String endDate = jsonBudgetPrimitives.getString("endDate");
        String name = jsonBudgetPrimitives.getString("name");
        String startDate = jsonBudgetPrimitives.getString("startDate");

        primitives.add(endDate);
        primitives.add(name);
        primitives.add(startDate);

        return primitives;
    }
}
