/**
 * Enum type for all Rank
 * is used for each Card
 */

package doppelkopf.Model.CardModel;

public enum Rank {
    ZEHN("ZEHN"),
    BUBEN("BUBEN"),
    DAMEN("DAMEN"),
    KOENIG("KOENIG"),
    ASS("ASS");

    private String rank;

    Rank(String rank){
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return String.format("%s", rank);
    }
}
