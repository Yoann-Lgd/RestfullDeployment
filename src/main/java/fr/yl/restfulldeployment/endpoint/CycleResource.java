package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Classification;
import fr.yl.restfulldeployment.work.Cycle;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/cycles")
@Tag(name = "cycles")
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

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Modifié")
    @ApiResponse(responseCode = "404", description = "non trouvée !")
    public Response update(Cycle cycle){
        if(cycle == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getCycleDAO().update(cycle))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Cycle cycle){
        if(cycle == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getCycleDAO().insert(cycle) != 0)
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response delete(@PathParam("id") Integer id){
        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getCycleDAO().delete(DAOFactory.getCycleDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
