package dominio;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Carrera;
import pojo.Facultad;
import pojo.Rol;

public class CatalogoImp {

    public static List<Rol> obtenerRoles() {
        List<Rol> roles = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                roles = conexionBD.selectList("catalogo.roles");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roles;
    }

    public static List<Facultad> obtenerFacultades() {
        List<Facultad> facultades = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                facultades = conexionBD.selectList("catalogo.obtener-facultades");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return facultades;
    }

    public static List<Carrera> obtenerCarrerasFacultad(int idFacultad) {
        List<Carrera> carreras = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                carreras = conexionBD.selectList("catalogo.obtener-carreras-facultad", idFacultad);
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return carreras;
    }
}
