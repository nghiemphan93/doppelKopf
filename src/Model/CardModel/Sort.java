package Model.CardModel;

import java.util.ArrayList;

public interface Sort {
    public static void sortBySuit(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getSuit().compareTo(card2.getSuit()));
    }

    public static void sortByRank(Cards cards){
        cards.getCards().sort((card1, card2) -> card1.getRank().compareTo(card2.getRank()));
    }

    public static void sortByStrength(Cards cards){

    }

    public static void sortByPoint(Cards cards){

    }
}
