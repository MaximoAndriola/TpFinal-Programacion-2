package model.clases.GestionJSON;

import model.clases.Ranking.Apuesta;
import model.clases.Usuario.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GestionJSON {

    public static ArrayList<Usuario> mapeoUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            JSONArray jUsuarios= new JSONArray(JSONUtiles.leer("usuarios.json"));


            for(int i = 0; i < jUsuarios.length() ; i ++) {
                JSONObject jUsuario = jUsuarios.getJSONObject(i);
                Usuario user = new Usuario();
                user.setNombre(jUsuario.getString("nombre"));
                user.setContrasenia(jUsuario.getString("contrasenia"));
                user.setSaldo(jUsuario.getDouble("saldo"));
                user.setEsAdmin(jUsuario.getBoolean("esAdmin"));
                usuarios.add(user);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public static void cargarArray(ArrayList<Usuario> usuarios) {
        JSONArray jUsuarios = new JSONArray();

        for (Usuario usuario : usuarios) {
            JSONObject jUser = new JSONObject();
            try {
                jUser.put("nombre", usuario.getNombre());
                jUser.put("contrasenia", usuario.getContrasenia());
                jUser.put("saldo", usuario.getSaldo());
                jUser.put("esAdmin", usuario.getEsAdmin());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            jUsuarios.put(jUser);
        }

        JSONUtiles.grabar(jUsuarios, "usuarios.json");
    }
    public static void cargarArrayApuestas(ArrayList<Apuesta> apuestas) {

        JSONArray jApuestas = new JSONArray();

        for(Apuesta apuesta : apuestas) {
            JSONObject jApuesta = new JSONObject();
            try {
                jApuesta.put("nombreUsuario", apuesta.getNombreUsuario());
                jApuesta.put("apuestaRealizada", apuesta.getApuestaRealizada());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            jApuestas.put(jApuesta);
        }
        JSONUtiles.grabar(jApuestas, "apuestas.json");
    }

    public static ArrayList<Apuesta> mapeoApuestas() {

        ArrayList<Apuesta> apuestas = new ArrayList<>();

        try {
            JSONArray jApuestas = new JSONArray(JSONUtiles.leer("apuestas.json"));


            for(int i = 0; i < jApuestas.length() ; i ++) {
                JSONObject jApuesta = jApuestas.getJSONObject(i);
                Apuesta apuesta = new Apuesta();
                apuesta.setNombreUsuario(jApuesta.getString("nombreUsuario"));
                apuesta.setApuestaRealizada(jApuesta.getDouble("apuestaRealizada"));
                apuestas.add(apuesta);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return apuestas;
    }
}


