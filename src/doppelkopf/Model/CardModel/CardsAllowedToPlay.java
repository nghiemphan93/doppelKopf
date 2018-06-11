/**
 * Store the list of allowed to play cards
 *  which were checked in setWhatCardToPlay() in Class Player
 *
 */

package doppelkopf.Model.CardModel;

public class CardsAllowedToPlay extends Cards{
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
}
