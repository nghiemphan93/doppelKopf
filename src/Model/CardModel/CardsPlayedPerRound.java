package Model.CardModel;

import Model.ObserverModel.Observable;
import Model.ObserverModel.Observer;
import Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class CardsPlayedPerRound extends Cards implements Observable {
    private ArrayList<Observer> playerSubscribers;

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

    @Override
    public Card add(Card card) {
        pushNotify();

        return super.add(card);
    }
}
