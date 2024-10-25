package persistence;

import java.util.List;

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
        budgetEntriesToJson(null); // use helper
        return null; // stub
    }

    // TODO:
    // EFFECT: returns JSONObject for budgeter's list of BudgetEntries
    public JSONObject budgetEntriesToJson(List<BudgetEntry> budgetEntries) {

        budgetEntryToJson(null); // use helper
        return null; // stub
    }

    // TODO:
    // EFFECT: returns JSONObject for a BudgetEntry
    public JSONObject budgetEntryToJson(BudgetEntry budgetEntry) {

        return null; // stub
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
