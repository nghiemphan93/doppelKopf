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
 *      rounds:             list holding information which player won which round
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
 */

package doppelkopf.Controller;

import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.CardModel.CardsPlayedPerRound;
import doppelkopf.Model.CardModel.SortHelper;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private int numbRound = 0;
    private PlayersSetup playersSetup;
    private CardsSetup cardsSetup;
    private Scanner sc;
    private ArrayList<Player> teamKreuzQueen = new ArrayList<>();
    private ArrayList<Player> teamNoKreuzQueen = new ArrayList<>();
    private Player whoHasTwoKreuzQueen;
    private ArrayList<Player> rounds = new ArrayList<>();

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
        this.playersSetup = new PlayersSetup();
        this.cardsSetup = new CardsSetup(this.playersSetup);
    }

    /**
     * Start a game
     */
    public void startGame(){
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

        // display all cards every player has collected
        displayEachPlayersCardsWon();

        // all points of each players
        displayEachPlayerPoints();

        // all points of each team including members
        sumUpPointAnddisplayTwoTeam();


    }

    /**
     * Prepare for new game
     */
    public void resetGame(){
        this.cardsSetup.getCardsToDeal().reset();
        this.numbRound = 0;
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
        }

        // display all player's hand + Cards played per round
        displayAllHands();
        displayCardsPlayedPerRound();

        // display who wins the round
        Player roundWinner = whoWinsTheRound(cardsSetup.getCardsPlayedPerRound());
        ArrayList<Card> cardsPerRound = cardsSetup.getCardsPlayedPerRound().getCards();

        System.out.println(roundWinner + " wins the round with " + cardsPerRound.get(0));

        // add the won cards to the player's CardsWon
        roundWinner.getCardsWon().getCards().addAll(cardsPerRound);


        // clear the CardsPlayedPerRound
        cardsSetup.getCardsPlayedPerRound().clear();
        System.out.println();

        // rearrange the order of players for next round
        // winner of the last round begins the next round

        // remove the winner from the player list
        this.playersSetup.getPlayers().remove(roundWinner);

        // add the winner to the first position in the players list
        this.playersSetup.getPlayers().add(0, roundWinner);

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
    public void sumUpPointAnddisplayTwoTeam(){
        // calculate points of each team
        // Team Kreuz Queen
        int pointTeamKreuzQueen = 0;
        for(int i = 0; i< this.teamKreuzQueen.size(); i++){
            pointTeamKreuzQueen += this.teamKreuzQueen.get(i).getPointsWonPerGame();
        }

        // Team No Kreuz Queen
        int pointTeamNoKreuzQueen = 0;
        for(int i = 0; i< this.teamNoKreuzQueen.size(); i++){
            pointTeamNoKreuzQueen += this.teamNoKreuzQueen.get(i).getPointsWonPerGame();
        }


        // display 2 teams and points of each team
        // Team Kreuz Queen
        switch (this.teamKreuzQueen.size()){
            case 1:
                System.out.println("Team Kreuz Queen: " + this.teamKreuzQueen.get(0) + " with " + pointTeamKreuzQueen + " points");
                break;
            case 2:
                System.out.println("Team Kreuz Queen: " + this.teamKreuzQueen.get(0) + " and " + this.teamKreuzQueen.get(1) + " with " + pointTeamKreuzQueen + " points");
                break;
        }

        // Team No Kreuz Queen
        switch (this.teamNoKreuzQueen.size()){
            case 1:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + " with " + pointTeamKreuzQueen + " points");
                break;
            case 2:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + " and " + this.teamNoKreuzQueen.get(1) + " with " + pointTeamNoKreuzQueen + " points");
                break;
            case 3:
                System.out.println("Team No Kreuz Queen: " + this.teamNoKreuzQueen.get(0) + ", " + this.teamNoKreuzQueen.get(1) + " and " + this.teamNoKreuzQueen.get(2)+ " with " + pointTeamNoKreuzQueen + " points");
                break;
        }


    }   // end of sumUpPointAnddisplayTwoTeam
}
