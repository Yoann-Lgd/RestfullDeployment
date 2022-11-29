package fr.yl.restfulldeployment.endpoint;

import fr.yl.restfulldeployment.dao.DAOFactory;
import fr.yl.restfulldeployment.work.Instrument;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/instruments")
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

}
