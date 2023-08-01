package com.nts.reproducer.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@ApplicationScoped
@Path("reproducer")
@Produces("application/json")
public class RestService {

    @GET
    public void anEndpoint() {
    }
}
