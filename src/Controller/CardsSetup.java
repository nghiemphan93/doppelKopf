package Controller;

import Model.CardModel.CardsOnHand;
import Model.CardModel.CardsPlayedPerRound;
import Model.CardModel.CardsToDeal;
import Model.CardModel.CardsWon;
import Model.PlayerModel.Player;

import java.util.ArrayList;

public class CardsSetup {
    private PlayersSetup playerSetup;
    private CardsToDeal cardsToDeal;
    private ArrayList<CardsWon> cardsWons;
    private ArrayList<CardsOnHand> cardsOnHands;

    public CardsSetup(){
        this.playerSetup = new PlayersSetup();
        this.cardsToDeal = new CardsToDeal(playerSetup.getPlayers());
        this.cardsWons = new ArrayList<>();
        this.cardsOnHands = new ArrayList<>();
    }

    public void initCardSetup(){
        this.playerSetup.initSeeding();
        cardsToDeal.init();
        cardsToDeal.deal();
    }

    public PlayersSetup getPlayerSetup() {
        return playerSetup;
    }

    public CardsToDeal getCardsToDeal() {
        return cardsToDeal;
    }

    public void setCardsToDeal(CardsToDeal cardsToDeal) {
        this.cardsToDeal = cardsToDeal;
    }

    public ArrayList<CardsWon> getCardsWons() {
        return cardsWons;
    }

    public void setCardsWons(ArrayList<CardsWon> cardsWons) {
        this.cardsWons = cardsWons;
    }

    public ArrayList<CardsOnHand> getCardsOnHands() {
        return cardsOnHands;
    }

    public void setCardsOnHands(ArrayList<CardsOnHand> cardsOnHands) {
        this.cardsOnHands = cardsOnHands;
    }

}
