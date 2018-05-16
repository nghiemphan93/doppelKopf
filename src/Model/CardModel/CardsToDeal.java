package Model.CardModel;

import Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CardsToDeal extends Cards{
    private ArrayList<Player> players = new ArrayList<>();

    public CardsToDeal(ArrayList<Player> players){
        this.players = players;
    }

    public void init(){
        Suit suits[] = Suit.values();
        Rank ranks[] = Rank.values();
        ArrayList<Card> cards = new ArrayList<>();

        for(Suit suit : suits){
            for(Rank rank : ranks){
                cards.add(new Card(suit.toString(), rank.toString()));
                cards.add(new Card(suit.toString(), rank.toString()));
            }
        }

        this.addAll(cards);
        shuffle();
    }

    public void deal(){
        CardsOnHand cardsToDeal = new CardsOnHand();
        for(Player player: players){
            for(int i = 0; i<10; i++){
                Card temp = this.getCards().remove(0);
                player.getCardsOnHand().add(temp);
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this.getCards());
    }

    public void reset(){
        clear();
        init();
    }
}
