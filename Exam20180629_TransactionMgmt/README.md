# Gestione di transazioni


Si scriva un programma per la gestione di transazioni. Le classi si trovano nel package <b>transactions</b>; 
la classe principale è <b>TransactionManager</b>. La classe <b>Example</b> nel package <b>main</b> presenta esempi di uso dei metodi principali 
ed esempi dei controlli richiesti. Si implementino soltanto i controlli richiesti.
Le eccezioni sono di tipo <b>TMException</b>.<br>


La <a href="https://oop.polito.it/api/" target="api" target="_blank">JDK documentation</a>  si trova sul server locale.


## R1: Regioni e trasportatori


Il metodo <b>List &lt;String&gt; addRegion</b> (String regionName, String... placeNames) introduce una regione e i posti che contiene dati i loro nomi. I posti con nomi già esistenti non sono inseriti. Come risultato fornisce la lista ordinata dei nomi dei posti inseriti.<br>

Il metodo <b> List &lt;String&gt; addCarrier</b> (String carrierName, String... regionNames) introduce un trasportatore e le regioni che serve. Nomi di regioni duplicati o inesistenti sono ignorati. 
Come risultato fornisce la lista ordinata dei nomi delle regioni associate al trasportatore.<br>

Il metodo <b> List &lt;String&gt; getCarriersForRegion</b> (String regionName) fornisce la lista ordinata dei nomi dei trasportatori associati alla regione di cui è dato il nome.<br>



## R2: Richieste e offerte

Il metodo <b>addRequest</b> (String requestId, String placeName, String productId) definisce una richiesta dato il codice della richiesta, il nome del posto di consegna e l'id del prodotto richiesto. 
Lancia un'eccezione se il posto è indefinito o il codice è duplicato.<br>

Il metodo <b>addOffer</b> (String offerId, String placeName, String productId) definisce un'offerta dato il codice dell'offerta, il nome del posto di prelievo e l'id del prodotto offerto. 
Lancia un'eccezione se il posto è indefinito o il codice è duplicato.<br>


## R3: Transazioni


Il metodo <b>addTransaction</b> (String transactionId, String carrierName, String requestId, String offerId)
definisce una transazione dato il codice della transazione, il nome del trasportatore, il codice di una richiesta e il codice di un'offerta. 
Si assume che il trasportatore, la richiesta e l'offerta siano definiti.
I controlli da effettuare sono quindi i seguenti: la richiesta o l'offerta non devono comparire in transazioni precedenti, devono riguardare lo stesso codice prodotto e il trasportatore deve trattare sia il posto di prelievo sia quello di consegna (cioè le regioni corrispondenti).
In caso negativo è lanciata un'eccezione, altrimenti la transazione è associata alla richiesta e all'offerta.<br>

Il metodo <b> boolean evaluateTransaction</b> (String transactionId, int score) dà un punteggio alla transazione. Una transazione è generata con il punteggio iniziale pari a 0.
Il risultato è falso se il punteggio non è compreso tra 1 e 10 (estremi inclusi); altrimenti è true.<br>

## R4: Statistiche

Il metodo <b>SortedMap &lt;Long, List &lt;String&gt;&gt; deliveryRegionsPerNT</b> dà la lista dei nomi delle regioni (ordinati alfabeticamente) 
che compaiono come regioni di consegna nello stesso numero di transazioni. 
La regione di consegna di una transazione è la regione che include il posto di consegna della richiesta associata alla transazione. 
La mappa presenta il numero di transazioni in ordine decrescente.<br>

Il metodo <b>SortedMap &lt;String, Integer&gt; scorePerCarrier</b> (int minimumScore) dà il punteggio totale delle transazioni che riguardano lo stesso trasportatore. 
Le transazioni con punteggio inferiore al parametro minimumScore sono ignorate.
I trasportatori compaiono in ordine alfabetico.<br>

Il metodo <b>SortedMap &lt;String, Long&gt; nTPerProduct</b> dà il numero di transazioni (soltanto se maggiore di 0) 
per id di prodotto, 
con gli id ordinati alfabeticamente.<br>