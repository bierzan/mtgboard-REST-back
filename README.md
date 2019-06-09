# MtG.Board
*Application in development stage*

An attempt to design **REST Service** that supports trading with **Magic the Gathering** cards.
Each User may have two kinds of card offer lists:
 - wanted list - cards that he/she wants to acquire,
 - sell list - cards that he/she wants to sell.
 
Application will allow users to search offers by card and present them in easy to compare manner.
### Implemented Features / REST API

##### GET:

`/cards/name/like/<partialName>`

- gets JSON table of card objects by <partialName>
- <partialName> means that card name contains requested substring (not only begins with)
- card object has 

example:
    
    
    /cards/name/like/<partialName>
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

    

### Used Technologies: 
- Spring
  - Spring Boot
  - Spring MVC
  - Spring Data
- Hibernate
- MySQL
- Lombok
- JUnit + Mockito

### REST API

### Features to be implemented 



