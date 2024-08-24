Pianificatore di incontri
========================

Scrivi un programma per supportare la pianificazione di incontri e la raccolta di preferenze per gli slot. Le classi si trovano nel package `it.polito.meet`; la classe principale è `MeetServer`. La classe `TestApp` nel package `example` mostra esempi di utilizzo dei metodi principali ed esempi dei controlli richiesti. Implementa solo i controlli richiesti. Le eccezioni nei metodi descritti di seguito sono del tipo `MeetException`.

La documentazione del JDK (https://oop.polito.it/api/) si trova sul server locale.

La versione Inglese di questi requisiti è disponibile in [README.md](README.md).


R1: Categorie e Incontri
------------------------

Il metodo `addCategories()` consente di aggiungere categorie di incontri all'elenco delle categorie. Accetta le categorie in ingresso e le aggiunge all'elenco.

Il metodo `getCategories()` recupera l'elenco delle categorie disponibili.

Il metodo `addMeeting()` aggiunge un nuovo meeting con una categoria specificata. Prende in ingresso il titolo, l'argomento e la categoria del meeting e restituisce un ID univoco per l'meeting. Se la categoria specificata non esiste, viene generata un'eccezione `MeetException`.

Il metodo `getMeetings()` recupera l'elenco degli incontri che appartengono a una determinata categoria. Prende in ingresso la categoria e restituisce una collezione di ID degli incontri.

Il metodo `getMeetingTitle()` recupera il titolo di un meeting in base al suo ID. Prende in ingresso l'ID del meeting e restituisce il titolo corrispondente.

Il metodo `getMeetingTopic()` recupera l'argomento di un meeting in base al suo ID. Prende in ingresso l'ID del meeting e restituisce l'argomento corrispondente.

R2: Opzioni di slot per gli incontri
-------------------------------------

La classe `MeetServer` fornisce metodi aggiuntivi per gestire le opzioni di slot per un meeting e recuperare informazioni sugli slot.

Per aggiungere un nuovo slot di opzione per un meeting, puoi utilizzare il metodo `addOption()`. Questo metodo richiede l'ID del meeting, la data, l'ora d'inizio e l'ora di fine come parametri. Si assicura che il nuovo slot non si sovrapponga con eventuali slot esistenti per lo stesso meeting. Se l'ID del meeting fornito non è valido, viene generata un'eccezione `MeetException`. Il metodo calcola la durata dello slot in ore e restituisce questo valore.

Se hai bisogno di recuperare informazioni sugli slot disponibili per un meeting specifico, puoi utilizzare il metodo `showSlots()`. Il valore restituito è una mappa in cui ogni data è una chiave e il valore corrispondente è un elenco di slot descritti nel formato "hh:mm-hh:mm". Questo formato indica l'ora d'inizio e di fine di ogni slot, consentendoti di visualizzare le opzioni di tempo disponibili per l'meeting.

R3: Preferenze
--------------

La classe `MeetServer` fornisce un insieme di metodi per gestire le preferenze per gli incontri.

Per dichiarare un meeting aperto per la raccolta di preferenze, puoi utilizzare il metodo `openPoll()`, che abilita la raccolta di preferenze per quell'meeting dato l'ID del meeting.

Quando i partecipanti desiderano registrare le proprie preferenze per uno slot o un'opzione specifica di un meeting, possono utilizzare il metodo `selectPreference()`. Questo metodo richiede l'email, il nome, il cognome, l'ID del meeting, la data dello slot selezionato e l'intervallo di tempo dello slot. Le preferenze possono essere registrate solo per incontri che hanno un sondaggio aperto. Il metodo aggiunge la preferenza e restituisce il numero totale di preferenze registrate per quel slot. Se viene fornito un ID di meeting o uno slot non validi, viene generata un'eccezione `MeetException`.

Infine, se hai bisogno di recuperare l'elenco delle preferenze espresse per un meeting specifico, puoi utilizzare il metodo `listPreferences()`. Fornendo l'ID del meeting, il metodo restituisce una collezione di preferenze espresse per quell'meeting. Ogni preferenza è rappresentata come una stringa nel formato "YYYY-MM-DDThh:mm-hh:mm=EMAIL", dove la data, l'intervallo di tempo e l'email del partecipante sono separati da "T" e "=".

R4: Chiusura del sondaggio
--------------------------

Per chiudere il sondaggio associato a un meeting e determinare le opzioni più preferite, puoi utilizzare il metodo `closePoll()`. Fornendo l'ID del meeting, il metodo recupera l'oggetto `Meeting` corrispondente. Quindi disabilita il sondaggio, indicando che non possono essere registrate ulteriori preferenze per quell'meeting.

Il metodo restituisce una collezione di stringhe che rappresentano le opzioni più preferite. Ogni opzione è formattata come "YYYY-MM-DDThh:mm-hh:mm=##", dove la data, l'intervallo di tempo e il conteggio delle preferenze sono separati da "T" e "=".

R5: Statistiche
---------------

Ulteriori metodi forniscono informazioni utili per analizzare le preferenze degli incontri e valutare il livello di partecipazione o coinvolgimento tra i partecipanti. Essi facilitano la presa di decisioni basata sui dati, presentando il conteggio delle preferenze in modo organizzato e consentendo agli organizzatori o alle parti interessate di ottenere informazioni sulle preferenze dei partecipanti e di effettuare scelte consapevoli in base ai dati raccolti.

Il metodo `meetingPreferences()` recupera il conteggio delle preferenze per ogni slot di un meeting. Prende in ingresso l'ID del meeting e restituisce una mappa in cui ogni chiave rappresenta una data associata all'meeting e il valore corrispondente è un elenco di slot con le rispettive preferenze. Ogni slot è descritto come una stringa nel formato "hh:mm-hh:mm=###", che indica l'intervallo di tempo e il numero di preferenze per quel slot, ad esempio "14:00-15:30=2". Questo metodo fornisce una panoramica delle preferenze espresse per ogni slot all'interno del meeting.

Il metodo `preferenceCount()` calcola il numero totale di preferenze raccolte per ogni meeting. Non richiede parametri d'ingresso e restituisce una mappa in cui ogni chiave rappresenta l'ID del meeting e il valore corrisponde al conteggio delle preferenze espresse per quell'meeting. Questo metodo consente di valutare il livello d'interesse o popolarità di ogni meeting in base al numero di preferenze ricevute.