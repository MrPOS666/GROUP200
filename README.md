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
User
##### Preconditions
The user must need a new account
##### Main Flow
1. The user navigates to the signup page.
2. The system displays the signup form with fields for username, password, re-enter password, 
and buttons: turn to login, sign up, cancel.
3. The system validates the user’s input.
4. The system creates the user account and stores the information in the database.
5. The user redirected to the login page and display a success message 
##### Alternative Flows
1. Invalid input: If any required fields are missing or invalid, 
the system displays error messages and prompts the user to correct the errors.
2. User Already Exists: If the email or username is already registered, 
the system indicating that the account already exists

#### 2. login

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 3. change password

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 4. logout

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 5. search

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 6. recommend

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 7. add favourite

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows

#### 8. delete favourite

##### Goal
##### Actors
##### Preconditions
##### Main Flow
##### Alternative Flows
