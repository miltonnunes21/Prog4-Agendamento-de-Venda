function validar(){
 
let nome = frmContacto.nome.value
let fone = frmContacto.fone.value

if (nome =="") {

  alert( 'Preencha o campo Nome')
  frmContacto.nome.focus()
  return false
} else if (fone === "") {

alert('Preencha o campo Fone')
frmContacto.fone.focus()
 return false


} else {

document.forms["frmContacto"].submit() 


}
}
