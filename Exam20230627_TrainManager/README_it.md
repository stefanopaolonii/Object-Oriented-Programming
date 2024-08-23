Gestione del Treno
==================

Scrivere un programma per supportare la prenotazione e l'esecuzione di controlli medici in un centro medico.
Le classi si trovano nel pacchetto `it.ffss.train`; la classe principale è `TrainManager`. 
La classe `TestApp` nel pacchetto `example` mostra esempi di utilizzo dei metodi principali ed esempi dei controlli richiesti.
SI ricorda di implementare solo i controlli richiesti.
Le eccezioni nei metodi descritti di seguito sono di tipo `TrainException`.

La [documentazione JDK](https://oop.polito.it/api/) si trova sul server locale.

La versione Inglese di questi requisiti è disponibile in [README.md](README.md).

R1: Classi e vagoni
--------------------

La classe `TrainManager` si occupa della gestione delle classi di viaggio e dei vagoni del treno. Fornisce diversi metodi per aggiungere, recuperare e manipolare informazioni sulle classi e sui vagoni.

Il metodo `addClasses()` consente di aggiungere un insieme di classi di viaggio all'elenco delle classi offerte sul treno. Accetta più nomi di classe come parametri e si assicura che i duplicati vengano ignorati.

Per recuperare l'elenco delle classi definite per il treno, è possibile utilizzare il metodo `getClasses()`. Restituisce una collezione di nomi di classe.

L'aggiunta di un nuovo vagone al treno viene facilitata dal metodo `addCar()`. Richiede un ID univoco, il numero di righe nel vagone, l'ultima lettera del posto e la classe del vagone. In caso di aggiunta riuscita, restituisce il numero di posti disponibili nel vagone del treno. In caso di ID duplicati o classi non esistenti, viene generata un'eccezione `TrainException`.

Per ottenere un elenco di vagoni appartenenti a una specifica classe, è disponibile il metodo `getCarsByClass()`. Prende come parametro la classe richiesta e restituisce una collezione di ID di vagoni.

Per determinare il numero di posti in un particolare vagone, è possibile utilizzare il metodo `getNumSeats()`. Richiede l'ID del vagone e restituisce il numero totale di posti in quel vagone.


R2: Fermate e posti
-------------------

La classe `TrainManager` include anche metodi relativi alla definizione delle fermate e alla ricerca dei posti disponibili in un viaggio in treno.

Il metodo `defineStops()` consente di definire le stazioni di fermata per un treno. Prende i nomi delle fermate come parametri e assegna un indice univoco a ciascuna fermata. Il metodo restituisce il numero di segmenti definiti, che è pari al numero di fermate meno uno.

Per recuperare i posti disponibili in un determinato viaggio dalla stazione di origine alla destinazione, viene utilizzato il metodo `findSeats()`. Richiede la stazione di partenza, la stazione di arrivo e la classe del vagone come parametri. Il metodo restituisce una mappa che rappresenta i posti disponibili per vagone. Ogni voce nella mappa corrisponde a un vagone che ha posti disponibili per la classe specificata e in tutti i segmenti dall'inizio alla fine del viaggio. I posti sono rappresentati come una lista di stringhe nel formato `"#X"`, dove `#` rappresenta il numero di riga e `X` rappresenta la posizione del posto. Nella mappa sono inclusi solo i posti non prenotati.

Il metodo `findSeats()` recupera i vagoni pertinenti per la classe specificata e raccoglie le informazioni sui posti disponibili in ciascun vagone. La mappa risultante associa ciascun ID di vagone a una lista di posti disponibili.


R3: Prenotazioni
----------------

La classe `TrainManager` include diversi metodi relativi alla prenotazione dei posti, al recupero delle informazioni sulle prenotazioni e all'elenco delle prenotazioni per un posto specifico.

Il metodo `bookSeat()` viene utilizzato per prenotare un posto per una persona che viaggia da una stazione di partenza a una stazione di arrivo su un determinato vagone. Richiede il codice fiscale del passeggero, il nome, il cognome, la stazione di partenza, la stazione di arrivo, l'ID del vagone e il numero del posto come parametri. Il metodo restituisce un codice di prenotazione univoco. Verifica se il vagone e il posto sono validi, valida le fermate e garantisce che il posto sia disponibile per tutti i segmenti del viaggio. Se la verifica non riesce, viene generata un'eccezione TrainException.

Per recuperare il vagone associato a una specifica prenotazione, viene utilizzato il metodo `getBookingCar()`. Prende come parametro l'ID della prenotazione e restituisce l'ID del vagone.

Il metodo `getBookingPassenger()` recupera il codice fiscale della persona che ha effettuato una determinata prenotazione. Prende come parametro l'ID della prenotazione e restituisce il codice fiscale del passeggero.

Il metodo `getBookingSeat()` recupera il posto associato a una specifica prenotazione. Prende come parametro l'ID della prenotazione e restituisce il numero del posto.

Il metodo `getBookingTrip()` recupera le informazioni sul viaggio per una specifica prenotazione. Il viaggio è descritto come la fermata iniziale e la fermata finale separate da un trattino ("-"). Prende come parametro l'ID della prenotazione e restituisce le informazioni sul viaggio.

Per elencare tutte le prenotazioni per un determinato posto, viene utilizzato il metodo `listBookings()`. Prende come parametri l'ID del vagone e il numero del posto e restituisce una collezione di prenotazioni rappresentate come stringhe. Ogni prenotazione è nel formato "inizio-fine:CF", dove "inizio" e "fine" rappresentano le fermate iniziale e finale, rispettivamente, e "CF" rappresenta il codice fiscale della persona prenotata.

Questi metodi forniscono funzionalità per gestire e recuperare informazioni sulle prenotazioni, facilitando il monitoraggio e la gestione delle prenotazioni dei posti per i viaggi in treno.


R4: Controllo dei passeggeri
-----------------------

La classe `TrainManager` include anche metodi relativi alla gestione e al controllo delle prenotazioni dei posti.

Il metodo `setLastStop()` viene utilizzato per definire l'ultima stazione in cui il treno si è fermato. Questa informazione è essenziale per determinare quali prenotazioni sono ancora valide per un posto. Prende il nome della fermata come parametro e lo imposta come ultima fermata. Il metodo restituisce il numero totale di persone che hanno prenotato posti sul treno dopo la fermata specificata. Calcola questo conteggio filtrando le prenotazioni in base a se includono l'ultima fermata e conta il numero di prenotazioni corrispondenti.

Per verificare l'ID di prenotazione associato a un determinato posto, viene utilizzato il metodo `checkSeat()`. Considera l'ultima fermata e identifica la prenotazione che inizia alla fermata specificata o prima di essa e non è ancora terminata. Se esiste una tale prenotazione per il posto specificato, il metodo la segnala come controllata e restituisce il suo ID di prenotazione. Se non è disponibile alcuna prenotazione di questo tipo, il metodo restituisce `null`. Questa funzionalità consente di convalidare e gestire le prenotazioni dei posti, garantendo che vengano considerate solo le prenotazioni valide.

Questi metodi contribuiscono alla funzionalità complessiva della classe `TrainManager`, fornendo la possibilità di impostare l'ultima fermata e controllare le prenotazioni dei posti in base all'ultima fermata. Aiutano a mantenere informazioni precise e aggiornate sulla disponibilità dei posti e sulle prenotazioni valide.


R5: Statistiche
---------

La classe `TrainManager` include metodi aggiuntivi relativi al calcolo e all'analisi delle prenotazioni dei posti.

Il metodo `showFillRatio()` calcola il rapporto di occupazione per tutti i posti di una determinata classe. Il rapporto di occupazione viene determinato dividendo il numero di posti che hanno almeno una prenotazione per il numero totale di posti di quella classe in tutti i vagoni.

Il metodo `checkCoverage()` calcola il conteggio dei controlli per ogni classe. Restituisce una mappa che riporta, per ogni classe, il numero di prenotazioni che sono state controllate.

Il metodo `showOccupationRatio()` calcola il rapporto di occupazione per tutti i posti di una determinata classe e per ciascun segmento del percorso del treno. Il rapporto di occupazione viene determinato dividendo il numero di posti occupati in ogni segmento per il numero totale di posti moltiplicato per il numero di segmenti.