package persistence;

import java.util.List;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Budget;
import model.Budgeter;
import model.Tracker;
import model.TrackerEntry;
import model.budgetentries.BudgetEntry;

// A handler for saving and loading the state of a Budgeting App
// Saves app states by writing field values in json
// Loads app states by reading saved json files and setting fields to corrisponding values
public class JsonHandler {

    private PrintWriter printWriter;

    public JsonHandler() {

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
    public JSONObject budgetPrimitiveFieldsToJson(Budget budget) {
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
    public JSONObject budgeterToJson(Budgeter budgeter) {
        JSONObject jsonBudgter = new JSONObject();

        JSONArray jsonBudgetEntries = budgetEntriesIdToJson(budgeter.getbudgetEntries());

        jsonBudgter.put("budgetEntries", jsonBudgetEntries);

        return jsonBudgter;
    }

    // EFFECT: returns JSONArray for the id's of each BudgetEntry in the
    // given list of BudgetEntries
    public JSONArray budgetEntriesIdToJson(List<BudgetEntry> budgetEntries) {

        JSONArray jsonBudgetEntries = new JSONArray();

        for (BudgetEntry budgetEntry : budgetEntries) {
            JSONObject jsonBudgetEntry = budgetEntryIdToJson(budgetEntry);
            jsonBudgetEntries.put(jsonBudgetEntry);
        }

        return jsonBudgetEntries;
    }

    // EFFECT: returns JSONObject of the id of given BudgetEntry
    public JSONObject budgetEntryIdToJson(BudgetEntry budgetEntry) {

        JSONObject jsonBudgetEntry = new JSONObject();

        String id = budgetEntry.getId();
        jsonBudgetEntry.put("id", id);

        return jsonBudgetEntry;
    }

    // EFFECT: returns JSONArray for given list of BudgetEntries
    public JSONArray budgetEntriesToJson(List<BudgetEntry> budgetEntries) {

        JSONArray jsonBudgetEntries = new JSONArray();

        for (BudgetEntry budgetEntry : budgetEntries) {
            JSONObject jsonBudgetEntry = budgetEntryToJson(budgetEntry);
            jsonBudgetEntries.put(jsonBudgetEntry);
        }

        return jsonBudgetEntries;
    }

    // EFFECT: returns JSONObject for given BudgetEntry
    public JSONObject budgetEntryToJson(BudgetEntry budgetEntry) {
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
    public JSONObject trackerToJson(Tracker tracker) {

        JSONObject jsonTracker = new JSONObject();
        JSONArray jsonTrackerEntries = trackerEntriesToJson(tracker.getEntries());

        jsonTracker.put("entries", jsonTrackerEntries);

        return jsonTracker;
    }

    // EFFECT: returns JSONObject for tracker's list of TrackerEntries
    public JSONArray trackerEntriesToJson(List<TrackerEntry> trackerEntries) {

        JSONArray jsonTrackerEntries = new JSONArray();

        for (TrackerEntry trackerEntry : trackerEntries) {
            JSONObject jsonTrackerEntry = trackerEntryToJson(trackerEntry);
            jsonTrackerEntries.put(jsonTrackerEntry);
        }

        return jsonTrackerEntries;
    }

    // EFFECT: returns JSONObject for a TrackerEntry
    public JSONObject trackerEntryToJson(TrackerEntry trackerEntry) {
        JSONObject jsonTrackerEntry = new JSONObject();

        BudgetEntry budgetEntry = trackerEntry.getBudgetEntry();
        JSONObject jsonBudgetEntryId = budgetEntryIdToJson(budgetEntry);

        jsonTrackerEntry.put("date", trackerEntry.getDate());
        jsonTrackerEntry.put("budgetEntry", jsonBudgetEntryId);
        jsonTrackerEntry.put("amount", trackerEntry.getAmount());

        return jsonTrackerEntry;
    }

    // ---------------- LOADING ----------------

    // TODO:
    // REQUIRES: - all BudgetEntry in budgetEntries must have actualAmount equal
    // to the sum of all amounts of their associated TrackerEntry(s)
    // - path is a path to a .json Budgets save file
    // EFFECT: returns a list of Budget of the given path to a Budgets save file
    public List<Budget> loadBudgetsFromFile(String path) {
        return null; // stub
    }
}
