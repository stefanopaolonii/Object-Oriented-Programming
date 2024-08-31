# Milliways

<em>Milliways</em>, noto anche come <em>Il Ristorante alla Fine dell'Universo</em>, 
è un esercizio immaginario descritto da Douglas Adams nel suo <em>Guida Galattica per Autostoppisti</em>.
Nei sui vasti saloni (hall) il ristorante accoglie diverse razze, con requisiti diversi ed eventualmente incompatibili. 


Tutte le classi devono trovarsi nel packages <b>it.polito.oop.milliways</b>.

La classe <b>Main</b> nel package <b>main</b> contiene esempi di uso del metodi principali.

È possibile accedere liberamente alla 
<a href="http://softeng.polito.it/courses/docs/api/index.html" target="api" target="_blank">Documentazione JDK</a>.


## R1: Razze

La classe principale del sistema è <b>Restaurant</b>, tramite essa vengono creati tutti gli oggetti.

La classe <b>Race</b> rappresenta una razza nel multiverso.
Ogni razza ha un nome unico, che viene passato come parametro al metodo di creazione  <b>defineRace()</b> della classe <i>Restaurant</i>;
se viene specificato un nome duplicato nella creazione viene lanciata un'eccezione <b>MilliwaysException</b>.
Il metodo restituisce un oggetto di classe <b>Race</b>. Il metodo <b>getName()</b> restituisce il nome della razza.


Una <em>razza</em> ha certi <em>requisiti</em>. 
Un requisito è una <code>String</code>, tipo <i>"Class M atmosphere (nitrogen-oxygen)"</i>, 
oppure <i>"Low gravity (less than 2g)"</i>. I requisiti per una data razza sono specificati
tramite il metodo <b>addRequirement()</b>; se lo stesso requisito è aggiunto più di una volta
viene lanciata l'eccezione <b>MilliwaysException</b>.
Il metodo <b>getRequirements()</b> restituisce la lista dei requisiti di un razza ordinati alfabeticamente.



## R2: Gruppi

La classe <b>Party</b> rappresenta un gruppo di compagni di tavola. È possibile creare un nuovo gruppo tramite il metodo 
<b>createParty()</b> della classe <i>Restaurant</i>.
Un gruppo è composto da compagni di diverse razze. Il metodo <b>addCompanions()</b> aggiunge al gruppo un dato numero
di compagni di una data razza.
Il metodo <b>getNum()</b> senza argomenti restituisce il numero di compagni nel gruppo. 
Quando viene passata una razza restituisce il numero di compagni di tale razza nel gruppo.
Il metodo <b>getRequirements()</b> restituisce una lista con tutti i requisiti del gruppo , ovvero,
l'unione dei requisiti di tutte le razze che compongono il gruppo, ordinati alfabeticamente e senza ripetizioni. 
Il metodo <b>getDescription()</b> restituisce la composizione del gruppo come mapp avente come chiavi i nomi della razze 
e come valori le corrispondenti numerosità.



## R3: Saloni

La classe <b>Hall</b> rappresenta un salone del Milliways. I saloni hanno capacità infinita ed un certo numero di <em>servizi</em>.
Ogni salone ha un codice numerico univoco <i>id</i>, specificato quando viene creato tramite il metodo <b>defineHall()</b> della classe <i>Restaurant</i>.

Se lo stesso id viene specificato più volte viene lanciata un'eccezione <i>MilliwaysException</i>; 
il metodo <b>getId()</b> della classe <i>Hall</i> restituisce il codice univoco del salone. 

Il metodo <b>addFacility()</b> permette di aggiungere un servizio ad un salone. Un servizio è rappresentato da una <i>String</i>,
per esempio <i>"Class X atmosphere (methane)"</i>; se lo stesso servizio viene aggiunto più volte allo stesso salone viene lanciata
l'eccesione <i>MilliwaysException</i>.
Il metodo <b>getFacilities()</b> restituisce la lista di tutti i servizi disponibili nel salore, ordinati alfabeticamente. 

Il metodo <b>isSuitable</b> verifica se un gruppo può essere ospitato in un salone, ovvero se tutti i suoi requisiti sono soddisfatti
dai servizi offerti dal salone. Un servizio soddisfa un requisito se le stringhe che li descrivono sono uguali.


## R4: Ristorante

La classe <i>Restaurant</i> rappresenta l'intero Milliways, ed è utilizzata per sistemare i gruppi.
Il metodo <b>getHallList()</b> restituisce la lista dei saloni nell'ordine con cui sono stati creati.

Il metodo <b>seat()</b> accetta due parametri e alloca il gruppo dato nel salone indicato; 
se il salone non può ospitare il gruppo (cioè <i>isSuitable()</i> è <i>false</i>), viene lanciata un'eccezione <i>MilliwaysException</i>. 
Quando <i>seat()</i> viene invocato con un solo parametro (un gruppo), scandisce i saloni del ristorante 
e alloca il gruppo nel primo salone che lo può accogliere. I saloni sono verificati nell'ordine in cui
sono stati creati, e se non viene trovato alcun salone adatto viene lanciata un'eccezione <i>MilliwaysException</i>.
Entrambi i metodi restituiscono il codice del salone in cui il gruppo è stato effettivamente allocato.

## R5: Statistiche

La classe <i>Restaurant</i> permette di calcolare alcune statistiche su Milliways.

Il metodo <b>statComposition()</b> riassume la composizione dei gruppi, ovvero gli esseri che sono stati seduti al ristorante, 
secondo la loro razza. Il metodo restituisce una <i>Map</i> con la razza come chiave, e il numero totale di esseri di tale
razza presenti nei diversi gruppi che sono stati seduti.

Il metodo <b>statFacility()</b> restituisce tutti i servizi disponibili nel ristorante, ordinati in base al numero decrescente 
di saloni in cui sono disponibili e, a parità di numero, alfabeticamente.

Il metodo <b>statHalls()</b> restituisce una <i>Map</i> avente per chiave il numero di servizi disponibili e come
valore la lista dei codici dei saloni che offrono tale numero di servizi. Sia le chiavi che i saloni sono in ordine crescente.