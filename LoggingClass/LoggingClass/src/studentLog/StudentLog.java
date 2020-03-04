package studentLog;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marek Vajgl
 */
public class StudentLog {

  /**
   * @param args the command line
   * arguments
   */
  public static void main(String[] args) {



    AugustaLog log = new AugustaLog();
    log.setLogConsole(true); // má se logovat do konzole
    log.setLogFile("C:\\temp\\log.txt"); // má se logovat do souboru

    log.log("Spuštěno"); // zalogování zprávy

    log.increaseIndent(); // zvýšení odsazení (pro přehlednost)
    log.log("aktuální čas " + new java.util.Date().toString()); // vložení další zprávy
    log.decreaseIndent(); // snížení odsazení

    log.log("Ukončeno");

  }
}
