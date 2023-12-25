package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;


@WebServlet(urlPatterns = {"/Controller" , "/main", "/insert", "/select", "/update", "/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();

	 JavaBeans contato = new JavaBeans();

    public Controller() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TESTE conexao
		dao.testeConexao();

String action = request.getServletPath();
System.out.println(action);
		 
   
if (action.equals("/main")){

	contatos(request, response);

} else if(action.equals("/insert")) {

	   novoContato(request, response);

	} else if(action.equals("/select")) {

		   listarContato(request, response);

		} else if(action.equals("/update")) {

			   editarContato(request, response);

			} else if(action.equals("/delete")) {

				   removerContato(request, response);

				}
		
		else {

	     response.sendRedirect("index.html");
	}


	}

	
	// Listar Conatctos:
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

// Criando um objecto que irá receber os dados do JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
		
}
	
	
	// Novo Conatctos:
	
		protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			//teste de recibemento
			System.out.println(request.getParameter("nome"));
			System.out.println(request.getParameter("fone"));
			System.out.println(request.getParameter("email"));
	  
			
			//Setar as variaveis JavaBeans

			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email")); 
			
			//Invocar o metodo inserirContato passando o objecto contato
			dao.inserirContato(contato);
			

			// redirecionar para o documento agenda.jsp
			response.sendRedirect("main");
				
				
			
			}

		
		// Edita contactos
	
		protected void listarContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			

			// Recibemento de id do contato que será editado 
			String idcon = request.getParameter("idcon");
             // Setar as variaveis do JavaBeans
			contato.setIdcon(idcon);
			

			// Executar o metodo selecionarContato (DA0)

			dao.selecionarContato(contato);
			
			// teste de recebimento: É opcional deixar
			
			System.out.println(contato.getIdcon());
			System.out.println(contato.getNome());
			System.out.println(contato.getFone());
			System.out.println(contato.getEmail());
			
			// Setar os Atributos do formulario com o conteudo javaBeans
			


       request.setAttribute("idcon", contato.getIdcon());
       request.setAttribute("nome", contato.getNome());
       request.setAttribute("fone", contato.getFone());
       request.setAttribute("email", contato.getEmail());
       
       // Encaminhar ao documento editar.jsp 
       
       RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
       rd.forward(request, response);
			
		}
	
		protected void editarContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			// teste de recebimento
			System.out.println(request.getParameter("idcon") );
			System.out.println(request.getParameter("nome") );
			System.out.println(request.getParameter("fone") );
			System.out.println(request.getParameter("email") );
			

			// setar a variáveis JavaBeans

			contato.setIdcon(request.getParameter("idcon")); 
			contato.setNome(request.getParameter("nome")); 
			contato.setFone(request.getParameter("fone")); 
			contato.setEmail(request.getParameter("email")); 
			
			 // Executar o método alterarContato

			dao.alterarContato(contato);
		
		
			 // Redirecionar para o documento agenda.jsp

			dao.alterarContato(contato);


			response.sendRedirect("main");
			
		}

		
		// Remover contatos
		
		protected void removerContato(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			
			String idcon = request.getParameter("idcon");
			System.out.println(idcon);
			 // Setar as variaveis JavaBeans
			 contato.setIdcon(idcon);
			// executar o método deletarContato

			dao.deletarContato(contato);

			// redirecionar para a pasta agenda.jsp
			response.sendRedirect("main");
		}
		

		
		
	}

