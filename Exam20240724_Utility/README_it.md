# Utility

I requisiti in Inglese sono disponibili [qui](README.md).

Creare un programma per la gestione della bollettazione di un'azienda di servizi pubblici (es. acqua, elettricità, gas)

L'interazione avviene tramire la classe facciata `Utility`.
Tutte le classi sono contenute nel package `it.polito.po.utility`.

## R1. Punti di Presa e Misuratori

Un punto di presa rappresenta il punto a cui gli utenti finali possono allacciarsi per usufruire della fornitura del servizio.

Un punto di presa viene definito tramite il metodo `defineServicePoint()` che riceve come parametri il comune, l'indirizzo e la posizione geografica in termini di latitudine e longitudine.
Il metodo restituisce un codice alfanumerico unico assegnato automaticamente dal sistema, il codice deve iniziare con `"SP"` e può essere seguito da cifre e lettere.

Un punto di servizio può essere collegato a un misuratore.

Un misuratore può essere aggiunto all'inventario con il metodo `addMeter()` che riceve come parametri un numero di serie, la marca, ed il modello e l'unità di misura. Il metodo restituisce un codice univoco che ha come prefisso `"MT"`.

È possibile collegare un misuratore ad un punto di presa con il metodo `installMeter()` che riceve come parametri il codice del punto di presa e quello del misuratore.

È possibile otterene i codici di tutti i punti di presa tramite il metodo `getServicePoints()` che restituisce una collezione di codici.
Per conoscere i dettagli di un punto di presa è possibile utilizzare il metodo `getServicePoint()` che dato il codice univoco restituisce un oggetto di tipo `ServicePoint`.
L'interfaccia `ServicePoint`, da implementarsi nella soluzione dell'esame,  offre i seguenti metodi getter: `getID()`, `getMunicipality()`, `getAddress()`, `getPosition()`, e `getMeter()` .

Per conoscere i dettagli di un misuratore è possibile utilizzare il metodo `getMeter()` che dato il codice univoco restituisce un oggetto di tipo `Meter`.
L'interfaccia `Meter`, da implementarsi nella soluzione dell'esame,  offre i seguenti metodi getter: `getSN()`, `getBrand()`, `getModel()`, `getUnit()` e `getServicePoint()`.



## R2. Utenti e Contratti

Gli utenti sono i fruitori a cui vengono forniti i servizi. Possono essere di due tipi:

- `ResidentialUser` rappresenta un utente domestico. È caratterizzato da un codice fiscale, un nome, un cognome, un indirizzo fisico e un indirizzo email.
- `BusinessUser` rappresenta un utente commerciale. È caratterizzato da una partita IVA, una ragione sociale, un indirizzo fisico e un indirizzo email. 

Entrambi i tipi di utenti possono essere registrati tramite il metodo `addUser()` che riceve come parametri i dati dell'utente e restituisce un codice univoco che ha come prefisso `"U"`.

È possibile ottenere i codici di tutti gli utenti tramite il metodo `getUsers()` che restituisce la collezione dei codici di tutti gli utenti.

Per conoscere i dettagli di un utente è possibile utilizzare il metodo `getUser()` che dato il codice univoco restituisce un oggetto di tipo `User`.

L'interfaccia `User`, da implementarsi nella soluzione dell'esame,  offre i seguenti metodi getter: `getID()`, `getCF()`, `getName()`, `getSurname()`, `getAddress()` e `getEmail()`.

È possibile stipulare un contratto di fornitura di servizi con un utente tramite il metodo `signContract()` che riceve come parametri il codice dell'utente e il codice del punto di presa. Il metodo restituisce un codice univoco che ha come prefisso `"C"`.

Se il codice utente o il codice del punto di presa non esistono, oppure se il punto di presa non ha un misuratore collegato, il metodo lancia un'eccezione `UtilityException`.

Per conoscere i dettagli di un contratto è possibile utilizzare il metodo `getContract()` che dato il codice univoco restituisce un oggetto di tipo `Contract`.
L'interfaccia `Contract`, da implementarsi nella soluzione dell'esame,  offre i seguenti metodi getter: `getID()`, `getUser()`, `getServicePoint()`.


## R3. Letture

Periodicamente vengono effettuate delle letture dei misuratori per calcolare il consumo di un utente.

Una lettura viene registrata tramite il metodo `addReading()` che riceve come parametri il codice del contratto, il codice del misuratore, la data della lettura e il valore rilevato. Viene lanciata un'eccezione se il contratto e il misuratore non corrispondono.

Dato un id del contratto è possibile ottenere tutte le letture effettuate con il metodo `getReadings()` che restituisce una mappa che associa la data della lettura al valore rilevato.

Dato un contratto è possibile ottenere l'ultimo valore rilevato con il metodo `getLastReading()` che restituisce il valore rilevato nell'ultima lettura effettuata.


## R4. Bollette

A partire da una data è possibile conoscere la lettura stimata di un contratto con il metodo `getEstimatedReading()` che riceve come parametri il codice del contratto e la data di riferimento.
La lettura è stimata come segue:

- se la data corrisponde ad una lettura effettiva, viene restituita quella;
- se la data cade tra due letture, la lettura stimata ($\hat y$) viene calcolata con una interpolazione lineare basata sulle differenza in giorni: 

    $$ \hat y = y_1 + ( t - t_1) \cdot \frac{y_2 - y_1}{t_2 - t_1} $$

    dove, $y_1$ e $y_2$ sono le letture nelle date $t_1$ e $t_2$ che immediatamente precedono e seguono la data richiesta $t$.

- se la data è successiva all'ultima lettura, viene calcolata una stima sulla base del trend esistente tra le ultime due letture, la formula è la stessa ma $y_1$ e $y_2$ sono le letture nelle ultime due date disponibili $t_1$ e $t_2$, ovvero $t_1 \lt t \lt t_2$.

- se la data precede la prima lettura o se non ci sono almeno due letture già acquisite, viene lanciata un'eccezione.

A partire da una data è possibile calcolare il consumo di un contratto con il metodo `getConsumption()` che riceve come parametri il codice del contratto e le date di riferimento.
Il metodo lancia una `UtilityException` se l'ID del contratto non è valido o non è possibile stimare una lettura per le date.

È possibile ottenere il dettaglio di una bolletta con  il metodo `getBillBreakdown()` che riceve come parametri il codice del contratto il mese di partenza, quello finale e l'anno. Il metodo ritorna `UtilityException` se il contratto non esiste.
Per ciascuno dei mesi della bolletta, si riportano le letture al 1 di quel mese e al primo del mese successivo e il relativo consumo.
Il metodo restituisce una lista di stringhe formattate come segue: `"<data-iniziale>..<data-finale>: <lettura iniziale> -> <lettura finale> = <consumo>"`.

Suggerimento:

- è possibile usare il metodo `toEpochDay()` della classe `LocalDate` per ottenere il numero di giorni trascorsi dal 1970-01-01. Es. `LocalDate.parse("2024-07-24").toEpochDay()` restituisce 19928.
- nel calcolo dei consumi e per il breakdown se le date non coincidono con quelle esatte delle letture è necessario usare le stime calcolate col metodo `getEstimatedReading()`