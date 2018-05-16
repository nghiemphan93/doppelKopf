package Model.PlayerModel;

import Model.CardModel.CardsOnHand;
import Model.CardModel.CardsPlayedPerRound;
import Model.CardModel.CardsWon;
import Model.ObserverModel.Observable;
import Model.ObserverModel.Observer;

public class Player implements Observer {
    private String name;
    private String password;
    private CardsOnHand cardsOnHand;
    private CardsWon cardsWon;
    private Observable cardsPlayedPerRound;

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
        this.cardsOnHand = new CardsOnHand();
        this.cardsWon = new CardsWon();
        this.cardsPlayedPerRound = new CardsPlayedPerRound();
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
}
