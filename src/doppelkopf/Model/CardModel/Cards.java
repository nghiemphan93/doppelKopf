/**
 * Base Card Collection for CardsOnHand, CardsPlayedPerRound, CardsToDeal, CardsWon, CardsAllowedToPlay
 *
 * Attribute:
 *     cards:           List storing all cards depending on purpose (deck, hand, Stich, cards won...)
 *     numCards:        Number of cards at particular moment
 *     players:         Keep references to Players
 *
 * Important Methods:
 *     add, addAll, remove, clear:  Manipulate elements in the collection
 *     filterFehl():                Filter all the FEHL in the card list
 *     filterTrumpf():              Filter all the TRUMPF in the card list
 *     display():                   Print out all Cards to ConsoleView
 */


package doppelkopf.Model.CardModel;

import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cards{
    //region Attributes
    private ArrayList<Card> cards;
    private int numCards;
    private ArrayList<Player> players = new ArrayList<>();
    //endregion

    //region Constructors
    public Cards(ArrayList<Card> cards) {
        this.cards = cards;
        this.numCards = this.cards.size();
        this.numCards = 0;
    }

    public Cards() {
        this.cards = new ArrayList<>();
        this.numCards = 0;
    }
    //endregion

    //region Important methods
    /**
     * Add a card to the card list
     * @param card
     * @return
     */
    public Card add(Card card) {
        this.cards.add(card);
        this.numCards++;

        return card;
    }

    /**
     * Add a bunch of cards to the card list
     * @param cards
     */
    public void addAll(ArrayList<Card> cards) {
        this.cards.addAll(cards);
        this.numCards += cards.size();
    }

    /**
     * Remove a card from the card list given the index
     * @param index
     * @return
     */
    public Card remove(int index) {
            Card temp = this.cards.remove(index);
            this.numCards--;
            return temp;
    }

    /**
     * Remove a card from the card list given the object card itself
     * @param card
     * @return
     */
    public Card remove(Card card){
        if(cards.contains(card)){
            cards.remove(card);
            this.numCards--;

            return card;
        }else{
            return null;
        }
    }

    /**
     * Clear all the cards from the card list
     */
    public void clear() {
        this.cards.clear();
        this.numCards = 0;
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
    //endregion

    //region Getter Setter
    public ArrayList<Card> getCards() {
        return cards;
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
    //endregion
}
