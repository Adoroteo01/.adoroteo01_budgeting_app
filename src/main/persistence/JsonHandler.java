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
    private JSONObject budgetPrimitiveFieldsToJson(Budget budget) {
        return null; // stub
    }

    // EFFECT: returns JSONObject for budget's budgeter
    private JSONObject budgeterToJson(Budgeter budgeter) {
        budgetEntriesToJson(null); // use helper
        return null; // stub
    }

    // EFFECT: returns JSONObject for budgeter's list of BudgetEntries
    private JSONObject budgetEntriesToJson(List<BudgetEntry> budgetEntries) {

        budgetEntryToJson(null); // use helper
        return null; // stub
    }

    // EFFECT: returns JSONObject for a BudgetEntry
    private JSONObject budgetEntryToJson(BudgetEntry budgetEntry) {

        return null; // stub
    }

    // EFFECT: returns JSONObject for budget's tracker
    private JSONObject trackerToJson(Tracker tracker) {

        trackerEntriesToJson(null); // use helper
        return null; // stub
    }

    // EFFECT: returns JSONObject for tracker's list of TrackerEntries
    private JSONObject trackerEntriesToJson(List<TrackerEntry> trackerEntries) {

        trackerEntryToJson(null); // use helper
        return null; // stub
    }

    // EFFECT: returns JSONObject for a TrackerEntry
    private JSONObject trackerEntryToJson(TrackerEntry trackerEntry) {
        return null; // stub
    }

}
