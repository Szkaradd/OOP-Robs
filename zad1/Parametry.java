package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parametry {
    private final int liczba_parametrów = 15;
    private int ile_tur;
    private int koszt_tury;//jednostek energii
    private int pocz_ile_robów;
    private int co_ile_wypisz;
    private int pocz_energia;
    private String pocz_progr_string;
    private String spis_instrukcji_string;
    private ArrayList<Instrukcja> pocz_progr;
    private ArrayList<Instrukcja> spis_instrukcji;
    private int ile_rośnie_jedzenie;//turach
    private int ile_daje_jedzenie;
    private int limit_powielenia;
    private double  pr_powielenia;
    private double ułamek_energii_rodzica;
    private double pr_usunięcia_instr;
    private double pr_dodania_instr;
    private double pr_zmiany_instr;

    //Gettery
    public double getPr_dodania_instr() {
        return pr_dodania_instr;
    }
    public double getPr_zmiany_instr() {
        return pr_zmiany_instr;
    }
    public double getPr_usunięcia_instr() {
        return pr_usunięcia_instr;
    }
    public String getPocz_progr_string() {
        return pocz_progr_string;
    }
    public double getPr_powielenia() {
        return pr_powielenia;
    }
    public ArrayList<Instrukcja> getSpis_instrukcji() {
        return spis_instrukcji;
    }
    public double getUłamek_energii_rodzica() {
        return ułamek_energii_rodzica;
    }
    public int getGetPocz_energia() {
        return pocz_energia;
    }
    public ArrayList<Instrukcja> getPocz_progr() {
        return pocz_progr;
    }
    public int getIle_tur() {
        return ile_tur;
    }
    public int getKoszt_tury() {
        return koszt_tury;
    }
    public int getPocz_ile_robów() {
        return pocz_ile_robów;
    }
    public int getCo_ile_wypisz() {
        return co_ile_wypisz;
    }
    public int getIle_rośnie_jedzenie() {
        return ile_rośnie_jedzenie;
    }
    public int getIle_daje_jedzenie() {
        return ile_daje_jedzenie;
    }
    public int getLimit_powielenia() {
        return limit_powielenia;
    }

    Parametry() throws FileNotFoundException {
        class NiepoprawnaNazwaParametru extends Exception{};
        class NiepoprawnaLiczbaParametrów extends Exception{};
        File file = new File("parametry.txt");
        Scanner sc = new Scanner(file);
        int licznik_parametrów = 0;
        while (sc.hasNext()) {
            String nazwa_parametru = sc.next();
            licznik_parametrów++;
            try {
                switch (nazwa_parametru) {
                    case "ile_tur":
                        this.ile_tur = sc.nextInt();
                        break;
                    case "ile_daje_jedzenie":
                        this.ile_daje_jedzenie = sc.nextInt();
                        break;
                    case "ile_rośnie_jedzenie":
                        this.ile_rośnie_jedzenie = sc.nextInt();
                        break;
                    case "koszt_tury":
                        this.koszt_tury = sc.nextInt();
                        break;
                    case "spis_instr":
                        this.spis_instrukcji_string = sc.next();
                        break;
                    case "pocz_ile_robów":
                        this.pocz_ile_robów = sc.nextInt();
                        break;
                    case "pocz_energia":
                        this.pocz_energia = sc.nextInt();
                        break;
                    case "pocz_progr":
                        this.pocz_progr_string = sc.next();
                        break;
                    case "pr_powielenia":
                        this.pr_powielenia = sc.nextDouble();
                        break;
                    case "ułamek_energii_rodzica":
                        this.ułamek_energii_rodzica = sc.nextDouble();
                        break;
                    case "pr_zmiany_instr":
                        this.pr_zmiany_instr = sc.nextDouble();
                        break;
                    case "pr_dodania_instr":
                        this.pr_dodania_instr = sc.nextDouble();
                        break;
                    case "pr_usunięcia_instr":
                        this.pr_usunięcia_instr = sc.nextDouble();
                        break;
                    case "limit_powielenia":
                        this.limit_powielenia = sc.nextInt();
                        break;
                    case "co_ile_wypisz":
                        this.co_ile_wypisz = sc.nextInt();
                        break;
                    default:
                        throw new NiepoprawnaNazwaParametru();
                }
            }
            catch(NiepoprawnaNazwaParametru np) {
                System.out.println("Niepoprawna nazwa parametru.");
                System.exit(1);
            }
            try{
                if (licznik_parametrów > liczba_parametrów) {
                    throw new NiepoprawnaLiczbaParametrów();
                }
            }
            catch (NiepoprawnaLiczbaParametrów np) {
                System.out.println("Niepoprawna liczba parametrów.");
                System.exit(1);
            }
        }
        sc.close();

        char[] pocz_progr_char_arr = pocz_progr_string.toCharArray();
        pocz_progr = new ArrayList<Instrukcja>();
        for (char c : pocz_progr_char_arr) {
            switch (c) {
                case 'l':
                    pocz_progr.add(new Lewo());
                    break;
                case 'p':
                    pocz_progr.add(new Prawo());
                    break;
                case 'w':
                    pocz_progr.add(new Wąchaj());
                    break;
                case 'i':
                    pocz_progr.add(new Idź());
                    break;
                case 'j':
                    pocz_progr.add(new Jedz());
                    break;
            }
        }

        char[] spis_instr_char_arr = spis_instrukcji_string.toCharArray();
        spis_instrukcji = new ArrayList<Instrukcja>();
        for (char c : spis_instr_char_arr) {
            switch (c) {
                case 'l':
                    spis_instrukcji.add(new Lewo());
                    break;
                case 'p':
                    spis_instrukcji.add(new Prawo());
                    break;
                case 'w':
                    spis_instrukcji.add(new Wąchaj());
                    break;
                case 'i':
                    spis_instrukcji.add(new Idź());
                    break;
                case 'j':
                    spis_instrukcji.add(new Jedz());
                    break;
            }
        }
    }
}

