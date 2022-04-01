//package U1_Refactoring.VierGewinnt.src.objektorientiert;
//
//import java.io.InputStream;
//import java.io.PrintStream;
//
//public class VierGewinntIO {
//
//    /**
//     * @param text -> Bildschirmausgabe
//     * @return -> Tastatureingabe
//     */
//    static int eingabeInt(String text, InputStream inputStream, PrintStream printStreamOut) {
//        printStreamOut.print(text);
//        java.util.Scanner input = new java.util.Scanner(inputStream);
//        return input.nextInt();
//    }
//
//    /**
//     * @param text -> Bildschirmausgabe
//     * @return -> Tastatureingabe
//     */
//    static String eingabeString(String text, InputStream inputStream, PrintStream printStreamOut) {
//        printStreamOut.print(text);
//        java.util.Scanner input = new java.util.Scanner(inputStream);
//        return input.next();
//    }
//
//    /**
//     * Zeigt das komplette Spielfeld auf dem Bildschirm
//     * @param spielfeld -> Das Spielfeld mit allen ben�tigten Daten
//     */
//    static void showSpielfeld(char[][] spielfeld, PrintStream printStreamOut) {
//        StringBuffer Geruest = new StringBuffer();
//        StringBuffer row_start = new StringBuffer(" "); // erste Zeile 1 2 3 4
//        StringBuffer row_divide = new StringBuffer("|"); // Trennzeile |-----|
//        StringBuffer row_end = new StringBuffer("-"); // letzte Zeile -------
//
//        if (spielfeld[0].length > 9) {
//            for (int i = 1; i <= spielfeld[0].length; i++)
//                row_start.append((i/10==0) ? " " : i/10).append(" ");
//            row_start.append("\n ");
//        }
//        for (int i = 1; i <= spielfeld[0].length; i++) {
//            row_start.append(i%10).append(" ");
//            row_divide.append((i==spielfeld[0].length)? "-|" : "--");
//            row_end.append("--");
//        }
//        printStreamOut.println(row_start);
//        printStreamOut.println(row_divide);
//
//        for (char[] arrZeile : spielfeld) { //iteriere durch alle Zeilen
//            for (char arrSpalte : arrZeile) { //iteriere durch alle Spalten
//                Geruest.append("|");
//                Geruest.append((arrSpalte == '\0') ? ' ' : arrSpalte);
//            }
//            Geruest.append("|\n");
//        }
//        Geruest.append(row_end).append("\n");
//        printStreamOut.println(Geruest);
//    }
//
//}
