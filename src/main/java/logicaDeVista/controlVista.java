package logicaDeVista;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicaIntegracion.logIn;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/controlVista")
public class controlVista extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private logIn login;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain");
    	String tipoFuncion = request.getParameter("tipoFuncion");
    	
        if(tipoFuncion.equals("logIn")){
        	procedimientoIniciarSesion(request,response);
        }
    }

	private void procedimientoIniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("user");
		String password = request.getParameter("password");


		try {
			login = new logIn(email, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter out = response.getWriter();
		// envia el resultado a la variable result del la funcion del javascrip

		try {
			out.println(login.verificacion());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

}

