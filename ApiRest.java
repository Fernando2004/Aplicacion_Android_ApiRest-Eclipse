package com.fernandogarcia.apirest01;


import com.fernandogarcia.objeto.Tarea;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//anotacion indica la ruta inicial de entrada
@Path("/tarea")
public class TareasApiRest  extends HttpServlet {
	
	

	private void response(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}
	
	
	
	
	
	   private static List<Tarea> lista = Tarea.getTareas();
	   
	   
	   //---------------------------------------------------------
	   //http://localhost:8080/com.fernandogarcia.apirest01/ApiRest/tareas
	   //Listar todos los productos	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getTareas(){
			
			return Response.ok(lista).build();
		}
		
		
		//--------------------------------------------------------
		//http://localhost:8080/com.fernandogarcia.apirest01/ApiRest/tarea/3
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		//Listamos uno en concreto por id
		public Response getTareas(@PathParam("id") int id) {
			
			System.out.println(id);
			Tarea tareas = new Tarea();
			tareas.setId(id);
			
			if(lista.contains(tareas))
			{
				for(Tarea obj:lista)
					if(obj.getId()==id)
						return Response.ok(obj).build();
			}
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		
		//-----------------------------------------------------------------
		//http://localhost:8080/com.fernandogarcia.apirest01/ApiRest/tareas/3
		@DELETE
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		//Borramos uno en concreto por id
		public Response borrarTareas(@PathParam("id") int id) {
			
		
			Tarea tareas = new Tarea();
			tareas.setId(id);
			
			if(lista.contains(tareas))
			{
				lista.remove(tareas);
				return Response.ok().build();
			}
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		//-----------------------------------------------------------------
		
		
				@POST
				@Path("/{id}")
				@Produces(MediaType.APPLICATION_JSON)
				@Consumes(MediaType.APPLICATION_JSON)
				//Guardamos  una tarea nueva con sus atributos id,nombre
				public Response guardarTarea(Tarea tareas) {
				
					lista.add(tareas);
					return Response.status(Response.Status.CREATED).entity(tareas).build();
				}
		
		
		
}
