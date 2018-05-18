/*
    Setup all kinds of cards needed for a game (Deck, Hand, Cards won...)

    Atribute:
            playerSetup:    holding setup infor of 4 players
            cardsToDeal:    create deck init
            cardsWons:      4 card lists storing STICH for each player
            cardsOnHands:   list of 4 card Hands for each players

    Important methods:
            initCardSetup()     initialize all types of cards and deal to players

 */

package doppelkopf.Controller;

import doppelkopf.Model.CardModel.*;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;

public class CardsSetup {
    private PlayersSetup playerSetup;
    private CardsToDeal cardsToDeal;
    private ArrayList<CardsWon> cardsWons;
    private ArrayList<CardsOnHand> cardsOnHands;
    private CardsPlayedPerRound cardsPlayedPerRound;

    public CardsSetup(PlayersSetup playersSetup){
        this.playerSetup = playersSetup;
        this.cardsToDeal = new CardsToDeal(playerSetup.getPlayers());
        this.cardsWons = new ArrayList<>();
        this.cardsOnHands = new ArrayList<>();
        this.cardsPlayedPerRound = new CardsPlayedPerRound();
    }

    public void initCardSetup(){
        this.playerSetup.initSeeding();
        cardsToDeal.init();
        cardsToDeal.deal();

        // init sorted by strength
        for(Player player : this.playerSetup.getPlayers()){
            SortHelper.sortByStrength(player.getCardsOnHand());
        }
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

    public void setPlayerSetup(PlayersSetup playerSetup) {
        this.playerSetup = playerSetup;
    }

    public CardsPlayedPerRound getCardsPlayedPerRound() {
        return cardsPlayedPerRound;
    }

    public void setCardsPlayedPerRound(CardsPlayedPerRound cardsPlayedPerRound) {
        this.cardsPlayedPerRound = cardsPlayedPerRound;
    }
}
