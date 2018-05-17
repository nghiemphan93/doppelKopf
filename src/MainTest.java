import Controller.CardsSetup;
import Controller.GameController;
import Controller.PlayersSetup;
import Model.CardModel.*;
import Model.PlayerModel.Player;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    public static void testDeck(){
        CardsToDeal cardsToDeal = new CardsToDeal(null);
        cardsToDeal.init();
        SortHelper.sortByStrength(cardsToDeal);

        for(Card card : cardsToDeal.getCards()){
            if(card.isFehl()){
                System.out.println(card + " FEHL " + card.getStrength() + " strong " + card.getPoint() + " points");
            }else{
                System.out.println(card + " TRUMPF "+ card.getStrength() + " strong " + card.getPoint() + " points");
            }

        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        String temp = sc.next();
//
//        System.out.println(temp);


//        Player player = gameController.getPlayersSetup().getPlayers().get(0);
//        CardsOnHand cards = player.getCardsOnHand();

        GameController gameController = new GameController();
        gameController.gameInit();
        gameController.startGame();







    }
}
