# Issue Management

Si sviluppi un programma che consenta ad un'azienda di trattare le segnalazioni di anomalie relative ai componenti software che  vende.
La classe principale si chiama <b>IssueManager</b>; tutte le classi si trovano nel package <b>ticketing</b>.
    La classe <b>Example</b> presenta esempi di uso dei metodi principali.<br>
    Le eccezioni lanciate dal programma sono di tipo <b>TicketException</b>.


La <a href="https://oop.polito.it/api" target="api" target="_blank">JDK documentation</a>  si trova sul server locale.



## R1: Users

Il sistema ammette due ruoli di utenti: <i>Reporter</i> and <i>Maintainer</i>. Un utente pu&ograve; svolgere un solo ruolo o entrambi.
Il metodo <b>createUser()</b> riceve uno username e il set dei ruoli (<b>UserClass</b>es) che l'utente svolge.
In alternativa al set si pu&ograve; usare una lista variabile di argomenti.

    In entrambi i casi il metodo lancia un'eccezione se lo username &egrave; gi&agrave; stato inserito o se nessun ruolo &egrave; indicato.    

Dato uno username si pu&ograve; ottenere il set dei ruoli dell'utente corrispondente con il metodo <b>getUserClasses().</b></p>



## R2: Components
    
I componenti forniti dall'azienda sono costituiti ricorsivamente da sotto-componenti. 
Il metodo <b>defineComponent()</b> genera un nuovo componente dato il nome e lancia un'eccezione se esiste gi&agrave; un componente con quel nome.

<svg viewBox="-10 -10 1010 820" style="float:right;height:8.2em;width:10.2em">
    <style>
    svg text {
    	font-size:50px;
    	font-family:sans-serif;
    }
    svg ellipse {
    	stroke: purple;
    	fill: rgb(250,240,230);
    	stroke-width: 4px;
    }
    svg line {
    	stroke: purple;
    	stroke-width: 4px;
    }
    svg .new {
    	stroke: orange;
    	stroke-dasharray: 5,5;
    	fill: white;
    }
    </style>
    <line x1="500" y1="100" x2="155" y2="400"/> 
    <line x1="500" y1="100" x2="500" y2="400"/> 
    <line x1="500" y1="100" x2="845" y2="400" class="new"/> 
    <line x1="500" y1="400" x2="300" y2="700"/> 
    <line x1="500" y1="400" x2="700" y2="700" class="new"/> 
	<ellipse cx="500" cy="100" rx="150" ry="100"/>
    <text x="500" y="100" text-anchor="middle" alignment-baseline="middle" style="">
    System
  	</text>
	<ellipse cx="155" cy="400" rx="150" ry="100" />
    <text x="155" y="400" text-anchor="middle" alignment-baseline="middle">
    Sub A
  	</text>
	<ellipse cx="500" cy="400" rx="150" ry="100" />
    <text x="500" y="400" text-anchor="middle" alignment-baseline="middle" >
    Sub B
  	</text>
	<ellipse cx="845" cy="400" rx="150" ry="100" class="new" />
    <text x="845" y="400" text-anchor="middle" alignment-baseline="middle" >
    Sub C
  	</text>
	<ellipse cx="300" cy="700" rx="150" ry="100" />
    <text x="300" y="700" text-anchor="middle" alignment-baseline="middle" >
    Sub B.1
  	</text>
	<ellipse cx="700" cy="700" rx="150" ry="100" class="new" />
    <text x="700" y="700" text-anchor="middle" alignment-baseline="middle" >
    Sub B.2
  	</text>
</svg>

Il metodo <b>defineSubComponent()</b> genera un nuovo sotto-componente dati il nome e il path che identifica il predecessore (componente o sotto-componente) di cui il nuovo elemento diventa sotto-componente. Lancia un'eccezione se il predecessore non esiste o se ha gi&agrave; un sotto-componente con lo stesso nome. 

    Esempio: dato il sistema in figura, per aggiungere <i>SubC</i> al componente System si scrive: <i>tm.defineSubComponent("SubC","/System");</i>, mentre per aggiungere 
    <i>SubB.2</i> si scrive: <i>tm.defineSubComponent("SubB.2","/System/SubB");</i>.


