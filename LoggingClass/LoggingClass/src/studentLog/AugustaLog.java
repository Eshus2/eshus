package studentLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AugustaLog implements IMyLog {
    private boolean logToConsole = false;
    private boolean logToFile = false;
    private int indent = 0;
    private String filePath = null;
    private boolean fileExists = false;
    private File file;


    @Override
    public void setLogConsole(boolean logToConsole) {
        this.logToConsole = logToConsole;
    }

    public boolean getLogConsole() {
        return logToConsole;
    }

    @Override
    public void setLogFile(String fileName) {
       if (fileName!=null){
           filePath = fileName;
           file = new File(filePath);
           logToFile = true;

           if (fileExists){

           }else{
               try {
                   file.createNewFile();

                   fileExists = true;
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

       }else {
           logToFile = false;
       }


        }


    @Override
    public String getLogFile() {
        return filePath;
    }

    @Override
    public void increaseIndent() {
    indent++;
    }

    @Override
    public void decreaseIndent() {

        if(indent == 0 ){
         indent = 0;
     }else {
            indent--;
        }
    }

    @Override
    public void setIndent(int val) {
        if(val>0){
            indent=val;
        }else{
            indent = 0;
        }


    }

    @Override
    public int getIndent() {
        return indent;
    }

    @Override
    public void log(String message) {
        if (this.logToConsole){
            String tmp = "";
            for (int i = 0; i < indent; i++) {
                tmp += "\t";
            }

            String ret = message.replaceAll( "(?m)^",tmp);
            System.out.println(ret);
        }
        if (this.logToFile){
          String tmp = "";
            for (int i = 0; i < indent; i++) {
                tmp += "\t";
            }
            String ret = message.replaceAll( "(?m)^",tmp);
            try {
               FileWriter writer = new FileWriter(file);
                writer.write(ret);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
    }

    @Override
    public void log(Throwable t) {

    }
}







/*
    @Override


    @Override
    public void setLogFile(String fileName){
        FileHandler fh;
        if(fileName == null){

        }else{
            File file = new File(fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(file.exists()) {

                try {
                    fh = new FileHandler(fileName);
                    logger.addHandler(fh);
                    SimpleFormatter formatter = new SimpleFormatter();
                    fh.setFormatter(formatter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





    @Override
    public void log(String message) {


        if (logToFile==true) {
            Handler handler = null;
            logger = Logger.getLogger(AugustaLog.class.getName());
            try {

                handler = new FileHandler(filename);
                handler.setLevel(Level.ALL);
                logger.addHandler(handler);
                logger.setLevel(Level.ALL);
                logger.setUseParentHandlers(false);

                for (int i = 0; i < indent; i++) {
                    message += "\t" + message;
                }
                logger.info(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
