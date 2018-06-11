/**
 * Game controller manages the game
 * Can create players, all cards needed
 * and coordinates the rounds
 *
 * Attributes:
 *      numbRound:              the current round
 *      playersSetupFactory:    holding setup references to all players
 *      cardsSetupFactory:      holding setup references to all kinds of cards
 *      teamKreuzQueen:         list holding all team Kreuz Queen members
 *      teamNoKreuzQueen:       list holding all team No Kreuz Queen members
 *      whoHasTwoKreuzQueen:    holding 1 Player who has all 2 Kreuz Queen, if noone => null
 *      rounds:                 list holding which player won which round and what kind the first card was played (FEHL >< TRUMPF)
 *      pointTeamKreuzQueen:    holding points of Team Kreuz Queen
 *      pointTeamNoKreuzQueen:  holding points of Team No Kreuz Queen
 *      console:                write to or read from console
 *      crud:                   connection to database
 *      game_ID:                hold id of the current game in database
 *
 *
 * Important methods:
 *      initGame()              Setup players and cards
 *      startGame()             Start a game
 *      resetGame()             Prepare for new game
 *      endGame()               End a game
 *      startRound()            Start a round
 *      startRoundSeeding()     Start a auto seeding round, used for DEMO purpose
 *      whoWinsTheRound()       Determines who wins the round, given the CardsPlayedPerRound sorted
 *      sumUpPointTwoTeams      calculate points of each team
 *      checkHochzeit()         In case there's one player (A) has all 2 Kreuz Queen
 *                              Check if there's someone else (B) won any of the first 3 rounds with a FEHL round
 *                              return B as result
 *                              These two will play together as partners at Team Kreuz Queen
 *                              Otherwise, player A will play alone vs other 3
 *                              return NULL
 *
 *       checkBazinga()             Check and return true/ false if the round has Bazinga or not respectively
 *       checkPlayerGuessBazinga()  Check if guess of some player for Bazinga was correct
 *                                  Give 10 bonus points or take 5 points away if it's correct or not respectively
 *        whoGuessBazingaSeeding()  Simulate each round there's some one or no one who wants to guess for Bazinga
 *        saveAllCardsToDatabase()  Create all cards to store in database
 *        whichTeamWon()            Decides which team won
 *                                  If
 *                                  return >0 => Team Kreuz Queen won
 *                                  return <0 => Team No Kreuz Queen won
 *                                  return 0 =>  Draw, no team wins
 *
 *       setGameWonEachPlayer():    Set the property gameWon to true or false accordingly to every player
 *       setPartner():              Set partner for each player
 *
 */

package doppelkopf.Controller;

