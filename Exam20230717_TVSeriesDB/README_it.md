Database di Serie Tv
========================

Scrivi un programma per supportare la gestione di un database di serie tv. Le classi si trovano nel package `it.polito.tvseriesdb`; la classe principale è `TVSeriesDB`. La classe `TestApp` nel package `example` mostra esempi di utilizzo dei metodi principali ed esempi dei controlli richiesti. Implementa solo i controlli richiesti. Le eccezioni nei metodi descritti di seguito sono del tipo `TSException`.

La documentazione del JDK (https://oop.polito.it/api/) si trova sul server locale.

La versione Inglese di questi requisiti è disponibile in [README.md](README.md).


R1: Gestione Serie
---------------------

Il metodo `addTransmissionService()` aggiunge un servizio di streaming (ad esempio, Netflix, Prime Video, Disney+) alla lista dei servizi disponibili. Prende in ingresso una lista di servizi di trasmissione, rimuove i duplicati e li aggiunge alla lista.

Il metodo `addTVSeries()` aggiunge una nuova serie TV specificando il nome, il servizio di trasmissione e il genere. Se il servizio di trasmissione non è disponibile, viene generata un'eccezione `TSException`. I nomi delle serie TV sono univoci, quindi se esiste già una serie con lo stesso nome, viene generata un'eccezione `TSException`. Il metodo ritorna il numero di serie TV inserite fino a quel momento.

Il metodo `addActor()` aggiunge un attore di serie TV al database. Prende in ingresso il nome, il cognome e la nazionalità dell'attore. La coppia nome e cognome dell'attore è univoca. Se l'attore è già presente nel database, un'eccezione `TSException` viene generata. Il metodo ritorna il numero di attori inseriti fino a quel momento. 

Il metodo `addCast()` aggiunge le informazioni sul cast di una serie TV. Richiede che la serie TV e tutti gli attori esistano nel database. In caso contrario, viene generata un'eccezione `TSException`. Il numero di membri del cast viene ritornato dal metodo.

R2: Gestione Episodi Serie TV
---------------------------------

Il metodo `addSeason()` aggiunge una nuova stagione per una specifica serie TV. Prende in ingresso il nome della serie TV, il numero di episodi e la data di uscita (formato "giorno:mese:anno"). Se la serie TV non esiste, viene generata un'eccezione `TSException`. Se la data di uscita della stagione da inserire è precedente alla data di uscita dell'ultima stagione inserita per quella serie TV, un'eccezione `TSException` viene generata. Il metodo restituisce il numero di stagioni per la serie TV fino a ora. 

Il metodo `addEpisode()` aggiunge il titolo di un episodio a una stagione di una serie TV. Richiede il nome della serie TV, il numero della stagione della serie TV, il titolo dell'episodio. Gli episodi vengono inseriti in ordine sequenziale all'interno della stagione. Se la serie TV o la stagione non esistono, se si sono già inseriti tutti gli episodi previsti per quella stagione o se esiste già un episodio con lo stesso nome in quella stagione, viene generata un'eccezione `TSException`. Il metodo ritorna il numero di episodi inseriti fino a quel momento per quella stagione di quella serie.

Il metodo `checkMissingEpisodes()` restituisce una mappa che specifica quali serie TV hanno informazioni mancanti e la lista delle stagioni incomplete, cioè le stagioni per le quali non sono state ancora inserite tutte le informazioni sugli episodi.


R3: Utenti e Gestione
-----------------------

Il metodo `addUser()` aggiunge un nuovo utente all'applicazione. Prende in ingresso lo username e il genere preferito dell'utente. Il nickname è univoco: se l'utente è già registrato, una `TSException` viene generata.  Ritorna il numero di utenti registrati fino a quel momento.

Il metodo `likeTVSeries()` aggiunge una serie TV alla lista delle serie preferite di un utente. Richiede che sia la serie TV che l'utente esistano nel database. Se uno dei due non esiste o se la serie è già nella lista, viene generata un'eccezione `TSException`. Ritorna il numero di serie preferite dell'utente fino a quel momento.

Il metodo `suggestTVSeries()` ritorna una lista di serie TV suggerite per un determinato utente, dopo aver controllato che esista nel database (altrimenti genera un'eccezione `TSException`). Una serie TV viene suggerita all'utente se non è ancora nella sua lista di serie tv preferite e se è del genere preferito dall'utente. Se non vengono trovati suggerimenti, viene restituita una lista contenente solo la stringa vuota "".

R4: Recensioni
------------

Il metodo `addReview()` consente agli utenti di aggiungere recensioni sotto forma di punteggi (compresi tra 0 e 10) per una specifica serie TV. Richiede che sia l'utente che la serie TV esistano nel database e che il punteggio sia compreso nell'intervallo valido. In caso contrario, viene generata un'eccezione `TSException`. Il metodo restituisce il punteggio medio delle recensioni per la serie TV inserite fino a quel momento. 

Il metodo `averageRating()` calcola e ritorna la valutazione media assegnata da uno specifico utente a tutte le serie TV nella sua lista di serie TV preferite. Se non sono presenti recensioni, la valutazione media è 0 (zero).

R5: Statistiche
---------------

Il metodo `mostAwaitedSeason()` restituisce la stagione più attesa (non ancora uscita alla data specificata come parametro) tra tutte le ultime stagioni delle serie TV inserite. La stagione più attesa è quella associata alla serie con il punteggio medio delle recensioni più alto. In caso di parità ritorna la stagione della prima serie tv in ordine alfabetico. La stagione è restituita come stringa con il formato `"TvSeriesName seasonNumber"`.
Se nessuna stagione per nessuna serie risulta con uscita successiva alla data fornita, il metodo restituisce la stringa vuota `“”`.

Il metodo `bestActors()` restituisce l'elenco dei nomi degli attori che hanno lavorato esclusivamente in serie TV con una valutazione media superiore a 8, disponibili sul servizio di trasmissione passato come parametro. Se nessun attore ha le caratteristiche richieste il metodo restituisce una lista vuota. Se il servizio di trasmissione non esiste, viene generata un'eccezione `TSException`.




