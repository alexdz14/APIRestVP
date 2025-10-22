/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ProfesorImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Profesor;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

@Path("profesor")
public class ProfesorWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profesor> obtenerTodos(){
        return ProfesorImp.obtenerTodos();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrar(String json){
        Gson gson = new Gson();
        try{
            Profesor profesor = gson.fromJson(json, Profesor.class);
            return ProfesorImp.registrar(profesor);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editar(String json){
        Gson gson = new Gson();
        try{
            Profesor profesor = gson.fromJson(json, Profesor.class);
            if (profesor == null || profesor.getIdProfesor() <= 0) {
                throw new BadRequestException("Se requiere un ID de profesor válido para editar.");
            }

            return ProfesorImp.editar(profesor);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("eliminar/{idProfesor}") 
    @DELETE 
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@PathParam("idProfesor") Integer idProfesor){
        
        try{
            if (idProfesor == null || idProfesor <= 0) {
                 throw new BadRequestException("ID de profesor no válido. Debe ser un número mayor a 0.");
            }
            
            Profesor profesorAEliminar = new Profesor();
            profesorAEliminar.setIdProfesor(idProfesor);
            
            return ProfesorImp.eliminar(profesorAEliminar);
            
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
