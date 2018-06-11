package doppelkopf;

import doppelkopf.Controller.CRUD;
import doppelkopf.Controller.GameController;
import doppelkopf.Controller.PlayersSetupBuilder;
import doppelkopf.Model.CardModel.*;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;

public class MainTest {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.play();


    }
}
