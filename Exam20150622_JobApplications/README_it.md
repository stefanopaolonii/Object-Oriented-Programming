Gestione Domande di Impiego
===========================

Il programma permette ad un'azienda di ricevere domande per posizioni scoperte. La classe principale è **HandleApplications**; le classi del programma si trovano nel package **applications**. La classe di test (**Test**) contiene un esempio che illustra l'uso dei metodi principali; è utile leggere i requisiti guardando questa classe. Le eccezioni lanciate dal programma sono di tipo **ApplicationException**.

È possibile accedere ad una copia locale della [documentazione JDK](http://softeng.polito.it/courses/docs/api/index.html).

R1: Inserimento di skills e positions
-------------------------------------

Il metodo **addSkills** inserisce un elenco di competenze dati i nomi. Lancia un'eccezione se trova una competenza duplicata.

Il metodo **addPosition** inserisce una posizione (dato il nome) con l'elenco delle competenze richieste (di cui sono dati i nomi). Lancia un'eccezione se la posizione è già stata inserita o se non trova una competenza richiesta tra quelle già inserite.

Il metodo **getSkill** fornisce un oggetto **Skill** dato il nome; se non lo trova tra quelli inseriti dà null.

Il metodo **getPositions** di _Skill_ dà la lista delle posizioni che chiedono lo skill, ordinate alfabeticamente per nome. Il metodo **getPosition** fornisce un oggetto **Position** dato il nome; se non lo trova tra quelli inseriti dà null. _Skill_ e _Position_ hanno i getter **getName**.

R2: Inserimento di richiedenti
------------------------------

Il metodo **addApplicant** inserisce un richiedente (dato il nome) con l'elenco delle sue capacità (capabilities). Un esempio di elenco è il seguente: _"java:9,sql:7"_. Ogni capacità è costituita dal nome della competenza, dal separatore _':'_, e dal livello (compreso tra 1 e 10) posseduto dal richiedente. Le capacità sono separate da virgole. Il metodo lancia un'eccezione se il richiedente è già stato inserito, una competenza non è stata inserita, un livello non appartiene al range stabilito.

Il metodo **getCapabilities** fornisce l'elenco delle capacità di un richiedente (dato il nome) nel formato indicato sopra: le capacità sono però ordinate alfabeticamente. Lancia un'eccezione se non trova il richiedente tra quelli già inseriti. Se non ci sono capacità dà la stringa vuota.

R3: Candidature
---------------

Il metodo **enterApplication** permette ad un richiedente di candidarsi ad una posizione. Lancia un'eccezione se il richiedente o la posizione non sono stati inseriti, se il richiedente non ha una capacità per ciascuno skill richiesto dalla posizione o si è già candidato ad una posizione. Ad esempio, se la posizione chiede gli skill java e uml, il richiedente deve avere tra le sue capabilities quella per java e quella per uml.

Il metodo **getApplicants** di _Position_ fornisce la lista ordinata alfabeticamente dei nomi dei candidati alla posizione.

R4: Vincitori
-------------

Il metodo **setWinner** stabilisce il vincitore (richiedente) per una posizione. Lancia un'eccezione se il richiedente non si è candidato alla posizione, se c'è già un altro vincitore per la stessa posizione, o se la somma delle capacità relative agli skill della posizione non è maggiore del numero degli skill moltiplicati per 6 (ad es. per una posizione con 2 skill la soglia da superare è 12). Fornisce la somma delle capacità del vincitore.

Il metodo **getWinner** di _Position_ dà il nome del vincitore o null se manca.

R5: Statistiche
---------------

Il metodo **skill\_nApplicants** dà il numero di richiedenti per skill; gli skill sono ordinati alfabeticamente per nome.

Il metodo **maxPosition** dà il nome della posizione con il maggior numero di candidati.