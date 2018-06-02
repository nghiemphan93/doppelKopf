/**
 * Show all infor on the ConsoleView
 *
 * Attribute:
 *  sc:                 take input from user
 *
 *
 * Important methods:
 *  displayAllHand()                display all cards of all hands
 *  disCarPlaPerRound()             display all cards played in the current round
 *  displayEachPlayersCardsWon()    display all cards each player won so far
 *  displayEachPlayerPoints()       display all points of each player
 *  displayTwoTeamResults()         display final results which team wins and points
 *  gameStartText()                 Game Start Text
 *  gameEnded()                     Game End Text
 *  displayWhoWonWhichRound()       Display who won which round
 *  displayWhatCardHasBeenPlayed()  Display what Card has been played
 *  displayWhoWinsTheRound()        Display who wins the round
 *  displayWhoGuessBazinga()        Display who guess Bazinga
 *  displayGuessBazingaCorrect()    Display guess Bazinga was correct
 *  displayGuessBazingaFalse()      Display guess Bazinga was false
 *  displayCardsOnHandOfPlayer()    Display Cards on Hand of the player
 *  displayCardsAllowedToPlayOfPlayer() Display cards allowed to play of the player
 *  displayHochzeitNewPartner()     Display the new Partner in case Hochzeit
 *  displayHochzeitAlone()          Display play alone in case Hochzeit
 *  displayCurrentRound()           Display the current round
 *  displayInstrucAnotherGameOrStop()   Display instruction to play another game or stop
 *  displayEnterCommandAgain()      Instruction to enter command again
 */

package doppelkopf.View;

