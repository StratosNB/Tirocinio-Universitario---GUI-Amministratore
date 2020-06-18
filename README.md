GUI for the system administrator

## **Gestione degli User e Client:**

**Principalli funzionalità:**

•	Visualizzazione, creazione, modifica e cancellazione degli User e Client presenti da parte dell’utente amministratore.

•	Visualizzazione, creazione, modifica e cancellazione degli attributi aggiuntivi degli User e Client e degli attributi di Object e Enviroment da parte dell’utente amministratore.

•	Possibilità per gli utenti non amministratori de cambiare la loro password, una volta autenticati.

•	Ricerca degli User e Client in base ai loro attributi.

**Design Patterns:**

1. MVC (Model View Controller)
2. DAO (Data Access Object)
3. EAV (Entity Attribute Value)


**Tecnologie usate:**

1.	Eclipse
2.	PostgreSQL
3.	JavaScript
4.	Redis

**Librerie esterne:**

1.	Swing
2.	MiGLayout
3.	RSyntaxTextArea
4.	RSTALanguageSupport
5.	Autocomplete
6.	Rhino
7.	Javassist
8.	Rs2xml
9.	Postgresql JDBC Driver
10.	Jedis


## **Manuale Utente**

**Login**

Al tempo in cui apriamo una delle due applicazioni scopriremo le finestre di login, che ci chiederanno le credenziali di accesso all'applicazione. Se le nostre credenziali sono correte, accediamo alla corrispondente applicazione.

![In a single picture](https://i.imgur.com/1jKMLYI.png)



**Gestione degli User e Client**

La gestione di queste due entità si fa a traverso le finestre di gestione degli User e Client dell'applicazione dell’utente amministratore, queste finestre vengono aperte facendo click nei tabs “Users” o “Clients”.
Nelle Figure 4 e 5 possiamo osservare che entrambe finestre seguono una strut-tura simile, l'unica differenza è nel bottone "Client ..." che si trova nella fi-nestra di gestione degli User. Questo apre la finestra responsabile dell'assegna-zione dei Client agli User.

![In a single picture](https://i.imgur.com/XMIjQUw.png)

•	Nella parte inferiore delle due finestre troviamo i bottoni "Delete" e "Add ...", questi servono per cancellare l'utente che stiamo visualizzando e di creare uno nuovo e in mezzo di questi bottoni troviamo i bottoni di naviga-zione: “First”, “Previous”, “Next”, “Last” questi ci permettono di navigare tra i record degli utenti presenti nel database e l'ordine di visualiz-zazione è dato dall'ID utente.

•	Nella parte superiore a destra degli attributi fissi troviamo i bottoni “Search...” e “Edit”, 

•	questi aprono le finestre per effettuare la ricerca e modificare i dati dell'utente che stiamo visualizzando.

**Visualizzazione**

Il meccanismo per la visualizzazione degli User e Client è abbastanza semplice, se vogliamo visualizzare il primo User/Client utilizziamo il bottone “First”, per vedere l'ultimo, usiamo “Last”, per andare avanti o indietro da un disco che usiamo “Next” e “Back”.

![Nella Figura possiamo visualizzare i bottoni di navigazione:]( https://i.imgur.com/9YZp2TC.png)

**Creazione e cancellazione** 

**Creazione**
1.	Per creare un User / Client, andiamo alla finestra di creazione che si apre con il bottone “Add...”, nella Figura 6 viene mostrata questa finestra.

2.	In questa finestra si scrivono i valori degli attributi di un nuovo User/Client e confermeremo con il buttone “OK”.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/0CqQYyA.png) 

Il nuovo User/Client che è stato appena creato sarà visibile dalla finestra di vi-sualizzazione degli User.


**Cancellazione**
1.	Per cancellare i User/Client che stiamo visualizzando, usiamo il bottone “Delete” che troviamo nella parte inferiore sinistra della finestra di ge-stione degli User.

2.	Apparirà un dialogo che ci chiede di confermare la nostra scelta. Come nella Figura 7.

3.	Facciamo clic sul "Sì" per confermare.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/tW4xWg3.png) 


**Modifica**

1.	Per modificare gli User/Client che stiamo visualizzando nelle finestre di ge-stione, apriamo la finestra di modifica (Figura 8), che viene aperta con il bot-tone “Edit...”.

2.	In questa finestra possiamo modificare i valori degli attributi degli User/Client in questione.

3.	Per confermare le modifiche premere il bottone “OK”.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/BZrPGVl.png) 


**Ricerca**

1.	Per ricercare gli User/ Client presenti, dobbiamo aprire la finestra di ricerca, utilizzando il bottone “Search”, che si trova nella finestra di gestione degli User/Client viste nelle Figure 3 e 4, che si trova nelle finestre di gestione de-gli User e Client. Nella Figura 12 vediamo la finestra di ricerca degli User:

 
Figura 8 Finestra di Ricerca degli User

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/JVfjW0f.png) 

2.	Nei campi di texto che troviamo a sinistra della finestra scriviamo i valori degli attributi degli User/Client che desideriamo cercare.

