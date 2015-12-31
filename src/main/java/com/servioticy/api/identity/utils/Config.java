package com.servioticy.api.identity.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

import com.servioticy.api.commons.utils.MySQL;

public class Config implements ServletContextListener {
  private static final String ATTRIBUTE_NAME = "config";

  private static Logger LOG = org.apache.log4j.Logger.getLogger(Config.class);

    public static Properties config = new Properties();
    
    public static MySQL mySQL;
    public Connection conn = null;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.info("[SERVIOTICY-Identity] Loading properties");
        
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            LOG.error(e);
            e.printStackTrace();
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
        }
        
        try {
            String identity_host = config.getProperty("identity_host");
            String identity_port = config.getProperty("identity_port");
            String identity_db   = config.getProperty("identity_db");
            String identity_user = config.getProperty("identity_user");
            String identity_pass = config.getProperty("identity_pass");

            mySQL = new MySQL(identity_host, identity_port, identity_db, identity_user, identity_pass);

        } catch (SQLException e) {
            LOG.error("SQLException: " + e.getMessage());
            LOG.error("SQLState: " + e.getSQLState());
            LOG.error("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        

        event.getServletContext().setAttribute(ATTRIBUTE_NAME, this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }
        
    }
}
