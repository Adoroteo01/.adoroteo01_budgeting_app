# Budgeting Application

## Project Details

- This application will be allow users to make a budget then track their spending compared to their budget. Users will be able to enter details such as income, expenses, bills, and debt for their budget. Then they will be able to enter their spending details and assign each spending item to one of the budgeted catagories and view statistics on their spending (such as the catagory most purchases).

- This application will be used by people who want to keep track of their money and practice good budgeting habits.

- This is a project of interest to me because I have been practicing budgeting for a long time, using a spreadsheet template to keep track of my spending. I want to create an application that I can use to make my budgetting process faster and more convienent.

## User Stories

- As a user, I want to be able to create a new entry for a purchase (cost, date, catagory, etc...) and add it as a new row to a table of entries

- As a user, I want to be able to view all my purchases in the table of entries

- As a user, I want to be able to view the categories I have most spending

- As a user, I want to be able to create custom budgets with diffferent tables of entries

- As a user I want to be able to view each table of entries in each custom budget

- As a user, I want to be able to have the option to save my budgets to file

- As a user, I want to be able to have the option to load my budgets from file


# Instructions for End User 

- You can create new Budgets with the New Budget button
- You can add Budget Entries to a Budget by selecting desired budget in the list then clicking New Budget Entry
- You can add Tracker Entries to a Budget by selecting desired budget in the list then clicking New Tracker Entry
- You can view the categories of most spending in any selected budget by clicking Summary
- You can locate my visual component by looking at the title bar. The visual component is the logo
- You can save the state of my application by clicking the save button on the left menu
- You can reload the state of my application by clicking the load button on the left menu


# Phase 4: Task 2

Tue Nov 26 15:42:43 PST 2024
Created new Budget buget 1
Tue Nov 26 15:42:59 PST 2024
Created new Bugdet Entry BE1 with budgeted amount:123.0
Tue Nov 26 15:43:58 PST 2024
Created new Tracker Entry for $40.0 for Budget Entry:BE1
Tue Nov 26 15:44:28 PST 2024
Created new Budget budget 2
Tue Nov 26 15:44:53 PST 2024
Created new Bugdet Entry BE1(2) with budgeted amount:987.0
Tue Nov 26 15:45:34 PST 2024
Tue Nov 26 15:45:59 PST 2024
Created new Tracker Entry for $77.0 for Budget Entry:BE2(2)
Tue Nov 26 15:46:40 PST 2024
Created new Tracker Entry for $88.0 for Budget Entry:BE1(2)
Tue Nov 26 15:47:44 PST 2024
Created new Tracker Entry for $8.77 for Budget Entry:BE2(2)
Tue Nov 26 15:48:11 PST 2024
Created Summary for Budget Entries
Tue Nov 26 15:48:42 PST 2024
Created Summary for Budget Entries
Tue Nov 26 15:49:07 PST 2024
Created Summary for Budget Entries
Tue Nov 26 15:49:10 PST 2024
Saved budgets to file


# Phase 4: Task 3

If there were more time to work on this project, a refactoring I would make to improve my design would be to remove the abstract class of BudgetEntry that is extended by Expense. My reasons for this refactoring are because BudgetEntry is redundant in this case. Since Expense is the only BudgetEntry in our code and the features we need or will need likley only use Expense, we should refactor it so Expense contains all the code in BudgetEntry. There is also not much code in BudgetEntry other than getters for fields that could be in Expense.

I think another refactoring that is needed would be to make all the ActionListeners into inner classes and not top level classes. This is because they are all used in exactly 1 place and nowhere else and they will only ever be used in 1 place. Making them into inner classes would remove all the associations they have in my UML diagram becuase all those inner classes would already have access to the information we give them as fields.