Dato il path di un elemento (componente o sotto-componente) si pu&ograve; ottenere il set dei nomi semplici dei sotto-componenti con il metodo 
<b>getSubComponents()</b> e il path del predecessore con il metodo <b>getParentComponent()</b> (che d&agrave; null se l'elemento non ha un predecessore).
    
Si noti che i path iniziano con <i>'/'</i> e contengono la lista dei (sotto-)componenti,
    a partire dal componente di top-level, separati da <i>'/'</i>.
    
## R3: Ticket opening
    
Un utente pu&ograve; aprire un ticket che contiene i dettagli di un'anomalia riguardante un dato elemento.</p>
    
Un ticket &egrave; aperto con il metodo <b>openTicket()</b> che riceve lo username dell'utente, il path dell'elemento difettoso, la descrizione dell'anomalia e la severit&agrave; (<b>Severity</b>) della stessa.
    Il metodo d&agrave; un id univoco (intero progressivo a partire da 1) per il ticket.
    Lancia un'eccezione se lo username non &egrave; valido, il path non identifica alcun elemento, o se l'utente non svolge il ruolo  <i>Reporter</i>.

Il metodo <b>getTicket()</b> riceve un id e d&agrave; l'oggetto <b>Ticket</b> corrispondente oppure null se l'id non &egrave; valido. Il metodo  <b>getAllTickets()</b> d&agrave; la lista dei ticket ordinata naturalmente (si legga la nota).
    
La classe <i>Ticket</i> offre i metodi getter <b>getDescription()</b>, <b>getId()</b>, <b>getComponent()</b>,  <b>getAuthor()</b> e <b>getSeverity()</b>.

<b>Nota</b>:
    Gli oggetti che compongono un tipo enumerativo implementano automaticamente l'interfaccia <i>Comparable</i>; l'ordinamento naturale corrisponde all'ordine con cui gli oggetti sono definiti nel tipo enumerativo. Quindi nella Severity <i>Blocking</i> precede <i>Major</i>.

    


## R4: Ticket lifecycle
    
I ticket hanno tre stati: <i>Open</i>, <i>Assigned</i>, <i>Closed</i>. Quando &egrave; aperto, un ticket &egrave; posto nello stato <i>Open</i>.


Il metodo <b>assignTicket()</b> riceve un ticket id e uno username:  porta lo stato del ticket a <i>Assigned</i>
e collega il ticket all'utente come assegnatario del ticket. Lancia un'eccezione se il ticket id o lo username non sono validi, o se l'utente non svolge il ruolo <i>Maintainer</i>.


Il metodo <b>closeTicket()</b> riceve un ticket id e la descrizione della soluzione, e porta lo stato del ticket a <i>Closed</i>. Lancia un'eccezione se il ticket non si trova nello stato <i>Assigned</i>.

La classe <i>Ticket</i> offre il metodo getter <b>getState()</b>, che d&agrave; lo stato corrente del ticket.

Il metodo <b>getSolutionDescription()</b> della classe <i>Ticket</i> d&agrave; la descrizione della soluzione; lancia un'eccezione se il ticket non si trova nello stato <i>Closed</i>.



## R5: Statistics
    
Il metodo <b>countBySeverityOfState()</b> dato uno stato dei ticket fornisce una mappa con il numero di ticket per 
Severity, considerando soltanto i ticket in quello stato oppure tutti i ticket se l'argomento &egrave; nullo. La mappa &egrave; ordinata in base alla Severity.

Il metodo <b>topMaintainers()</b> d&agrave; una lista di stringhe: ogni stringa ha il formato <i>"username:###"</i> dove <i>username</i> &egrave; il nome dell'utente e <i>###</i> &egrave; il numero di ticket chiusi dall'utente come maintainer. 
La lista &egrave; ordinata per numero decrescente di ticket e poi per username (in ordine alfabetico).
