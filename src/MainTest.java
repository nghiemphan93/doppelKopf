import Controller.CardsSetup;
import Controller.PlayersSetup;
import Model.CardModel.*;
import Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class MainTest {
//    public static ArrayList<String> init(){
//        Suit suits[] = Suit.values();
//        Rank ranks[] = Rank.values();
//        ArrayList<String> cards = new ArrayList<>();
//
//        for(Suit suit : suits){
//            for(Rank rank : ranks){
//                cards.add(suit + " " + rank);
//                cards.add(suit + " " + rank);
//            }
//        }
//
//    return cards;
//    }

    public static <T> void display(ArrayList<T> list){
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
    }



//    public static ArrayList<String> filterFehl(ArrayList<String> list){
//        ArrayList<String> resultList = new ArrayList<>();
//
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            String temp = iterator.next();
//            switch (temp){
//                case "PIK ASS":
//                case "PIK KOENIG":
//                case "PIK ZEHN":
//                case "KREUZ ASS":
//                case "KREUZ KOENIG":
//                case "KREUZ ZEHN":
//                case "HERZ ASS":
//                case "HERZ KOENIG": resultList.add(temp);
//            }
//        }
//
//        return resultList;
//    }

//    public static ArrayList<String> filterTrumpf(ArrayList<String> list){
//        ArrayList<String> resultList = list;
//        ArrayList<String> fehl = filterFehl(list);
//
//        resultList.removeAll(fehl);
//
//        return resultList;
//    }

    public static void main(String[] args) {
//        CardsToDeal cards = new CardsToDeal();
//        cards.init();
//        cards.shuffle();
//
//        ArrayList<Card> fehl = cards.filterFehl();
//        ArrayList<Card> trumpf = cards.filterTrumpf();
//
//        System.out.println(fehl.size());
//        System.out.println(trumpf.size());
//        System.out.println(cards.getNumCards());
//
//        Sort.sortBySuit(cards);
//        Sort.sortByRank(cards);
//        cards.display();

        CardsSetup cardsSetup = new CardsSetup();

        cardsSetup.initCardSetup();

        for(Player player: cardsSetup.getPlayerSetup().getPlayers()){
            System.out.println(player);
            Sort.sortBySuit(player.getCardsOnHand());
            player.getCardsOnHand().display();
            System.out.println(player.getCardsOnHand().getNumCards() + " cards");
            System.out.println();
        }












    }
}