3.	Scegliamo con quale operatore logico realizzare la ricerca, cominciamo la ri-cerca con il bottone “Search” risultati della ricerca appariranno a destra nel-la tabella dei risultati.
In questa finestra, addizionalmente alla funzione di ricerca di un singolo User/Client, possiamo anche enumerare tutti gli User/Client presenti e modifica-re o eliminare gli utenti dalla tavola dei risultati.

**Assegnazione dei Client agli User

Questa funzione è disponibile per gli User. La finestra di assegnazione dei Client si apre facendo clic sul bottone "Client ...”. Nella Figura 13 vediamo la fi-nestra di assegnazione dei Client:


![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/HkMrUxJ.png) 


In questa finestra abbiamo due tabelle, sulla sinistra c'è la tabella dei Client che sono assegnati all'User che stiamo visualizzando; nella finestra di gestione degli User, sulla destra ci sono i Client "disponibili" o che non sono ancora stati as-segnati a nessun utente.
•	Se vogliamo assegnare un client all'User, scegliere uno dei Client dispo-nibili e premere il bottone "<<".

•	Se si desidera annullare l’assegnazione di un Client all’User selezionare il Client di nostro interesse nella tabella che contiene i Client assegnati all’User e premere il bottone “>>”. 


6.2.6	Gestione degli attributi

Per gestire gli Attributi di User/Client e Object/Enviroment dobiamo aprire le finestre di gestione dei Attributi rispettivi, i tabs di queste finestre portano questi nomi “Users and Clients Attributes” e “Object and Enviroment At-tributes”.

Nella Figura 10 vediamo la finestra di gestione degli Attributi di User/Clients.
 
![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/FCvdnvH.png) 

**Creazione**

1.	Per creare un attributo pulsiamo il bottone "Add ..." e si aprira un dialogo come nella Figura 11 nella quale scriveremo il nome e il tipo di questo attributo.

2.	Una volta precisati i dati, usiamo il bottone "OK" per confermare la crea-zione di un nuovo attributo 

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/FUKoFtO.png)

**Modifica**
1.	Scegliere con un click l'attributo del nostro interesse e pulsare il bottone “Edit". Successivamente si aprirà una finestra di dialogo simile a quella della Figura 11.

2.	Una volta che i dati sono stati modificati, utilizziamo "OK" per confermare.
**Cancellazione**
1.	Scegliere con un click l’attributo che vogliamo cancellare.

2.	Fare click nel buttone “Delete”, si aprirà un dialogo di finestra simile a quello della Figura 8.

3.	Nell dialogo di conferma premere il buttone “OK” per confermare cancella-zione.



**Gestione delle Access Control Policies e User Preferences**

Per gestire le Policies e le Preferences, dobbiamo aprire le finestre di gestione facendo click nei tabs “Access Control Policies” dall'applicazione dell’utente amministratore o “User Preferences” dall'applicazione dell'utente non amministratore.
 
![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/ZKJ975e.png)

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/xQ4mAwp.png)
            
La finestra di gestione delle Access Control Policies si basa sulla stessa struttura delle finestre di visualizzazione degli User/Clients viste nelle Figure 3 e 4. Nel caso delle User Preferences, c'è una tabella a sinistra con gli ID di queste come possiamo vedere nella Figura 13.


**Visualizzazione**

•	Il meccanismo di visualizzazione delle Access Control Policies è similare a quello che abbiamo visto in precedenza nella Figura 5, con i bottoni “First”, “Previous”, “Next” y “Last”.

•	Il meccanismo di visualizzazione delle User Preferences è stato implementa-to in modo diverso:

1.	Per visualizzare i dati di una preferenza, prima scegliamo l'id di uno di essi, nella tabella delle User Preferences che si vede nella Figura 14, nella parte sinistra della finestra.

2.	I dati delle Preferenze, relativi al Topic Filter Expression e Parametric Predicate scelti saranno visualizzati nei componenti grafici che si tro-vano nella parte destra della finestra.


**Gestione delle funzioni JavaScript**

Per gestire le funzioni JavaScript dobbiamo usare le finestre di Gestione delle funzioni JavaScript, che si aprono da entrambi applicazioni facendo click sul tab di nome “JavaScript Functions”. 
Nella Figura 15 vediamo la finestra per gestire le funzioni dell’utente ammini-stratore:

•	In questa finestra osserveremo a sinistra una tabella con tutte le funzioni presenti.

•	Sulla destra troviamo un editor Js nel qualle possiamo vedere il codice della funzione scelta.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/RJsIfti.png)

**Creazione**
1.	Per creare una nuova funzione, facciamo clic nel buttone “Add...”, e si aprirà la finestra di creazione come nella Figura 16.

2.	Nell'editor di questa finestra scriveremo la funzione JavaScript.

3.	Una volta scritta la funzione dobbiamo validarla facendo click sul buttone “Validate”.

4.	Se nel quadro di “Validation Output”, vediamo il messaggio: “No sysntax errors where found” possiamo confermare la creazione con il buttone “OK”. Se c'è un errore, nel quadro di “Validation Output” vedremo un messaggio come riportato nella Figura 17

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/QrMkZKi.png)


