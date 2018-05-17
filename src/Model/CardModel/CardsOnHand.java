/*
    Hand of each player

    Attribute:
        fehl:       Collection holding all FEHL
        strumpf:    Collection holding all STRUMPF

    Important Methods:
        playCard():     take out a card from the Hand

*/

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

    // remove a card from the Hand
    public Card playCard(Card card){
        this.getCards().remove(card);   //remove from the main collection
        this.fehl.remove(card);         //remove from the Fehl collection
        this.fehl.remove(card);         //remove from the Strumpf collection

        return card;
    }

    public int playCard(int index){
        this.getCards().remove(index);   //remove from the main collection
        this.fehl.remove(index);         //remove from the Fehl collection
        this.fehl.remove(index);         //remove from the Strumpf collection

        return index;
    }

    @Override
    public String toString() {
        String result = "";

        for(int i = 0; i<this.getCards().size(); i++){
            Card card = this.getCards().get(i);
            result += String.format("%d", i) + card + " ";
        }


        return result;
    }

}
