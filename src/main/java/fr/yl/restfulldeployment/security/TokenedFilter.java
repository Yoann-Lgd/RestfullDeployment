package fr.yl.restfulldeployment.security;


import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;


@Provider
@Tokened
@Priority(Priorities.AUTHENTICATION)
public class TokenedFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
         // Récupère la clef Token dans le header
        String tokenHeader = requestContext.getHeaderString(Tokened.TOKEN);
        MyToken token = new MyToken(tokenHeader);

        if (!token.isValide())
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}