import doppelkopf.Base.Doppelkopf;
import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.CardModel.CardsPlayedPerRound;
import doppelkopf.Model.PlayerModel.Player;
import doppelkopf.View.ConsoleView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameController extends Doppelkopf {
    //region Attributes
    private int numbRound;
    private PlayersSetupFactory playersSetupFactory;
    private CardsSetupFactory cardsSetupFactory;
    private ArrayList<Player> teamKreuzQueen;
    private ArrayList<Player> teamNoKreuzQueen;
    private Player whoHasTwoKreuzQueen;
    private ArrayList rounds;
    private int pointTeamKreuzQueen;
    private int pointTeamNoKreuzQueen;
    private ArrayList<Player> playersGuessBazinga;
    private ConsoleView console;
    private CRUD crud;
    private int game_ID = 0;
    //endregion

    //region Important methods
    /**
     * Setup players and cards
     */
    public void initGame(){
        this.numbRound = 0;
        this.playersSetupFactory = new PlayersSetupFactory();
        this.cardsSetupFactory = new CardsSetupFactory(this.playersSetupFactory);
        this.teamKreuzQueen = new ArrayList<>();
        this.teamNoKreuzQueen = new ArrayList<>();
        this.whoHasTwoKreuzQueen = whoHasTwoKreuzQueen();
        this.rounds = new ArrayList<>();
        this.pointTeamKreuzQueen = 0;
        this.pointTeamNoKreuzQueen = 0;
        this.playersGuessBazinga = new ArrayList<>();
        this.console = new ConsoleView();
        this.crud = new CRUD();
        this.game_ID = 0;
    }

    /**
     * Start a game
     */
    public void startGame(){
        // check to play another game or stop
        boolean anotherGame = true;

        // simulate the game
        while(anotherGame){
            // game start
            console.gameStartText();

            // create all cards needed then deal to players
            this.cardsSetupFactory.initCardSetup();

            // increase game_ID
            this.game_ID = this.crud.insertNewGame(this.playersSetupFactory.getPlayers());

            System.out.println(this.game_ID);

            //  prepare which players are allowed to guess for Bazinga
            this.playersGuessBazinga.addAll(this.playersSetupFactory.getPlayers());
            this.playersGuessBazinga.add(null);

            // set who has Kreuz Queen, who not
            this.cardsSetupFactory.checkPlayerHasKreuzQueen();

            // separate two teams
            // set the one who has all 2 Kreuz Queen in case there's actually someone
            this.whoHasTwoKreuzQueen = whoHasTwoKreuzQueen();

            // play 10 rounds
            for(int i = 0; i<10; i++){
                // set the current round
                this.numbRound = i+1;

                // until round 4, check Hochzeit for the first three rounds
                if(this.numbRound == 4 && this.whoHasTwoKreuzQueen != null){
                    Player dreamPartner = checkHochzeit();
                    if(dreamPartner != null){
                        // getName to screen new partner
                        console.displayHochzeitNewPartner(dreamPartner, this);
                    }else{
                        // getName to screen who plays alone
                        console.displayHochzeitAlone(this);
                    }
                }

                // show the current round title
                console.displayCurrentRound(this);

                // simulate a round
                startRoundSeeding();
            }

            // game ended
            console.gameEnded();

            // debug
            console.displayWhoWonWhichRound(this);

            // getName all cards every player has collected
            console.displayEachPlayersCardsWon(this);

            // all points of each players
            console.displayEachPlayerPoints(this);

            // all points of each team including members
            sumUpPointTwoTeams();

            // which team wins
            console.displayTwoTeamResults(this);

            // set game won for each player
            setGameWonEachPlayer();

            // insert stuff to database
            // insert all cards won for each player
            for(Player player : this.playersSetupFactory.getPlayers()){
                this.crud.insertCardsCollected(player, player.getCardsWon().getCards());
            }

            // insert the game was played to history of each player
            // first setPartner
            setPartner();
            for(Player player : this.playersSetupFactory.getPlayers()){
                this.crud.insertGamePlayed(player, player.getPartner(), this.game_ID);
            }


            // type "y" to play another game
            // "n" to stop
            console.displayInstrucAnotherGameOrStop();

            while(true){
                String command = this.console.getSc().next();
                if(command.toUpperCase().compareTo("Y") == 0){
                    anotherGame = true;
                    resetGame();
                    break;
                }else{
                    if(command.toUpperCase().compareTo("N") == 0){
                        anotherGame = false;
                        break;
                    }else{
                        console.displayEnterCommandAgain();
                    }
                }
            } // end of while (command)
        }   // end of while(anotherGame)
    }

    /**
     * Prepare for new game
     */
    public void resetGame(){
        initGame();
    }

    /**
     * End a game
     */
    public void endGame(){

    }

    /**
     * Start a round
     */
    public void startRound(){

    }

    /**
     * Start an auto seeding round, used for DEMO purpose
     */
    public void startRoundSeeding(){
        // each player takes turn to play
        for(int i = 0; i<4; i++){
            Player player = playersSetupFactory.getPlayers().get(i);

            // if 1. Player => CardsAllowedToPlay = CardsOnHand
            if(i == 0){
                player.getCardsAllowedToPlay().clear();
                player.getCardsAllowedToPlay().addAll(player.getCardsOnHand().getCards());
            }else{
                player.setWhatCardToPlay(cardsSetupFactory.getCardsPlayedPerRound().getCards().get(0));
            }

            // getName the CardsOnHand of the player
            console.displayCardsOnHandOfPlayer(player);

            // getName the CardsAllowedToPlay of the player
            console.displayCardsAllowedToPlayOfPlayer(player);

            // show what card has been played
            Card card = player.playARandomCard();
            console.displayWhatCardHasBeenPlayed(player, card);

            // save the just played card to CardsPlayed in Database
            this.crud.insertCardPlayed(player, card);

            // add the card to CardsPlayedPerRound
            cardsSetupFactory.getCardsPlayedPerRound().add(card);
        }   // end of for

        // getName all player's hand + Cards played per round
        console.displayAllHands(this);
        console.displayCardsPlayedPerRound(this);

        // getName who wins the round
        Player roundWinner = whoWinsTheRound(cardsSetupFactory.getCardsPlayedPerRound());
        ArrayList<Card> cardsPerRound = cardsSetupFactory.getCardsPlayedPerRound().getCards();
        console.displayWhoWinsTheRound(roundWinner, cardsPerRound);

        // add the winner to the list rounds
        this.rounds.add(roundWinner);
        // add the first card played in round next to the winner
        // to determine if the player won a FEHL Stich or Trumpf Stich
        this.rounds.add(this.cardsSetupFactory.getCardsPlayedPerRound().getCards().get(0));

        // add the won cards to the player's CardsWon
        roundWinner.getCardsWon().getCards().addAll(cardsPerRound);

        // rearrange the order of players for next round
        // winner of the last round begins the next round
        for(int i = 0; i < this.playersSetupFactory.getPlayers().size(); i++){
            Player player = this.playersSetupFactory.getPlayers().get(0);

            if(player.toString().compareTo(roundWinner.toString()) == 0){
                // if the first position is the round winner, stop
                break;
            }else{
                // add the first player to the end of the players list
                this.playersSetupFactory.getPlayers().add(player);

                // remove the first player from the player list
                this.playersSetupFactory.getPlayers().remove(0);
            }
        }

        // debug simulate Bazinga guess
        Player whoGuessBazinga = whoGuessBazingaSeeding();
        if(whoGuessBazinga != null){
            console.displayWhoGuessBazinga(whoGuessBazinga);
        }

        // check if guess for Bazinga was correct
        if(whoGuessBazinga != null){
            if(checkPlayerGuessBazinga(whoGuessBazinga, checkBazinga())){
                console.displayGuessBazingaCorrect(whoGuessBazinga);
            }else{
                console.displayGuessBazingaFalse(whoGuessBazinga);
            }
        }

        // clear the CardsPlayedPerRound
        cardsSetupFactory.getCardsPlayedPerRound().clear();
    }

    public void endRound(){

    }

    /**
     * Determines who wins the round, given the CardsPlayedPerRound sorted
     * @param cardsPlayedPerRound
     * @return
     */
    public Player whoWinsTheRound(CardsPlayedPerRound cardsPlayedPerRound){

        return cardsPlayedPerRound.getCards().get(0).getBelongsToPlayer();
    }   // end of whoWinsTheRound

    /**
     * Determine who has Kreuz Queen and who not
     * Return the player who has all 2 Kreuz Queen
     * if no one, return NULL
     * @return
     */
    public Player whoHasTwoKreuzQueen(){
        // walk through every player
        for(Player player : this.playersSetupFactory.getPlayers()){
            if(player.hasKreuzQueen()){
                // if the player has KREUZ QUEEN
                this.teamKreuzQueen.add(player);
            }else{
                // if no KREUZ QUEEN
                this.teamNoKreuzQueen.add(player);
            }
        }   // end of for

        if(this.teamKreuzQueen.size() == 1){
            // if there's someone who has all 2 Kreuz Queen, return him
            return this.teamKreuzQueen.get(0);
        }else{
            // if not, return NULL
            return null;
        }
    }   // end of whoHasTwoKreuzQueen

    /**
     * Calculate points of each team then getName them accordingly
     */
    public void sumUpPointTwoTeams(){
        // calculate points of each team
        // Team Kreuz Queen
        this.pointTeamKreuzQueen = 0;
        for(int i = 0; i< this.teamKreuzQueen.size(); i++){
            this.pointTeamKreuzQueen += this.teamKreuzQueen.get(i).getPointsWonPerGame();
            System.out.println(this.teamKreuzQueen.get(i) + " " + this.teamKreuzQueen.get(i).getPointsWonPerGame());
        }

        // Team No Kreuz Queen
        this.pointTeamNoKreuzQueen = 0;
        for(int i = 0; i< this.teamNoKreuzQueen.size(); i++){
            this.pointTeamNoKreuzQueen += this.teamNoKreuzQueen.get(i).getPointsWonPerGame();
            System.out.println(this.teamNoKreuzQueen.get(i) + " " + this.teamNoKreuzQueen.get(i).getPointsWonPerGame());
        }

    }   // end of sumUpPointTwoTeams

    /**
     * In case there's one player (A) has all 2 Kreuz Queen
     * Check if there's someone else (B) won any of the first 3 rounds with a FEHL round
     * return B as result
     * These two will play together as partners at Team Kreuz Queen
     *
     * Otherwise, player A will play alone vs other 3
     * return NULL
     * @return
     */
    public Player checkHochzeit(){
        for(int i = 0; i < 5; i+= 2){
            // get each player
            // check if it's not the player who's got all 2 Kreuz Queen
            Player dreamPartner = (Player)this.rounds.get(i);
            if(dreamPartner.toString().compareTo(this.whoHasTwoKreuzQueen.toString()) != 0){
                // check if the round won by him was FEHL
                Card roundWonByDreamPartner = (Card)this.rounds.get(i+1);
                if (roundWonByDreamPartner.isFehl()){
                    // add dreamPartner to Team Kreuz Queen
                    this.teamKreuzQueen.add(dreamPartner);

                    // remove him from Team No Kreuz Queen
                    this.teamNoKreuzQueen.remove(dreamPartner);

                    return dreamPartner;
                }
            }
        }   // end of for
        return null;
    }   // end of checkHochzeit

    /**
     * Check and return true/ false if the round has Bazinga or not respectively
     * @return
     */
    public boolean checkBazinga() {
        // initialize an array holding how many times each Suit appears in the round
        int[] suitNumb = new int[4];

        // count the times each Suit appears
        for (Card card : this.cardsSetupFactory.getCardsPlayedPerRound().getCards()) {
            switch (card.getSuit()) {
                case "HERZ":
                    suitNumb[0]++;
                    break;
                case "KARO":
                    suitNumb[1]++;
                    break;
                case "PICK":
                    suitNumb[2]++;
                    break;
                case "KREUZ":
                    suitNumb[3]++;
                    break;
            }   // end of switch
        }   // enf of for

        // count how many times the number "2" appears in the suitNumb array
        // if the number "2" appears exactly twice => Bazinga
        int frequencyOfTwo = 0;
        for(int i = 0; i < suitNumb.length; i++){
            if(suitNumb[i] == 2){
                frequencyOfTwo++;
            }
        }   // end of for

        // return result
        if(frequencyOfTwo == 2){
            return true;
        }else{
            return false;
        }
    }   // end of checkBazinga

    /**
     * Check if guess of some player for Bazinga was correct
     * Give 10 bonus points or take 5 points away if it's correct or not respectively
     * @param whoGuessBazinga
     * @param checkBazinga
     * @return
     */
    public boolean checkPlayerGuessBazinga(Player whoGuessBazinga, boolean checkBazinga){
        if(checkBazinga){
            whoGuessBazinga.setSpecialPoints(10);
            return true;
        }else{
            whoGuessBazinga.setSpecialPoints(-5);
            return false;
        }
    }   // end of checkPlayerGuessBazinga

    /**
     * Simulate each round there's some one or no one who wants to guess for Bazinga
     *
     * @return
     */
    public Player whoGuessBazingaSeeding(){
        // randomly pick the position of player who tries to guess for Bazinga of the current round
        // the positions are in the list playersGuessBazinga
        // last position means no player wanna guess for Bazinga this round
        // after each guess, the player who guessed will be removed => no longer can guess next time
        int numbPlayersGuessBazinga = ThreadLocalRandom.current().nextInt(0, this.playersGuessBazinga.size());

        Player playerToReturn = this.playersGuessBazinga.get(numbPlayersGuessBazinga);
        if(playerToReturn != null){
            playerToReturn.guessBazinga();
            this.playersGuessBazinga.remove(playerToReturn);
        }

        return playerToReturn;
    }   // end of whoGuessBazingaSeeding

    /**
     * Decides which team won
     * If
     *  return >0 => Team Kreuz Queen won
     *  return <0 => Team No Kreuz Queen won
     *  return 0 =>  Draw, no team wins
     * @return
     */
    public int whichTeamWon(){
        return this.pointTeamKreuzQueen - this.pointTeamNoKreuzQueen;
    }

    /**
     * Set the property gameWon to true or false accordingly to every player
     */
    public void setGameWonEachPlayer(){
        if(whichTeamWon() > 0){
            for (Player player: this.getTeamKreuzQueen()){
                int index = this.playersSetupFactory.getPlayers().indexOf(player);
                this.playersSetupFactory.getPlayers().get(index).setGameWon(true);
            }
        }else{
            if(whichTeamWon() < 0){
                for (Player player: this.getTeamNoKreuzQueen()){
                    int index = this.playersSetupFactory.getPlayers().indexOf(player);
                    this.playersSetupFactory.getPlayers().get(index).setGameWon(true);
                }
            }
        }
    }   // end of setGameWonEachPlayer


    /**
     * Set partner for each player
     */
    public void setPartner(){
        // assume that there's always 2 vs 2 teams
        if(this.teamNoKreuzQueen.size() == this.teamKreuzQueen.size()){
            // for team Kreuz Queen
            this.teamKreuzQueen.get(0).setPartner(this.teamKreuzQueen.get(1));
            this.teamKreuzQueen.get(1).setPartner(this.teamKreuzQueen.get(0));

            // for team No Kreuz Queen
            this.teamNoKreuzQueen.get(0).setPartner(this.teamNoKreuzQueen.get(1));
            this.teamNoKreuzQueen.get(1).setPartner(this.teamNoKreuzQueen.get(0));
        }
    }   // end of setPartner
    //endregion

    //region Getter Setter
    public ArrayList<Player> getTeamKreuzQueen() {
        return teamKreuzQueen;
    }

    public void setTeamKreuzQueen(ArrayList<Player> teamKreuzQueen) {
        this.teamKreuzQueen = teamKreuzQueen;
    }

    public ArrayList<Player> getTeamNoKreuzQueen() {
        return teamNoKreuzQueen;
    }

    public void setTeamNoKreuzQueen(ArrayList<Player> teamNoKreuzQueen) {
        this.teamNoKreuzQueen = teamNoKreuzQueen;
    }

    public Player getWhoHasTwoKreuzQueen() {
        return whoHasTwoKreuzQueen;
    }

    public void setWhoHasTwoKreuzQueen(Player whoHasTwoKreuzQueen) {
        this.whoHasTwoKreuzQueen = whoHasTwoKreuzQueen;
    }

    public ArrayList getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList rounds) {
        this.rounds = rounds;
    }

    public int getPointTeamKreuzQueen() {
        return pointTeamKreuzQueen;
    }

    public void setPointTeamKreuzQueen(int pointTeamKreuzQueen) {
        this.pointTeamKreuzQueen = pointTeamKreuzQueen;
    }

    public int getPointTeamNoKreuzQueen() {
        return pointTeamNoKreuzQueen;
    }

    public void setPointTeamNoKreuzQueen(int pointTeamNoKreuzQueen) {
        this.pointTeamNoKreuzQueen = pointTeamNoKreuzQueen;
    }

    public ArrayList<Player> getPlayersGuessBazinga() {
        return playersGuessBazinga;
    }

    public void setPlayersGuessBazinga(ArrayList<Player> playersGuessBazinga) {
        this.playersGuessBazinga = playersGuessBazinga;
    }

    public ConsoleView getConsole() {
        return console;
    }

    public void setConsole(ConsoleView console) {
        this.console = console;
    }

    public int getNumbRound() {
        return numbRound;
    }

    public void setNumbRound(int numbRound) {
        this.numbRound = numbRound;
    }

    public PlayersSetupFactory getPlayersSetupFactory() {
        return playersSetupFactory;
    }

    public void setPlayersSetupFactory(PlayersSetupFactory playersSetupFactory) {
        this.playersSetupFactory = playersSetupFactory;
    }

    public CardsSetupFactory getCardsSetupFactory() {
        return cardsSetupFactory;
    }

    public void setCardsSetupFactory(CardsSetupFactory cardsSetupFactory) {
        this.cardsSetupFactory = cardsSetupFactory;
    }

    public CRUD getCrud() {
        return crud;
    }

    public void setCrud(CRUD crud) {
        this.crud = crud;
    }

    public int getGame_ID() {
        return game_ID;
    }

    public void setGame_ID(int game_ID) {
        this.game_ID = game_ID;
    }
    //endregion
}





























