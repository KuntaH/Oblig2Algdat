# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Navn Navnesen, S981737, s981737@oslomet.no
* ...

Vi har brukt git til å dokumentere arbeidet vårt. Vi har 16 commits totalt, og hver logg-melding beskriver det vi har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Marius har hatt hovedansvar for oppgave 1, 2, og 6. 
* Oskar har hatt hovedansvar for oppgave 4, 5, 8, og 10. 
* Simen har hatt hovedansvar for oppgave 7 og 9. 
* Kunta har hatt hovedansvar for oppgave 3.

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: boolean tom() ble løst ved å retunerne hode, hale og antall til null.
int antall() ble løst ved å returnere antall.
Konstruktøren ble løst ved å først kaste en NullPointerException
Deretter loope igjennom for å finne første null element og lage en node
* Oppgave 2: toString() og omvendtString() metoden ble løst ved hjelp av StringBuilder
Starter på hode i toString() og på hale i omvendtString()
Looper igjennom med en while-løkke og hvis node ikke er null, modifiseres de. 
De modifiseres og hopper til neste i toString() og til forrige i omvendtString()
* Oppgave 3:
* Oppgave 4:
* Oppgave 5:
* Oppgave 6:
* Oppgave 7: void nullstill() ble løst ved å erstatte alle verdier og pekere i listen mellom hode til hale med null ved å loope med en while-loop mens den nåværende noden sin neste ikke er halen. Når loopen ankommer halen settes hodet og halen til null, og lengden (antall) settes til 0.
* Oppgave 8:
* Oppgave 9: det en del hindrer som først må passeres, og disse sjekkes opp gjennom if-statements. Etter det blir det sjekket opp hvor i listen slettingen skal foregå. Dersom antallet == 1, så vil hele listen slettes (hode og hale), slettes siste element så oppdateres halen (hale = hale.forrige), og likedan for hode (hode = hode.neste). Dersom elementet er inne i listen så oppdateres pekerne slik at Java sin garbage collection tar seg av slettingen. Antall minskes til slutt.
* Oppgave 10:

# Ekstra

* De fleste warnings er fjernet."Method will throw an exception when parameter is null" er eneste warningen som er igjen. I oppgaveteksten blir det bedt om at null-pointer exception skal kastes dersom parameteren er null. 
