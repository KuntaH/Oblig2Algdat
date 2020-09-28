import algdat.DobbeltLenketListe;
import algdat.Liste;

public class Main {
    public static void main(String[] args) {


        // Oppgave 1
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());    // Utskrift: 0 true

        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> listeK = new DobbeltLenketListe<>(s);
        System.out.println(listeK.antall() + " " + listeK.tom());  // Utskrift: 3 false
    }
}
