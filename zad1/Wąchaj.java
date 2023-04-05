package zad1;

/*
 * Podklasa instrukcji, odpowiada za instrukcje Wąchaj.
 */
public class Wąchaj extends Instrukcja{
    public Wąchaj() {
        identyfikator = 'w';
    }

    @Override
    public void wykonajInstrukcje(Rob R) {
        R.wąchaj();
    }
}
