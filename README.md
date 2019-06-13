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

- gets JSON array of card objects by <partialName>
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
  
  - gets JSON from card objects by <cardName> and <setName>
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
      
      
   ....

##### POST:

  `/cards/name/set/<cardName>/<setName>`
  
  - posts card into database by <cardName> and <setName>
  - <cardName> means full card name - case sensitive
  - <setName> means full set name that card was printed in - case sensitive
  - all necessary data for card entity is provided by external API
  
  ...

### Used Technologies: 
- Spring
  - Spring Boot
  - Spring MVC
  - Spring Data
- Hibernate
- MySQL
- Lombok
- JUnit + Mockito

### Features to be implemented 


