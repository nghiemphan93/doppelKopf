package Model.CardModel;

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

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return String.format("%s", text);
    }
}