**Modifica**
1.	Se vogliamo modificare una funzione dobbiamo prima scegliere la fun-zione che vogliamo modificare nella finestra di gestione delle funzioni vi-sta nella Figura 15 e premere il buttone "Edit ...", la finestra che si aprirà è simile a quella utilizzata per creare le funzioni che abbiamo visto nella Figura 16, l'unica differenza è che nell’editor vedremo il codice della funzione che abbiamo scelto.

2.	Da ora in poi il processo è molto simile a quello che abbiamo visto nella creazione delle funzioni, facciamo le modifiche che vogliamo alla funzio-ne, validiamo la funzione e confermiamo con il bottone "OK".

**Cancellazione**
1.	Il processo di cancellazione è semplice, e simile a quello visto prima nella gestione degli Attributi, dobbiamo scegliere la funzione che vogliamo ri-muovere, nella finestra di gestione delle funzioni vista nella Figura 15 e pulsare “Delete". 

2.	Apparirà un dialogo di conferma come quello della Figura 12, pulsare “Si” per confermare.

**Creazione e Cancellazione**

Creazione
1.	Per creare una nuova Policy/Preference, utilizziamo il bottone "Add ..."che si trova nella finestra di gestione delle Policies/Preferences per aprire la finestra di creazione come riportato nella Figura 18. 

2.	In questa finestra dobbiamo inserire i dati necessari per la creazione della Policy/Preferenze, solo nel caso delle Policies dovremo specificare l’ID dell’User o Client.

3.	Per creare il “Parametric Predicate” utilizziamo gli attributi degli User/Client, Object e Environment insieme alle funzioni JavaScript, per vedere questi attributi dobbiamo scrivere l'iniziale di questi (s Subject, e Enviroment, o Object y f functions) seguito da un punto.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/vIDK6OO.png)

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/UoMaKGT.png)

Per esempio, se vogliamo vedere tutti gli attributi del Subject, scriviamo "s.” Nell'editor dei Parametric Predicate nella Figura 19:

Gli Attributi saranno esibiti come suggerimenti, possiamo utilizzare i bot-toni o il puntatore per scegliere uno di essi, il meccanismo di Auto-Complete similare a quello di Eclipse.

4.	Una volta validata la funzione come abbiamo visto anche nella finestra di creazione di funzioni Js della Figura 16, confermiamo la creazione con il buttone “OK”.

**Cancellazione**

La cancellazione di una Policy o Preferenze avviene di maniera simile a quella vista con gli User/Client.
1.	Per cancellare una Policy/Preferenze navighiamo verso la Policy di nostro interesse con i bottoni di navigazione oppure scegliamo l’id della Prefe-renze nelle finestre di gestione e usiamo il buttone “Delete”.

2.	Apparirà un Dialogo di conferma come quello che abbiamo visto prima nella Figura 12, usiamo il bottone “Si” per confermare la cancellazione.

**Ricerca**

La ricerca di Access Control Policies e User Preferences è stata implementata in maniera diversa dalla ricerca degli User/Client. 

1.	Se vogliamo cercare una Policy/ Preference, apriamo la finestra di ricerca con il bottone "Search ...",che troviamo nelle finestre di gestione delle Policies/Preferences, viste nelle Figure 13 e 14.

![Nella Figura possiamo visualizzare i bottoni di navigazione:](https://i.imgur.com/V5d2KD4.png)

 
Figura 19 Finestra di Ricerca delle Access Control Policies
2.	Solo nel caso delle policy, sarà indispensabile determinare l'id degli User/Client.

3.	Usiamo il buttone “Search". La tabella dei risultati verrà popolata con tutte le Policy associate all'ID che abbiamo scelto.

4.	Filtriamo i risultati scrivendo il “Topic Filter Expression” nel campo che si trova nella parte superiore della tabella dei risultati.


**Modifica**

**Modificare una Access Control Policy:**

•	La modifica di una policy avviene in maniera simile a quello che abbiamo vi-sto nella modifica di User/Client, la finestra che si aprirà quando si usa il bot-tone “Edit...” sarà somigliante alla finestra di creazione della politica vista nella Figura 18 e il processo sarà lo stesso a quello della creazione.

Modificare una User Preference:
•	Le Preferences possono essere modificate direttamente dalla finestra di ge-stione delle Preferences (Figura 14). Quello che dobbiamo fare è scegliere semplicemente la User Preference che vogliamo e modificare i dati dall'e-spressione dei “Topic Filter Expression” e “Parametric Predicate”. Una volta fatte le modifiche, confermiamo con il bottone “Update”.

•	Apparirà un messaggio indicando il avvenuto successo della modifica.


**Esportazione su Redis:**

1.	Nelle finestre di gestione delle Policies/Preferences (Figure 13 e 14) usiamo il bottone “Export...”.

2.	Apparirà un dialogo che ci chiede di confermare l'operazione, confermiamo la nostra selezione con il buttone “Si”.

3.	Un messaggio apparirà per informarci che la esportazione è avvenuta con successo.
