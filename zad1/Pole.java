package zad1;

public abstract class Pole {
    protected boolean żywieniowe;
    protected boolean czy_jest_jedzenie;
    protected Pole[] sąsiednie_pola;
    protected Pole lewy_sąsiad;
    protected Pole prawy_sąsiad;
    protected Pole górny_sąsiad;
    protected Pole dolny_sąsiad;
    protected int czas_regeneracji;
    protected int ile_rośnie_jedzenie;
    protected int wiersz;
    protected int kolumna;

    public void setSąsiedzi(Pole lewy, Pole prawy, Pole górny, Pole dolny, Pole na_ukos_lewy_górny,
                     Pole na_ukos_lewy_dolny, Pole na_ukos_prawy_górny, Pole na_ukos_prawy_dolny) {
        lewy_sąsiad = lewy;
        prawy_sąsiad = prawy;
        górny_sąsiad = górny;
        dolny_sąsiad = dolny;
        sąsiednie_pola = new Pole[8];
        sąsiednie_pola[0] = lewy_sąsiad;
        sąsiednie_pola[1] = prawy_sąsiad;
        sąsiednie_pola[2] = górny_sąsiad;
        sąsiednie_pola[3] = dolny_sąsiad;
        sąsiednie_pola[4] = na_ukos_lewy_górny;
        sąsiednie_pola[5] = na_ukos_lewy_dolny;
        sąsiednie_pola[6] = na_ukos_prawy_górny;
        sąsiednie_pola[7] = na_ukos_prawy_dolny;
    }

    public Pole[] getSąsiednie_pola() {
        return sąsiednie_pola;
    }

    public Pole getGórny_sąsiad() {
        return górny_sąsiad;
    }

    public Pole getDolny_sąsiad() {
        return dolny_sąsiad;
    }

    public Pole getLewy_sąsiad() {
        return lewy_sąsiad;
    }

    public Pole getPrawy_sąsiad() {
        return prawy_sąsiad;
    }

    public boolean Czy_jest_jedzenie() {
        return czy_jest_jedzenie;
    }

    public boolean czyŻywieniowe() {
        return żywieniowe;
    }

    public void setCzy_jest_jedzenie(boolean czy_jest_jedzenie) {
        this.czy_jest_jedzenie = czy_jest_jedzenie;
    }

    public void zacznij_regeneracje() {
        czas_regeneracji = 0;
    }

    public void zwieksz_czas_regeneracji() {
        czas_regeneracji++;
        if (czas_regeneracji == ile_rośnie_jedzenie) {
            czy_jest_jedzenie = true;
        }
    }

    public String toString() {
        String res = "(" + wiersz + ", " + kolumna + ") - ";
        if (żywieniowe) {
            res += "pole żywieniowe.";
        }
        else {
            res += "pole puste.";
        }
        return res;
    }

}
