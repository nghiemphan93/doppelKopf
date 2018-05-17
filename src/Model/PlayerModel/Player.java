package Model.PlayerModel;

import Model.CardModel.*;
import Model.ObserverModel.Observable;
import Model.ObserverModel.Observer;

import java.util.concurrent.ThreadLocalRandom;

public class Player implements Observer {
    private String name;
    private String password;
    private CardsOnHand cardsOnHand;
    private CardsWon cardsWon;
    private Observable cardsPlayedPerRound;
    private CardsAllowedToPlay cardsAllowedToPlay;

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

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update() {

    }

    public Card playACard(int index){
        Card card = this.cardsOnHand.remove(index);
        return card;
    }

    public Card playARandomCard(){
        Card card = null;
        if(this.cardsOnHand.getNumCards() >= 0){
             card = this.cardsOnHand.remove(ThreadLocalRandom.current().nextInt(0, this.cardsOnHand.getNumCards()));
        }

        return card;
    }
}
