# CSC207 Cocktail Application

## Fall 2024

## Team: GROUP 200

- Stanway Cen
- Yuxin Li
- Qianwen Wang
- Martin ZOU

## Project Description
By this program, users can get recommendations, recipes, and components of cocktails and save them in their “favorite” 
if they really like the cocktail. The user should create an account before using the actual application; once one is 
logged in, a daily recommendation will be shown. A user can search a cocktail by its name or components. A user can save
a cocktail file into the “favorite” file belonging to each user. A user can generate a list of items and pricing they 
need to get a cocktail by themselves. 

### Views

#### 1. SignUp
##### Purpose:
Allow user to create an account.
##### Elements:
- [TextField] Choose username
- [TextField] Choose password
- [TextField] Enter password again
- [button] Go to Login
- [button] Sign up
- [button] Cancel

#### 2. Login
##### Purpose:
Allow user to access their account
##### Elements:
- [TextField] Username
- [TextField] Password
- [button] Log in
- [button] Cancel

#### 3. HomePage
##### Purpose:
Main landing page, which may contain options for various actions, showing Recommendation
##### Elements:
- [button] Search
- [button] Recommendation
- [button] My Favourite
- [button] Change Password

#### 4. Change Password
##### Purpose:
Allows logged-in users to update their password.
##### Elements
- [TextField] New password
- [button] Change password
- [button] Cancel

#### 5. Search
##### Purpose
Allow user  to search for cocktails
##### Elements
- [TextField]
- [button] Search
- [List] Cocktail name and photo + [listener] Cocktail information
- [button] Return 

#### 6. Recommendation
##### Purpose
Provides random recommendations of Cocktail to user
##### Elements
- [List] Cocktail name and photo + [listener] Cocktail information
- [button] Return

#### 7. MyFavourite
##### Purpose
Displays a list of items that user has marked as favorites
##### Elements
- [List] Cocktail name and photo + [button] delete + [listener] Cocktail information
- [button] Return

#### 8. CocktailInfo
##### Purpose
Shows detailed information about a selected cocktail, such as ingredients, recipe, or images.
##### Elements
- [Panel] Cocktail photo
- [List] Cocktail name, ingredients, recipe
- [button] Add to My Favourite
- [button] Return

### Use Cases

#### 1. signup
##### Goal 
Allow a new user to create an account in the system. 
##### Actors
User: a user who need an account
##### Preconditions
The user must need a new account at signup page
##### Main Flow
1. The user navigates to the signup page.
2. The system displays the signup form with fields for username, password, re-enter password, 
and buttons: turn to login, sign up, cancel.
3. The system validates the user’s input.
4. The system creates the user account and stores the information in the database.
5. The user redirected to the login page and display a success message. 
##### Alternative Flows
- Invalid input: If any required fields are missing or invalid, 
the system displays error messages and prompts the user to correct the errors.
- User Already Exists: If the email or username is already registered, 
the system indicating that the account already exists

#### 2. login
##### Goal
All a user with an existing account to log into the system
##### Actors
User: a user who has an existing account
##### Preconditions
The user must have an account at login page
##### Main Flow
1. The user navigates to the login page.
2. The system displays the login form with fields for username and password, and buttons.
3. The user enters credentials and click log in button.
4. The system verifies the credentials.
5. If the credentials are valid, the system grants access to the user’s account and redirects to the homepage.
##### Alternative Flows
- Invalid Credentials: If the username or password is incorrect, 
the system displays an error message and prompts the user to re-enter their credentials.

#### 3. change password
##### Goal
Allow a logged-in user to change its account password
##### Actors
User: a logged-in user who want to change the password
##### Preconditions
The user must be logged-in at homepage
##### Main Flow
1. The user navigates to the change password page.
2. The system displays the change password form with field for new password, and buttons.
3. The user enters new password and click change password button.
4. The system updates the password in the database.
5. The system redirects to the homepage and display a success message.
##### Alternative Flows


#### 4. logout
##### Goal
Allow a logged-in user to safely log out of their account
##### Actors
User: a logged-in user who wants to log out
##### Preconditions
The user must be logged in at homepage
##### Main Flow
1. The user clicks the logout button on homepage
2. The system terminates user's session
3. The system redirects user to the signup page and displays a confirmation message.
##### Alternative Flows

#### 5. search by name/id
##### Goal
Allow users to search for Cocktails in the system based on name or id
##### Actors
User: a logged-in user who wants to search for a cocktail by name or id
Cocktail Database: provide information about cocktail
##### Preconditions
The user must be logged in at homepage
##### Main Flow
1. The user navigates to the search page.
2. the system display the search bar field and buttons
3. The user enters name/id for the cocktail and click search button
4. The system display a list of Panels containing Name and photo of each cocktail under the search bar
5. 
##### Alternative Flows

#### 6. search by ingredient
##### Goal
##### Actors
##### Preconditions
The user must be logged in at homepage
##### Main Flow
##### Alternative Flows

#### 7. filter
##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 8. recommend
##### Goal
##### Actors
##### Preconditions
The user must be logged in at homepage
##### Main Flow
##### Alternative Flows

#### 9. add favourite
##### Goal
##### Actors
##### Preconditions
The user must be logged in at homepage
##### Main Flow
##### Alternative Flows

#### 10. delete favourite
##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows
