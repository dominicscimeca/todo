#Todo App extends Maintainable Code

###### #top-down #test-driven

### Stories (in priority)
1. **[Complete]** User Service (Zero feature story)
    1. User -> full_name, first_name, last_name, id
1. **[In Progress]** User Service get by user by Id
    1. User -> full_name, first_name, last_name, id
1. Front end displays user details by Id (zero feature story)
1. Login
    1. As: a customer 
    1. Scenario login
        1. Given: 
            1. I am on "/login" 
        1. When:
            1. I put in correct credentials
        1. Then: 
            1. I go to "/"
            1. I am logged in
    1. Scenario logout
        1. Given: 
            1. I am on "/" 
        1. When:
            1. I click logout
        1. Then: 
            1. I go to "/login"
            1. I am logged out
    1. Scenario redirect to login
        1. Given:
            1. I am not logged in
        1. When:
            1. I go to "/"
        1. Then:
            1. I am redirected to "/login"
    1. Scenario reload on login
        1. Given:
            1. I am logged in
            1. I am on "/"
        1. When:
            1. I reload the page
        1. Then:
            1. I stay on "/"
1. Welcome message
    1. As: a customer 
    1. Scenario logged in welcome
        1. Given: 
            1. I am on "/"
            1. I am logged in
            1. My full name is Bob Smith
        1. When:
            1. I look at the top nav
        1. Then: 
            1. I will see the message "Welcome back Bob!"
1. Adding todos
    1. As: a logged in customer 
    1. Scenario add todo
        1. Given: 
            1. I am on "/"
        1. When:
            1. I enter "go to the store" in the "add todo" input
            1. I click the "add todo" button
        1. Then: 
            1. I see a new todo called "go to the store"
1. Persisting todos
    1. As: a logged in customer on the home page
    1. Scenario persist add todo
        1. Given: 
            1. I have added todos called "A","B","C"
        1. When:
            1. I reload the page
        1. Then: 
            1. I see todos "A","B","C"
1. Checking off todos
    1. As: a logged in customer on the home page
    1. Scenario check off todo
        1. Given: 
            1. I have added todos called "A","B","C"
        1. When:
            1. I click the check box next to "B"
        1. Then: 
            1. B todo turns gray  with line through and box is checked       
    1. Scenario un-check off
        1. Given: 
            1. I have a checked todo "A"
        1. When:
            1. I uncheck todo "A"
        1. Then: 
            1. I see "A" without styling and box is unchecked   
    1. Scenario checking off all todos
        1. Given: 
            1. I have unchecked todos
        1. When:
            1. I check them all
        1. Then: 
            1. I will see a message "Congrats! All Done!"
1. Deleting todos
    1. As: a logged in customer on the home page
    1. Scenario delete todo
        1. Given: 
            1. I have added todos called "A","B","C"
        1. When:
            1. I reload the page
        1. Then: 
            1. I see todos "A","B","C"       
    1. Scenario delete last todo
        1. Given: 
            1. I have one todo
        1. When:
            1. I delete the todo
        1. Then: 
            1. I see the message "No Todos. Please create one to get Started"   
1. Add todos from template
    1. As: a logged in customer on the home page
    1. Scenario add todos from template
        1. Given: 
            1. I have added todos called "A","B","C"
            1. There is a right side bar with template 1
            1. template one has todos "C","D","E"
        1. When:
            1. I click the "add from template" button for template 1
        1. Then: 
            1. I see todos "A","B","C","D","E","F"       
1. Live Sync todo
    1. As: a logged in customer A on the home page
    1. Scenario other user added todos
        1. Given: 
            1. I have added todos called "A","B","C"
            1. Customer B is also logged in
        1. When:
            1. Customer B adds todo "D"
            1. I do not reload
        1. Then: 
            1. I see todos "A","B","C","D"     
           
##Initial Architectural Planning
### Java base library
- Common http server functionality

### Scala Services
##### use common functionality (java) through import
- User Service
    - get by id

- Auth Service
  - login 
    - returns token
        - jwt
    
- Todo Service
    -  list, add, delete, getOne
    
- TodoTemplate Service
    - creates a bunch of new todos off of a template

    
### Haskell Services
- Greeting Service
    - welcome greeting

### React
1. uses auth service to login
2. User service to get name
3. greeting service to get greeting
4. todo service for todo app functionality

- Docker
- AWS
    - ECS


## **Bonus**
Websockets for Todo live sync