package algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {

        this();
        Objects.requireNonNull(a, "Tabellen a er null!");

        /*
       if (a == null){
           throw new NullPointerException("Tabellen a er null!");
        }

         */
        hode = hale = new Node<>(null);

        for (T elementer : a){
            if (elementer != null){  // leter etter forste "ikke null" element og lager en node

                hale = hale.neste = new Node(elementer, hale, null);
                antall++;
            }
        }
        if(antall == 0){
            hode=hale=null;
        }
        else{
            (hode=hode.neste).forrige=null;
        }

    }

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    public Liste<T> subliste(int fra, int til){
        //throw new UnsupportedOperationException();

        fratilKontroll(antall, fra, til);

        Liste<T> liste = new DobbeltLenketListe<>();            //Opprette subliste
        //Skjekke at fra og til ikke er nullpeker.
        //Skjekke at fra ikke er større enn til og at til ikke er mindre enn fra
        //Sette antall ved å ta til minus fra.

        int lengde = til - fra;


        Node<T> node = finnNode(fra);

        while (lengde > 0) {
            liste.leggInn(node.verdi);
            node = node.neste;
            lengde--;
        }
        return liste;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return (hode == null && hale == null && antall == 0);
    }

    @Override
    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");

        if (tom()) {
            hale = hode = new Node<T>(verdi, null, null);  // Tilfelle 1: tom liste
        } else {
            hale = hale.neste = new Node<T>(verdi, hale, null);  // Tilfelle 2: ikke tom liste

        }

        endringer++;        // Oker endringer
        antall++;           // Oker antallet
        return true;

    }

    @Override
    // 1,2,3,4,5
    public void leggInn(int indeks, T verdi) {
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Indeks utenfor listen!");
        }
        if(verdi == null) {
            throw new NullPointerException("Ikke lov med null-verdier i listen");
        }
        if(antall == 0) {
            hale = hode = new Node<T>(verdi, null, null);
            antall++;
            endringer++;
            return;
        }
        Node<T> newNode = new Node<T>(verdi);
        Node<T> prev = finnNode(indeks-1);
        Node<T> next = finnNode(indeks);
        if(indeks == antall) {  // legge til paa slutten
            newNode.neste = null;
            newNode.forrige = prev;
            prev.neste = newNode;
            this.hale = newNode;
        } else if (indeks == 0) { // legge til paa starten
            newNode.forrige = null;
            newNode.neste = next;
            next.forrige = newNode;
            this.hode = newNode;
        } else{ // legge til mellom to noder
            prev.neste = newNode;
            newNode.neste = next;
            newNode.forrige = prev;
            next.forrige = newNode;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) == -1) {
            return false;
        }
        return true;
    }

    @Override
    public T hent(int indeks) {
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks,false);           //Skjekker indeksen
        Node<T> n = finnNode(indeks);
        return n.verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }
        Node current = this.hode;
        int index;

        for (int i = 0; i < this.antall; i++) {
            if(verdi.equals(current.verdi)) {
                return i;
            }
            current = current.neste;
        }
       return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        //throw new UnsupportedOperationException();
        indeksKontroll(indeks, false);          //Skjekker indeksen
        if(nyverdi == null){
            throw new NullPointerException();
        }

        Node<T> n = finnNode(indeks);                   //Finner og lagrer noden fra indeks
        T gammelVerdi = n.verdi;                        //Oppretter en ny verdi som lagrer nodens nåværende vedi
        n.verdi = nyverdi;                              //Endrer nodens verdi til til den nye verdi
        endringer++;
        return gammelVerdi;                             //Returnerer nodens gamle verdi
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }

        Node<T> p = hode;

        while (p != null) {   // hvis p ikke er lik null, leter etter verdien
            if (p.verdi.equals(verdi)) break; {
                p = p.neste;
            }
        }

        if (p == null){
            return false;   // verdien er ikke i lista
        }

        else if (antall == 1){  // finnes bare 1 node i lista
            hode = hale = null;
        }
        else if (p == hode){        // hvis den første skal fjernes
            hode = hode.neste;
            hode.forrige = null;
        }
        else if (p == hale){        // hvis den siste skal fjernes
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            p.forrige.neste = p.neste;      // fjernes en verdi mellom to noder
            p.neste.forrige = p.forrige;
        }
        antall--;  // en verdi mindre i lista
        endringer++;    // en ny endring i lista

        return true;  // fjerning vellykket

    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> node = hode;
        if (antall == 1) {  // hvis det bare er en node i lista
            hode = hale = null;
        }
        else if (indeks == 0){  // hvis den første skal fjernes fra lista
            hode = hode.neste;
            hode.forrige = null;
        }
        else if (indeks == antall -1){      // hvis den siste skal fjernes fra lista
            node = hale;
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            node = finnNode(indeks); // bruker hjelpemetoden finnNode() for å finne verdi mellom to noder
            node.forrige.neste = node.neste;
            node.neste.forrige = node.forrige;
        }

        T verdi = node.verdi;  // verdi skal returneres

        antall--;       // en verdi mindre i lista
        endringer++;    // en ny endring i lista


        return verdi;
    }

    @Override
    /**
     * replaces all values and pointers in the list to null,
     * documents the changes, sets the head and tail to null,
     * and then reduces the length to 0.
     *
     * 7.1 was chosen because of a very, very slightly shorter runtime.
     *
     * @author: Simen N. Renberg
     * @version: 1.0
     * @since 29/9/2020
     */
    public void nullstill() {
        //7.1

        Node<T> p = hode;
        while(p.neste != hale){
            Node<T> nesteNode = p.neste;
            p.forrige = null;
            p.verdi = null;
            p.neste = null;
            endringer++;
            p = nesteNode;
       }
        hale =  hode = null;
        endringer++;
        antall = 0;
        /*
        //7.2
        while (antall > 0){
            this.fjern(0);
        }
        */
    }

    @Override
    public String toString() {

        if (tom()) return "[]";     // Hvis listen er tom retuneres det []

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Node<T> node = hode;

        builder.append(node.verdi);
        node = node.neste;

        while (node != null) {      // Hvis node ikke er lik null, modifiserer StringBuilder streng-objektene

            builder.append(",").append(" ");
            builder.append(node.verdi);

            node = node.neste;        // Hopper fremover
        }

        builder.append("]");
        return builder.toString();
    }
