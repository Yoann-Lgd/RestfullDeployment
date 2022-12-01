package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.security.MyToken;
import fr.yl.restfulldeployment.security.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static fr.yl.restfulldeployment.security.Tokened.TOKEN;

@Path("/user")
@Tag(name = "user")
public class UserResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        if (user.getLogin().equals("admin") && user.getPassword().equals("admin")) {
            return Response.ok().header(TOKEN, new MyToken(user.getLogin(), user.getPassword())).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
