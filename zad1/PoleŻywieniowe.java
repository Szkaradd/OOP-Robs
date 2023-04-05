package zad1;

public class PoleŻywieniowe extends Pole{
    public PoleŻywieniowe(int ile_rośnie_jedzenie, int wiersz, int kolumna) {
        this.żywieniowe = true;
        this.czy_jest_jedzenie = true;
        this.ile_rośnie_jedzenie = ile_rośnie_jedzenie;
        this.wiersz = wiersz;
        this.kolumna = kolumna;
    }
}
