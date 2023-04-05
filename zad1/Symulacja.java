/*
 * Autor: Mikołaj Szkaradek
 * Program zaliczeniowy 1 z Programowania Obiektowego.
 * Program przeprowadza symulację ewolucji świata Robów przez określoną liczbę tur.
 */
package zad1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Symulacja {
    private Plansza plansza;
    private Parametry parametry;

    public Symulacja() throws FileNotFoundException {
        parametry = new Parametry();
        plansza = new Plansza(parametry.getIle_rośnie_jedzenie());
        RozmieśćRoby();
    }

    /*
     * Losuje pozycje na planszy dla kązdego roby, oraz tworzy nowe roby.
     */
    private void RozmieśćRoby() {
        Random x = new Random();
        Random y = new Random();
        int rozmiar_planszy_x = plansza.getRozmiar_planszy_x();
        int rozmiar_planszy_y = plansza.getRozmiar_planszy_y();
        ArrayList<Rob> żywe_roby = new ArrayList<Rob>();
        for (int i = 0; i < parametry.getPocz_ile_robów(); i++) {
            int współrzędna_x = x.nextInt(rozmiar_planszy_x);
            int współrzędna_y = y.nextInt(rozmiar_planszy_y);
            żywe_roby.add(new Rob(plansza.getPole(współrzędna_y,współrzędna_x),
                    parametry.getGetPocz_energia(), parametry.getPocz_progr(),
                    parametry.getIle_daje_jedzenie(), parametry.getUłamek_energii_rodzica()));
        }
        plansza.setŻywe_roby(żywe_roby);
    }

    private String wypisz_stan_poczatkowy() {
        String stan = "Na planszy jest " + parametry.getPocz_ile_robów() +
                " robów, każdy z nich ma " + parametry.getGetPocz_energia() + " jednostek energii."
                + " Pól żywieniowych jest: " + plansza.getLiczba_pol_z_jedzeniem() + "."
                + " Każdy rob jest wyposażony w program: " + parametry.getPocz_progr_string() + ".";
        return stan;
    }

    /*
     * Funkcja na początku i na końcu pokazuje raport o obecnym stanie planszy.
     * Tworzony jest jeden obiekt typu Mutacja, który odpowiada za mutacje robów.
     * Co co_ile_wypisz również wypisywany jest raport o obecnym stanie planszy.
     * Symulacja przeprowadza ile_tur tur.
     */
    private void symuluj_ewolucje() {
        System.out.println(parametry.getLimit_powielenia());
        Raport stan_poczatkowy = new Raport(plansza.getŻywe_roby(), plansza);
        System.out.println("Stan początkowy: ");
        System.out.println(wypisz_stan_poczatkowy());
        stan_poczatkowy.raportuj();
        Mutacja mutacja = new Mutacja(parametry.getPr_dodania_instr(),
                parametry.getPr_usunięcia_instr(),
                parametry.getPr_zmiany_instr(), parametry.getSpis_instrukcji());

        int licznik_wypisywania = 0;
        for (int i = 1; i <=     parametry.getIle_tur(); i++) {
            Tura tura = new Tura(parametry.getKoszt_tury(), plansza.getŻywe_roby(),
                    mutacja, parametry.getPr_powielenia(),
                    parametry.getLimit_powielenia(), plansza, i);
            tura.wykonaj_turę();
            licznik_wypisywania++;
            if (licznik_wypisywania == parametry.getCo_ile_wypisz()) {
                licznik_wypisywania = 0;
                Raport obecny_stan = new Raport(plansza.getŻywe_roby(), plansza);
                obecny_stan.raportuj();
            }
        }
        Raport stan_końcowy = new Raport(plansza.getŻywe_roby(), plansza);
        System.out.println("Stan końcowy: ");
        stan_końcowy.raportuj();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Symulacja ewolucja = new Symulacja();
        ewolucja.symuluj_ewolucje();
    }
}
