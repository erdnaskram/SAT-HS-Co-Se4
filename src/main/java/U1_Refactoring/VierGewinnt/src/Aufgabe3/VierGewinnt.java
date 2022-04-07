package U1_Refactoring.VierGewinnt.src.Aufgabe3;


import java.io.InputStream;
import java.io.PrintStream;

import static U1_Refactoring.VierGewinnt.src.Aufgabe3.VierGewinntIO.*;

public class VierGewinnt {

    static String SPIELER; // der aktuelle Spielername -> fuer die Gewinner Ausgabe

    private static InputStream inputStream;
    private static PrintStream printStreamOut;
    private static PrintStream printStreamErr;

    public VierGewinnt(InputStream inputStream, PrintStream printStreamOut, PrintStream printStreamErr) {
        VierGewinnt.inputStream = inputStream;
        VierGewinnt.printStreamOut = printStreamOut;
        VierGewinnt.printStreamErr = printStreamErr;
    }

    /**
     *
     */
    public void play() {
        int columns, rows, zaehler = 0, eingabe;
        String player1, player2;
        char zeichen;
        char[][] spielfeld;
        Character winner = null;

        //Abfragen des Spielernamens
        player1 = eingabeString("Name von SpielerIn A\t\t\t: ", inputStream, printStreamOut);

        do {
            player2 = eingabeString("Name von SpielerIn B\t\t\t: ", inputStream, printStreamOut);
        } while (player1.equals(player2)); //Frage erneut, wenn die Spielernamen gleich sind

        //Abfragen der Masse
        do {
            columns = eingabeInt("Breite des Spielfeldes (mindestens 4)\t: ", inputStream, printStreamOut);
        } while (columns < 4); //Frage erneut, wenn die Breite zu klein gewaehlt wurde

        do {
            rows = eingabeInt("Hoehe des Spielfeldes (mindestens 4)\t: ", inputStream, printStreamOut);
        } while (rows < 4); //Frage erneut, wenn die Hoehe zu klein gewaehlt wurde

        spielfeld = new char[rows][columns];

        while (zaehler < columns * rows && winner == null) {
            zeichen = (zaehler % 2 == 0) ? 'o' : '+';
            SPIELER = (zaehler % 2 == 0) ? player1 : player2;
            showSpielfeld(spielfeld, printStreamOut);
            eingabe = eingabeInt("\n" + SPIELER + "(" + zeichen
                    + ") ist am Zug. Bitte gib die Spalte ein: ", inputStream, printStreamOut);
            if (eingabe > columns || eingabe < 1)

                printStreamErr.println("Feld existriert nicht.. Bitte versuch es nochmal!");
            else {
                zaehler++; // naechster Bitte
                winner = setzeFeld(spielfeld, eingabe, zeichen);
            }
        }
        if (winner == null) {
            showSpielfeld(spielfeld, printStreamOut);
            printStreamErr.println("Unentschieden!");
        }
    }

    /**
     * Spalte wird uebergeben und das Feld wird gesetzt
     *
     * @param spielfeld -> Das Spielfeld mit allen benoetigten Daten
     * @param column    -> eingegebene Spalte
     * @param zeichen   -> jeder Spieler hat ein Zeichen (*) oder (+)
     */
    static Character setzeFeld(char[][] spielfeld, int column, char zeichen) {
        column--; // Weil der gemeine Mensch denkt, der Zahlenbereich wuerde sich von 1 bis 4 erstrecken
        int pos2check;
        if (spielfeld[0][column] != '\0') {
            printStreamErr.println("Die Reihe ist voll.. Pech!");
            return null;
        }
        for (int i = 0; i < spielfeld.length; i++) { //Iteriere durch die Zeilen
            if (i + 1 == spielfeld.length) {
                // Nach der letzten Zeile kommt nichts mehr..
                // also darf in das aktuelle Kaestchen geschrieben werden, obwohl im
                // naechsten nichts steht
                pos2check = i;
                if (spielfeld[pos2check][column] == '\0') {
                    spielfeld[i][column] = zeichen;
                    if (IsGameOver(spielfeld, i, column, zeichen)) {// Hat jmd gewonnen?
                        printStreamOut.println("Spieler mit " + zeichen + "hat gewonnen");
                        return zeichen;
                    }
                    break;
                }

            } else {
                //ueberpruefe immer das folgende Feld
                pos2check = i + 1;
                if (spielfeld[pos2check][column] != '\0') {
                    spielfeld[i][column] = zeichen;
                    if (IsGameOver(spielfeld, i, column, zeichen)) {// Hat jmd gewonnen?
                        printStreamOut.println("Spieler mit " + zeichen + "hat gewonnen");
                        return zeichen;
                    }
                    break;
                }
            }
        }
        return null;
    }



