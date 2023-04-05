package zad1;

/*
 * Podklasa instrukcji, odpowiada za instrukcje Lewo.
 */
public class Lewo extends Instrukcja{
    Lewo() {
        identyfikator = 'l';
    }

    @Override
    public void wykonajInstrukcje(Rob R) {
        R.zmieńKierunek(false);
    }
}
