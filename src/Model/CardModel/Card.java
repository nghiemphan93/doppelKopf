/*
    The single Card to play in game

    Attribute:
        Suit            = PIK | KARO | HERZ | KREUZ
        Rank            = ZEHN | BUBE | DAME | KOENIG | ASS
        POINT           = 10   |   2  |  3   |  4    |  11
        STRENGTH = ???
        FEHL or TRUMPF  (is determined before dealing in Class CardsToDeal)

    Important Methods:
        setPoint():     check and set Point automatically according to Rank
*/


package Model.CardModel;

import Model.PlayerModel.Player;

public class Card {
    private Suit suit;         // PIK, KARO, HERZ, KREUZ
    private Rank rank;         // ZEHN, BUBE, DAME, KOENIG, ASS
    private String imageURL;
    private Player belongsToPlayer;
    private int strength;
    private int point;
    private boolean isFehl;
    private boolean isTrumpf;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isFehl() {
        return isFehl;
    }

    public void setFehl(boolean fehl) {
        isFehl = fehl;
    }

    public boolean isTrumpf() {
        return isTrumpf;
    }

    public void setTrumpf(boolean trumpf) {
        isTrumpf = trumpf;
    }

    public String getSuit() {
        return suit.toString();
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank.toString();
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Player getBelongsToPlayer() {
        return belongsToPlayer;
    }

    public void setBelongsToPlayer(Player belongsToPlayer) {
        this.belongsToPlayer = belongsToPlayer;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getPoint() {
        return point;
    }

    // Check and set Point according to Rank
    public void setPoint() {
        switch (this.rank){
            case ZEHN:
                this.point = 10;
                break;
            case BUBEN:
                this.point = 2;
                break;
            case DAMEN:
                this.point = 3;
                break;
            case KOENIG:
                this.point = 4;
                break;
            case ASS:
                this.point = 11;
                break;
        }
    }

    public String suitToUnicode(){
        String result = "";
        switch (getSuit()){
            case "PIK":
                result = "\u2660";
                break;
            case "KARO":
                result = "\u2663";
            break;
            case "HERZ":
                result = "\u2665";
            break;
            case "KREUZ":
                result = "\u2666";
            break;
        }   // end of switch

        return result;
    }

    public String rankToUnicode(){
        String result = "";
        switch (getRank()){
            case "ZEHN":
                result = "10";
                break;
            case "BUBEN":
                result = "J";
                break;
            case "DAMEN":
                result = "Q";
                break;
            case "KOENIG":
                result = "K";
                break;
            case "ASS":
                result = "A";
                break;
        }   // end of switch

        return result;
    }

    @Override
    public String toString() {
        return suitToUnicode() + rankToUnicode();
    }
}
