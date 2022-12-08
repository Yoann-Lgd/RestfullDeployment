package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Ecole;
import fr.yl.restfulldeployment.work.Instrument;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/instruments")
@Tag(name = "instruments")
public class InstrumentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Instrument> instruments = DAOFactory.getInstrumentDAO().getAll(0);
        return Response.ok(instruments).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Integer id){
        Instrument instrument  = DAOFactory.getInstrumentDAO().getByID(id);
        return Response.ok(instrument).build();
    }
    @GET
    @Path("familles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInstrumentsByFamille(@PathParam("id") Integer idOfFamille){
        List<Instrument> instrumentList = DAOFactory.getInstrumentDAO().getByFamille(idOfFamille);
        return Response.ok(instrumentList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response create(Instrument instrument){
        if(instrument == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getInstrumentDAO().insert(instrument) != 0){
            return Response.status(204).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiResponse(responseCode = "204", description = "Créé")
    @ApiResponse(responseCode = "404", description = "non créée !")
    public Response update(Instrument instrument){
        if(instrument == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOFactory.getInstrumentDAO().update(instrument)){
            return Response.status(204).build();
        }
        else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
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
        if (DAOFactory.getInstrumentDAO().delete(DAOFactory.getInstrumentDAO().getByID(id)))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
