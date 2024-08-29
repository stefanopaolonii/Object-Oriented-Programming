# Property Management

Un gestore di appartamenti riceve richieste di intervento dai proprietari e le assegna a professionisti idonei. 
La classe principale si chiama <b>PropertyManager</b>; tutte le classi si trovano nel package <b>managingProperties</b>.
La classe <b>Example</b> presenta esempi di uso dei metodi principali.<br>

La <a href="https://oop.polito.it/api" target="api" target="_blank">JDK documentation</a>  si trova sul server locale.

## R1: Edifici, appartamenti e proprietari</h2>

I metodi seguenti permettono di registrare nel sistema gli edifici, ciascuno con il n. di appartamenti e gli utenti.
<br>
Il metodo <b>addBuilding()</b> riceve l'id (univoco) dell'edificio (es. <i>"b1"</i>) e il n. dei suoi appartamenti e li registra. 
Lancia un'eccezione se l'id &egrave; gi&agrave; stato inserito o se il n. di appartamenti non &egrave; compreso tra 1 e 100.
<br>Il metodo <b>addOwner()</b> riceve l'id (univoco) del proprietario e l'elenco dei suoi appartamenti e li registra. 
Un appartamento &egrave; indicato da una stringa contenente l'id dell'edificio e il n. dell'appartamento separati da : 
(es. "b1:10"). 
Lancia un'eccezione se l'id del proprietario &egrave; gi&agrave; stato inserito, l'id del building non esiste, 
il n. non corrisponde ad un appartamento, l'appartamento ha gi&agrave; un owner.
<br>Il metodo <b>getBuildings()</b>
restituisce una mappa che raggruppa per numero crescente di appartamenti le liste ordinate alfabeticamente degli id degli edifici.
<!--
d&agrave; la lista ordinata alfabeticamente degli id degli edifici per il n. crescente di appartamenti.
-->

## R2: Professionisti
Il metodo <b>addProfessionals()</b> riceve il nome della professione e l'elenco degli id dei professionisti relativi.
Le professioni usate negli esempi sono elettricista, idraulico, muratore (electrician, plumber, mason).
Lancia un'eccezione se la stessa professione compare in una precedente chiamata dello stesso metodo, 
o se lo stesso id compare in pi&ugrave; elenchi (ogni professionista svolge un'unica professione).
<br>Il metodo <b>getProfessions()</b> d&agrave; il n. di professionisti per le professioni in ordine alfabetico.


## R3: Richieste di Manutenzione
Il metodo <b>addRequest()</b> riceve l'id del proprietario, l'id dell'appartamento (es. "b1:10") e 
il nome della professione, e genera una nuova richiesta nello stato <i>pending</i>; d&agrave; il numero della richiesta. Le richieste hanno
un numero progressivo a partire da 1.
Lancia un'eccezione se il proprietario o l'appartamento
o la professione non esistono o se il proprietario non possiede quell'appartamento.
<br>Il metodo <b>assign()</b> assegna la richiesta (di cui &egrave; dato il numero) al professionista e cambia la stato della
richiesta in <i>assigned</i>.
Lancia un'eccezione se il professionista non svolge la professione indicata nella richiesta o la richiesta &egrave; inesistente
o non &egrave; pi&ugrave; nello stato pending.
<br> Il metodo <b>getAssignedRequests()</b> d&agrave; l'elenco dei n. delle richieste assegnate in ordine crescente.

## R4: Addebiti
Per addebitare una manutenzione si usa il metodo <b>charge()</b> che riceve il n. della richiesta e l'importo (n. intero). 
Il metodo cambia lo stato della richiesta in <i>completed</i>.
Lancia un'eccezione se la richiesta non esiste o non &egrave; nello stato assigned o l'importo non &egrave; compreso tra 0 e 1000.
<br> Il metodo <b>getCompletedRequests()</b> d&agrave; l'elenco dei n. delle richieste completate in ordine crescente.

## R5: Statistiche

Il metodo <b>getCharges()</b> d&agrave; le spese per proprietario; i proprietari compaiono in ordine alfabetico. 
Le spese  di un proprietario sono date dalla
somma degli importi delle richieste completate che ha presentato. Solo i proprietari con spese non nulle sono considerati.

Il metodo <b>getChargesOfBuildings()</b> d&agrave; le spese per edificio; gli edifici compaiono in ordine alfabetico.
Le spese sono date per professionista in ordine alfabetico
e risultano dalla somma degli importi delle richieste completate relative ad appartamenti dell'edificio e al professionista.
Sono considerati solo edifici e professionisti con spese non nulle.