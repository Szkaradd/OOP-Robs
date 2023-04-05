package zad1;

/*
 * Klasa abstrakcyjna odpowiedzialna za instrukcje wykonywane przez roby.
 */
public abstract class Instrukcja {
    private final int koszt = 1;//jednostek energii roba
    protected char identyfikator;

    public int getKoszt() {
        return koszt;
    }

    public char getIdentyfikator() {
        return identyfikator;
    }

    public abstract void wykonajInstrukcje(Rob R);
}
