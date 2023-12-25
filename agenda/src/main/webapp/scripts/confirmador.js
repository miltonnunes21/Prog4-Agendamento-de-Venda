/**
 * 
 * // Confirmação da Exclusáo do Contacto
 * @ Autores Dimas e Nunes"
 * 
 */

 

function confirmar(idcon){
let resposta = confirm("Confirma a Exclusão do Contacto?")
if(resposta === true){
alert(idcon)
window.location.href = "delete?idcon=" + idcon

}

}