package zad1;

/*
 * Podklasa instrukcji, odpowiada za instrukcje Idź.
 */
public class Idź extends Instrukcja{
    Idź() {
        identyfikator = 'i';
    }

    @Override
    public void wykonajInstrukcje(Rob R) {
        R.idźIZjedz();
    }
}
