package pbouda.gctester;

import java.util.ResourceBundle;

public class ConsoleLogger implements System.Logger {
 
    @Override
    public String getName() {
        return "ConsoleLogger";
    }
 
    @Override
    public boolean isLoggable(Level level) {
        return level != Level.OFF;
    }

    @Override
    public void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        if (isLoggable(level)) {
            System.out.println(msg);
        }
    }
 
    @Override
    public void log(Level level, ResourceBundle bundle, String msg, Object... params) {
        if (isLoggable(level)) {
            System.out.println(msg);
        }
    }

    public static class CustomLoggerFinder extends System.LoggerFinder {

        @Override
        public System.Logger getLogger(String name, Module module) {
            return new ConsoleLogger();
        }
    }
}