# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* s333993 - Oskar Ruyter - s333993@oslomet.no
* s344170 - Marius Sørum - s344170@oslomet.no
* s331179 - Simen Nordgaard Renberg - s3331179@oslomet.no
* s344179 - Kunta Samuel Houen - s344179@oslomet.no

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
* Oppgave 3:lagde finnNode() metode som leter fra hode eller hale avhengig av indeks og returnerer noden som stoppes på når indeksen er funnet.
lagde hent() metode some bruker indekskontroll fra Liste interface. Bruker finnNode() til å opprette en node og returnere verdien av noden vi vil ha.
lagde oppdater(). Noden vi skal oppdatere finner vi med finnNode() fra tidligere. Vi lagrer verdien av noden i en hjelpenode, erstatter verdien med den nye og                    returnerer hjelpenoden som er den gamle verdien.
lagde subliste() metode som oppretter en kopi av listen fra en gitt start til en gitt slutt ved å bruke en while loop.
* Oppgave 4: Jeg lagde en for loop som sjekker hvert element i listen om det er likt som inn-parameteren. 
Dersom den finner den så returnerer den posisjonen til elementet. 
Dersom den ikke finner, returneres -1. 
* Oppgave 5: Lagde først sjekker på om verdien er null, eller om indeksen sendt inn i metoden er utenfor tillatt intervall. 
Dersom liste.antall == 0 så legger den bare inn elementet og setter hode og hale pekeren på den. 
Dersom indeks == antall så skal verdien legges til på slutten, hode pekeren endres ikke på, men hale pekeren endres. 
Omtrent samme måte med å legge til på starten. Passer på og inkrementere antall og endringer. 
* Oppgave 6: boolean fjern() ble løst ved å først loope med en while-løkke, dersom p ikke er null, leter den etter verdien
Deretter sjekker den alle tilfellene som oppgaven ber om og returner true om fjerningen er vellykket.
T fjern() sjekker alle tilfellene oppgaven ber om men har bruk metoden indeksKontroll() for å sjekke indeks.
Brukte også hjelpemetoden finnNode() for å fjerne verdi mellom to noder
* Oppgave 7: void nullstill() ble løst ved å erstatte alle verdier og pekere i listen mellom hode til hale med null ved å 
loope med en while-loop mens den nåværende noden sin neste ikke er halen. 
Når loopen ankommer halen settes hodet og halen til null, og lengden (antall) settes til 0.
* Oppgave 8: Denne oppgaven var veldig "straight forward" siden det var bare å følge oppgavebeskrivelsen. 
* Oppgave 9: Ble løst ved å først sjekke hindrene som må passeres for å bruke metoden, og disse sjekkes opp gjennom if-statements. 
Etter det blir det sjekket opp hvor i listen slettingen skal foregå. 
Dersom antallet == 1, så vil hele listen slettes (hode og hale), slettes siste element så oppdateres halen (hale = hale.forrige), og likedan for hode (hode = hode.neste). 
Dersom elementet er inne i listen så oppdateres pekerne slik at Java sin garbage collection tar seg av slettingen. 
Antall minskes til slutt.
* Oppgave 10: Oppgave 10 ble løst ved hjelp av to for loops, en Comparator<? Super T> c parameter og oppdater() metoden. 
For loopen looper gjennom alle elementene og sammenlikner element i med element i+1 ved hjelp av Comparator. 
Dersom de står i "feil" rekkefølge så bytter de plass ved hjelp av oppdater() metoden. Dette gjøres 2 ganger for hvert element. 
Trodde først det skulle gå med kun en for loop, men da var det alltid ett element som sto feil sortert. 
Dette ble fikset da jeg la inn en baklengs for loop inne i den vanlige for loopen.


# Obs
* OBS 1 : Oppgave 6 - Dersom man kjører hele test klassen hender det at Oppgave 6zg feiler. Tok dette opp med studie assistentene og de mente at det skulle stå som det var, og at det kan variere fra maskin til maskin.
* OBS 2 : De fleste warnings er fjernet utenom "Method will throw an exception when parameter is null". Oppgavebeskrivelsen ber oss om å kaste nullpointer-exception dersom parameteren er null, så denne warningen for bli værende.
