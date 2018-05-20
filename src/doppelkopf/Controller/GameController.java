/**
 * Game controller manages the game
 * Can create players, all cards needed
 * and coordinates the rounds
 *
 * Attributes:
 *      numbRound:          the current round
 *      playerSetup:        holding setup references to all players
 *      cardsSetup:         holding setup references to all kinds of cards
 *      sc:                 take input from user
 *      teamKreuzQueen:     list holding all team Kreuz Queen members
 *      teamNoKreuzQueen:   list holding all team No Kreuz Queen members
 *      whoHasTwoKreuzQueen: holding 1 Player who has all 2 Kreuz Queen, if noone => null
 *      rounds:             list holding which player won which round and what kind the first card was played (FEHL >< TRUMPF)
 *      pointTeamKreuzQueen:    holding points of Team Kreuz Queen
 *      pointTeamNoKreuzQueen:  holding points of Team No Kreuz Queen
 *
 *
 * Important methods:
 *      gameInit()              Setup players and cards
 *      startGame()             Start a game
 *      resetGame()             Prepare for new game
 *      endGame()               End a game
 *      startRound()            Start a round
 *      startRoundSeeding()     Start a auto seeding round, used for DEMO purpose
 *      whoWinsTheRound()       Determines who wins the round, given the CardsPlayedPerRound sorted
 *      displayAllHand()        display all cards of all hands
 *      disCarPlaPerRound()     display all cards played in the current round
 *      displayEachPlayersCardsWon()    display all cards each player won so far
 *      displayEachPlayerPoints()       display all points of each player
 *      sumUpAndDisplayTeam     calculate points of each team then display them accordingly
 *      checkHochzeit()         In case there's one player (A) has all 2 Kreuz Queen
 *                              Check if there's someone else (B) won any of the first 3 rounds with a FEHL round
 *                              return B as result
 *                              These two will play together as partners at Team Kreuz Queen
 *
 *                              Otherwise, player A will play alone vs other 3
 *                              return NULL
 *       displayTwoTeamResults()    display final results which team wins and points
 */

package doppelkopf.Controller;

