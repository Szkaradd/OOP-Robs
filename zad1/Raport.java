package zad1;

import java.util.ArrayList;

/*
 * Klasa odpowiadająca składaniu raportów.
 */
public class Raport {
    private ArrayList<Rob> żywe_roby;
    private Plansza plansza;

    public Raport(ArrayList<Rob> żywe_roby, Plansza plansza) {
        this.żywe_roby = żywe_roby;
        this.plansza = plansza;
    }

    public void raportuj() {
        System.out.println();
        if (żywe_roby.size() == 0) {
            System.out.println("Brak żywych robów.");
        }
        else {
            for (Rob r : żywe_roby) {
                System.out.println("Rob " + żywe_roby.indexOf(r) + ": " + r);
            }
        }
        System.out.println("Stan planszy: ");
        plansza.drukuj_plansze();
        System.out.println();
    }
}
