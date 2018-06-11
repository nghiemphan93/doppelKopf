/**
 * Initialize players for a game
 *
 * Attribute:
 *     players:        hold reference to all players
 *
 * Important Methods:
 *     init()          prepare sign up for 4 real players
 *     initSeeding():  Auto seeding 4 player for Demo purpose
 *     addPlayer():    Add a player to the Players collection
 */


package doppelkopf.Controller;

import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;

public class PlayersSetupFactory {
    //region Attributes
    private ArrayList<Player> players;
    //endregion

    //region Constructors
    public PlayersSetupFactory(ArrayList<Player> players) {
        this.players = players;
    }

    public PlayersSetupFactory() {
        this.players = new ArrayList<>();
    }
    //endregion

    //region Important methods
    /**
     * Prepare sign up for 4 real players
     */
    public void init(){

    }

    /**
     * Auto seeding 4 player for Demo purpose
     */
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

    /**
     * Add a player to the Players collection
     * @param player
     */
    public void addPlayer(Player player){
        this.players.add(player);
    }
    //endregion

    //region Getter Setter
    public ArrayList<Player> getPlayers() {
        return players;
    }
    //endregion
}
