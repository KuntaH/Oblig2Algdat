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
            if (elementer != null){  // leter etter første "ikke null" element og lager en node

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


    public Liste<T> subliste(int fra, int til){
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    // 1,2,3,4,5
    public void leggInn(int indeks, T verdi) {
        if (indeks < 0 || indeks > antall) {
            throw new IllegalArgumentException("Indeks utenfor listen");
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
        this.antall++;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) {
            return -1;
        }
        Node current = this.hode;
        int index;

        for (int i = 0; i < this.antall; i++) {
            if(verdi == current.verdi) {
                return i;
            }
            current = current.neste;
        }
       return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
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


