package zad1;

public class PolePuste extends Pole{
    PolePuste(int wiersz, int kolumna) {
        this.czy_jest_jedzenie = false;
        this.żywieniowe = false;
        this.wiersz = wiersz;
        this.kolumna = kolumna;
    }
}
