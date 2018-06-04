/**
 * The single Card to play in game
 *
 *     Attribute:
 *         Suit            = PIK | KARO | HERZ | KREUZ
 *         Rank            = ZEHN | BUBE | DAME | KOENIG | ASS
 *         POINT           = 10   |   2  |  3   |  4    |  11
 *         STRENGTH        see below, determined by setStrength()
 *         FEHL or TRUMPF  is determined in setFehlAndTrumpf()
 *         belongsToPlayer from what player does the card come
 *         isFehl          is Fehl
 *         isTrumpf        is Trumpf
 *
 *     Important Methods:
 *         setPoint():             check and set Point automatically according to Rank
 *         setFehlAndTrumpf():     set status FEHL | TRUMPF for each card
 *         setPoint():             Check and set Point according to Rank
 *         setStrength():          determine the strength of each card compared to others
 *         suitToUnicode():        Return UNICODE Symbol for SUIT
 *         rankToUnicode():        Return UNICODE Symbol for RANK
 *         getName():              getSuit() + " " + getRank()
 *
 *      These are the cards sorted by strength
 *      Each card has a code representing the strength "__"
 *      The first letter is the group order:
 *                                  1 2 3 4 5 6 7
 *                                  123 are FEHL
 *                                  4567 are TRUMPF
 *      The second letter is the order in group:
 *                                  A B C D
 *                                  A < B < C < D
 *
 *                     ♥10 TRUMPF 7 strong 10 points
 *                     ♥10 TRUMPF 7 strong 10 points
 *                     ♣Q TRUMPF 6D strong 3 points
 *                     ♣Q TRUMPF 6D strong 3 points
 *                     ♠Q TRUMPF 6C strong 3 points
 *                     ♠Q TRUMPF 6C strong 3 points
 *                     ♥Q TRUMPF 6B strong 3 points
 *                     ♥Q TRUMPF 6B strong 3 points
 *                     ♦Q TRUMPF 6A strong 3 points
 *                     ♦Q TRUMPF 6A strong 3 points
 *                     ♣J TRUMPF 5D strong 2 points
 *                     ♣J TRUMPF 5D strong 2 points
 *                     ♠J TRUMPF 5C strong 2 points
 *                     ♠J TRUMPF 5C strong 2 points
 *                     ♥J TRUMPF 5B strong 2 points
 *                     ♥J TRUMPF 5B strong 2 points
 *                     ♦J TRUMPF 5A strong 2 points
 *                     ♦J TRUMPF 5A strong 2 points
 *                     ♦A TRUMPF 4C strong 11 points
 *                     ♦A TRUMPF 4C strong 11 points
 *                     ♦10 TRUMPF 4B strong 10 points
 *                     ♦10 TRUMPF 4B strong 10 points
 *                     ♦K TRUMPF 4A strong 4 points
 *                     ♦K TRUMPF 4A strong 4 points
 *                     ♥A FEHL 3C strong 11 points
 *                     ♥A FEHL 3C strong 11 points
 *                     ♥K FEHL 3A strong 4 points
 *                     ♥K FEHL 3A strong 4 points
 *                     ♠A FEHL 2C strong 11 points
 *                     ♠A FEHL 2C strong 11 points
 *                     ♠10 FEHL 2B strong 10 points
 *                     ♠10 FEHL 2B strong 10 points
 *                     ♠K FEHL 2A strong 4 points
 *                     ♠K FEHL 2A strong 4 points
 *                     ♣A FEHL 1C strong 11 points
 *                     ♣A FEHL 1C strong 11 points
 *                     ♣10 FEHL 1B strong 10 points
 *                     ♣10 FEHL 1B strong 10 points
 *                     ♣K FEHL 1A strong 4 points
 *                     ♣K FEHL 1A strong 4 points
 */



package doppelkopf.Model.CardModel;

import doppelkopf.Model.PlayerModel.Player;

public class Card {
    private Suit suit;         // PIK, KARO, HERZ, KREUZ
    private Rank rank;         // ZEHN, BUBE, DAME, KOENIG, ASS
    private String imageURL;
    private Player belongsToPlayer;
    private String strength = "";
    private int point;
    private boolean isFehl;
    private boolean isTrumpf;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        setFehlAndTrumpf();
        setPoint();
        setStrength();
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

    /**
     * Set status FEHL | TRUMPF for each card
     */
    public void setFehlAndTrumpf(){
        switch (getSuit() + " " + getRank()){
            case "KREUZ ASS":
            case "KREUZ KOENIG":
            case "KREUZ ZEHN":
            case "PIK ASS":
            case "PIK KOENIG":
            case "PIK ZEHN":
            case "HERZ ASS":
            case "HERZ KOENIG":
                this.setFehl(true);
                this.setTrumpf(false);
                break;
            default:
                this.setTrumpf(true);
                this.setFehl(false);
        }   // end of switch
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

    public String getStrength() {
        return strength;
    }

    /**
     * Determine the strength of each card compared to others
     */
    public void setStrength() {
        if(isFehl){
            // Set Strength for FEHL
            switch (suit){
                case KREUZ:
                    setStrengthHelperAssZehnKoenig("1");
                    break;
                case PIK:
                    setStrengthHelperAssZehnKoenig("2");
                    break;
                case HERZ:
                    setStrengthHelperAssZehnKoenig("3");
                    break;
            }
        }else{
            // Set Strength for TRUMPF
            switch (rank){
                case DAMEN:
                    strength = "6";
                    setStrengthHelperDameBuben();
                    break;
                case BUBEN:
                    strength = "5";
                    setStrengthHelperDameBuben();
                    break;
                default:
                    if(suit == Suit.HERZ){
                        // the Heart 10
                        strength = "7";
                    }else{
                        // the Kreuz Ass, Zehn, Koenig
                        setStrengthHelperAssZehnKoenig("4");
                    }

            }
        }
    }

    /**
     * Help method for setStrength
     */
    public void setStrengthHelperDameBuben(){
        switch (suit){
            case KARO:
                strength += "A";
                break;
            case HERZ:
                strength += "B";
                break;
            case PIK:
                strength += "C";
                break;
            case KREUZ:
                strength += "D";
                break;
        }
    }

    /**
     * Help method for setStrength
     */
    public void setStrengthHelperAssZehnKoenig(String firstLetter){
        strength = firstLetter;
        switch (rank){
            case KOENIG:
                strength += "A";
                break;
            case ZEHN:
                strength += "B";
                break;
            case ASS:
                strength += "C";
                break;
        }
    }

    public int getPoint() {
        return point;
    }

    /**
     * Check and set Point according to Rank
     */

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

    /**
     * Return UNICODE Symbol for SUIT
     * @return
     */
    public String suitToUnicode(){
        String result = "";
        switch (getSuit()){
            case "PIK":
                result = "\u2660"; // ♠
                break;
            case "KARO":
                result = "\u2666"; // ♦
            break;
            case "HERZ":
                result = "\u2665";  // ♥
            break;
            case "KREUZ":
                result = "\u2663"; // ♣
            break;
        }   // end of switch

        return result;
    }

    /**
     * Return UNICODE Symbol for RANK
     * @return
     */
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

    public String getName() {
        return getSuit() + " " + getRank();
    }
}
