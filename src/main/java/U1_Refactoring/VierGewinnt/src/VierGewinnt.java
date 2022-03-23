package U1_Refactoring.VierGewinnt.src;

/**
 * @author Torben Brodt
 * @version 1.1
 *
 * <p />Spiel: Vier gewinnt
 * <p />Funktioniert mit Java < 1.5
 *
 */
public class VierGewinnt {

        static String SPIELER; // der aktuelle Spielername -> fuer die Gewinner Ausgabe

        /**
         * @param args -> unbenutzt
         */
        public static void main(String[] args) {
                int columns, rows, zaehler = 0, eingabe;
                String player1, player2;
                char zeichen;
                char[][] spielfeld;
               
                //Abfragen des Spielernamens
                player1 = eingabeString("Name von SpielerIn A\t\t\t: ");
               
                do {
                        player2 = eingabeString("Name von SpielerIn B\t\t\t: ");
                } while(player1.equals(player2)); //Frage erneut, wenn die Spielernamen gleich sind

                //Abfragen der Masse
                do {
                        columns = eingabeInt("Breite des Spielfeldes (mindestens 4)\t: ");
                } while (columns < 4); //Frage erneut, wenn die Breite zu klein gewaehlt wurde

                do {
                        rows = eingabeInt("Hoehe des Spielfeldes (mindestens 4)\t: ");
                } while (rows < 4); //Frage erneut, wenn die Hoehe zu klein gewaehlt wurde

                spielfeld = new char[rows][columns];

                while (zaehler < columns*rows) {
                        zeichen = (zaehler % 2 == 0) ? 'o' : '+';
                        SPIELER = (zaehler % 2 == 0) ? player1 : player2;
                        showSpielfeld(spielfeld);
                        eingabe = eingabeInt("\n" + SPIELER + "(" + zeichen
                                        + ") ist am Zug. Bitte gib die Spalte ein: ");
                        if (eingabe > columns || eingabe < 1)
                                System.err.println("Feld existriert nicht.. Bitte versuch es nochmal!");
                        else {
                                zaehler++; // naechster Bitte
                                setzeFeld(spielfeld, eingabe, zeichen);
                        }
                }
                showSpielfeld(spielfeld);
                System.err.println("Unentschieden!");
        }

        /**
         * @param text -> Bildschirmausgabe
         * @return -> Tastatureingabe
         */
        static int eingabeInt(String text) {
                System.out.print(text);
                java.util.Scanner input = new java.util.Scanner(System.in);
                return input.nextInt();
        }

        /**
         * @param text -> Bildschirmausgabe
         * @return -> Tastatureingabe
         */
        static String eingabeString(String text) {
                System.out.print(text);
                java.util.Scanner input = new java.util.Scanner(System.in);
                return input.next();
        }

        /**
         * Spalte wird �bergeben und das Feld wird gesetzt
         * @param spielfeld -> Das Spielfeld mit allen ben�tigten Daten
         * @param column -> eingegebene Spalte
         * @param zeichen -> jeder Spieler hat ein Zeichen (*) oder (+)
         */
        static void setzeFeld(char[][] spielfeld, int column, char zeichen) {
                column--; // Weil der gemeine Mensch denkt, der Zahlenbereich w�rde sich von 1 bis 4 erstrecken
                int pos2check;
                if (spielfeld[0][column] != '\0'){
                        System.err.println("Die Reihe ist voll.. Pech!");
                        return;
                }
                for (int i = 0; i < spielfeld.length; i++) { //Iteriere durch die Zeilen
                	if (i + 1 == spielfeld.length) {
                		// Nach der letzten Zeile kommt nichts mehr..
                		// also darf in das aktuelle K�stchen geschrieben werden, obwohl im
                		// n�chsten nichts steht
                		pos2check = i;
                		if (spielfeld[pos2check][column] == '\0') {
                			spielfeld[i][column] = zeichen;
                			if(IsGameOver(spielfeld, i, column, zeichen)) {// Hat jmd gewonnen?
                				System.out.println("Spieler mit "+zeichen+"hat gewonnen");
                				System.exit(0);
                			}
                			break;
                		}

                	} else {
                		//�berpr�fe immer das folgende Feld
                		pos2check = i+1;
                		if (spielfeld[pos2check][column] != '\0') {
                			spielfeld[i][column] = zeichen;
                			if(IsGameOver(spielfeld, i, column, zeichen)) {// Hat jmd gewonnen?
                				System.out.println("Spieler mit "+zeichen+"hat gewonnen");
                				System.exit(0);
                			}
                			break;
                		}
                	}
                }
        }

        /**
         * Sammelstelle f�r die Funktionen, die �berpr�fen ob jmd. gewonnen hat
         * @param spielfeld -> Das Spielfeld mit allen ben�tigten Daten
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

        /**
         * Bricht das Programm ab und liefert den Gewinner
         * @param spielfeld -> Das Spielfeld mit allen ben�tigten Daten
         */
        static void spielFertig(char[][] spielfeld) {
                showSpielfeld(spielfeld);
                System.out.println(SPIELER + " hat gewonnen\n");
                System.exit(1);
        }

        /**
         * Zeigt das komplette Spielfeld auf dem Bildschirm
         * @param spielfeld -> Das Spielfeld mit allen ben�tigten Daten
         */
        static void showSpielfeld(char[][] spielfeld) {
                StringBuffer Geruest = new StringBuffer();
                StringBuffer row_start = new StringBuffer(" "); // erste Zeile 1 2 3 4
                StringBuffer row_divide = new StringBuffer("|"); // Trennzeile |-----|
                StringBuffer row_end = new StringBuffer("-"); // letzte Zeile -------

                if (spielfeld[0].length > 9) {
                        for (int i = 1; i <= spielfeld[0].length; i++)
                                row_start.append((i/10==0) ? " " : i/10).append(" ");
                        row_start.append("\n ");
                }
                for (int i = 1; i <= spielfeld[0].length; i++) {
                        row_start.append(i%10).append(" ");
                        row_divide.append((i==spielfeld[0].length)? "-|" : "--");
                        row_end.append("--");
                }
                System.out.println(row_start);
                System.out.println(row_divide);

                for (char[] arrZeile : spielfeld) { //iteriere durch alle Zeilen
                        for (char arrSpalte : arrZeile) { //iteriere durch alle Spalten
                                Geruest.append("|");
                                Geruest.append((arrSpalte == '\0') ? ' ' : arrSpalte);
                        }
                        Geruest.append("|\n");
                }
                Geruest.append(row_end).append("\n");
                System.out.println(Geruest);
        }
}