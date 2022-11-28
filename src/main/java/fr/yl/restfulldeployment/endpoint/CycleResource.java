package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Cycle;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cycles")
public class CycleResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Cycle> cycles = DAOFactory.getCycleDAO().getAll(0);
        return Response.ok(cycles).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Cycle cycle = DAOFactory.getCycleDAO().getByID(id);
        return Response.ok(cycle).build();
    }

}
