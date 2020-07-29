# Deck Of Cards API Automation Testing

The test contained 3 api testing. Did testing through REST Assured, all are completed with minimum 2 test cases.
### API 1 : 
##### Test Get API (create a new deck of Cards) -
 
### Answer : 
    Tested API = https://deckofcardsapi.com/api/deck/new/
  
### API 2 : 
##### Test Post API(Adding jokers in the new deck).

### Answer : 
    Tested API = https://deckofcardsapi.com/api/deck/new?jokers_enabled=true
   
## API 3 : 
##### Test Get API(Drawing one or more cards from the deck).

### Answer : 
     To Draw a card we have two ways :
    - case 1 : Draw cards from already created Deck : For This I made a @test function to extract deck_id 
               API = https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/?count=2
    - case 2 : Drwa cards from new Deck
               API = https://deckofcardsapi.com/api/deck/new/draw/?count=2

# Steps to run
    1. Simply run TestingApis.java file in test/java/ApiTesting folder
    2. You can also Run run mvn test 