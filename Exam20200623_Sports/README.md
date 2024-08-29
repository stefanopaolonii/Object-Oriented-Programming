# Sports
Il programma implementa il back-end di un portale di valutazioni di attrezzature sportive.

Tutte le classi si trovano nel package sports. La classe principale è Sports. La classe TestApp nel package example contiene un esempio. Le eccezioni sono lanciate mediante la classe SportsException.

È possibile accedere alla <a href="https://oop.polito.it/api/index.html" target="api" target="_blank">documentation</a> delle API Java.

# R1 Attività e Categorie

Il metodo defineActivities(String... activities) definisce le attività sportive trattate dal portale. Il metodo lancia un'eccezione se non è passata alcuna attività.

Il metodo getActivities() dà la lista delle attività definite, ordinate alfabeticamente.

Il metodo addCategory(String name, String... linkedActivities) aggiunge una categoria di prodotti e le attività collegate ad essi. Lancia un'eccezione se qualcuna delle attività non è definita.

Il metodo countCategories() restituisce il numero di categorie definite.

Il metodo getCategoriesForActivity(String activity) produce la lista ordinata alfabeticamente dei nomi delle categorie legate all'attività sportiva indicata. Dà una lista vuota se non è definita nessuna categoria per l'attività, o se l'attività non esiste.
# R2 Prodotti

Il metodo addProduct(String name, String activity, String category) aggiunge un prodotto di cui sono dati il nome, l'attività e la categoria di appartenenza. Lancia un'eccezione se esiste già un prodotto con quel nome.

Il metodo getProductsForCategory(String categoryName) restituisce la lista ordinata alfabeticamente dei nomi dei prodotti della categoria indicata. Se la categoria non è definita o non ha prodotti viene restituita una lista vuota.

Il metodo getProductsForActivity(String activityName) restituisce la lista ordinata alfabeticamente dei nomi dei prodotti dell'attività indicata. Se l'attività non è definita o non ha prodotti viene restituita una lista vuota.

Il metodo getProducts(String activityName, String... categoryNames) restituisce la lista ordinata dei nomi dei prodotti che appartengono all'attività indicata e ad una delle categorie indicate. Se l'attività non è definita o non ha prodotti viene restituita una lista vuota.

# R3 Utenti e recensioni

Il metodo addRating(String productName, String userName, int numStars, String comment) aggiunge una recensione per un prodotto da parte di un utente del sistema. La recensione prevede un voto, come numero di stelle tra 0 e 5, ed un commento. Lancia un'eccezione se il numero di stelle non è compreso tra 0 e 5 (inclusi).

Il metodo getRatingsForProduct(String productName) produce la lista ordinata per numero di stelle decrescente delle recensioni formattate come "# : [Comment]", dove # è il numero di stelle mentre [Comment] è il commento associato. Dà una lista vuota se nessun utente ha inserito recensioni per il prodotto.
# R4 Valutazioni

Il metodo getStarsOfProduct(String productName) dà il numero medio di stelle delle recensioni del prodotto indicato.

Il metodo averageStars() calcola il numero medio di stelle su tutte le recensioni.
# R5 Statistiche

Il metodo starsPerActivity() dà una mappa che associa il nome di una attività alla media di stelle dei prodotti ad essa associati, con i nomi delle attività ordinati alfabeticamente. Nel risultato non devono comparire le attività i cui prodotti non hanno ricevuto recensioni.

Il metodo getProductsPerStars dà una mappa che associa al numero medio di stelle la lista dei nomi di prodotti che hanno tale media, con le medie ordinate in modo decrescente e i nomi dei prodotti ordinati alfabeticamente. Sono scartati i prodotti senza recensioni. 