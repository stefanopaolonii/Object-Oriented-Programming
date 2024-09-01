# Approvvigionamento Merci


Sviluppare l'applicazione che consente di gestire un magazzino.<br>
Tutte le classi devono essere nel package <b>warehouse</b>.<br>

La <a href="http://softeng.polito.it/courses/docs/api/index.html" target="api" target="_blank">JDK documentation</a>  
si trova sul server locale.


## R1 - Definizione Prodotti

Il sistema interagisce tramite la classe principale <b>Warehouse</b>.
<br>
Il metodo <b>newProduct() </b>che accetta come parametro il codice e la descrizione della merce, crea un
oggetto <b>Product</b> e lo registra nel magazzino. La classe <b>Product</b> offre
il metodo <b>getCode()</b> per leggere il codice e <b>getDescription()</b> per leggere la descrizione.
<br>
Per definire la quantit&agrave; di merci presenti in magazzino si utilizza il metodo <b>setQuantity()</b>
che riceve come parametro un intero; inoltre c'&egrave; a disposizione il metodo <b>decreaseQuantity()</b>
che decrementa la quantit&agrave; di 1 unit&agrave;. Il metodo <b>getQuantity()</b> permette di sapere la quantit&agrave; corrente.
<br>
Per ottenere l'elenco dei prodotti si usa il metodo <b>products()</b> che restituisce una collezione di oggetti <i>Prodotto</i>. 
Per ottenere uno specifico prodotto si usa il metodo <b>findProduct()</b> che riceve
come parametro il codice e restituisce l'oggetto <i>Product</i> corrispondente.
<br>



## R2 - Definizione Fornitori

Le merci vengono fornite da dei fornitori. Per definire un fornitore si
utilizza il metodo <b>newSupplier()</b> della classe <i>Warehouse</i> che riceve come parametri il codice ed il nome
del fornitore; tale metodo restituisce un oggetto di tipo <b>Supplier</b>.
La classe <i>Supplier</i> offre il metodo <b>getCode()</b> per ottenere il codice e <b>getName()</b> per ottenere il nome.
<br>
Per ottenere uno specifico fornitore &egrave; possibile utilizzare il metodod <b>findSupplier()</b> delle
classe <i>Warehouse</i> che riceve il codice del fornitore.<br>
Per inserire quali merci vengono fornite da un dato fornitore si usa il metodo <b>newSupply()</b> della 
classe <i>Supplier</i> che
riceve come parametro un oggetto di tipo <i>Product</i>.<br>
&Egrave; possibile sapere quali merci sono fornite da un fornitore tramite il metodo <b>supplies()</b> della classe <i>Supplier</i>
che restituisce una list di oggetti <i>Product</i> ordinati alfabeticamente per descrizione.<br>
Viceversa, per sapere quali fornitori forniscono una data merce si utilizza il metodo <b>suppliers()</b>
della classe <i>Product</i> che restituisce una collezione di oggetti <i>Supplier</i> ordinati per nome.
<br>



## R3 - Ordinazione

Quando necessario, un  addetto pu&ograve; emetter un ordine. 
Gli ordini vengono emessi tramite il metodo <b>issueOrder()</b> della  classe <i>Warehouse</i>, 
che riceve come parametri la merce, il fornitore e la quantit&agrave; da ordinare e restituisce un oggetto di classe <b>Order</b>.
<br>
Se il fornitore non &egrave; tra quelli che forniscono la merce specificata viene generata un'eccezione di <b>InvalidSupplier</b>.
<br>
Il magazzino genera un codice alfanumerico univoco nella forma <i>"ORD<span style="font-style: italic;">n</span>"</i> 
dove <span style="font-style: italic;">n</span> &egrave; un numero intero progressivo che parte da 1.
Il codice dell'ordine pu&ograve; essere recuperato tramite il metodo <b>getCode()</b> della classe <i>Order</i>.
<br>
Per ottenere un ordine specifico si utilizza il metodo <b>findOrder()</b> della classe <i>Warehouse</i> 
che riceve il codice e restituisce l'oggetto <i>Order</i> corrispondente.
<br>
Ogni ordine pu&ograve; essere convertito in stringa con il metodo <b>toString()</b> che restituisce una stringa della forma: 
<br>
<p style="text-align: center;">
<i>Order &lt;codice ordine&gt; for &lt;quantit&agrave;&gt; of &lt;codice prodotto&gt; :
 &lt;descrizione prodotto&gt; from &lt;nome fornitore&gt;</i>
<br>


Ad esempio:

<i>"Order ORD2 for 100 of BNN : Banane from Del Monte"</i>




## R4 - Ordini in corso

Gli ordini, quando vengono creati sono inizialmente nello stato non consegnato. 
Per verificare lo stato di consegna si utilizza il metodo <b>delivered()</b> che restituisce un valore booleano. 
Alla ricezione della merce si pu&ograve; invocare il metodo <b>setDelivered()</b> che imposta lo stato di consegna a <i>true</i> 
ed incrementa la quantit&agrave; della merce della quantit&agrave; dell'ordine.
<br>
Se l'ordine era gi&agrave; stato consegnato, viene generata un'eccezione di <b>MultipleDelivery</b>.
<br>
La classe <i>Warehouse</i> offre il metodo <b>pendingOrders()</b> che restituisce una collezione di ordini non ancora consegnati,
ordinati alfabeticamente per codice di prodotto.
<br>
Anche la classe <i>Product</i> offre un metodo <b>pendingOrders()</b> che resituisce la collezione degli ordini 
non ancora consegnati per quello specifico prodotto, ordinati per quantità decrescente.
<br>
Inoltre il metodo <b>deliveryRate()</b> della classe <i>Supplier</i> restituisce la frazione di ordini 
per il fornitore che sono stati consegnati.
<br>


## R5 - Statistiche

Devono essere fornite alcune statistiche sul funzionamento del magazzino, in particolare:
<ul>

<li>il metodo <b>ordersByProduct()</b> restituisce una mappa che raggruppa tutti gli ordini
(sia in sospeso che consegnati) in base al codice del prodotto;

<li>il metodo <b>orderNBySupplier()</b> restituisce il conteggio degli ordini completati per
ciascuno dei fornitori (riportati per nome ed ordinati alfabeticamente).

<li>il metodo <b>countDeliveredByProduct()</b> restituisce una lista di stringhe, ciascuna costituita
dal codice del prodotto ed il conteggio degli ordini consegnati separati da <i>" - "</i>
(es. <i>"BNN - 10"</i>)
ordinati in base al numero decrescente di ordini.

</ul>