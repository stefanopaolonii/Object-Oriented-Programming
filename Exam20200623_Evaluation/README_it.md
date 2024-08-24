Evaluations
===========

Il programma simula la valutazione dei gruppi di ricerca di un ente. La valutazione riguarda i punti ottenuti dalla pubblicazione di articoli su riviste. Le riviste sono suddivise in livelli e i livelli hanno punteggi diversi.

Tutte le classi si trovano nel package **evaluation**. La classe principale � **Evaluations**. La classe **TestApp** nel package **example** contiene un esempio. Le eccezioni sono lanciate mediante la classe **EvaluationsException**.

È possibile accedere alla [documentazione delle API Java](https://oop.polito.it/api).

R1 Livelli e riviste
--------------------

Il metodo **addPointsForLevels (int... points)** definisce i punti dei livelli. Il numero di livelli � assunto essere pari al numero di punti, i livelli iniziano da 1 e sono progressivi. Il numero di punti � almeno 2. I punti sono decrescenti (al livello 1 sono associati pi� punti che al livello 2 e cos� via); il metodo lancia un'eccezione se i punti non sono decrescenti.

Il metodo **getPointsOfLevel(int level)** d� i punti del livello indicato.

Il metodo **addJournal(String name, String discipline, int level)** aggiunge una rivista di cui sono dati il nome, la disciplina di pertinenza e il livello; inoltre definisce la disciplina. Lancia un'eccezione se il livello � indefinito.

Il metodo **countJournals()** restituisce il numero di riviste definite.

Il metodo **getJournalNamesOfAGivenDiscipline(String discipline)** produce la lista ordinata alfabeticamente dei nomi delle riviste interessate alla disciplina indicata. D� una lista vuota se non � definita nessuna rivista per la disciplina.

R2 Gruppi e membri
------------------

Il metodo **addGroup(String name, String... disciplines)** aggiunge un gruppo (di ricerca) di cui sono dati il nome e le discipline di interesse. Lancia un'eccezione se esiste gi� un gruppo con quel nome.

Il metodo **setMembers(String groupName, String... memberNames)** definisce i membri di un gruppo. Lancia un'eccezione se il gruppo � indefinito.

Il metodo **getMembers()** restituisce la lista dei membri del gruppo dato, ordinati alfabeticamente. Se il gruppo non � definito o non ha membri viene restituita una lista vuota.

Il metodo **getGroupNamesOfAGivenDiscipline(String discipline)** produce la lista ordinata dei nomi dei gruppi interessati alla disciplina indicata. D� una lista vuota se la disciplina non � definita o se non ci sono gruppi per quella disciplina.

R3 Articoli
-----------

Il metodo **addPaper(String title, String journalName, String... memberNames)** aggiunge un articolo di cui sono dati il titolo, la rivista e gli autori (almeno uno). Lancia un'eccezione se la rivista non � definita o se non c'� almeno un autore.

Il metodo **getTitlesOfAGivenAuthor (String authorName)** produce la lista ordinata dei titoli degli articoli dell'autore (membro di gruppo) indicato. D� una lista vuota se non ci sono articoli per quell'autore.

R4 Valutazioni
--------------

Il metodo **getPointsOfAGivenAuthor(String memberName)** d� il punteggio del membro di gruppo di ricerca indicato. Per ogni articolo di cui � autore, il membro indicato riceve un numero di punti pari a quelli della rivista associata all'articolo, divisi per il numero totale di autori. Il risultato del metodo � dato dalla somma dei punti suddetti arrotondata all'intero pi� vicino tramite il metodo _Math.round()_.

Il metodo **evaluate()** calcola il totale dei punti per tutti gli articoli (sono quelli delle riviste di appartenenza). Si nota che il totale dei punti degli articoli non � necessariamente uguale alla somma dei punti di tutti gli autori, a causa degli arrotondamenti.

R5 Statistiche
--------------

Il metodo **pointsForGroup()** d� una mappa che associa il nome di un gruppo al numero di punti per gruppo, con i nomi dei gruppi ordinati alfabeticamente. Il numero di punti di un gruppo � dato dalla somma dei punteggi dei membri del gruppo.

Il metodo **getAuthorNamesPerPoints** d� una mappa che associa ad un punteggio la lista dei nomi degli autori che hanno tale punteggio, con i punteggi ordinati in modo decrescente e i nomi deli autori ordinati alfabeticamente. Sono scartati gli autori con punteggio 0.