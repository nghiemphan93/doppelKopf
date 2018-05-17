package Controller;

import Model.CardModel.Card;
import Model.CardModel.CardsPlayedPerRound;
import Model.PlayerModel.Player;

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

    public void gameInit(){
        this.playersSetup = new PlayersSetup();
        this.cardsSetup = new CardsSetup(this.playersSetup);
    }

    public void startGame(){
        this.cardsSetup.initCardSetup();
        for(int i = 0; i<10; i++){
            startRoundSeeding();
        }



//        for(Card card : playersSetup.getPlayers().get(0).getCardsOnHand().getCards()){
//            System.out.println(card.getBelongsToPlayer());
//        }
    }

    public void resetGame(){
        this.cardsSetup.getCardsToDeal().reset();
        this.numbRound = 0;
    }

    public void endGame(){

    }

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

        displayCardsOnHand();
        System.out.println();

    }

    public void startRoundSeeding(){
        // each player takes turn to play
        for(int i = 0; i<4; i++){
            Player player = playersSetup.getPlayers().get(i);

            // display the cards of the player
            System.out.println(player + ": " + player.getCardsOnHand());

            // show what card has been played
            Card card = player.playARandomCard();
            System.out.println(player + " play " + card);
            System.out.println(player + " still has " + player.getCardsOnHand().getCards().size());
            System.out.println();

            // add the card to CardsPlayedPerRound
            cardsSetup.getCardsPlayedPerRound().add(card);
        }

        displayCardsOnHand();
        System.out.println(cardsSetup.getCardsPlayedPerRound());
        System.out.println();

    }

    public void endRound(){

    }

    public Player whoWinsTheRound(CardsPlayedPerRound cardsPlayedPerRound){

        return new Player("","");
    }

    public ArrayList<Card> whatCardsToPlay(){


        return new ArrayList<>();
    }

    public void displayCardsOnHand(){
        for(Player player : this.playersSetup.getPlayers()){
            System.out.println(player + ": " + player.getCardsOnHand());
        }
    }


}
