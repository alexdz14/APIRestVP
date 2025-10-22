package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Profesor;

public class ProfesorImp {
    
    public static List<Profesor> obtenerTodos(){
        List<Profesor> profesores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
              profesores = conexionBD.selectList("profesor.obtener-todos");
              conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profesores;
    }
    
    public static Respuesta registrar(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("profesor.registrar", profesor);
                conexionBD.commit();
                if(filasAfectadas > 0){
                respuesta.setError(false);
                respuesta.setMensaje("Registro del profesor "+profesor.getNombre()+", guardado correctamente.");
            }else{
                respuesta.setError(true);
                respuesta.setMensaje("Lo sentimos la información no puede ser guardada.");
            }
            conexionBD.close();
        }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());       
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos por el momento no hay conexión al almacenamiento de información.");
        }
        return respuesta;
    }
    
    public static Respuesta editar(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("profesor.editar", profesor);
                conexionBD.commit();
                
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Profesor '" + profesor.getNombre() + "', actualizado correctamente.");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la información no pudo ser actualizada (ID no encontrado).");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());       
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión al almacenamiento.");
        }
        return respuesta;
    }

    public static Respuesta eliminar(Profesor profesor){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.delete("profesor.eliminar", profesor);
                conexionBD.commit();
                
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Profesor eliminado exitosamente.");
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el profesor no pudo ser eliminado (ID no encontrado).");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setError(true);
                if (e.getMessage().contains("ConstraintViolationException")) {
                     respuesta.setMensaje("No se puede eliminar el profesor, tiene datos relacionados.");
                } else {
                     respuesta.setMensaje(e.getMessage());
                }      
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Lo sentimos, por el momento no hay conexión al almacenamiento.");
        }
        return respuesta;
    }
}
