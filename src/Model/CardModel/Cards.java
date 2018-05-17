/*
    Base Card Collection for CardsOnHand, CardsPlayedPerRound, CardsToDeal, CardsWon.
    Attribute:
        cards:      Collection storing all cards depending on purpose (deck, hand, Stich, cards won...)
        numCards:   number of cards at particular moment
        players:    keeping references to Players

    Important Methods:
        add, addAll, remove, clear:     manipulate elements in the collection
        filterFehl:     filter all the FEHL in the collection
        filterTrumpf:   filter all the TRUMPF in the collection

 */

package Model.CardModel;

import Model.PlayerModel.Player;

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
        if(this.cards.contains(index)){
            Card temp = this.cards.remove(index);
            this.numCards--;
            return temp;
        }else{
            return null;
        }
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

    // filter all the FEHL in the card list
    public ArrayList<Card> filterFehl(){
        ArrayList<Card> resultList = new ArrayList<>();

        // walk through all Cards and check out the FEHL ones
        // set attribute isTrumpf and isFehl accordingly
        Iterator<Card> iterator = this.cards.iterator();
        while (iterator.hasNext()){
            Card temp = iterator.next();
            switch (temp.toString()){
                case "PIK ASS":
                case "PIK KOENIG":
                case "PIK ZEHN":
                case "KREUZ ASS":
                case "KREUZ KOENIG":
                case "KREUZ ZEHN":
                case "HERZ ASS":
                case "HERZ KOENIG":
                    temp.setFehl(true);
                    temp.setTrumpf(false);
                    resultList.add(temp);
            }   // end of switch
        }   // end of while

        return resultList;
    }

    // Filter all the TRUMPF in the card list
    public ArrayList<Card> filterTrumpf(){
        ArrayList<Card> resultList = new ArrayList<>();
        ArrayList<Card> fehl = filterFehl();            // keep all the FEHL

        // walk through all Cards and copy the one which is not in FEHL list
        // set attribute isTrumpf and isFehl accordingly
        for(Card temp : this.cards){
            if(!fehl.contains(temp)){
                temp.setTrumpf(true);
                temp.setFehl(false);
                resultList.add(temp);
            }
        }

        return resultList;
    }

    //print out all Cards to Console
    public void display(){
        Iterator<Card> iterator = this.cards.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }




}
