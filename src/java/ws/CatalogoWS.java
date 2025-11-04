package ws;


import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Rol;

@Path("catalogos")
public class CatalogoWS {
    
    @Path("roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles(){
        return CatalogoImp.obtenerRoles();
    
}
}
    
