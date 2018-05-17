//

package Model.CardModel;

import Model.PlayerModel.Player;

public class Card {
    private Suit suit; // PIK, KARO, HERZ, KREUZ
    private Rank rank; // ZEHN, BUBE, DAME, KOENIG, ASS
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

    public void setPoint() {
        switch (this.rank){
            case ZEHN:

                break;
            case BUBEN:
                break;
            case DAMEN:
                break;
            case KOENIG:
                break;
        }
    }

    @Override
    public String toString() {
        return getSuit() + " " + getRank();
    }
}
