package zad1;

/*
 * Podklasa instrukcji, odpowiada za instrukcje Jedz.
 */
public class Jedz extends Instrukcja{
    public Jedz() {
        identyfikator = 'j';
    }

    @Override
    public void wykonajInstrukcje(Rob R) {
        R.Jedz();
    }
}
