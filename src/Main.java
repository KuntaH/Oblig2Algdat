import algdat.DobbeltLenketListe;
import algdat.Liste;

import java.util.Arrays;
import java.util.Comparator;

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

        //Oppgave 3
        Character[] c = { 'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'I' , 'J' ,};
        DobbeltLenketListe<Character> l = new DobbeltLenketListe<>(c);
        System. out .println(l.subliste(3,8)); // [D, E, F, G, H]
        System. out .println(l.subliste(5,5)); // []
        System. out .println(l.subliste(8,l.antall())); // [I, J]
        // System.out.println(liste.subliste(0,11)); // skal kaste unntak


        // Oppgave 4
        DobbeltLenketListe<Integer> test = new DobbeltLenketListe<>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(test.indeksTil(3));
        System.out.println(test.inneholder(6));

        for (int i : test) System.out.println(i);
        // Oppgave 5
        test.leggInn(0, 6);
        DobbeltLenketListe<Integer> test2 = new DobbeltLenketListe<>();
        test2.leggInn(0, 4);

        System.out.println("-----------------");



        //  Oppgave 6
        Liste<String>  liste6 = new DobbeltLenketListe<>(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        liste6.fjern("B");
        System.out.println(liste6);

        // Oppgave 7
        System.out.println("------7------");
        DobbeltLenketListe<Integer> test7 = new DobbeltLenketListe<>(new Integer[]{1,2,3,4,5});
        test7.nullstill();

        // oppgave 8
        String[] navn = {"Lars","Anders","Bodil","Kari","Per","Berit"};
        Liste<String> listee = new DobbeltLenketListe<>(navn);
        listee.forEach(se -> System.out.print(se + " "));
        System.out.println();
        for (String se : listee) System.out.print(se + " ");

        // oppgave 9
        System.out.println();
        DobbeltLenketListe<String> liste9 = new DobbeltLenketListe<>(new String[]{"Birger","Lars","Anders","Bodil","Kari","Per","Berit"});
        liste9.fjernHvis(n -> n.charAt(0) == 'B');
        System.out.println("oppg 9 test 1: "+ liste9 + " " + liste9.omvendtString());

        liste9.fjernHvis(n -> n.toLowerCase().contains("e"));
        System.out.println("oppg 9 test 2: "+ liste9 + " " + liste9.omvendtString());


        // Oppgave 10 - oskar sitt forsøk
        System.out.println("\n----------OPPGAVE 10--------");
        String[] names = {"Lars", "Anders", "Bodil", "Kari", "Per", "Berit"};
        Liste<String> liste10 = new DobbeltLenketListe<>(names);
        DobbeltLenketListe.sorter(liste10, Comparator.naturalOrder());
        Integer[] p = {7, 9, 3, 4, 2, 12, 43, 17};
        Liste<Integer> n = new DobbeltLenketListe<>(p);
        DobbeltLenketListe.sorter(n, Comparator.naturalOrder());
        Character[] x = {'P', 'Q' , 'A', 'C', 'B', 'E', 'D'};
        Liste<Character> q = new DobbeltLenketListe<>(x);
        DobbeltLenketListe.sorter(q, Comparator.naturalOrder());
        q.forEach(i -> System.out.println(i));
        n.forEach(i -> System.out.println(i));
        liste10.forEach(i-> System.out.println(i));
        System.out.println(Arrays.toString(names));
    }
}
