package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Adresse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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

}
