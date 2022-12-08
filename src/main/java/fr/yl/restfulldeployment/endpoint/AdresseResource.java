package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Adresse;
import fr.yl.restfulldeployment.work.Ville;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/adresses")
@Tag(name = "adresses")
public class AdresseResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Liste des adresses")
    public Response getAll(){
        List<Adresse> adresses = DAOFactory.getAdresseDAO().getAll(0);
        if (adresses.isEmpty())
            return  Response.noContent().build();
        else
            return Response.ok(adresses).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Adresse adresse = DAOFactory.getAdresseDAO().getByID(id);
        if (adresse != null)
            return Response.ok(adresse).build();
        else
            return  Response.noContent().build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @ApiResponse(responseCode = "204", description = "Supprimé")
    @ApiResponse(responseCode = "404", description = "non trouvée !")
    public Response delete(@PathParam("id") Integer id){
        if(id == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getAdresseDAO().delete(DAOFactory.getAdresseDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Modifié")
    @ApiResponse(responseCode = "404", description = "non trouvée !")
    public Response update(Adresse adresse){
        if(adresse == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getAdresseDAO().update(adresse))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Adresse adresse){
        if(adresse == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getAdresseDAO().insert(adresse) != 0)
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
