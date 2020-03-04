package studentLog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marek Vajgl
 */
public class MyLogTest {

  public MyLogTest() {
  }
  /*
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  * */

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void tearDown() {
    System.setOut(null);
  }

  @Test
  public void testLogConsole() {
    IMyLog instance = getInstance();

    instance.setLogConsole(false);
    assertFalse(instance.getLogConsole());

    instance.setLogConsole(true);
    assertTrue(instance.getLogConsole());
  }

  @Test
  public void testLogFile() {
    IMyLog instance = getInstance();

    instance.setLogFile(null);
    assertNull(instance.getLogFile());

    String fileName = "C:\\testLogFile.txt"; // má se logovat do souboru
    instance.setLogFile(fileName);
    assertEquals(fileName, instance.getLogFile());
  }

  @Test
  public void testSetIndent(){
    IMyLog instance = getInstance();

    assertEquals("Indent u nové instance musí být indent= 0.",
        0, instance.getIndent());

    instance.setIndent(5);
    assertEquals(5, instance.getIndent());

    instance.setIndent(-5);
    assertEquals("Indent nastavený na zápornou hodnotu se musí nastavit na 0.",
        0, instance.getIndent());
  }

  @Test
  public void testIncDecIndent(){
    IMyLog instance = getInstance();

    assertEquals("Indent u nové instance musí být indent= 0.",
        0, instance.getIndent());

    instance.increaseIndent();
    assertEquals("Po zvýšení indentu musí být hodnota 1",
        1, instance.getIndent());

    instance.increaseIndent();
    assertEquals("Po zvýšení indentu musí být hodnota 2",
        2, instance.getIndent());

    instance.decreaseIndent();
    assertEquals("Po snížení indentu musí být hodnota 1",
        1, instance.getIndent());

    instance.decreaseIndent();
    assertEquals("Po snížení indentu musí být hodnota 0",
        0, instance.getIndent());

    instance.decreaseIndent();
    assertEquals("Po snížení indentu pod 0 musí být hodnota 0",
        0, instance.getIndent());
  }

  @Test
  public void testLog_C_NoOutput(){
    String message = "hallo";
    IMyLog instance = getInstance();

    instance.setLogFile(null);
    instance.setLogConsole(false);
    instance.log(message);

    String expected = "";
    String result = outContent.toString();

    assertTrue(result.contains(expected));
  }

  @Test
  public void testLog_C_String() {
    String message = "hallo";
    IMyLog instance = getInstance();

    instance.setLogFile(null);
    instance.setLogConsole(true);
    instance.log(message);

    String expected = message;
    String result = outContent.toString();

    assertTrue(result.contains(expected));
  }

  @Test
  public void testLog_C_Indent_String() {
    String message = "hallo";
    IMyLog instance = getInstance();

    instance.setLogFile(null);
    instance.setLogConsole(true);
    instance.setIndent(3);
    instance.log(message);

    String expectedPrefix = "\t\t\t";
    String expectedText = expectedPrefix + message;
    String result = outContent.toString();

    assertTrue("Očekává se odsazení výstupu o 3 tabulátory.",
        result.startsWith(expectedPrefix)
            ||
            result.contains(expectedText));
  }

  @Test
  public void testLog_F_NoFile(){
    String logFileName = getTestLogFileName();


    IMyLog log = getInstance();
    log.setLogConsole(false);
    log.setLogFile(logFileName);
    log.setLogFile(null);

    deleteLogFile(logFileName);

    log.log("aaa");

    java.io.File f = new java.io.File(logFileName);
    if (f.exists())
      fail("Log soubor byl vytvořen, i když logování do souboru je vypnuto.");
  }

  @Test
  public void testLog_F_String(){
    String logFileName = getTestLogFileName();

    IMyLog log = getInstance();
    log.setLogConsole(false);
    log.setLogFile(logFileName);
    log.setIndent(3);

    String message = "TEST LOG MESSAGE";
    log.log(message);

    java.io.File f = new java.io.File(logFileName);
    if (!f.exists())
      fail("Log soubor nebyl vytvořen, i když logování do souboru je zapnuto.");

    String content = readFileContent(logFileName);
    if (!content.contains(message))
      fail("Logovaný obsah nebyl v souboru nalezen.");

    if (!content.contains("\t\t\t"))
      fail("Logovaný soubor neobsahuje odsazení.");
  }

  /**
   * Test of log method, of class MyLog.
   */
  @Test
  public void testLog_Throwable() {
    Throwable t = null;
    IMyLog instance = getInstance();
    //instance.log(t);


    // TODO review the generated test code and remove the default call to fail.
    //fail("The test case is a prototype.");
  }

  private String getTestLogFileName() {
    String ret;
    java.io.File f;
    try {
      f = java.io.File.createTempFile("IMyLogTest", "test");
      f.deleteOnExit();

      ret = f.getAbsolutePath();
    } catch (IOException ex) {
      fail("Nepodařilo se vytvořit dočasný soubor.");
      ret = null;
    }
    return ret;
  }

  private String readFileContent(String logFileName) {
    String ret = "";
    java.io.FileReader fr;
    java.io.BufferedReader br;

    try{
      fr = new FileReader(logFileName);
      br = new BufferedReader(fr);

      String nextLine = br.readLine();
      while (nextLine!=null){
        ret += nextLine;
        nextLine = br.readLine();
      }

      br.close();
      fr.close();
    } catch (Throwable t){
      fail("Nepodařilo se načíst obsah souboru " + logFileName);
      ret = "";
    }

    return ret;
  }

  private void deleteLogFile(String logFileName) {
    java.io.File f;

    f = new java.io.File(logFileName);
    if (f.exists())
      f.delete();
  }

  private IMyLog getInstance() {
    IMyLog ret = new AugustaLog();
    return ret;
  }
}
