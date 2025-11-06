package dominio;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
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
}
