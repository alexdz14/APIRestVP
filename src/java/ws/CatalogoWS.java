package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Carrera;
import pojo.Facultad;
import pojo.Rol;

@Path("catalogos")
public class CatalogoWS {

    @Path("roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles() {
        return CatalogoImp.obtenerRoles();
    }

    @Path("obtener-facultades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Facultad> obtenerFacultades() {
        return CatalogoImp.obtenerFacultades();
    }

    @Path("obtener-carreras-facultad/{idFacultad}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrera> obtenerCarrerasFacultad(@PathParam("idFacultad") Integer idFacultad) {
        if (idFacultad != null && idFacultad > 0) {
            return CatalogoImp.obtenerCarrerasFacultad(idFacultad);
        }
        throw new BadRequestException();
    }
}
