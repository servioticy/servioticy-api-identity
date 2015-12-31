package com.servioticy.api.identity.utils;

import java.util.Date;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

@Provider
public class IdentityResponseFilter implements ContainerResponseFilter{

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        
        response.getHttpHeaders().add("Server", "api.servIoTicy.identity");
        response.getHttpHeaders().add("Date", new Date(System.currentTimeMillis()));
        
        return response;
    }
    
}

