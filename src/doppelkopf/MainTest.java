package doppelkopf;

import doppelkopf.Controller.CRUD;
import doppelkopf.Controller.GameController;
import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.CardModel.CardsToDeal;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.play();
    }
}
