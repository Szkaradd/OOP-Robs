package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Plansza {
    private int rozmiar_planszy_x;
    private int rozmiar_planszy_y;
    private int ile_rośnie_jedzenie;
    private int liczba_pol_z_jedzeniem;
    ArrayList<Rob> żywe_roby;
    Pole[][] pola;

    public Plansza(int ile_rośnie_jedzenie) throws FileNotFoundException{
        class BłędnyZnak extends Exception {
            private final char c;
            public BłędnyZnak(char c){
                this.c = c;
            }
            public char dajZnak(){
                return c;
            }
        }
        class PustaPlansza extends Exception {};
        class RóżnaDługośćWierszy extends Exception {};

        this.ile_rośnie_jedzenie = ile_rośnie_jedzenie;

        File file = new File("plansza.txt");
        Scanner s = new Scanner(file);
        Scanner counter = new Scanner(file);

        int x = 0;
        int y = 0;
        if (counter.hasNextLine()) {
            x = counter.nextLine().length();
            y++;
        }
        try {
            while(counter.hasNextLine()) {
                y++;
                if (counter.nextLine().length() != x) {
                    throw new RóżnaDługośćWierszy();
                }
            }
        }
        catch (RóżnaDługośćWierszy r) {
            System.out.println("Różna długość wierszy.");
            System.exit(1);
        }
        rozmiar_planszy_x = x;
        rozmiar_planszy_y = y;
        try {
            if (x == 0 || y == 0) {
                throw new PustaPlansza();
            }
        }
        catch (PustaPlansza p) {
            System.out.println("Plansza jest pusta.");
            System.exit(1);
        }
        counter.close();

        pola = new Pole[y][x];
        try {
            for (int i = 0; i < y; i++) {
                String line = s.nextLine();
                char[] chars = line.toCharArray();
                for (int j = 0; j < x; j++) {
                    if (chars[j] == 'x') {
                        pola[i][j] = new PoleŻywieniowe(ile_rośnie_jedzenie, i, j);
                        liczba_pol_z_jedzeniem++;
                    } else if (chars[j] == ' ') {
                        pola[i][j] = new PolePuste(i, j);
                    } else {
                        throw new BłędnyZnak(chars[j]);
                    }
                }
            }
        }
        catch (BłędnyZnak e) {
            System.out.println("Błędny znak " + e.dajZnak() + ".");
            System.exit(1);
        }
        s.close();

        /* Ustawiam sąsiadów pól dla każdego pola na planszy.
         * Jeżeli pole ma jeden z indeksów = 0 to jego sasiadem jest ostatnie pole z wiersza/kolumny.
         * Jeżeli ustawiam sąsiada, ktory jest polem z dołu lub prawej strony pola to index jest reszta z dzielenia
         * index + 1 % rozmiar_planszy_index.
         */
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if ((j - 1) >= 0 && (i - 1) >= 0) {
                    pola[i][j].setSąsiedzi(pola[i][j - 1], pola[i][(j + 1) % x], pola[i - 1][j], pola[(i + 1) % y][j],
                            pola[i - 1][j - 1], pola[(i + 1) % y][j - 1], pola[i - 1][(j + 1) % x], pola[(i + 1) % y][(j + 1) % x]);
                }
                else if ((j - 1) == -1 && (i - 1) >= 0) {
                    pola[i][j].setSąsiedzi(pola[i][x - 1], pola[i][(j + 1) % x], pola[i - 1][j], pola[(i + 1) % y][j],
                            pola[i - 1][x - 1], pola[(i + 1) % y][x - 1], pola[i - 1][(j + 1) % x], pola[(i + 1) % y][(j + 1) % x]);
                }
                else if ((j - 1) >= 0) {
                    pola[i][j].setSąsiedzi(pola[i][j - 1], pola[i][(j + 1) % x], pola[y - 1][j], pola[(i + 1) % y][j],
                            pola[y - 1][j - 1], pola[(i + 1) % y][j - 1], pola[y - 1][(j + 1) % x], pola[(i + 1) % y][(j + 1) % x]);
                }
                else {
                    pola[i][j].setSąsiedzi(pola[i][x - 1], pola[i][(j + 1) % x], pola[y - 1][j], pola[(i + 1) % y][j],
                            pola[y - 1][x - 1], pola[(i + 1) % y][x - 1], pola[y - 1][(j + 1) % x], pola[(i + 1) % y][(j + 1) % x]);
                }
            }
        }
    }

    public int getLiczba_pol_z_jedzeniem() {
        return liczba_pol_z_jedzeniem;
    }

    public void setLiczba_pol_z_jedzeniem(int liczba_pol_z_jedzeniem) {
        this.liczba_pol_z_jedzeniem = liczba_pol_z_jedzeniem;
    }

    public int getRozmiar_planszy_x() {
        return rozmiar_planszy_x;
    }

    public int getRozmiar_planszy_y() {
        return rozmiar_planszy_y;
    }

    public void setŻywe_roby(ArrayList<Rob> żywe_roby) {
        this.żywe_roby = żywe_roby;
    }

    public ArrayList<Rob> getŻywe_roby() {
        return żywe_roby;
    }

    public Pole getPole(int współrzedna_y, int współrzedna_x) {
        return pola[współrzedna_y][współrzedna_x];
    }

    /*
     * drukuje plansze, pola z jedzeniem oznaczone jako '+',
     * pola żywieniowe bez jedzenie jako '-', a pola puste jako ' '
     */
    public void drukuj_plansze() {
        for (int i = 0; i < rozmiar_planszy_y; i++) {
            for (int j = 0; j < rozmiar_planszy_x; j++) {
                if (pola[i][j].czyŻywieniowe()) {
                    if (pola[i][j].Czy_jest_jedzenie()) {
                        System.out.print('+');
                    }
                    else System.out.print('-');
                }
                else System.out.print(' ');
            }
            System.out.print('\n');
        }
    }
}
