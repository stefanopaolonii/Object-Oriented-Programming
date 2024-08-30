# Fit

Sviluppare un sistema che permetta di gestire il sistema delle prenotazioni per la catena di palestre Fit.

Tutte le classi si trovano nel package <b>it.polito.oop.fit</b>. La classe principale
&eacute; <b>Fit</b>. La classe <b>TestApp</b> nel package example contiene un esempio di utilizzo della classe.
Le eccezioni sono lanciate mediante la classe <b>FitException</b>.

La <a href="https://oop.polito.it/api/index.html" target="api" target="_blank">JDK documentation</a>  
si trova sul server locale.


## R1: Palestre
La catena Fit ha diverse palestre, ognuna identificata dal suo nome.

Per aggiungere una palestra si usa il metodo   
<b>addGymn(String)</b>. Il metodo riceve come parametro il nome della palestra (es. MacFit Polito). Nel caso 
la palestra esista già il metodo deve generare un'eccezione.
E' possibile conoscere il numero di palestre registrate tramite il metodo <b>getNumGymns()</b>.

## R2: Lezioni

Ogni palestra organizza, settimana per settimana, le proprie attività giornaliere in lezioni dedicate a una specialità (es. aerobica).
Ogni lezione occupa uno slot di un'ora a partire dalle 8 del mattino fino alle 21 di sera 
(in totale ci sono 13 slot in un giorno).

E' possibile aggiungere una serie di lezioni al calendario settimanale di una palestra usando il  
metodo <b>addLessons(String , String , int , String , String ...)</b>. Il metodo riceve
come parametro il nome della palestra in cui è tenuta la lezione, la specialit&agrave; (es. Aerobica), il numero massimo
di partecipanti alle lezioni, una stringa che codifica l'orario delle lezioni 
(la codifica della stringa è la seguente: <i>"day1.slot1,day2.slot2, ..."</i>) e 
infine l'elenco dei nomi degli istruttori associati alle lezioni.<br>
I giorni sono indicati con numeri da 1 (Lunedì) a 7 (Domenica); 
gli slot sono codificati con numeri da 8 (orario 8:00 - 9:00) a 20 (orario 20:00 - 21:00).


Il metodo lancia un'eccezione nel caso in cui:
<ul>
<li>il nome della palestra sia errato </li>
<li>il giorno della settimana o l'orario non siano nel range indicato</li>
<li>non tutti gli slot chiesti siano liberi nella palestra indicata (alcuni possono essere già occupati).</li>
</ul>

## R3: Clienti 

Un nuovo cliente viene aggiunto usando il metodo<b> addCustomer(String )</b>. 
Il metodo riceve come parametro il nome del cliente rappresentato tramite una stringa e 
ritorna il codice cliente rappresentato da  un numero intero progressivo a partire da 1 compreso.

E' possibile trovare un cliente tramite il metodo <b>getCustomer(int)</b>. 
Il metodo riceve come parametro il codice cliente e ritorna il nome del 
cliente. Nel caso in cui il codice cliente non sia valido il metodo genera un'eccezione.


## R4: Prenotazioni 

I clienti possono iscriversi ad una lezione in una palestra tramite il metodo 
<b>placeReservation(int, String, int, int)</b>. 
I parametri sono il codice cliente, il nome della palestra, 
il giorno della settimana e lo slot in cui si tiene la lezione. 
La prenotazione va a buon fine se: 
<ul>
<li>il codice identificativo del cliente è valido</li>
<li>il nome della palestra è valido</li>
<li>il giorno e lo slot sono validi</li>
<li>ci sono ancora posti liberi nella lezione</li>
<li>il cliente non è già registrato alla lezione.</li>
</ul>

In tutti gli altri casi il metodo genera un'eccezione.

Il metodo <b>int getNumLessons(int)</b> restituisce il numero di prenotazioni di un cliente identificato 
tramite il suo codice cliente, considerando tutte le palestre 
(infatti un cliente potrebbe frequentare più palestre della catena).

## R5: Lezioni tenute 

Il metodo <b>addLessonGiven (String, int, int, String)</b> indica chi ha tenuto una data lezione. Il metodo
riceve come parametro il nome della palestra, il giorno e lo slot della lezione e il nome dell'istruttore.




Il metodo genera un'eccezione se:
<ul>
	<li>la palestra non esiste</li>
	<li>il giorno e lo slot non sono validi</li>
	<li>l'istruttore non è tra quelli associati alla lezione.</li>
</ul>

Il metodo <b>getNumLessonsGiven (String, String)</b> riceve come parametri il nome di una palestra e 
il nome di un istruttore e ritorna il numero di lezioni tenute dall'istruttore. 
Il metodo genera un'eccezione se la palestra non esiste.


## R6: Statistiche  


Il metodo <b>mostActiveGymn()</b> fornisce il nome della palestra con il maggior numero di lezioni.

Il metodo <b>totalLessonsPerGymn()</b> fornisce per ogni palestra il numero di lezioni.

Il metodo <b>TreeMap&lt;Integer, List&lt;String&gt;&gt; slotsPerNofParticipants(String)</b> fornisce, 
dato il nome di una palestra, gli slot della palestra raggruppati per numero crescente di partecipanti, 
in base ai prenotati alle lezioni.
Gli slot sono indicati con <i>giorno.slot</i>.

Esempio: <i>8 = [6.18], 10 = [1.8,3.10]</i>
indica che ci sono 8 partecipanti nello slot 18:00-19:00 del Sabato (giorno 6) e 
10 partecipanti negli slot 8:00-9:00 del Lunedì (giorno 1) e 10:00-11:00 del Mercoledì (giorno 3).