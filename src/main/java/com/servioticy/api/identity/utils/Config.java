package com.servioticy.api.identity.utils;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.servioticy.api.commons.exceptions.ServIoTWebApplicationException;

public class Config implements ServletContextListener {
  private static final String ATTRIBUTE_NAME = "config";

  private static Logger LOG = org.apache.log4j.Logger.getLogger(Config.class);

	public static Properties config = new Properties();

    @Override
    public void contextInitialized(ServletContextEvent event) {
    	LOG.info("[SERVIOTICY-API] Loading properties");
        System.out.println("[SERVIOTICY-API] Loading properties");

        try {
			config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			LOG.error(e);
			throw new ServIoTWebApplicationException(Response.Status.INTERNAL_SERVER_ERROR, "Loading config failed = " + e.getMessage());
		}

        event.getServletContext().setAttribute(ATTRIBUTE_NAME, this);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	
    }


}
