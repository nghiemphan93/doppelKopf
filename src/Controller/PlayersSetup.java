package Controller;

import Model.PlayerModel.Player;

import java.util.ArrayList;

public class PlayersSetup {
    private ArrayList<Player> players;

    public PlayersSetup(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayersSetup() {
        this.players = new ArrayList<>();
    }

    public void init(){

    }

    public void initSeeding(){
        Player phan = new Player("Phan", "phan");
        Player melanie = new Player("Melanie", "melanie");
        Player sebastian = new Player("Sebastian", "sebastian");
        Player dominik = new Player("Dominik", "dominik");

        addPlayer(phan);
        addPlayer(melanie);
        addPlayer(sebastian);
        addPlayer(dominik);
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
