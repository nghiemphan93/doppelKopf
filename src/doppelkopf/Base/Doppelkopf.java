package doppelkopf.Base;

/**
 * Doppelkopf Base Template Pattern
 *
 *
 */
public abstract class Doppelkopf {
    public abstract void initGame();
    public abstract void startGame();
    public abstract void resetGame();
    public abstract void endGame();

    public final void play(){
        initGame();
        startGame();
        resetGame();
        endGame();
    }
}
