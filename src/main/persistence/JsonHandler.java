package persistence;

import java.util.List;

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

    public JsonHandler() {

    }

    // EFFECTS: writes fields of budget in a JSONObject for saving
    public void saveBudget(Budget budget) {

        // use helpers
        budgetPrimitiveFieldsToJson(budget);
        budgetEntriesToJson(null);
        budgeterToJson(null);
        trackerToJson(null);

        // stub
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

    // TODO:
    // EFFECT: returns JSONObject for budget's budgeter
    public JSONObject budgeterToJson(Budgeter budgeter) {
        budgetEntriesIdToJson(null); // use helper
        return null; // stub
    }

    // TODO:
    // EFFECT: returns JSONArray for the id's of each BudgetEntry in the
    // given list of BudgetEntries
    public JSONArray budgetEntriesIdToJson(List<BudgetEntry> budgetEntries) {

        // use helper
        budgetEntryIdToJson(null);

        return null; // stub
    }

    // TODO:
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

    // TODO:
    // EFFECT: returns JSONObject for budget's tracker
    public JSONObject trackerToJson(Tracker tracker) {

        trackerEntriesToJson(null); // use helper
        return null; // stub
    }

    // TODO:
    // EFFECT: returns JSONObject for tracker's list of TrackerEntries
    public JSONObject trackerEntriesToJson(List<TrackerEntry> trackerEntries) {

        trackerEntryToJson(null); // use helper
        return null; // stub
    }

    // TODO:
    // EFFECT: returns JSONObject for a TrackerEntry
    public JSONObject trackerEntryToJson(TrackerEntry trackerEntry) {
        return null; // stub
    }

}
