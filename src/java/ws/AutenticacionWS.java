package ws;

import dominio.AutenticacionImp;
import dto.RSAutenticacionAdmin;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("autenticacion")
public class AutenticacionWS {
    
    @Path("administracion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RSAutenticacionAdmin autenticarProfesor(@FormParam("noPersonal") String noPersonal, 
            @FormParam("password") String password){
        
        if( (noPersonal !=null && !noPersonal.isEmpty()) && (password !=null && !password.isEmpty()) ){
            return AutenticacionImp.autenticarAdministracion(noPersonal, password);
           
        }  
        throw new BadRequestException("Credenciales en estado incorrecto");
    }
}