import doppelkopf.Controller.GameController;
import doppelkopf.Model.CardModel.Card;
import doppelkopf.Model.CardModel.SortHelper;
import doppelkopf.Model.PlayerModel.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    private Scanner sc;


    public ConsoleView(){
        this.sc = new Scanner(System.in);
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Display hands of every player
     * @param gameController
     */
    public void displayAllHands(GameController gameController){
        for(Player player : gameController.getPlayersSetupBuilder().getPlayers()){
            System.out.println(player + "'s hand: " + player.getCardsOnHand());
        }
    }

    /**
     * Display cards played on table per round
     * @param gameController
     */
    public void displayCardsPlayedPerRound(GameController gameController){
        // Sort by strength
        SortHelper.sortByStrength(gameController.getCardsSetupBuilder().getCardsPlayedPerRound());

        System.out.print("Cards played in round: ");
        for(Card card : gameController.getCardsSetupBuilder().getCardsPlayedPerRound().getCards()){
            System.out.print(card + ":" + card.getBelongsToPlayer() + " ");
        }
        System.out.println();
    }

    /**
     * Display each players cards won
     * @param gameController
     */
    public void displayEachPlayersCardsWon(GameController gameController){
        for(Player player : gameController.getPlayersSetupBuilder().getPlayers()){
            System.out.print(player + " collected: ");

            for(Card card : player.getCardsWon().getCards()){
                System.out.print(card + " ");
            }

            System.out.println();

        }
        System.out.println();
    }

    /**
     * Display each player Points
     * @param gameController
     */
    public void displayEachPlayerPoints(GameController gameController){
        for(Player player : gameController.getPlayersSetupBuilder().getPlayers()){
            System.out.println(player + " achieved: " + player.calcPointsWonPerGame() + " points");
        }
        System.out.println();
    }

    /**
     * Display final results which team wins and points
     * @param gameController
     */
    public void displayTwoTeamResults(GameController gameController){
        // display 2 teams and points of each team
        // Team Kreuz Queen
        switch (gameController.getTeamKreuzQueen().size()){
            case 1:
                System.out.println("Team Kreuz Queen: " + gameController.getTeamKreuzQueen().get(0) + " with " + gameController.getPointTeamKreuzQueen() + " points");
                break;
            case 2:
                System.out.println("Team Kreuz Queen: " + gameController.getTeamKreuzQueen().get(0) + " and " + gameController.getTeamKreuzQueen().get(1) + " with " + gameController.getPointTeamKreuzQueen() + " points");
                break;
        }

        // Team No Kreuz Queen
        switch (gameController.getTeamNoKreuzQueen().size()){
            case 1:
                System.out.println("Team No Kreuz Queen: " + gameController.getTeamNoKreuzQueen().get(0) + " with " + gameController.getPointTeamKreuzQueen() + " points");
                break;
            case 2:
                System.out.println("Team No Kreuz Queen: " + gameController.getTeamNoKreuzQueen().get(0) + " and " + gameController.getTeamNoKreuzQueen().get(1) + " with " + gameController.getPointTeamNoKreuzQueen() + " points");
                break;
            case 3:
                System.out.println("Team No Kreuz Queen: " + gameController.getTeamNoKreuzQueen().get(0) + ", " + gameController.getTeamNoKreuzQueen().get(1) + " and " + gameController.getTeamNoKreuzQueen().get(2)+ " with " + gameController.getPointTeamNoKreuzQueen() + " points");
                break;
        }

        // which team wins
        System.out.println();
        if(gameController.whichTeamWon() > 0){
            System.out.println("=====> Team Kreuz Queen wins with " + gameController.getPointTeamKreuzQueen() + " points");
        }else{
            if(gameController.whichTeamWon() < 0){
                System.out.println("=====> Team No Kreuz Queen wins with " + gameController.getPointTeamNoKreuzQueen() + " points");
            }else{
                System.out.println("=====> Draw -- No team wins");
            }
        }

    }

    /**
     * Game Start Text
     */
    public void gameStartText() {
        // game start
        System.out.println("==================================================================");
        System.out.println("GAME STARTED ");
        System.out.println("==================================================================");
    }

    /**
     * Game End Text
     */
    public void gameEnded() {
        // game ended
        System.out.println("==================================================================");
        System.out.println("GAME ENDED ");
        System.out.println("==================================================================");
    }

    /**
     * Display who won which round
     * @param gameController
     */
    public void displayWhoWonWhichRound(GameController gameController) {
        System.out.println("Players won the round next to: " + gameController.getRounds());
    }

    /**
     * Display what Card has been played
     * @param player
     * @param card
     */
    public void displayWhatCardHasBeenPlayed(Player player, Card card) {
        System.out.println(player + " played: " + card);
        System.out.println(player + " still has: " + player.getCardsOnHand().getCards().size());
        System.out.println();
    }

    /**
     * Display who wins the round
     * @param roundWinner
     * @param cardsPerRound
     */
    public void displayWhoWinsTheRound(Player roundWinner, ArrayList<Card> cardsPerRound) {
        System.out.println();
        System.out.println(roundWinner + " wins the round with " + cardsPerRound.get(0));
    }

    /**
     * Display who guess Bazinga
     * @param whoGuessBazinga
     */
    public void displayWhoGuessBazinga(Player whoGuessBazinga) {
        System.out.println();
        System.out.println(whoGuessBazinga + " guesses for Bazinga");
    }

    /**
     * Display guess Bazinga was correct
     * @param whoGuessBazinga
     */
    public void displayGuessBazingaCorrect(Player whoGuessBazinga) {
        System.out.println("Guess for Bazinga was correct");
        System.out.println(whoGuessBazinga + " receives 10 bonus points");
        System.out.println();
    }

    /**
     * Display guess Bazinga was false
     * @param whoGuessBazinga
     */
    public void displayGuessBazingaFalse(Player whoGuessBazinga) {
        System.out.println("Sorry, guess for Bazinga was not correct");
        System.out.println(whoGuessBazinga + " loses 5 points");
        System.out.println();
    }

    /**
     * Display Cards on Hand of the player
     * @param player
     */
    public void displayCardsOnHandOfPlayer(Player player) {
        System.out.println(player);
        System.out.println("Cards on hand: " + player.getCardsOnHand());
    }

    /**
     * Display cards allowed to play of the player
     * @param player
     */
    public void displayCardsAllowedToPlayOfPlayer(Player player) {
        System.out.println("Cards allowed to play: " + player.getCardsAllowedToPlay());
    }

    /**
     * Display the new Partner in case Hochzeit
     * @param dreamPartner
     * @param gameController
     */
    public void displayHochzeitNewPartner(Player dreamPartner, GameController gameController) {
        System.out.println("--------HOCHZEIT--------");
        System.out.println(dreamPartner + " plays with " + gameController.getWhoHasTwoKreuzQueen());
    }

    /**
     * Display play alone in case Hochzeit
     * @param gameController
     */
    public void displayHochzeitAlone(GameController gameController) {
        System.out.println("--------HOCHZEIT--------");
        System.out.println(gameController.getWhoHasTwoKreuzQueen() + " plays alone");
    }

    /**
     * Display the current round
     * @param gameController
     */
    public void displayCurrentRound(GameController gameController) {
        System.out.println("==================================================================");
        System.out.println("ROUND " + gameController.getNumbRound());
        System.out.println("==================================================================");
    }

    /**
     * Display instruction to play another game or stop
     */
    public void displayInstrucAnotherGameOrStop() {
        System.out.println();
        System.out.println("Please enter \"y\" or \"Y\" to play another game: ");
        System.out.print("Please enter \"n\" or \"N\" to stop: ");
    }

    /**
     * Instruction to enter command again
     */
    public void displayEnterCommandAgain() {
        System.out.print("Please enter the command again: ");
    }
}
