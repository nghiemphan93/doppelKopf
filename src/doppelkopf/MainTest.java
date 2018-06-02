package doppelkopf;

import doppelkopf.Base.Doppelkopf;
import doppelkopf.Controller.GameController;
import doppelkopf.Model.CardModel.*;

import java.util.*;

public class MainTest {
    public static <T> void display(ArrayList<T> list){
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
    }

    public static void testDeck(){
        CardsToDeal cardsToDeal = new CardsToDeal(null);
        cardsToDeal.init();
        SortHelper.sortByStrength(cardsToDeal);

        for(Card card : cardsToDeal.getCards()){
            if(card.isFehl()){
                System.out.println(card + " FEHL " + card.display() + " "+ card.getStrength() + " strong " + card.getPoint() + " points");
            }else{
                System.out.println(card + " TRUMPF " + card.display() + " "+ card.getStrength() + " strong " + card.getPoint() + " points");
            }
        }
    }

    public static void main(String[] args) {
        Doppelkopf game = new GameController();
        game.play();

    }
}
