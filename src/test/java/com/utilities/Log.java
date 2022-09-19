package com.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private Logger logger;

    public Log() {
        System.setProperty("log4j.configurationFile","./log4j2.xml");
        logger  = LogManager.getLogger(Log.class.getName());
    }
    public void info(String message){
        logger.info(message);
    }
}