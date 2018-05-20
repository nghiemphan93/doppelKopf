package doppelkopf.View;


import java.util.ArrayList;

public class EndView{

    public void showEnd(){
        System.out.println("==================================================================");
        System.out.println("GAME ENDED ");
        System.out.println("==================================================================");
    }

    public void showPlayersWonRounds(ArrayList rounds) {
        System.out.println("Players won the round next to: " + rounds);
    }
}
