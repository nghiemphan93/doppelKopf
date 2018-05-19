/**
 * Game controller manages the game
 * Can create players, all cards needed
 * and coordinates the rounds
 *
 * Attributes:
 *      numbRound:      number of round
 *      playerSetup:    holding setup references to all players
 *      cardsSetup:     holding setup references to all kinds of cards
 *      sc:             take input from user
 *
 * Important methods:
 *      gameInit()          Setup players and cards
 *      startGame()         Start a game
 *      resetGame()         Prepare for new game
 *      endGame()           End a game
 *      startRound()        Start a round
 *      startRoundSeeding() Start a auto seeding round, used for DEMO purpose
 *      whoWinsTheRound()   Determines who wins the round, given the CardsPlayedPerRound sorted
 *      displayAllHand()
 *      disCarPlaPerRound()
 *      displayEachPlayersCardsWon()
 *      displayEachPlayerPoints()
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
        this.cardsSetup.initCardSetup();
        for(int i = 0; i<10; i++){
            System.out.println("==================================================================");
            System.out.println("ROUND " + i+1);
            System.out.println("==================================================================");
            startRoundSeeding();
        }

        displayEachPlayersCardsWon();
        displayEachPlayerPoints();


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
            System.out.println(player + ": " + player.getCardsOnHand());
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
}
