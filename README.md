# MtG.Board
*Application in development stage*

*Readme in progress*

An attempt to design **REST Service** that supports trading with **Magic the Gathering** cards.
Each User may have two kinds of card offer lists:
 - wanted list - cards that he/she wants to acquire,
 - sell list - cards that he/she wants to sell.
 
Application will allow users to search offers by card and present them in easy to compare manner.

### Implemented Features / REST API

Service uses external API provided by [MagicTheGathering.io](https://magicthegathering.io/).

Some methods provide or collect data by reaching API endpoints and converting recieved information into required objects.

##### GET:

`/cards/name/like/<partialName>`

- gets JSON array of card objects by `<partialName>`
- <partialName> means that card name contains requested substring (not only begins with) - case insensitive
- each object contains name and setName fields

example:
    
    
    /cards/name/like/chandra
    [
        {
            "name": "Chandra's Outrage",
            "setName": "Masters 25"
        },
        {
            "name": "Chandra's Revolution",
            "setName": "Aether Revolt"
        },
        {...}      
    ]

  `/cards/name/set/<cardName>/<setName>`
  
  - gets JSON from card objects by `<cardName>` and `<setName>`
  - <cardName> means full card name - case sensitive
  - <setName> means full set name that card was printed in - case sensitive
  
  example:
      
      
      /cards/name/set/Shock/Aether Revolt
      {
          "id": 10,
          "name": "Shock",
          "names": null,
          "manaCost": "{R}",
          "cmc": "1.0",
          "colors": "Red",
          "type": "Instant",
          "rarity": "Common",
          "cardSet": "Aether Revolt",
          "text": "Shock deals 2 damage to any target.",
          "flavor": "The tools of invention became the weapons of revolution.",
          "artist": "Jason Rainville",
          "number": "98",
          "power": null,
          "toughness": null,
          "layout": "normal",
          "multiverseId": 423765,
          "imageUrl": "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=423765&type=card",
          "languages": "English, German, Spanish, French, Italian, Japanese, Korean, Portuguese (Brazil), Russian, Chinese Simplified, Chinese Traditional"
      }
      
  `/cards/couter/<cardId>`
  
  - gets JSON with card Id number and number of searches by `<cardId>`,

example:

        /cards/counter/34
        {
            "cardId": 34,
            "countedSearches": 3
        }
    
    
  `/cards/couter/top/<limit>`
  
  - gets JSON array with cards with most searches,
  - number of cards in array is defined by `<limit>`.
  - fields are limited to some basic information and price data.

example:

        /cards/counter/top/2
        [
            {
                "id": 2,
                "name": "Ugin, the Spirit Dragon",
                "setName": "Mythic Edition",
                "rarity": "Mythic",
                "imageUrl": "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=463942&type=card",
                "avgWant": 44,
                "avgSell": 5.2
            },
            {
                "id": 70,
                "name": "Vraska, Regal Gorgon",
                "setName": "Guilds of Ravnica",
                "rarity": "Mythic",
                "imageUrl": "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=455609&type=card",
                "avgWant": 3.2,
                "avgSell": 8.7
            }
        ]
 
 `/offers/<cardId>`
 
 - gets JSON array of offers objects by `<cardId>`
       
example:

        /offers/2
       [
           {
               "id": 1,
               "cardName": "Ugin, the Spirit Dragon",
               "cardId": 2,
               "userName": "user1",
               "userId": 21,
               "quantity": 3,
               "language": "English",
               "cardCondition": "M",
               "comment": "free sleeves",
               "price": 22,
               "offerType": "WANT",
               "signed": false,
               "foiled": false,
               "altered": false
           },
           {
               "id": 2,
               "cardName": "Ugin, the Spirit Dragon",
               "cardId": 2,
               "userName": "user2",
               "userId": 18,
               "quantity": 1,
               "language": "English",
               "cardCondition": "M",
               "comment": "only cash",
               "price": 3,
               "offerType": "WANT",
               "signed": false,
               "foiled": false,
               "altered": false
           },
           {...}
       ]       
      
      
 `/offers/prices/<cardId>`
 
 - gets JSON with card price statistics by `<cardId>`
       
example:

        /offers/prices/2
        {
            "cardId": 2,
            "wantQuantity": 24,
            "sellQuantity": 22,
            "wantFoilQuantity": 4,
            "sellFoilQuantity": 0,
            "minWant": 1,
            "avgWant": 44,
            "minSell": 0.01,
            "avgSell": 5.2,
            "minFoilWant": 33,
            "minFoilSell": 0
        }
        
 `/offers/history/<cardId>`
 
 - gets JSON array with card price history by `<cardId>`,
 - prices are divided on two types:  want and sell,
 - starting date is the earliest offer (want or sell) date. If there is only one type of offer, other gets avg price 0.00.
       
example:

        /offers/prices/2
        {
            "cardId": 70,
            "wants": [
                {
                    "date": "2019.06.09",
                    "avgPrice": 0.00
                },
                {
                    "date": "2019.06.10",
                    "avgPrice": 3.78
                },
                {
                    "date": "2019.06.11",
                    "avgPrice": 3.78
                },
                {
                    "date": "2019.06.12",
                    "avgPrice": 3.78
                }
            ],
            "sells": [
                {
                    "date": "2019.06.09",
                    "avgPrice": 3
                },
                {
                    "date": "2019.06.10",
                    "avgPrice": 3
                },
                {
                    "date": "2019.06.11",
                    "avgPrice": 3
                },
                {
                    "date": "2019.06.12",
                    "avgPrice": 3
                }
            ]
        }
      

##### POST:


`/cards/name/<cardName>`
  
  - posts cards into database by `<cardName>` - cards that were reprinted in many sets,
  - `<cardName>` means full card name - case insensitive,
  - all necessary data for card entity is provided by external API.


`/cards/name/set/<cardName>/<setName>`
  
  - posts card into database by `<cardName>` and `<setName>`
  -`<cardName>` means full card name - case sensitive
  - `<setName>` means full set name that card was printed in - case sensitive
  - all necessary data for card entity is provided by external API
  
  
  `/cards/name/set/<cardName>/<setName>`
  - posts +1 counter for card search counts,
  -`<cardName>` means full card name - case sensitive,
  -`<setName>` means full set name that card was printed in - case sensitive.
    
 `/users`
  - creates new user based on user data in RequestBody
  - to be modified to Spring Security registration process 
 
  `/users/login`
   - mocked logging
   - based on data from UserDto user gets authorization and recieves JSON with token,
   - to be replaced with Spring Security authorization process.

`/user/cards`
- endpoint requires authorization by token (to be replaced with Spring Security authorization)
- creates new Offer for authorized user,
- offer data based on request body,
     
   `/user/message`
   - endpoint requires authorization by token (to be replaced with Spring Security authorization)
   - sends message to other user, and puts message data into database
   - message data based on request body,
     
  
### Used Technologies: 
- Spring
  - Spring Boot
  - Spring MVC
  - Spring Data
- Hibernate
- MySQL
- Lombok
- JUnit + Mockito

### Features to be implemented:
- Spring Security,
- offers list by user,
- endpoints to modify or delete offer,
- batch offer modification,
- batch offer posting,
 


