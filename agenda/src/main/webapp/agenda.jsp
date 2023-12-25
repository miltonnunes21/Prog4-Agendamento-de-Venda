<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.JavaBeans" %>
<%@ page import="java.util.ArrayList"%>


<%

ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
request.getAttribute("contatos");



%>
    
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title> Agendar Contactos </title>

<link  rel="icon" href="imagem/icon.jpg">
<link rel="stylesheet" href="style.css">

</head>
<body>


<div class="prin2">

<h1> Agendamento de Vendas</h1>

<a href="novo.html" class="botao2"> Novo Agendamento </a>

     <table id="tabela" >

   <thead>
      <tr>
          <th style="text-align:center"> Id </th>
         <th style="text-align:center"> Nome </th>
         <th style="text-align:center"> Fone</th>
         <th style="text-align:center"> E-mail </th>
         <th style="text-align:center" >Opções</th>
         

       
        </tr>
  
   </thead>

   <tbody>
       <%for (int i =0; i < lista.size(); i++ ){%>
            <tr>
               <td><%=lista.get(i).getIdcon()%></td>
               <td><%=lista.get(i).getNome()%></td>
               <td><%=lista.get(i).getFone()%></td>
               <td><%=lista.get(i).getEmail()%></td>
                <td><a href="select?idcon=<%=lista.get(i).getIdcon() %>  " class="botao1 "> Editar</a>  
      
               <a href="javascript: confirmar(<%=lista.get(i).getIdcon() %> )"  class="botao3"> Excluir </a>
                </td>
 
           </tr>





    <%} %>


   </tbody>


</table>


  <script src="scripts/confirmador.js"> </script>

</div>

</body>
</html>