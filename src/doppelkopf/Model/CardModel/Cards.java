/**
 * Base Card Collection for CardsOnHand, CardsPlayedPerRound, CardsToDeal, CardsWon, CardsAllowedToPlay
 *
 * Attribute:
 *     cards:      Collection storing all cards depending on purpose (deck, hand, Stich, cards won...)
 *     numCards:   number of cards at particular moment
 *     players:    keeping references to Players
 *
 * Important Methods:
 *     add, addAll, remove, clear:     manipulate elements in the collection
 *     filterFehl:     Filter all the FEHL in the card list
 *     filterTrumpf:   filter all the TRUMPF in the collection
 */


package doppelkopf.Model.CardModel;

import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cards{
    private ArrayList<Card> cards;
    private int numCards;
    private ArrayList<Player> players = new ArrayList<>();

    public Cards(ArrayList<Card> cards) {
        this.cards = cards;
        this.numCards = this.cards.size();
        this.numCards = 0;
    }

    public Cards() {
        this.cards = new ArrayList<>();
        this.numCards = 0;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card add(Card card) {
        this.cards.add(card);
        this.numCards++;

        return card;
    }

    public void addAll(ArrayList<Card> cards) {
        this.cards.addAll(cards);
        this.numCards += cards.size();
    }


    public Card remove(int index) {
            Card temp = this.cards.remove(index);
            this.numCards--;
            return temp;
    }

    public Card remove(Card card){
        if(cards.contains(card)){
            cards.remove(card);
            this.numCards--;

            return card;
        }else{
            return null;
        }
    }

    public void clear() {
        this.cards.clear();
        this.numCards = 0;
    }

    public int getNumCards() {
        return this.numCards;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Filter all the FEHL in the card list
     */
    public ArrayList<Card> filterFehl(){
        ArrayList<Card> resultList = new ArrayList<>();

        // walk through all Cards and check out the FEHL ones
        for(Card card : cards){
            if(card.isFehl()){
                resultList.add(card);
            }
        }

        return resultList;
    }

    /**
     * Filter all the TRUMPF in the card list
     */
    public ArrayList<Card> filterTrumpf(){
        ArrayList<Card> resultList = new ArrayList<>();

        // walk through all Cards and copy the one which is not in FEHL list
        for(Card card : cards){
            if(card.isTrumpf()){
                resultList.add(card);
            }
        }

        return resultList;
    }

    /**
     * Print out all Cards to ConsoleView
     */
    public void display(){
        Iterator<Card> iterator = this.cards.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String result = "";

        for (Card card : cards) {
            result += card + " ";
        }
        return result;
    }
}
