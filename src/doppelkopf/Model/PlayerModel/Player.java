/**
 * Class for each player who takes part in the game
 *
 * Attribute:
 *      name:                name
 *      password:            password
 *      cardsOnHand:         cards on hand
 *      cardsWon:            cards were collected
 *      cardsPlayedPerRound: cards played on table each round
 *      CardsAllowedToPlay  cards allowed to play(bedienen)
 *      pointsWonPerGame    all points of each game
 *
 *  Important methods:
 *      playACard():            Play a chosen card
 *      playARandomCard():      Play a random card, used for DEMO
 *      setWhatCardToPlay():    Check and determine what cards on Hand are allowed to play, depending on the first card was played
 *      calcPointsWonPerGame(): Calculation of points won per game
 *
 */

package doppelkopf.Model.PlayerModel;

import doppelkopf.Model.CardModel.*;
import doppelkopf.Model.ObserverModel.Observable;
import doppelkopf.Model.ObserverModel.Observer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements Observer {
    private String name;
    private String password;
    private CardsOnHand cardsOnHand;
    private CardsWon cardsWon;
    private Observable cardsPlayedPerRound;
    private CardsAllowedToPlay cardsAllowedToPlay;
    private int pointsWonPerGame = 0;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.cardsOnHand = new CardsOnHand();
        this.cardsWon = new CardsWon();
        this.cardsPlayedPerRound = new CardsPlayedPerRound();
        this.cardsAllowedToPlay = new CardsAllowedToPlay();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CardsOnHand getCardsOnHand() {
        return cardsOnHand;
    }

    public void setCardsOnHand(CardsOnHand cardsOnHand) {
        this.cardsOnHand = cardsOnHand;
    }

    public CardsWon getCardsWon() {
        return cardsWon;
    }

    public void setCardsWon(CardsWon cardsWon) {
        this.cardsWon = cardsWon;
    }

    public Observable getCardsPlayedPerRound() {
        return cardsPlayedPerRound;
    }

    public void setCardsPlayedPerRound(Observable cardsPlayedPerRound) {
        this.cardsPlayedPerRound = cardsPlayedPerRound;
    }

    public CardsAllowedToPlay getCardsAllowedToPlay() {
        return cardsAllowedToPlay;
    }

    public void setCardsAllowedToPlay(CardsAllowedToPlay cardsAllowedToPlay) {
        this.cardsAllowedToPlay = cardsAllowedToPlay;
    }

    public int getPointsWonPerGame() {
        return pointsWonPerGame;
    }

    public void setPointsWonPerGame(int pointsWonPerGame) {
        this.pointsWonPerGame = pointsWonPerGame;
    }

    @Override
    public String toString() {
        return this.name.toUpperCase();
    }

    @Override
    public void update() {

    }

    /**
     * Play a chosen card
     * @param index
     * @return
     */
    public Card playACard(int index){
        Card card = this.cardsOnHand.remove(index);
        return card;
    }

    /**
     * Play a random card, used for DEMO
     * @return
     */
    public Card playARandomCard(){
        Card card = null;

        // if there's still at least 1 Card in CardsAllowedToPlay
        if(this.cardsAllowedToPlay.getNumCards() >= 0){
             card = this.cardsAllowedToPlay.remove(ThreadLocalRandom.current().nextInt(0, this.cardsAllowedToPlay.getNumCards()));
             this.cardsOnHand.remove(card);
        }

        return card;
    }

    /**
     * Check and determine what cards on Hand are allowed to play
     * depending on the first card was played
     * @param firstCardPlayed
     */
    public void setWhatCardToPlay(Card firstCardPlayed){
        ArrayList<Card> cardsToPlayReturn = new ArrayList<>();

        // check if the first Card in CardsPlayedPerRound Fehl or Trumpf
        if(firstCardPlayed.isFehl()){
            // The first card was played is FEHL

            // check if the player's Hand had the same FEHL color => bedienen
            // if not, then play whatever
            boolean hasSameFehl = false;
            for(Card card : this.getCardsOnHand().getCards()){
                // if it's the same FEHL color, then add to CardsToPlay
                // set hasSameFehl = true
                // FEHL's STRENGTH has the same first Letter: 1 or 2 or 3
                if(card.getStrength().charAt(0) == firstCardPlayed.getStrength().charAt(0)){
                    cardsToPlayReturn.add(card);
                    hasSameFehl = true;
                }
            }

            // if the Hand has the same FEHL color
            // then return CardsToPlay containing all same FEHL color cards
            if (hasSameFehl == true){
                this.cardsAllowedToPlay.clear();
                this.cardsAllowedToPlay.addAll(cardsToPlayReturn);
            }else{
                // if Hand doesn't contain any same FEHL color
                // return everthing from the Hand
                this.cardsAllowedToPlay.clear();
                this.cardsAllowedToPlay.addAll(this.cardsOnHand.getCards());
            }

        }else{
            // The first card was played is TRUMPF

            // if Hand contains any TRUMPF
            // then return every TRUMPF
            // otherwise return everything from Hand
            boolean hasTrumpf = false;
            for(Card card : this.getCardsOnHand().getCards()){
                // TRUMPF's STRENGTH begins with 4 or 5 or 6 or 7
                if(card.getStrength().charAt(0) >= '4'){
                    cardsToPlayReturn.add(card);
                    hasTrumpf = true;
                }
            }

            if(hasTrumpf == true){
                // return all TRUMPF
                this.cardsAllowedToPlay.clear();
                this.cardsAllowedToPlay.addAll(cardsToPlayReturn);
            }else{
                // return everything from HAND
                this.cardsAllowedToPlay.clear();
                this.cardsAllowedToPlay.addAll(this.cardsOnHand.getCards());
            }

        }
    }   // end of checkWhatCardToPlay()

    /**
     * Calculation of points won per game
     * @return
     */
    public int calcPointsWonPerGame(){
        int sum = 0;
        for(Card card : this.cardsWon.getCards()){
            sum += card.getPoint();
        }
        setPointsWonPerGame(sum);
        return sum;
    }


}
