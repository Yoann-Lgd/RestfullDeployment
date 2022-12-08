package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Cycle;
import fr.yl.restfulldeployment.work.Famille;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/familles")
@Tag(name = "familles")
public class FamilleResource {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAll(){
            List<Famille> familles = DAOFactory.getFamilleDAO().getAll(0);
            return Response.ok(familles).build();
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getById(@PathParam("id") Integer id){
            Famille famille  = DAOFactory.getFamilleDAO().getByID(id);
            return Response.ok(famille).build();
        }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Modifié")
    @ApiResponse(responseCode = "404", description = "non trouvée !")
    public Response update(Famille famille){
        if(famille == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getFamilleDAO().update(famille))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Famille famille){
        if(famille == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getFamilleDAO().insert(famille) != 0)
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
        if (DAOFactory.getFamilleDAO().delete(DAOFactory.getFamilleDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
