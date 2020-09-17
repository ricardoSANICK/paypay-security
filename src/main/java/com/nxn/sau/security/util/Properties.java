package com.nxn.sau.security.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Properties {
	
	static Logger log = LoggerFactory.getLogger(Properties.class);
    
    public java.util.Properties getProperties(String archivo) {
        try {
        	java.util.Properties propiedades = new java.util.Properties();
            propiedades.load(getClass().getResourceAsStream(archivo));
            if (!propiedades.isEmpty()) {
                return propiedades;
            } else {
            	log.error("El archivo de propiedades en la ruta "+archivo +" esta vacio");
                return null;
            }
        } catch (IOException ex) {
        	log.error("No se encuentra el archivo de propiedades en la ruta "+archivo + ex);
            return null;
        }
    }
    
}