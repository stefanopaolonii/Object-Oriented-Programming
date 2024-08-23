Restaurants
===========

Si sviluppi un programma che permetta di gestire una catena di ristoranti.

Tutte le classi si trovano nel package **restaurantChain**. La classe **MainClass** nel package main presenta esempi di uso dei metodi principali.

La [documentazione JDK](http://softeng.polito.it/courses/docs/api/index.html) è disponibile su un server local.

R1: Restaurant chain
--------------------

La classe **Chain** rappresenta una catena di ristoranti e fornisce i seguenti metodi:

\- Il metodo **addRestaurant()** permette di aggiungere un ristorante alla catena, e riceve due argomenti: il nome del ristorante e il numero di tavoli presenti nel ristorante; se il nome è già presente nella catena, solleva l’eccezione _InvalidName_.

\- Il metodo **getRestaurant()** riceve il nome di un ristorante e restituisce l’oggetto di tipo **Restaurant** corrispondente; se il nome non è tra quelli presenti nella catena, solleva l’eccezione _InvalidName_.

R2: Restaurants
---------------

La classe Restaurant rappresenta un ristorante e fornisce i seguenti metodi:

\- Il metodo **getName()** restituisce il nome del ristorante.

\- Il metodo **addMenu()** permette di aggiungere un menù alla carta del ristorante, e riceve due argomenti: il nome del menù ed il suo prezzo; se il nome è già presente nella carta, solleva l'eccezione _InvalidName_.

\- Il metodo **reserve()** permette ad un gruppo di commensali di riservare dei tavoli, e riceve due argomenti: il nome di un referente del gruppo, ed il numero di commensali; se il nome è già stato usato da un altro gruppo, solleva l’eccezione _InvalidName_.

I commensali vengono disposti su tavoli da quattro posti ciascuno, che vengono riservati al gruppo. Se i tavoli disponibili non sono sufficienti, l’operazione viene rifiutata. Il metodo restituisce il numero di tavoli riservati al gruppo (zero se non ci sono tavoli a sufficienza).

\- Il metodo **getRefused()** restituisce il numero totale di persone che non sono state accolte nel ristorante per mancanza di tavoli.

\- Il metodo **getUnusedTables()** restituisce il numero dei tavoli del ristorante che non sono stati utilizzati.

R3: Orders
----------

\- Il metodo **order()** permette ad un gruppo di commensali, seduti ai tavoli del ristorante, di effettuare un’ordinazione. Il metodo riceve il nome del referente del gruppo, ed una sequenza di nomi di menù; se il nome del referente non è tra quelli che hanno riservato tavoli, solleva l’eccezione _InvalidName_. L’ordinazione viene accettata soltanto se il numero dei menù ordinati (presenti nella sequenza) è maggiore o uguale al numero dei commensali appartenenti al gruppo; il metodo restituisce il valore booleano _false_ se l’ordinazione non è accettata. Se i nomi dei menù indicati nell’ordinazione non sono tutti presenti nella carta del ristorante, il metodo solleva l’eccezione _InvalidName_.

\- Il metodo **getUnordered()** restituisce l’elenco, in ordine alfabetico, dei nomi di referenti di gruppo che hanno tavoli riservati ma non hanno ancora effettuato l’ordinazione.

\- Il metodo **pay()** riceve il nome di un referente di gruppo e gli permette di saldare il conto della sua ordinazione (cioè la somma dei prezzi dei menù ordinati); se il nome del referente non è tra quelli che hanno riservato tavoli, solleva l’eccezione _InvalidName_. Il metodo restituisce l’importo pagato (zero se il gruppo non ha ancora effettuato l’ordinazione).

\- Il metodo **getUnpaid()** restituisce l’elenco, in ordine alfabetico, dei nomi di referenti di gruppo che hanno effettuato l’ordinazione ma non hanno ancora pagato il conto.

\- Il metodo **getIncome()** restituisce il totale delle somme incassate dal ristorante.

R4: Reports
-----------

I seguenti metodi della classe **Chain** permettono di confrontare l’andamento dei ristoranti appartenenti alla catena:

\- **sortByIncome()** restituisce l’elenco dei ristoranti ordinato per valori decrescenti delle somme incassate.

\- **sortByRefused()**b restituisce l’elenco dei ristoranti ordinato per valori crescenti del numero di persone non accolte per mancanza di tavoli.

\- **sortByUnusedTables()** restituisce l’elenco dei ristoranti ordinato per valori crescenti del numero di tavoli non utilizzati.