/**
 *     Hand of each player
 *
 *     Attribute:
 *         fehl:       Collection holding all FEHL
 *         strumpf:    Collection holding all STRUMPF
 *
 *     Important Methods:
 */


package doppelkopf.Model.CardModel;
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



    @Override
    public String toString() {
        String result = "";

        for(int i = 0; i<this.getCards().size(); i++){
            Card card = this.getCards().get(i);
            result += String.format("%d_", i) + card + " ";
        }
        return result;
    }

}
