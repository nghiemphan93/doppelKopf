package doppelkopf.View;

import doppelkopf.Model.PlayerModel.Player;

public class HochzeitView {


    public void showPartner(Player dreamPartner, Player whoHasTwoKreuzQueen) {
        System.out.println("--------HOCHZEIT--------");
        System.out.println(dreamPartner + " plays with " + whoHasTwoKreuzQueen);
    }

    public void showPlayAlone(Player whoHasTwoKreuzQueen) {
        System.out.println("--------HOCHZEIT--------");
        System.out.println(whoHasTwoKreuzQueen + " plays alone");
    }
}
