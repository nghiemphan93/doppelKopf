/**
 *     Help interface for sorting all cards classes
 *
 *     Attribute:
 *
 *     Important Methods:
 *         sortBySuit():    Sort the card list by SUIT
 *         sortByRank():    Sort the card list by RANK
 *         sortByFehl():    Sort the card list by FEHL
 *         sortByTrumpf():  Sort the card list by TRUMPF
 *         sortByStrength():Sort the card list by STRENGTH
 *         sortByPoint():   Sort the card list by POINT
 */

package doppelkopf.Model.CardModel;

public interface SortHelper {
    //region Important methods
    /**
     * Sort the card list by SUIT
     * @param cards
     */
    public static void sortBySuit(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getSuit().compareTo(card2.getSuit()));
    }

    /**
     * Sort the card list by RANK
     * @param cards
     */
    public static void sortByRank(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getRank().compareTo(card2.getRank()));
    }

    /**
     * Sort the card list by FEHL
     * @param cards
     */
    public static void sortByFehl(Cards cards){
        cards.getCards().sort((card2, card1) ->
                String.valueOf(card1.isFehl()).compareTo(String.valueOf(card2.isFehl())
        ));
    }

    /**
     * Sort the card list by TRUMPF
     * @param cards
     */
    public static void sortByTrumpf(Cards cards){
        cards.getCards().sort((card2, card1) ->
                String.valueOf(card1.isTrumpf()).compareTo(String.valueOf(card2.isTrumpf())
                ));
    }

    /**
     * Sort the card list by STRENGTH
     * @param cards
     */
    public static void sortByStrength(Cards cards){
        cards.getCards().sort((card2, card1) -> card1.getStrength().compareTo(card2.getStrength()));
    }

    /**
     * Sort the card list by POINT
     * @param cards
     */
    public static void sortByPoint(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getPoint() - card2.getPoint());
    }
    //endregion
}