// Utskrift: [] [A] [A, B] [] [A] [B, A]

    public String omvendtString() {

        if (tom()) return "[]";    // Hvis listen er tom retuneres det []

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Node<T> node = hale;

        builder.append(node.verdi);
        node = node.forrige;

        while (node != null) {      // Hvis node ikke er lik null, modifiserer StringBuilder streng-objektene

            builder.append(",").append(" ");
            builder.append(node.verdi);


            node = node.forrige;    // Hopper bakover
        }

        builder.append("]");
        return builder.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);  // p starter paa indeks
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            // sjekke om iteratorendringer er lik endringer
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("Endringer og iteratorendringer ikke like");
            }
            // sjekke om det er flere elementer igjen i listen
            if (!hasNext()) {
                throw new NoSuchElementException("Ikke flere elementer i listen");
            }
            fjernOK = true;
            T returVerdi = denne.verdi;
            denne = denne.neste;
            return returVerdi;
        }

        @Override
        public void remove() {
            if (!fjernOK) {
                throw new IllegalStateException();
            }
            if (endringer != iteratorendringer) {
                throw new ConcurrentModificationException();
            } else {
                fjernOK = false;
            }

            if (antall == 1) {
                hode = hale = null;
            } else if (antall > 0) {
                if (denne == null) {
                    hale = hale.forrige;
                    hale.neste = null;
                } else if (denne.forrige == hode) {
                    hode = hode.neste;
                    hode.forrige = null;
                } else {
                    denne.forrige.forrige.neste = denne;
                    denne.forrige = denne.forrige.forrige;
                }
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    }

    // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        for(T i : liste) {
            System.out.println(i);
        }
        int val = c.compare(liste.hent(0), liste.hent(1));
        System.out.println("sammenligning av "+ liste.hent(0)+ " og "+liste.hent(1)+" blir: "+val);


        // if val == 0, verdiene er like
        // if val == -x, liste[i] > liste[i+1]
        // if val == x, liste[i] < liste[i+1]

        // veldig innefektiv metode, men den funker
        // skal se mer paa den
        // tror det blir noe som O(n^8) idk
        // pekerne er ikke korekte, vet ikke om de må være det
        for (int i = 0; i < liste.antall()-1; i++) {
            if (c.compare(liste.hent(i), liste.hent(i+1)) > 0) {
                T p = liste.oppdater(i,liste.hent(i+1));
                liste.oppdater(i+1, p);
            }
            for (int j = liste.antall()-1; j>=1; j--) {
                if (c.compare(liste.hent(j), liste.hent(j - 1)) < 0) {
                    T p = liste.oppdater(j, liste.hent(j - 1));
                    liste.oppdater(j - 1, p);
                }
            }
        }


        System.out.println("----Etter loop------");
        for(T i : liste) {
            System.out.println(i);
        }
    }

    private static void byttPlass(Node a, Node b) {

    }


    private Node<T> finnNode(int indeks){

        Node<T> current;                    //Skal først initialisere en current node som.

        if(indeks < antall /2){             //Jeg tar en test på om indeksen er høyere enn eller mindre enn halvparten av listen. Dette er for effektivitet av letingen
            current = hode;
            for(int i = 0; i < indeks;i++){
                current = current.neste;
            }
        }else {                             //Dersom indeksen er på den andre halvdelen av listen starter vi letingen fra halen og skjekker bakover i listen
            current = hale;
            for(int i = antall-1; i > indeks;i--){
                current = current.forrige;
            }
        }                                   //Når if statement er ferdig har vi funnet posisjon av noden på indeks og lagret den noden som current.
        return current; //returnerer current node.
    }

} // class DobbeltLenketListe




