package Model.CardModel;

import Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cards{
    private ArrayList<Card> cards;
    private int numCards;
    private Player player;

    public Cards(ArrayList<Card> cards) {
        this.cards = cards;
        this.numCards = this.cards.size();
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
        if(getNumCards() > 0){
            Card temp = this.cards.remove(index);
            this.numCards--;
            return temp;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Card> filterFehl(){
        ArrayList<Card> resultList = new ArrayList<>();

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
                case "HERZ KOENIG": resultList.add(temp);
            }   // end of switch
        }   // end of while

        return resultList;
    }

    public ArrayList<Card> filterTrumpf(){
        ArrayList<Card> resultList = new ArrayList<>();
        ArrayList<Card> fehl = filterFehl();

//        Iterator<Card> iterator = fehl.iterator();
//        while (iterator.hasNext()){
//            Card temp = iterator.next();
//            if(!cards.contains(temp)){
//                resultList.add(temp);
//            }
//        }
        for(Card temp : this.cards){
            if(!fehl.contains(temp)){
                resultList.add(temp);
            }
        }

        return resultList;
    }

    public void display(){
        Iterator<Card> iterator = this.cards.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
    }




}
