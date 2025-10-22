/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import com.google.gson.Gson;
import java.util.HashSet;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import pojo.OperacionAritmetica;
import pojo.OperacionSolicitud;

/**
 * REST Web Service
 *
 * @author stivm
 */
@Path("prueba")
public class Prueba {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Prueba
     */
    public Prueba() {
    }

    
    @Path("saludar/{nombre},{apellidos}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("nombre") String nombre,
          @PathParam("apellidos") String apellidos) {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Hola: "+nombre+ " "+apellidos+ ", bienvenido(a)";
    }
    
    @Path("sumar/{num1},{num2}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionAritmetica sumar(@PathParam("num1") Integer num1, @PathParam("num2") Integer num2){

        OperacionAritmetica resultado = new OperacionAritmetica();
        resultado.setOperacion("suma");
        resultado.setOperandos(num1+","+num2);
        resultado.setResultado((float)num1+num2);
        return resultado;
    }
    
   
    @Path("restar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionAritmetica restar(@FormParam("num1")Integer num1, @FormParam("num2") Integer num2){
       
        OperacionAritmetica resultado = new OperacionAritmetica();
        resultado.setOperacion("resta");
        resultado.setOperandos(num1+","+num2);
        resultado.setResultado((float)num1-num2);
        return resultado;
    }
    
    @Path("multiplicar/{num1}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public OperacionAritmetica multiplicar(@PathParam("num1")Integer num1, @FormParam("num2")Integer num2){
        
        OperacionAritmetica resultado = new OperacionAritmetica();
        resultado.setOperacion("multiplicacion");
        resultado.setOperandos(num1+","+num2);
        resultado.setResultado((float)num1*num2);
        return resultado;
    }
    
    
    @Path("dividir")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OperacionAritmetica dividir(String json){
        System.out.println("JSON: "+json);
        
        Gson gson = new Gson();
        OperacionSolicitud datos = gson.fromJson(json, OperacionSolicitud.class);
        
        OperacionAritmetica resultado = new OperacionAritmetica();
        resultado.setOperacion(datos.getOperacion());
        resultado.setOperandos(datos.getNum1()+","+datos.getNum2());
        resultado.setResultado((float)datos.getNum1() / datos.getNum2());
        return resultado;
    }
    
    //Tarea
    @Path("calcular")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OperacionAritmetica calcular(String json){
        System.out.println("JSON: "+json);
        
        Gson gson = new Gson();
        OperacionSolicitud datos = gson.fromJson(json, OperacionSolicitud.class);
        
        OperacionAritmetica resultado = new OperacionAritmetica();
        resultado.setOperacion(datos.getOperacion());
        resultado.setOperandos(datos.getNum1() + "," +datos.getNum2());
        
        switch(datos.getOperacion().toLowerCase()){
            case "suma":
                resultado.setResultado((float) datos.getNum1() + datos.getNum2());
                break;
            case "resta":
                resultado.setResultado((float) datos.getNum1() - datos.getNum2());
                break;
            case "multiplicacion":
                resultado.setResultado((float) datos.getNum1() * datos.getNum2());
                break;
            case "dividir":
                if(datos.getNum2() == 0)
                    throw new BadRequestException();
                
                resultado.setResultado((float) datos.getNum1() / datos.getNum2());
                break;
            default:
                throw new BadRequestException();
        }
        return resultado;
    }
}