import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.CardModel.CardsPlayedPerRound;
import doppelkopf.Model.CardModel.SortHelper;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private int numbRound;
    private PlayersSetup playersSetup;
    private CardsSetup cardsSetup;
    private Scanner sc;
    private ArrayList<Player> teamKreuzQueen;
    private ArrayList<Player> teamNoKreuzQueen;
    private Player whoHasTwoKreuzQueen;
    private ArrayList rounds;
    private int pointTeamKreuzQueen;
    private int pointTeamNoKreuzQueen;

    public GameController() {
        this.sc = new Scanner(System.in);
    }

    public int getNumbRound() {
        return numbRound;
    }

    public void setNumbRound(int numbRound) {
        this.numbRound = numbRound;
    }

    public PlayersSetup getPlayersSetup() {
        return playersSetup;
    }

    public void setPlayersSetup(PlayersSetup playersSetup) {
        this.playersSetup = playersSetup;
    }

    public CardsSetup getCardsSetup() {
        return cardsSetup;
    }

    public void setCardsSetup(CardsSetup cardsSetup) {
        this.cardsSetup = cardsSetup;
    }

    /**
     * Setup players and cards
     */
    public void gameInit(){
        this.numbRound = 0;
        this.playersSetup = new PlayersSetup();
        this.cardsSetup = new CardsSetup(this.playersSetup);
        this.teamKreuzQueen = new ArrayList<>();
        this.teamNoKreuzQueen = new ArrayList<>();
        this.whoHasTwoKreuzQueen = whoHasTwoKreuzQueen();
        this.rounds = new ArrayList<>();
        this.pointTeamKreuzQueen = 0;
        this.pointTeamNoKreuzQueen = 0;
    }

    /**
     * Start a game
     */
    public void startGame(){
        boolean anotherGame = true;


        while(anotherGame){
            // game start
            System.out.println("==================================================================");
            System.out.println("GAME STARTED ");
            System.out.println("==================================================================");


            // create all cards needed then deal to players
            this.cardsSetup.initCardSetup();

            // set who has Kreuz Queen, who not
            this.cardsSetup.checkPlayerHasKreuzQueen();

            // separate two teams
            // set the one who has all 2 Kreuz Queen in case there's actually someone
            this.whoHasTwoKreuzQueen = whoHasTwoKreuzQueen();

            // play 10 rounds
            for(int i = 0; i<10; i++){
                // set the current round
                this.numbRound = i+1;

                // until round 4, check Hochzeit for the first three rounds
                if(this.numbRound == 4 && this.whoHasTwoKreuzQueen != null){
                    Player dreamPartner = checkHochzeit();
                    if(dreamPartner != null){
                        // display to screen new partner
                        System.out.println("--------HOCHZEIT--------");
                        System.out.println(dreamPartner + " plays with " + this.whoHasTwoKreuzQueen);
                    }else{
                        // display to screen who plays alone
                        System.out.println("--------HOCHZEIT--------");
                        System.out.println(this.whoHasTwoKreuzQueen + " plays alone");
                    }
                }


                System.out.println("==================================================================");
                System.out.println("ROUND " + this.numbRound);
                System.out.println("==================================================================");

                // simulate a round
                startRoundSeeding();
            }

            // game ended
            System.out.println("==================================================================");
            System.out.println("GAME ENDED ");
            System.out.println("==================================================================");

            // debug
            System.out.println("Players won the round next to: " + rounds);

            // display all cards every player has collected
            displayEachPlayersCardsWon();

            // all points of each players
            displayEachPlayerPoints();

            // all points of each team including members
            sumUpPointTwoTeams();

            // which team wins
            displayTwoTeamResults();

            // type "y" to play another game
            // "n" to stop
            System.out.println("Please enter \"y\" or \"Y\" to play another game: ");
            System.out.print("Please enter \"n\" or \"N\" to stop: ");

            while(true){
                String command = sc.next();
                if(command.toUpperCase().compareTo("Y") == 0){
                    anotherGame = true;
                    resetGame();
                    break;
                }else{
                    if(command.toUpperCase().compareTo("N") == 0){
                        anotherGame = false;
                        break;
                    }else{
                        System.out.print("Please enter the command again: ");
                    }
                }
            }



        }   // end of while
    }

    /**
     * Prepare for new game
     */
    public void resetGame(){
        gameInit();
    }

    /**
     * End a game
     */
    public void endGame(){

    }

    /**
     * Start a round
     */
    public void startRound(){
        // each player takes turn to play
        for(int i = 0; i<4; i++){
            Player player = playersSetup.getPlayers().get(i);


            // display the cards of the player
            System.out.println(player + ": " + player.getCardsOnHand());
            System.out.print("Card to play: ");

            // take the card index wanted to play from player
            int cardIndexWannaPlay = Integer.parseInt((this.sc.next()));

            // remove the card from hand
            Card card = player.playACard(cardIndexWannaPlay);

            // show what card has been played
            System.out.println(player + " play " + card);
            System.out.println(player + " still has " + player.getCardsOnHand().getCards().size());
            System.out.println();

            // add the card to CardsPlayedPerRound
            cardsSetup.getCardsPlayedPerRound().add(card);
        }

        displayAllHands();
        System.out.println();

    }

    /**
     * Start a auto seeding round, used for DEMO purpose
     */
    public void startRoundSeeding(){
        // each player takes turn to play
        for(int i = 0; i<4; i++){
            Player player = playersSetup.getPlayers().get(i);

            // if 1. Player => CardsAllowedToPlay = CardsOnHand
            if(i == 0){
                player.getCardsAllowedToPlay().clear();
                player.getCardsAllowedToPlay().addAll(player.getCardsOnHand().getCards());
            }else{
                player.setWhatCardToPlay(cardsSetup.getCardsPlayedPerRound().getCards().get(0));
            }

            // display the CardsOnHand of the player
            System.out.println(player);
            System.out.println("Cards on hand: " + player.getCardsOnHand());

            // display the CardsAllowedToPlay of the player
            System.out.println("Cards allowed to play: " + player.getCardsAllowedToPlay());

            // show what card has been played
            Card card = player.playARandomCard();
            System.out.println(player + " played: " + card);
            System.out.println(player + " still has: " + player.getCardsOnHand().getCards().size());
            System.out.println();

            // add the card to CardsPlayedPerRound
            cardsSetup.getCardsPlayedPerRound().add(card);
        }   // end of for

        // display all player's hand + Cards played per round
        displayAllHands();
        displayCardsPlayedPerRound();

        // display who wins the round
        Player roundWinner = whoWinsTheRound(cardsSetup.getCardsPlayedPerRound());
        ArrayList<Card> cardsPerRound = cardsSetup.getCardsPlayedPerRound().getCards();

        System.out.println(roundWinner + " wins the round with " + cardsPerRound.get(0));

        // add the winner to the list rounds
        this.rounds.add(roundWinner);
        // add the first card played in round next to the winner
        // to determine if the player won a FEHL Stich or Trumpf Stich
        this.rounds.add(this.cardsSetup.getCardsPlayedPerRound().getCards().get(0));

        // add the won cards to the player's CardsWon
        roundWinner.getCardsWon().getCards().addAll(cardsPerRound);


        // clear the CardsPlayedPerRound
        cardsSetup.getCardsPlayedPerRound().clear();
        System.out.println();

        // rearrange the order of players for next round
        // winner of the last round begins the next round
        for(int i = 0; i < this.playersSetup.getPlayers().size(); i++){
            Player player = this.playersSetup.getPlayers().get(0);

            if(player.toString().compareTo(roundWinner.toString()) == 0){
                // if the first position is the round winner, stop
                break;
            }else{
                // add the first player to the end of the players list
                this.playersSetup.getPlayers().add(player);

                // remove the first player from the player list
                this.playersSetup.getPlayers().remove(0);

            }
        }
    }

    public void endRound(){

    }

    /**
     * Determines who wins the round, given the CardsPlayedPerRound sorted
     * @param cardsPlayedPerRound
     * @return
     */
    public Player whoWinsTheRound(CardsPlayedPerRound cardsPlayedPerRound){

        return cardsPlayedPerRound.getCards().get(0).getBelongsToPlayer();
    }

    /**
     * Display hands of every player
     */
    public void displayAllHands(){
        for(Player player : this.playersSetup.getPlayers()){
            System.out.println(player + "'s hand: " + player.getCardsOnHand());
        }
    }

    /**
     * Display cards played on table per round
     */
    public void displayCardsPlayedPerRound(){
        // Sort by strength
        SortHelper.sortByStrength(cardsSetup.getCardsPlayedPerRound());

        System.out.print("Cards played in round: ");
        for(Card card : cardsSetup.getCardsPlayedPerRound().getCards()){
            System.out.print(card + ":" + card.getBelongsToPlayer() + " ");
        }
        System.out.println();
    }

    /**
     * Display each players cards won
     */
    public void displayEachPlayersCardsWon(){
        for(Player player : this.playersSetup.getPlayers()){
            System.out.print(player + " collected: ");

            for(Card card : player.getCardsWon().getCards()){
                System.out.print(card + " ");
            }

            System.out.println();

        }
        System.out.println();
    }

    /**
     * Display each player Points
     */
    public void displayEachPlayerPoints(){
        for(Player player : this.playersSetup.getPlayers()){
            System.out.println(player + " achieved: " + player.calcPointsWonPerGame() + " points");
        }
        System.out.println();
    }

    /**
     * Determine who has Kreuz Queen and who not
     * Return the player who has all 2 Kreuz Queen
     * if no one, return NULL
     * @return
     */
    public Player whoHasTwoKreuzQueen(){
        // walk through every player
        for(Player player : this.playersSetup.getPlayers()){
            if(player.hasKreuzQueen()){
                // if the player has KREUZ QUEEN
                this.teamKreuzQueen.add(player);
            }else{
                // if no KREUZ QUEEN
                this.teamNoKreuzQueen.add(player);
            }
        }   // end of for

        if(this.teamKreuzQueen.size() == 1){
            // if there's someone who has all 2 Kreuz Queen, return him
            return this.teamKreuzQueen.get(0);
        }else{
            // if not, return NULL
            return null;
        }
    }

    /**
     * Calculate points of each team then display them accordingly
     */
    public void sumUpPointTwoTeams(){
        // calculate points of each team
        // Team Kreuz Queen
        this.pointTeamKreuzQueen = 0;
        for(int i = 0; i< this.teamKreuzQueen.size(); i++){
            this.pointTeamKreuzQueen += this.teamKreuzQueen.get(i).getPointsWonPerGame();
        }

        // Team No Kreuz Queen
        this.pointTeamNoKreuzQueen = 0;
        for(int i = 0; i< this.teamNoKreuzQueen.size(); i++){
            this.pointTeamNoKreuzQueen += this.teamNoKreuzQueen.get(i).getPointsWonPerGame();
        }

    }   // end of sumUpPointTwoTeams

    /**
     * Display final results which team wins and points
     */
    public void displayTwoTeamResults(){
        // display 2 teams and points of each team
        // Team Kreuz Queen
        switch (this.teamKreuzQueen.size()){
            case 1:
                System.out.println("Team Kreuz Queen: " + this.teamKreuzQueen.get(0) + " with " + this.pointTeamKreuzQueen + " points");
                break;
            case 2:
                System.out.println("Team Kreuz Queen: " + this.teamKreuzQueen.get(0) + " and " + this.teamKreuzQueen.get(1) + " with " + this.pointTeamKreuzQueen + " points");
                break;
        }

        // Team No Kreuz Queen
        switch (this.teamNoKreuzQueen.size()){
            case 1:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + " with " + this.pointTeamKreuzQueen + " points");
                break;
            case 2:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + " and " + this.teamNoKreuzQueen.get(1) + " with " + this.pointTeamNoKreuzQueen + " points");
                break;
            case 3:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + ", " + this.teamNoKreuzQueen.get(1) + " and " + this.teamNoKreuzQueen.get(2)+ " with " + this.pointTeamNoKreuzQueen + " points");
                break;
        }

        // which team wins
        System.out.println();
        if(whichTeamWon() > 0){
            System.out.println("=====> Team Kreuz Queen wins with " + this.pointTeamKreuzQueen + " points");
        }else{
            if(whichTeamWon() < 0){
                System.out.println("=====> Team No Kreuz Queen wins with " + this.pointTeamNoKreuzQueen + " points");
            }else{
                System.out.println("=====> Draw -- No team wins");
            }
        }

    }

    /**
     * In case there's one player (A) has all 2 Kreuz Queen
     * Check if there's someone else (B) won any of the first 3 rounds with a FEHL round
     * return B as result
     * These two will play together as partners at Team Kreuz Queen
     *
     * Otherwise, player A will play alone vs other 3
     * return NULL
     * @return
     */
    public Player checkHochzeit(){
        for(int i = 0; i < 5; i+= 2){
            // get each player
            // check if it's not the player who's got all 2 Kreuz Queen
            Player dreamPartner = (Player)this.rounds.get(i);
            if(dreamPartner.toString().compareTo(this.whoHasTwoKreuzQueen.toString()) != 0){
                // check if the round won by him was FEHL
                Card roundWonByDreamPartner = (Card)this.rounds.get(i+1);
                if (roundWonByDreamPartner.isFehl()){
                    // add dreamPartner to Team Kreuz Queen
                    this.teamKreuzQueen.add(dreamPartner);

                    // remove him from Team No Kreuz Queen
                    this.teamNoKreuzQueen.remove(dreamPartner);

                    return dreamPartner;
                }
            }
        }   // end of for
        return null;
    }

    /**
     * Decides which team won
     * If
     *  return >0 => Team Kreuz Queen won
     *  return <0 => Team No Kreuz Queen won
     *  return 0 =>  Draw, no team wins
     * @return
     */
    public int whichTeamWon(){
        return this.pointTeamKreuzQueen - this.pointTeamNoKreuzQueen;
    }
}
