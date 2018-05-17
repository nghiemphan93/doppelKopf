/*
    Help interface for sorting

    Attribute:

    Important Methods:
        sortBySuit()
        sortByRank()
        sortByFehl()
        sortByTrumpf()
        sortByStrength()
        sortByPoint()
*/

package Model.CardModel;

import java.util.ArrayList;

public interface SortHelper {
    public static void sortBySuit(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getSuit().compareTo(card2.getSuit()));
    }

    public static void sortByRank(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getRank().compareTo(card2.getRank()));
    }

    public static void sortByFehl(Cards cards){
        cards.getCards().sort((card2, card1) ->
                String.valueOf(card1.isFehl()).compareTo(String.valueOf(card2.isFehl())
        ));
    }

    public static void sortByTrumpf(Cards cards){
        cards.getCards().sort((card2, card1) ->
                String.valueOf(card1.isTrumpf()).compareTo(String.valueOf(card2.isTrumpf())
                ));
    }

    public static void sortByStrength(Cards cards){
        cards.getCards().sort((card2, card1) -> card1.getStrength().compareTo(card2.getStrength()));
    }

    public static void sortByPoint(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getPoint() - card2.getPoint());
    }
}
