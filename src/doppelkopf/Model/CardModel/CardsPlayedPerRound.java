/**
 *     Cards played on table in each round which all players can see
 *
 *     Attribute:
 *         playerSubscribers:  holding references of all players
 *
 *     Important Methods:
 *         register():     subscribe players to get notification when a new card was played
 *         unregister():   unsubscibe player => no more notification
 *         pushNotify():   notify all players who subscribed that a new card was played
 *         add():           every time  a card was added, every player will be notified
 */


package doppelkopf.Model.CardModel;

import doppelkopf.Model.ObserverModel.Observable;
import doppelkopf.Model.ObserverModel.Observer;

import java.util.ArrayList;

public class CardsPlayedPerRound extends Cards implements Observable {
    private ArrayList<Observer> playerSubscribers;

    public CardsPlayedPerRound(){
        this.playerSubscribers = new ArrayList<>();
    }

    @Override
    public void register(Observer o) {
        this.playerSubscribers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        this.playerSubscribers.remove(o);
    }

    @Override
    public void pushNotify() {
        for(Observer playerSubscriber : this.playerSubscribers){
            playerSubscriber.update();
        }
    }

    /**
     * Every time  a card was added, every player will be notified
     * @param card
     * @return
     */
    @Override
    public Card add(Card card) {
        pushNotify();

        return super.add(card);
    }

}
