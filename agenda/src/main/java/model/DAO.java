package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO{

   private String driver = "com.mysql.cj.jdbc.Driver";
   private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

   
   private String user = "root";
   private String password = ""; 
  
  private Connection conectar(){
      Connection con =  null;
      
      

      try{
       Class.forName(driver);
       con = DriverManager.getConnection(url, user, password);
       return con;       
      }  catch (Exception e) {
            System.out.println(e);
            return null;
     

       }
  }
  
  
  // CRUDE CRIAR USER

  
  public void inserirContato(JavaBeans contato){

	  String create = "insert into contatos (nome,fone,email) values(?,?,?)"; 

	  try{
	  //Abrir a conexáo

	  Connection con = conectar();
	  //Preparar a query a execucao de dados
	  PreparedStatement pst = con.prepareStatement(create);
	  //  Subsitituir os parametros (?) pelo conteúdo das variáveis

	  pst.setString(1, contato.getNome());
	  pst.setString(2, contato.getFone());
	  pst.setString(3, contato.getEmail());

	  // Executar a query ou variavel create
	  pst.executeUpdate();
	  //Encerrar a conexao com o banco
	  con.close(); 


	  } catch (Exception e){

	   System.out.println(e);



	  }
	  
	  

	  



	  


	  }
  
  
  
  
  
  
  
//teste de conexao

public  void testeConexao(){
try{
Connection con = conectar();
System.out.println(con);

} catch (Exception e){
 System.out.println(e);



}
}


// CRUDE Read 

public ArrayList<JavaBeans> listarContatos(){
	
	//Craindo um objecto para acessar a classe JavaBeans
	ArrayList<JavaBeans>  contatos = new ArrayList<>();
	String read = "Select * from contatos  ";
	
  try {
	  
	 Connection con = conectar(); 
	 PreparedStatement pst = con.prepareStatement(read); 
	 ResultSet rs = pst.executeQuery();
	 
	// Os laços abaixo será executado enquanto tiver contacto
	 
	 while(rs.next()){
		 // Variaveis de Apoio que recebem os dados do banco
		String  idcon = rs.getString(1);
		String  nome = rs.getString(2);
		String  fone = rs.getString(3);
		String  email = rs.getString(4);
		
	//Pupulando o ArrayList
	contatos.add(new JavaBeans(idcon, nome, fone, email));	
	
		 
	 }
	 
	 con.close();
	 return contatos;
	  
  } catch (Exception e) {
	  
	 System.out.println(e);
	 return null;
	  
  }
	
	
	
}

// CRUDE UPDATE 

//selecionar  po contato

public void selecionarContato(JavaBeans contato){

	String read2 = "select * from contatos where idcon=?"; 
	 
	 try{

	Connection con = conectar();
	PreparedStatement pst = con.prepareStatement(read2);
	pst.setString(1, contato.getIdcon());
	ResultSet rs = pst.executeQuery();
	
	while(rs.next()){
		// setar as variaveis JavaBeans
		 contato.setIdcon(rs.getString(1));
		 contato.setNome(rs.getString(2 ));
		 contato.setFone(rs.getString(3));
		 contato.setEmail(rs.getString(4));

		}

	con.close();
	} catch (Exception e){

	   System.out.println(e);


	}
  


}


// editar o contato

  public void alterarContato(JavaBeans contato){
 
	  String create = "update contatos set nome=?, fone=?,email=? where idcon=? "; 

	  try {

	  Connection con = conectar();
	  PreparedStatement pst = con.prepareStatement(create);
	  pst.setString(1, contato.getNome());
	  pst.setString(2, contato.getFone());
	  pst.setString(3, contato.getEmail());
	  pst.setString(4 , contato.getIdcon());

	  pst.executeUpdate();
	  con.close();


	  } catch (Exception e) {

	  System.out.println(e);



	  }




     }
  

//CRUDE DELete

public void deletarContato (JavaBeans contato){



String delete = " delete from contatos where idcon=?";

try{

Connection con = conectar();
PreparedStatement pst = con.prepareStatement(delete);
pst.setString(1, contato.getIdcon());
pst.executeUpdate ();
con.close() ;
 

} catch(Exception e) {

System.out.println(e);


}




  
  
}



}
    

 
   




