/**
 *
 *  Enum type for all Suit
 *  is used for each Card
 *
 */

package doppelkopf.Model.CardModel;

public enum Suit { // Farbe
    PIK("PIK"),
    KARO("KARO"),
    HERZ("HERZ"),
    KREUZ("KREUZ");

    private String text;

    /**
     * @param text
     */
    Suit(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return String.format("%s", text);
    }
}
