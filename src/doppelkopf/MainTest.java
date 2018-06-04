package doppelkopf;

import doppelkopf.Controller.CRUD;
import doppelkopf.Controller.GameController;
import doppelkopf.Controller.PlayersSetupBuilder;
import doppelkopf.Model.CardModel.*;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;

public class MainTest {
//    public static <T> void getName(ArrayList<T> list){
//        Iterator<T> iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//        System.out.println();
//    }
//
//    public static void testDeck(){
//        CardsToDeal cardsToDeal = new CardsToDeal(null);
//        cardsToDeal.init();
//        SortHelper.sortByStrength(cardsToDeal);
//
//        for(Card card : cardsToDeal.getCards()){
//            if(card.isFehl()){
//                System.out.println(card + " FEHL " + card.getName() + " "+ card.getStrength() + " strong " + card.getPoint() + " points");
//            }else{
//                System.out.println(card + " TRUMPF " + card.getName() + " "+ card.getStrength() + " strong " + card.getPoint() + " points");
//            }
//        }
//    }

    public static void main(String[] args) {
        GameController game = new GameController();
        game.initGame();
        game.startGame();




//        Card card = new Card(Suit.HERZ, Rank.KOENIG);
//        CRUD crud = new CRUD();
//        CardsToDeal cardsToDeal = new CardsToDeal(null);
//        cardsToDeal.init();
//        PlayersSetupBuilder playersSetupBuilder = new PlayersSetupBuilder();
//        playersSetupBuilder.initSeeding();
//        ArrayList<Player> players = playersSetupBuilder.getPlayers();
//
//        crud.insertNewGame(players);

//        crud.insertNewGame(playersSetupBuilder.getPlayers());
//        System.out.println(playersSetupBuilder.getPlayers().get(0).getPassword());
//        ArrayList<Player> players = playersSetupBuilder.getPlayers();
//        System.out.println(crud.insertNewGame(players));
//        for(Player player: players){
//            System.out.println(crud.selectPlayerID(player));
//        }

//        crud.insertAllCardsToDatabase(cardsToDeal.getCards());
//        players.get(2).setGameWon(true);
//        players.get(2).setPointsWonPerGame(25);
//        crud.insertGamePlayed(players.get(2), players.get(1), 5);

//        crud.insertCardsCollected(players.get(0), cardsToDeal.getCards());




    }
}
