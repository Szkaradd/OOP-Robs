package zad1;

import java.util.ArrayList;
import java.util.Random;

public class Mutacja {
    private double pr_usunięcia_instr;
    private double pr_dodania_instr;
    private double pr_zmiany_instr;
    private ArrayList<Instrukcja> spis_instrukcji;

    public Mutacja(double pr_dodania_instr, double pr_usunięcia_instr,
                   double pr_zmiany_instr, ArrayList<Instrukcja> spis_instrukcji) {
        this.pr_dodania_instr = pr_dodania_instr;
        this.pr_usunięcia_instr = pr_usunięcia_instr;
        this.pr_zmiany_instr = pr_zmiany_instr;
        this.spis_instrukcji = spis_instrukcji;
    }

    /*
     * Funkcja dostaje kopie programu rodzica, tworzy nowy program,
     * który będzie programem dla syna Roba. Losuje czy zajdzie któraś mutacja.
     */
    public ArrayList<Instrukcja> mutuj(ArrayList<Instrukcja> program) {
        ArrayList<Instrukcja> program_syna = program;
        Random r = new Random();
        double pr_usuniecia = r.nextDouble();
        double pr_dodania = r.nextDouble();
        double pr_zmiany = r.nextDouble();

        if (pr_usuniecia <= pr_usunięcia_instr) {
            if (program_syna.size() >= 1) program_syna.remove(program_syna.size() - 1);
        }
        if (pr_dodania <= pr_dodania_instr) {
            int nr_instrukcji = r.nextInt(spis_instrukcji.size());
            program_syna.add(spis_instrukcji.get(nr_instrukcji));

        }
        if (pr_zmiany <= pr_zmiany_instr) {
            if (program_syna.size() >= 1) {
                int nr_instrukcji = r.nextInt(spis_instrukcji.size());
                int nr_zmiany = r.nextInt(program_syna.size());
                program_syna.set(nr_zmiany, spis_instrukcji.get(nr_instrukcji));
            }
        }

        return program_syna;
    }

}
