package Model.CardModel;

import java.util.ArrayList;

public class CardsOnHand extends Cards {
    private ArrayList<Card> fehl = filterFehl();
    private ArrayList<Card> trumpf = filterTrumpf();

    public ArrayList<Card> getFehl() {
        return fehl;
    }

    public ArrayList<Card> getTrumpf() {
        return trumpf;
    }

    public Card playCard(Card card){
        this.getCards().remove(card);
        return card;
    }

}