    /**
     * Sammelstelle fuer die Funktionen, die ueberpruefen ob jmd. gewonnen hat
     * @param spielfeld -> Das Spielfeld mit allen benoetigten Daten
     * @param column -> die Spalte an der das Zeichen gesetzt wurde
     * @param row -> die Reihe an der das Zeichen gesetzt wurde
     * @param zeichen -> das Zeichen
     */
    static boolean IsGameOver(char[][] spielfeld, int column, int row, char zeichen) {
        boolean b1 = GameIsOver_row(spielfeld, column, row, zeichen);
        boolean b2 = GameIsOver_column(spielfeld, column, row, zeichen);
        boolean b3 = GameIsOver_straight1(spielfeld, column, row, zeichen);
        boolean b4 = GameIsOver_straight2(spielfeld, column, row, zeichen);

        return (b1||b2||b3||b4);
    }

    static boolean GameIsOver_row(char[][] spielfeld, int column, int row, char zeichen) {
        // nach links
        int go = row - 1; // mit dem Punkt links neber dem gesetzten beginne
        // ich
        int i = 1; // der gesetzte Punkt = 1 Treffer
        while (go >= 0) {
            if (spielfeld[column][go] != zeichen)
                break;
            go--;
            i++;
        }

        // nach rechts
        go = row + 1;
        while (go < spielfeld.length) {
            if (spielfeld[column][go] != zeichen)
                break;
            go++;
            i++;
        }

        return (i > 3);
    }

    static boolean GameIsOver_column(char[][] spielfeld, int column, int row, char zeichen) {
        // nach oben
        int go = column - 1;
        int i = 1;
        while (go >= 0) {
            if (spielfeld[go][row] != zeichen)
                break;
            go--;
            i++;
        }

        // nach unten
        go = column + 1;
        while (go < spielfeld.length) {
            if (spielfeld[go][row] != zeichen)
                break;
            go++;
            i++;
        }

        return (i > 3);
    }

    static boolean GameIsOver_straight1(char[][] spielfeld, int column, int row, char zeichen) {
        // nach links oben
        int go = row - 1;
        int go2 = column - 1;
        int i = 1;
        while (go >= 0 && go2 >= 0) {
            if (spielfeld[go2][go] != zeichen)
                break;
            go--;
            go2--;
            i++;
        }

        // nach rechts unten
        go = row + 1;
        go2 = column + 1;
        while (go < spielfeld[0].length && go2 < spielfeld.length) {
            if (spielfeld[go2][go] != zeichen)
                break;
            go++;
            go2++;
            i++;
        }

        return (i > 3);
    }

    static boolean GameIsOver_straight2(char[][] spielfeld, int column, int row, char zeichen) {
        // nach links unten
        int go = row - 1;
        int go2 = column + 1;
        int i = 1;
        while (go >= 0 && go2 < spielfeld.length) {
            if (spielfeld[go2][go] != zeichen)
                break;
            go--;
            go2++;
            i++;
        }

        // nach rechts oben
        go = row + 1;
        go2 = column - 1;
        while (go < spielfeld[0].length && go2 >= 0) {
            if (spielfeld[go2][go] != zeichen)
                break;
            go++;
            go2--;
            i++;
        }

        return (i > 3);
    }

}
