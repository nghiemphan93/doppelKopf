# Doppelkopf  Spiel

## INSTRUCTION TO RUN THE PROGRAM
1.  Install MySql and MySql Workbench then create a user with:  
    - user name: "student"  
    - password: "student"
2. Create a local connection with the above user then run the Script from /Database/doppelkopf-Script.sql
3. Open the project in IntelliJ then run MainTest from src/doppelkopf/MainTest


## CONTENT 
- This is a demo game of 4 players playing Doppelkopf
- Implemented functions:
    * all cards needed will be created and dealt to each players
    * a game runs 10 rounds where every player takes turn to play one card at a round
    * the card which the next player is allowed to play will be dependent on the card played by the first player => "bedienen"
    * after each round, the 4 cards played will be sorted by STRENGTH to decide which person has won the round. This player will collect these 4 for himself or herself and start the next round
    * every player has a chance to guess for BAZINGA to take Bonus points at the end of each round
    * right before round 4 and in case there's some one has all 2 KREUZ QUEEN, "Hochzeit" will take place to decide if this player will keep playing alone or have a partner to form a team
    * at the end of the game, the program will sum up all points of each player and each team given the cards collected during the game then decide which players and team won the game
    * all cards played, collected, game played, which player won, which team won... will be saved to the database
    * press "Y" | "y" to play another game or press "N" | "n" to stop

- Todo functions:
    * register for each player
    * each player really takes turn to play, not a demo game
    * divide the program into 3 parts: client, server and database
    * clients will use a real time communication technique with the server in between (considering socket.io but have to convert the code base from Java to Typescript and use node.js as server)
    

## SPECIALITIES
### BAZINGA 
- 1 BAZINGA für jeden Spieler
- BAZINGA bedeutet, dass ein Spieler 2 Farben (welche auf genau 2 Karten aufgeteilt sind) im "Stich" erkennt 
  > &#9827;  Q &#9827; K  &#9829; 10 &#9829; B 
- Wenn ein Spieler mit dem BAZINGA richtig liegt, dann +10 Punkt
- Wenn ein Spieler mit dem BAZINGA falsch liegt, dann -5 Punkt
- BAZINGA darf von jedem Spieler im ganzen Spiel nur einmalig gesagt werden.

### Hochzeit 
- Wenn ein Spieler &#9827;Q &#9827;Q hat
- Ein Spieler spielt dann allein, wenn
  - Kein Spieler innerhalb der ersten 3 Runden mit einem "Fehlstich" gewinnt. 
- Ein Spieler spielt mit dem Spieler, der den "Fehlstich" innerhalb der ersten 3 Runden gewonnen hat. 

### Fehlstich 
- Fehlstich bedeutet, dass mit einer Fehlkarte begonnen wurde


