package studentLog;

/**
 *
 * @author Marek Vajgl
 */
public interface IMyLog {
  
  /**
   * Určí, zda se má logovat na konzolový výstup (System.out) či nikoliv.
   * @param logToConsole True pro logování, jinak false.
   */
  public void setLogConsole(boolean logToConsole);
  
  /**
   * Vrací příznak, zda se loguje do konzolového výstupu.
   * @return True pokud se loguje, jinak false.
   */
  public boolean getLogConsole();
  
  /**
   * Udává, zda se bude logovat do souboru.
   * @param fileName Název souboru pro logování do souboru, jinak NULL.
   */
  public void setLogFile(String fileName);
  
  /**
   * Udává, zda se provádí logování do souboru. Pokud ano, vrací název souboru,
   * jinak vrací NULL.
   * @return Název logovacího souboru při logování, hodnotu NULL, pokud se do souboru neloguje.
   */
  public String getLogFile();
  
  /** 
   * Zvýší odsazení zapisovaných informací
   */
  public void increaseIndent();
  
  /**
   * Sníží odsazení zapisovaných informací (minimální odsazení je 0).
   */
  public void decreaseIndent();
  
  /**
   * Nastaví odsazení zapisovaných informací na pevnou hodnotu (>=0).
   * @param val 
   */
  public void setIndent(int val);
  
  /**
   * Vrátí aktuální hodnotu odsazení.
   */
  public int getIndent();
  
  /**
   * Zapíše do logu zprávu na dané úrovni
   * @param message Zpráva k zapsání
   */
  public void log(String message);
  
  /**
   * Zapíše do logu výjimku na dané úrovni
   * @param t Výjimka
   * Zapisovat se musí jak text výjimky, tak typ dané výjimky. Pokud výjimka obsahuje vnořené výjimky,
   * zapisuje se i text všech vnořených výjimek i jejich typů. Zapisuje se také obsah zásobníku volání.
   */
  public void log(Throwable t);
}
