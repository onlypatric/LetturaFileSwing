# LetturaFileSwing

Questo progetto è realizzato per permettere di gestire file txt all'interno di cartelle, possiede funzionalità quali:

- apertura di un'altra cartella
- selezione veloce dei file txt nella cartella tramite combobox
- apertura automatica del file txt in scrittura quando viene selezionata la combobox
- salvataggio di eventuali modifiche con il pulsante salva
- possibilità di creare un nuovo file tramite `Menu > File > Nuovo`

Il progetto è stato realizzato utilizzando componenti UI personalizzati, includono `Button`, `Horizontal`, `Vertical`, tramite metodi che ritornano indietro "l'oggetto stesso", possiamo chiamare più metodi nello stesso blocco di codice

## come avviarlo

compilare tutte le classi tramite:

```bash
javac *.java -d build
```

eseguire le classi tramite:

```bash
cd build
java LetturaFileSwing
```

Costruito e testato in Java JDK 22 `64-Bit Server VM build 22+36-2370`

Progetto iniziato da Stefano Punta e completato da Patric Pintescul in versione 1.2
