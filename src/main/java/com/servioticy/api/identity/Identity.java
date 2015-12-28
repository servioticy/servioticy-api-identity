package com.servioticy.api.identity;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.servioticy.api.identity.exceptions.IdentityWebApplicationException;
import com.servioticy.api.identity.utils.Config;

@Path("/identity")
public class Identity {

    @Path("/{userMail}")
    @GET
    public Response getAuthToken(@Context HttpHeaders hh, @PathParam("userMail") String userMail) {
        String response = null;

        try {
            response = Config.mySQL.portalGetToken(userMail);
        } catch (SQLException e) {
            throw new IdentityWebApplicationException(Response.Status.BAD_REQUEST,
                                                      "SQLException: " + e.getMessage());
        }

        return Response.ok(response).build();
    }

}
