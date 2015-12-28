package com.servioticy.api.identity.utils;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class IdentityResponseFilter implements ContainerResponseFilter{

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        
        responseContext.getHeaders().add("Server", "api.servIoTicy.identity");
        responseContext.getHeaders().add("Date", new Date(System.currentTimeMillis()));
    }
}

