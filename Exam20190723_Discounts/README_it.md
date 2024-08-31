
# Discounts	

Il programma simula la gestione degli sconti in un grande magazzino.
Tutte le classi si trovano nel package <b>it.polito.oop.discounts</b>. La classe principale
è <b>Discounts</b>. La classe <b>TestApp</b> nel package example contiene un esempio.
Le eccezioni sono lanciate mediante la classe <b>DiscountsException</b>.

La documentazione JDK è accessibile all'URL <a href="https://oop.polito.it/api/index.html">https://oop.polito.it/api/index.html</a>. 



## R1: Carte 

Per usufruire degli sconti occorre avere una carta.
Una carta si ottiene con il metodo <b>issueCard()</b> che riceve il nome del cliente e fornisce un numero intero progressivo a partire da 1 compreso.

Per sapere il nome dell'intestatario della carta si usa il metodo <b>cardHolder()</b> che restituisce il nome del cliente dato il numero della carta.

Per ottenere il numero di carte fornite si usa il metodo <b>nOfCards()</b>.



## R2: Prodotti  


Per registrare un tipo di prodotto nel grande magazzino si usa il metodo  
<b>addProduct(String, String, double)</b>. 
Il primo parametro è il codice della categoria di appartenenza, il secondo è il codice del prodotto 
e il terzo è il prezzo. Il metodo lancia un'eccezione se il codice del prodotto è già definito.

Il metodo  <b>getPrice(String)</b> fornisce il prezzo dato il codice del prodotto; 
se il codice del prodotto è indefinito lancia un'eccezione.

Il metodo  <b>getAveragePrice(String)</b> fornisce il prezzo medio arrotondato 
dei tipi di prodotti relativi alla categoria passata come parametro; 
se la categoria è indefinita (ovvero non contiene prodotti) lancia un'eccezione.



 
## R3: Sconti  

Gli sconti si possono definire per le categorie e valgono per tutti i tipi di prodotti relativi.
Inizialmente le categorie hanno uno sconto pari a 0%.

Il metodo <b>setDiscount(String, int)</b> assegna una percentuale di sconto 
(valore intero tra 0 e 50 estremi inclusi) alla categoria indicata dal primo parametro; 
lancia un'eccezione se la categoria è indefinita o il valore non è compreso tra 0 e 50 estremi inclusi.
Il metodo <b>getDiscount(String)</b> fornisce lo sconto associato alla categoria data.


## R4: Acquisti  


Gli acquisti si possono effettuare con o senza carta. 
Nel primo caso si usa il metodo <b>addPurchase(int, String..)</b>
che ha come primo parametro il codice di una carta. 
Nel secondo caso si usa il metodo  <b>addPurchase(String..)</b>.
Il parametro <i>String...</i> indica gli item comprati. 
Un item è costituito dal codice del prodotto e dal n. di unità separati dal carattere <i>":"</i> 
senza spazi intermedi. 
I metodi  forniscono un codice intero progressivo (a partire da 1 compreso) dell'acquisto.

I metodi suddetti scrivono nell'acquisto l'importo complessivo che è pari alla somma degli importi degli item; 
l'importo di un item è dato dal prezzo del tipo di prodotto moltiplicato per il numero di unità. 
Nel caso di acquisto con carta, il metodo applica uno sconto ai prezzi dei tipi dei prodotti: 
lo sconto si legge nelle categorie corrispondenti.
I metodi lanciano un'eccezione se includono un codice di prodotto indefinito.

Il metodo <b>getAmount(int)</b> dato il codice dell'acquisto, fornisce l'importo dell'acquisto dopo l'eventuale sconto.


Il metodo <b>getDiscount(int)</b> fornisce lo sconto applicato all'acquisto dato il codice dell'acquisto.


Il metodo <b>getNofUnits(int)</b> fornisce il numero di unità presenti nell'acquisto.


## R5: Statistiche  


Il metodo <b>productIdsPerNofUnits()</b> fornisce la lista dei codici prodotto per numero di unità
 risultanti dagli acquisti fatti. Sono considerati i prodotti che compaiono almeno in un acquisto.
 Le chiavi (numero di unità) sono ordinate per valori crescenti e le liste dei codici prodotto 
 sono ordinate alfabeticamente.

Il metodo <b>totalPurchasePerCard()</b> fornisce l'importo totale degli acquisti per carta. 
Sono considerate soltanto le carte con le quali sono stati fatti degli acquisti. 
Le chiavi sono ordinate per valori crescenti. Gli importi totali sono arrotondati.

Il metodo <b>totalPurchaseWithoutCard()</b> fornisce l'importo totale arrotondato 
degli acquisti fatti senza carte.

Il metodo <b>totalDiscountPerCard()</b> fornisce lo sconto totale arrotondato per carta. 
Sono considerate soltanto le carte che hanno beneficiato di sconti. 
Le chiavi sono ordinate per valori crescenti.