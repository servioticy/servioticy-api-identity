package com.servioticy.api.identity;

import java.util.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/")
public class Paths {
  @GET
  @Produces("application/json")
  public Response getAllSOs(@Context HttpHeaders hh) {

    return Response.ok("Hello world!")
             	   .header("Server", "api.servIoTicy")
                   .header("Date", new Date(System.currentTimeMillis()))
                   .build();
  }

}
