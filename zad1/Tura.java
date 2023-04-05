package zad1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tura {
    private int koszt_tury;
    private int nr_tury;
    private ArrayList<Rob> żywe_roby;
    private Mutacja mutacja;
    private double pr_powielenia;
    private int limit_powielania;
    private Plansza plansza;
    private int rozmiar_planszy_y;
    private int rozmiar_planszy_x;
    private int poczatkowa_liczba_robów;
    private int robów_przeżyło;
    private int pola_z_jedzeniem = 0;

    public Tura(int koszt_tury, ArrayList<Rob> żywe_roby, Mutacja mutacja,
                double pr_powielenia, int limit_powielania, Plansza plansza, int nr_tury) {
        this.nr_tury = nr_tury;
        this.koszt_tury = koszt_tury;
        this.żywe_roby = żywe_roby;
        this.mutacja = mutacja;
        this.pr_powielenia = pr_powielenia;
        this.limit_powielania = limit_powielania;
        this.plansza = plansza;
        rozmiar_planszy_x = plansza.getRozmiar_planszy_x();
        rozmiar_planszy_y = plansza.getRozmiar_planszy_y();
        poczatkowa_liczba_robów = żywe_roby.size();
    }

    /*
     * funkcja na początku tury regeneruje jedzenie na każdym polu żywieniowym,
     * na którym go obecnie nie ma.
     */
    private void regeneruj_jedzenie() {
        for (int i = 0; i < rozmiar_planszy_y; i++) {
            for (int j = 0; j < rozmiar_planszy_x; j++) {
                if (plansza.getPole(i,j).czyŻywieniowe() && !plansza.getPole(i, j).Czy_jest_jedzenie()) {
                    plansza.getPole(i, j).zwieksz_czas_regeneracji();
                }
            }
        }
    }

    /*
     * Funkcja po regeneracji jedzenia, pobiera od każdego z robów koszt_tury energii.
     * Poźniej wykonuje program wszystkich robów
     * (Jeżeli podczas tych czynności energia roba spadnie poniżej zera, rob umiera).
     * Tworzona jest lista robów, które przeżyły. Później roby, które mają co najmniej limit_powielania energii
     * próbują się powielić. Tworzona jest lista dzieci robów(których program mutuje). Na końcu żywe_roby ustawiana jest na połączenie
     * tych co przeżyły i ich dzieci.
     */
    public void wykonaj_turę() {
        regeneruj_jedzenie();
        for (Rob r : żywe_roby) {
            r.pobierz_energia_za_ture(koszt_tury);
        }
        for (Rob r : żywe_roby) {
            r.wykonaj_program();
        }

        ArrayList<Rob> roby_które_przeżyły_ture = new ArrayList<Rob>();
        for (Rob r : żywe_roby) {
            if (r.czy_rob_żyje()) {
                roby_które_przeżyły_ture.add(r);
            }
        }

        Random random = new Random();
        ArrayList<Rob> dzieci_robów = new ArrayList<Rob>();
        for (Rob r : roby_które_przeżyły_ture) {
            double pr_powiel = random.nextDouble();
            if (r.getZapas_energii() >= limit_powielania && pr_powiel <= pr_powielenia) {
                ArrayList<Instrukcja> kopia_programu_rodzica = new ArrayList<Instrukcja>(r.getProgram());
                ArrayList<Instrukcja> program_syna = mutacja.mutuj(kopia_programu_rodzica);
                dzieci_robów.add(r.powiel_się(program_syna));
            }
        }

        żywe_roby = roby_które_przeżyły_ture;
        for (Rob r : roby_które_przeżyły_ture) {
            r.zwieksz_wiek();
        }
        żywe_roby.addAll(dzieci_robów);
        robów_przeżyło = żywe_roby.size();
        Collections.shuffle(żywe_roby);

        //licznik pól na których jest jedzenie.
        for (int i = 0; i < rozmiar_planszy_y; i++) {
            for (int j = 0; j < rozmiar_planszy_x; j++) {
                if (plansza.getPole(i, j).Czy_jest_jedzenie()) {
                    pola_z_jedzeniem++;
                }
            }
        }

        plansza.setŻywe_roby(żywe_roby);
        plansza.setLiczba_pol_z_jedzeniem(pola_z_jedzeniem);
        System.out.println(wypisz_stan());
    }

    public String wypisz_stan() {
        double średnia_długość_prg;
        int suma_długości_prg = 0;
        int max_długość_prg = 0;
        int min_długość_prg = Integer.MAX_VALUE;

        double średnia_energia_roba;
        int max_energia_roba = 0;
        int min_energia_roba = Integer.MAX_VALUE;
        int suma_energi_robów = 0;

        double średni_wiek_roba;
        int max_wiek_roba = 0;
        int min_wiek_roba = Integer.MAX_VALUE;
        int suma_lat_robów = 0;

        //Ustawienie maksymalnych i średnich wartości sprawdzając po wszystkich robach.
        for (Rob r : żywe_roby) {
            if (r.daj_długość_programu() > max_długość_prg) {
                max_długość_prg = r.daj_długość_programu();
            }
            if (r.daj_długość_programu() < min_długość_prg) {
                min_długość_prg = r.daj_długość_programu();
            }
            suma_długości_prg += r.daj_długość_programu();

            if (r.getZapas_energii() > max_energia_roba) {
                max_energia_roba = r.getZapas_energii();
            }
            if (r.getZapas_energii() < min_energia_roba) {
                min_energia_roba = r.getZapas_energii();
            }
            suma_energi_robów += r.getZapas_energii();

            if (r.getWiek() > max_wiek_roba) {
                max_wiek_roba = r.getWiek();
            }
            if (r.getWiek() < min_wiek_roba) {
                min_wiek_roba = r.getWiek();
            }
            suma_lat_robów += r.getWiek();
        }
        //Jeśli nie ma robów.
        if (żywe_roby.size() == 0) {
            min_długość_prg = 0;
            min_energia_roba = 0;
            min_wiek_roba = 0;
            średni_wiek_roba = 0;
            średnia_długość_prg = 0;
            średnia_energia_roba = 0;
        }
        else {
            double liczba_robow = (double)żywe_roby.size();
            średnia_długość_prg = suma_długości_prg / liczba_robow;
            średnia_energia_roba = suma_energi_robów / liczba_robow;
            średni_wiek_roba = suma_lat_robów / liczba_robow;
        }

        String stan = "Tura: " + nr_tury + ", rob: " + robów_przeżyło + ", żyw: " + pola_z_jedzeniem +
                ", prg: " + min_długość_prg + "/" + String.format("%.2f", średnia_długość_prg) + "/" + max_długość_prg +
                ", energ: " + min_energia_roba + "/" + String.format("%.2f",średnia_energia_roba) + "/" + max_energia_roba
                + ", wiek: " + min_wiek_roba + "/" + String.format("%.2f",średni_wiek_roba) + "/" + max_wiek_roba;
        return stan;
    }

    public ArrayList<Rob> getŻywe_roby() {
        return żywe_roby;
    }
}
