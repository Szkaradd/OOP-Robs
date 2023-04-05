package zad1;

/*
 * Podklasa instrukcji, odpowiada za instrukcje Prawo.
 */
public class Prawo extends Instrukcja{
    public Prawo() {
        identyfikator = 'p';
    }

    @Override
    public void wykonajInstrukcje(Rob R) {
        R.zmieńKierunek(true);
    }
}
