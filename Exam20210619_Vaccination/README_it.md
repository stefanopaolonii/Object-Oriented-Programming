Campagna Vaccinale
==================

Sviluppare un sistema che permetta la pianificazione di una campagna vaccinale in un territorio in cui sono disponibili diversi centri vaccinali.

Tutte le classi devono essere nel package `it.polito.oop.vaccination`. La classe _facade_ attraverso cui tutte le operazioni sono eseguite è `Vaccines`, ogni nuova istanza di questa classe rappresenta un sistema vaccinale distinto, con persone e centri propri. La segnalazioni di errori ed anomalie avviene tramite eccezioni di tipo `VaccineException`.

La classe `TestApp` nel default package contiene una sintetico test per l’applicazione.

La documentazione JDK è accessibile all’URL [http://softeng.polito.it/courses/docs/api/index.html](http://softeng.polito.it/courses/docs/api/index.html).

R1: Popolazione
---------------

La popolazione da vaccinare è composta da persone di cui è noto il codice fiscale, nome, cognome e anno di nascita.

È possibile inserire una nuova persona tramite il metodo `addPerson()` che riceve come parametri nome, cognome, codice fiscale e anno di nascita. Se esiste già una persona con lo stesso codice fiscale il metodo scarta l’informazione e restituisce _false_, altrimenti restituisce _true_. Il conteggio delle persone inserite è restituito dal metodo `countPeople()`. Ogni istanza della classe `Vaccines` rappresenta un sistema vaccinale distinto, con persone e centri propri.

Il metodo `getPerson()` accetta un codice fiscale e restituisce una stringa contenente codice fiscale, cognome e nome separati da `","`. Il metodo `getAge()` restituisce l’età di una persona dato il codice fiscale.

Le informazioni relative alle persone possono essere lette da un file in formato CSV. La lettura avviene tramite il metodo `loadPeople()` che legge un file CSV che contiene per ogni persona (una persona per riga) il codice fiscale (SSN in inglese), cognome, nome e anno di nascita, separati da virgola senza spazi. Esempio: `MLNLCU50Z403RE,Melone,Luca,1950`.

Il file ha come intestazione (prima riga) almeno le colonne `SSN`, `LAST`, `FIRST` e `YEAR`, nell’ordine. Se l’intestazione non è corretta, viene generata un’eccezione.

L’inserimento delle singole persone lette dal CSV avviene come per il metodo `addPerson()`. In caso di codice fiscale duplicato o di informazioni mancanti la linea viene scartata.

Il metodo accetta come parametro un `Reader` e restituisce il numero di persone lette correttamente.

In caso di errori di I/O l’eccezione deve essere propogata al chiamante.

*   Suggerimento: per leggere una riga alla volta è possibile usare il metodo `readLine()` della classe `BufferedReader`.

R2: Fasce di età
----------------

Le persone possono essere suddivise in fasce di età che sono definite tramite il metodo `setAgeIntervals()` che riceve come parametro una serie di interi che corrispondono alle età che suddividono gli intervalli. Gli intervalli sono chiusi all’estremo inferiore e aperti a quello superiore.

Ad esempio `setAgeIntervals(40,50,60)` definisce quattro intervalli: _\[0,40), \[40,50), \[50,60), \[60,∞)_.

Il metodo `getAgeIntervals()` restituisce una collezione ordinata contenente le etichette degli intervalli di età formattati come `"[40,50)"`, e dove al posto del simbolo di infinito si usa il carattere `'+'`.

Il metodo `getInInterval()` accetta l’etichetta di un intervallo e restituisce l’elenco dei codici fiscali delle persone la cui età, calcolata sottraendo l’anno di nascita dall’anno corrente, ricade nell’intervallo. Se non sono presenti persone con età compresa in tale intervallo, viene restituita una collezione vuota.

*   Suggerimento: per ottenere l’anno corrente è possibile usare `java.time.LocalDate.now().getYear()`

R3: Centri Vaccinali
--------------------

I centri vaccinali sono definiti tramite il metodo `defineHub()` che accetta un nome, il metodo lancia un’eccezione in caso di nome duplicato.

L’elenco dei centri vaccinali è restituito dal metodo `getHubs()`.

Il metodo `setStaff()` permette di definire la configurazione di personale in termini di medici, infermieri, altro personale. Il metodo lancia un’eccezione se non è stato definito un centro vaccinale con il nome specificato o se uno dei parametri numerici è <= 0.

Il metodo `estimateHourlyCapacity()` accetta il nome di un centro vaccinale e restituisce la stima della capacità oraria. È possibile stimare la capacità oraria di un centro vaccinale sulla base della dotazione di personale. La capacità di un centro vaccinale è calcolata come il minimo tra:

*   10 \* numero dottori,
*   12 \* numero infermieri,
*   20 \* numero di altro personale.

Questo metodo lancia un’eccezione se non è stato definito un centro vaccinale con il nome specificato oppure se per il centro vaccinale non è stato ancora definito il personale.

R4: Orario settimanale
----------------------

Il metodo `setHours()` riceve un array con il numero di ore lavorative disponibili nei giorni della settiamana, da lunedì (indice 0) a domenica (indice 6). Tali ore valgono per tutti i centri vaccinali. Il metodo lancia un’eccezione se il parametro non ha 7 elementi o se in un giorno sono indicate più di 12 ore.

Il metodo `getHours()` restituisce una lista (con un elemento per giorno della settimana) i cui elementi sono le liste degli orari con il formato `"hh:mm"`.  
Gli orari sono defini ogni 15 minuti (4 ogni ora), iniziano alle 9:00 e si estendono per il numero di ore indicato nella chiamata al metodo precedente.

Il metodo `getDailyAvailable()` riceve come parametri il nome di un centro vaccinale e il numero d’ordine di un giorno della settima (0=Lunedì) e restituisce la capacità di vaccinazione per quel centro nel giorno indicato. La capacità di vaccinazione, ovvero il numero di vaccinazioni che è possibilile effettuare nel centro per il giorno indicato, è pari al numero di ore lavorative (del giorno indicato) moltiplicato per la capacità oraria (come fornita dal metodo `estimateHourlyCapacity()`).

Il metodo `getAvailable()` restituisce una mappa che associa il nome dei centri vaccinali con le capacità di vaccinazione nei sette giorni della settimana, calcolate come specificato nel metodo precedente.

*   Suggerimento: per stampare un intero su due caratteri con lo 0 iniziale è possibile usare `String.format("%02d", i)`

R5: Pianificazione
------------------

Il metodo `allocate()` riceve come parametri il nome di un centro, il numero d’ordine di un giorno della settimana (0=Lunedì).

Il metodo cerca di allocare un numero di persone pari alla disponibilità per il centro e giorno specificati dando la precedenza alle fasce di età più elevate. I numero totale di persona da allocare nel giorno è pari alla capacità di vaccinazione (come indicato dal metodo `getDailyAvailable()`).

Per ogni fascia si alloca un numero di persone pari al 40% del numero di allocazioni ancora libere (valore troncato).

Viene seguito il seguente algoritmo:

*   sia _n_ il numero di persone da allocare
*   si itera per ogni fascia di età partendo dalla più elevata:
    *   si selezionano al più _n\*0.4_ persone ( valore troncato ) non ancora allocate dalla fascia di età corrente,
    *   se la fascia di età contiene meno persone di quelle richieste vengono selezionate tutte,
    *   le persone vengono segnate come allocate per il centro e giorno indicati,
    *   si aggiorna _n = n - a_ dove _a_ è il numero di persone della fascia effettivamente allocate,
*   se alla fine delle iterazioni sono state allocate meno persone della disponibilità (ovvero _n != 0_)
    *   si inseriscono tutte le persone disponibili a partire dalla fascia di età più alta fino a saturare i posti residui.

Quindi se abbiamo sei fasce di età e supponendo pari a 100 il numero di allocazioni libere, per la 6a fascia saranno eseguite 40 allocazioni, per la 5a fascia 24 allocazioni, per la 4a fascia 14, per la 3a fascia 8, per la 2a fascia 5, per la 1a fascia 3. Restano ancora 6 posizioni libere che sono assegnate a persone della 6a fascia.

Ovviamente non si deve superare il numero massimo di allocazioni (100 nell’esempio).

Il metodo restituisce l’elenco dei codici fiscali delle persone allocate nel centro e giorno indicati. Le persone nella lista vengono segnate come allocate e non sono considerate nelle successive chiamate del metodo.

Le allocazioni possono essere cancellate con il metodo `clearAllocation()` che segna tutte le persone come non allocate (libere).

La programmazione del piano generale settimanale viene svolta tramite il metodo `weekAllocate()`.

Il metodo restituisce una lista, con un elemento per giorno della settimana che è una mappa che ha come chiava il nome dell’hub e come valore la lista dei codici fiscali delle persone allocate all’hub per quel giorno.

*   Nota: in base all’algoritmo precedente, per costruzione restano dei posti da assegnare dopo la prima fase (~ 8%), perciò alla fascia più elevata vengono assegnate il 48% (40% + 8%) dei posti disponibili, a quella successiva il 24% (40% \* 60%), alla terza il 14% (40% \* 60% \* 60%).

R6 Statistiche piano
--------------------

Dopo aver prodotto un piano settimanale è possibile valutarne le caratteristiche usando i seguenti metodi che si basano sul risultato dell’ultima chiamata al metodo `weekAllocate()`.

Il metodo `propAllocated()` restituisce la proporzione di persone allocate nel piano rispetto al totale delle persone inserite nel sistema.

Il metodo `propAllocatedAge()` restituisce una mappa che associa la fascia di età alla proporzione di persone in tale fascia d’età allocate nel piano rispetto alle persone (della fascia di età) inserite nel sistema.

Il metodo `distributionAllocated()` restituisce una mappa che associa la fascia di età alla distribuzione delle persone allocate, ovvero il numero di persone allocate di ciascuna fascia diviso per il numero totale di persone allocate.

R7: Lettura file avanzata
-------------------------

È possibile attivare una gestione avanzata della lettura dei file (in estensione a quanto descritto nel requisitio R1) chiamando il metodo `setLoadListener()` prima di invocare il metodo di lettura da file descritto in R3. Il metodo riceve come parametro un oggetto listener che implementa l’interfaccia `BiConsumer<Integer,String>`.

Se è stato definito il listener, quando durante la lettura si incontra un errore (linea incompleta o codice fiscale duplicato) deve essere invocato il metodo `accept()` del listener passando il numero della riga che ha causato il problema e la riga stessa. Le righe sono numerata a partire da 1, la riga 1 corrisponde all’intestazione.

Il metodo `accept()` del listener deve essere invocato anche prima di lanciare l’eccezione relativa all’intestazione non corretta.