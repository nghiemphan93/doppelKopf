package doppelkopf.Base;

/**
 * Doppelkopf Strategy Template Pattern
 * Now we can say what
 *
 * https://www.tutorialspoint.com/design_pattern/template_pattern.htm
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
