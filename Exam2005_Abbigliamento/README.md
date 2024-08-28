Programmazione ad Oggetti

Magazzino  

============

Sviluppare l’applicazione che consente di gestire collezioni di abbigliamento.  
Tutte le classi devono essere nel package "abbigliamento".  

* * *

R1 - Definizione Modelli  

---------------------------

I modelli vengono definiti tramite la clase Modello, il cui costruttore riceve come paremetri: il nome del modello, il costo fisso per unità e la quantità di tessuto richiesta per confezionare il modello espressa in metri quadri.  
Deve essere possibile conoscere i valori passati al costruttore tramite dei metodi getter: getNome(), getCosto(), getQuantita().  

R2 - Definizione Materiali e Colori  

--------------------------------------

I materiali sono rappresentati dalla classe Materiale, il cui costruttore riceve come parametri: il nome ed il costo al metro quadro. Tramite i metodi getNome() e getCosto() è possibilie conoscere nome e costo.  
I colori sono rappresentati dalla classe Colore, il cui costruttore riceve come parametro il nome. Il nome è accessibile tramite il metodo getNome().  

R3 - Definizione dei Capi  

----------------------------

I capi sono definiti in termini di modelli confezionati con un certo materiale. Il costruttore della classe Capo riceve come parametri il modello, il materiale, e il colore.  
Il metodo prezzo() permette di calcolare il prezzo di un singolo capo:  
  

prezzo = costoFisso + quantitàMateriale \* costoMateriale  

  
Deve essere possibile stampare un capo tramite il metodo toString() che restituisce una stringa strutturata come segue:  
`<modello> <colore> di <materiale>`, ad esempio t-shirt rosa di cotone.  

R4  - Collezione  

-------------------

Una collezione è un insieme di capi ed è rappresentata dalla classe Collezione.  
E' possibile aggiungere un capo ad una collezione tramite il metodo add().  
La classe Collezione definisce una serie di metodi per ricercare capi all'interno di una collezione. Il metodo trova() viene definito in tre varianti che hanno come parametro un Modello, un Colore, un Materiale. Il metodo restituisce una collezione di Capi che utilizzano il modello, colore, o materiale specificato.  
  

R5  - Lettura da file  

------------------------

È possibile leggere le informazioni da file. La lettura viene gestita dalla classe Abbigliamento, tramite il metodo leggiFile().  
Le informazioni sono memorizzate, un elemento per linea.  
I modelli sono definiti in linee che iniziano con MOD, seguiti da nome, costo fisso e quantita' di materiale, tutti separati da ";".  
I colori sono definiti in linee che iniziano con COL, seguiti da nome, separati da ";".  
I materiali sono definiti in linee che iniziano con MAT, seguiti da nome e costo del materiale, tutti separati da ";".  
I capi sono definiti in linee che iniziano per CAP, seguiti da nome, nome del modello, nome del materiale, e nome del colore, tutti separati da ";".  
Le collezioni sono descritte in linee che iniziano con COL, seguito dai nomi dei capi tutti separati da ";".  
  
Dopo la lettura si possono usare i metodi: getModello(), getColore(), getMateriale(), getCapo(), e getCollezione(). Essi ricevono come parametro il nome e restituiscono l'elemento corrispondente.  

* * *