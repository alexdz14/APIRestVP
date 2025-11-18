package dominio;

import dto.RSAutenticacionAdmin;
import dto.RSAutenticacionAlumno;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Alumno;
import pojo.Profesor;


public class AutenticacionImp {
    
    
    public static RSAutenticacionAdmin autenticarAdministracion(String noPersonal, String password){
        RSAutenticacionAdmin respuesta = new RSAutenticacionAdmin();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
                Profesor profesor = conexionBD.selectOne("autenticacion.administrador", parametros);
                if(profesor !=null){
                    //Flujo normal Credenciales son correctas
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del profesor: "+profesor.getNombre());
                    respuesta.setProfesor(profesor);
                }else{
                    //Flujo alterno 2 Credenciales incorrectas
                    respuesta.setError(true);
                    respuesta.setMensaje("Credenciales incorrectas, verifica tu información.");
                }
                conexionBD.close();
            }catch (Exception e){
                //Flujo alterno 3 Error al ejecutar algo de la consulta
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            //Flujo alterno 1 No hay conexion con BD
            respuesta.setError(true);
            respuesta.setMensaje("La conexion a la informacion no esta disponible por el momento.");
        }
        return respuesta;
    }
    
    public static RSAutenticacionAlumno autenticarAlumno(String matricula, String password){
        RSAutenticacionAlumno respuesta = new RSAutenticacionAlumno();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("matricula", matricula);
                parametros.put("password", password);
                Alumno alumno = conexionBD.selectOne("autenticacion.alumno", parametros);
                if(alumno != null){
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del alumno(a) "+alumno.getNombre());
                    respuesta.setAlumno(alumno);
                }else{
                    respuesta.setMensaje("Matricula y/o password incorrectos, favor de verificarlos.");
                }
                conexionBD.close();
            }catch (Exception e){
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setMensaje("La conexión a la información no esta disponible por el momento.");
        }
        return respuesta;
    }
}
