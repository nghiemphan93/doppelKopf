/**
 *     Hand of each player
 *
 *     Attribute:
 *         fehl:       List holding all FEHL
 *         strumpf:    List holding all STRUMPF
 *
 *     Important Methods:
 */


package doppelkopf.Model.CardModel;
import java.util.ArrayList;

public class CardsOnHand extends Cards {
    //region Attributes
    private ArrayList<Card> fehl = filterFehl();
    private ArrayList<Card> trumpf = filterTrumpf();
    //endregion

    //region Methods
    @Override
    public String toString() {
        String result = "";

        for(int i = 0; i<this.getCards().size(); i++){
            Card card = this.getCards().get(i);
            result += String.format("%d_", i) + card + " ";
        }
        return result;
    }
    //endregion

    //region Getter Setter
    public ArrayList<Card> getFehl() {
        return fehl;
    }

    public ArrayList<Card> getTrumpf() {
        return trumpf;
    }
    //endregion

}
