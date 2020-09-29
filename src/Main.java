import algdat.DobbeltLenketListe;
import algdat.Liste;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        // Oppgave 1
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());    // Utskrift: 0 true

        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> listeK = new DobbeltLenketListe<>(s);
        System.out.println(listeK.antall() + " " + listeK.tom());  // Utskrift: 3 false

        // Oppgave 2
        String[] s1 = {}, s2 = {"A"}, s3 = {null,"A",null,"B",null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);

        System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " "
                + l2.omvendtString() + " " + l3.omvendtString());


        DobbeltLenketListe<Integer> listeLeginn = new DobbeltLenketListe<>();
        System.out.println(listeLeginn.toString() + " " + listeLeginn.omvendtString());
        for (int i = 1; i <= 3; i++) {
            listeLeginn.leggInn(i);
            System.out.println(listeLeginn.toString() + " " + listeLeginn.omvendtString()); }


        // Oppgave 4
        DobbeltLenketListe<Integer> test = new DobbeltLenketListe<>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(test.indeksTil(3));
        System.out.println(test.inneholder(6));

        test.leggInn(0, 6);
        System.out.println(test.indeksTil(6));
        System.out.println(test.inneholder(6));

        System.out.println("-----------------");



        //  Oppgave 6
        Liste<String>  liste5 = new DobbeltLenketListe<>(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        liste5.fjern("G");
        System.out.println(liste5);

    }
}
