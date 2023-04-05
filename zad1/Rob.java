package zad1;

import java.util.ArrayList;
import java.util.Random;

public class Rob {
    private int wiek;
    private int zapas_energii;
    ArrayList<Instrukcja> program;
    private char kierunek; //w - góra a - lewo s - dół d - prawo.
    private Pole pole;
    private int ile_daje_jedzenie;
    private boolean czy_żyje;
    private double ułamek_energii_dla_syna;

    public Rob(Pole pole, int pocz_energia, ArrayList<Instrukcja> pocz_progr,
               int ile_daje_jedzenie, double ułamek_energii_dla_syna) {
        this.ułamek_energii_dla_syna = ułamek_energii_dla_syna;
        czy_żyje = true;
        this.pole = pole;
        //Początkowy kierunek jest losowany.
        Random r = new Random();
        int kierunek = r.nextInt(4);
        if (kierunek == 0) this.kierunek = 'w';
        else if (kierunek == 1)  this.kierunek = 's';
        else if (kierunek == 2) this.kierunek = 'a';
        else this.kierunek = 'd';
        this.wiek = 0;
        zapas_energii = pocz_energia;
        program = pocz_progr;
        this.ile_daje_jedzenie = ile_daje_jedzenie;
    }

    public boolean czy_wystarcza_energii_do_życia() {
        if (zapas_energii >= 0) return true;
        else return false;
    }

    public void pobierz_energia_za_ture(int koszt_tury) {
        if (zapas_energii - koszt_tury <= 0) {
            zapas_energii = 0;
            umrzyj();
        }
        else {
            zapas_energii -= koszt_tury;
        }
    }

    //Funkcja zmienia kierunek Roba o 90 stopni w lewo lub w prawo.
    public void zmieńKierunek(boolean prawo) {
        if (prawo) {
            if (kierunek == 'w') kierunek = 'd';
            else if (kierunek == 'a') kierunek = 'w';
            else if (kierunek == 's') kierunek = 'a';
            else kierunek = 's';
        }
        else {
            if (kierunek == 'w') kierunek = 'a';
            else if (kierunek == 'a') kierunek = 's';
            else if (kierunek == 's') kierunek = 'd';
            else kierunek = 'w';
        }
    }

    private void Zjedz() {
        zapas_energii += ile_daje_jedzenie;
        pole.zacznij_regeneracje();
        pole.setCzy_jest_jedzenie(false);
    }

    public void idźIZjedz() {
        if (kierunek == 'w') {
                pole = pole.getGórny_sąsiad();
        }
        else if (kierunek == 'a') {
            pole = pole.getLewy_sąsiad();
        }
        else if (kierunek == 's') {
            pole = pole.getDolny_sąsiad();
        }
        else {
            pole = pole.getPrawy_sąsiad();
        }
        if (pole.czyŻywieniowe()) {
            if (pole.Czy_jest_jedzenie()) {
                Zjedz();
            }
        }
    }

    //Funkcja ustawia kierunek roba na kierunek pierwszego znalezionego
    // sąsiedniego pola z jedzeniem.
    public void wąchaj() {
        if (pole.getGórny_sąsiad().Czy_jest_jedzenie()) {
            kierunek = 'w';
        }
        else if (pole.getDolny_sąsiad().Czy_jest_jedzenie()) {
            kierunek = 's';
        }
        else if (pole.getLewy_sąsiad().Czy_jest_jedzenie()) {
            kierunek = 'a';
        }
        else {
            if (pole.getPrawy_sąsiad().Czy_jest_jedzenie()) {
                kierunek = 'd';
            }
        }
    }

    public void Jedz() {
        Pole[] sąsiednie_pola = pole.getSąsiednie_pola();
        for (Pole p : sąsiednie_pola) {
            if(p.Czy_jest_jedzenie()) {
                pole = p;
                Zjedz();
                break;
            }
        }
    }

    public void umrzyj() {
        czy_żyje = false;
    }

    public boolean czy_rob_żyje() {
        if (czy_żyje) return true;
        else return false;
    }

    public void wykonaj_program() {
        if (czy_żyje) {
            for (Instrukcja i : program) {
                if(zapas_energii > 0) {
                    i.wykonajInstrukcje(this);
                    this.zapas_energii -= i.getKoszt();
                }
                else {
                    umrzyj();
                    break;
                }
            }
        }
    }

    //Zwraca syna roba.
    public Rob powiel_się(ArrayList<Instrukcja> program_syna) {
        int energia_syna = (int)Math.floor(ułamek_energii_dla_syna * (double)zapas_energii);
        zapas_energii -= energia_syna;
        Rob syn = new Rob(pole, energia_syna, program_syna, ile_daje_jedzenie, ułamek_energii_dla_syna);
        switch (kierunek) {
            case 'w':
                syn.kierunek = 's';
                break;
            case 's':
                syn.kierunek = 'w';
                break;
            case 'a':
                syn.kierunek = 'd';
                break;
            case 'd':
                syn.kierunek = 'a';
                break;
        }
        return syn;
    }

    public ArrayList<Instrukcja> getProgram() {
        return program;
    }

    public int getZapas_energii() {
        return zapas_energii;
    }

    public void zwieksz_wiek() {
        wiek++;
    }

    public int daj_długość_programu() {
        return program.size();
    }

    public int getWiek() {
        return wiek;
    }

    public String toString() {
        String rob = "Energia: " + zapas_energii + ", wiek: " + wiek;
        if (program.size() == 0) {
            rob += ", program roba jest pusty.";
        }
        else {
            rob += ", program: ";
            for (Instrukcja i : program) {
                rob += i.getIdentyfikator();
            }
            rob += ",";
        }
        rob += " pole: " + pole;
        return rob;
    }
}
