/*
    Card Deck creating all cards needed in game and deal to players.

    Attribute:

    Important Methods:
        init():     Initialize all cards needed and shuffle
        deal():     Deal cards to all players
        shuffle():  shuffle
        reset():    clear Deck then prepare for new game

 */

package Model.CardModel;

import Model.PlayerModel.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CardsToDeal extends Cards{

    public CardsToDeal(ArrayList<Player> players){
        setPlayers(players);
    }

    // Initialize all cards needed and shuffle
    public void init(){
        // Prepare all possible Suits and Ranks
        Suit suits[] = Suit.values();
        Rank ranks[] = Rank.values();
        ArrayList<Card> cards = new ArrayList<>();

        // Create all possible card combinations from Suits and Ranks
        for(Suit suit : suits){
            for(Rank rank : ranks){
                cards.add(new Card(suit, rank));
                cards.add(new Card(suit, rank));
            }
        }

        this.addAll(cards);
        shuffle();
    }

    // Deal cards to all players
    public void deal(){
        // Prepare a Hand for every player
        CardsOnHand cardsToDeal = new CardsOnHand();

        // For each player: remove 10 Cards from Deck then transfer to each Hand
        for(Player player: getPlayers()){
            for(int i = 0; i<10; i++){
                Card temp = this.getCards().remove(0);
                player.getCardsOnHand().add(temp);
            }
        }
    }

    // shuffle
    public void shuffle(){
        Collections.shuffle(this.getCards());
    }

    // clear Deck then prepare for new game
    public void reset(){
        clear();
        init();
    }

}

