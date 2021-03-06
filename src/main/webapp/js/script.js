var app = new function() {
		this.elUsuarios = document.getElementById("usuarios");
		this.usuarios = [];
		this.itemEditar = -1;
		this.idUsuEditar;
		this.contar = function(qtd) {
			var elContador = document.getElementById("contador");
			var nome = "Usuario";
			if (qtd) {
				if (qtd > 1) {
					nome = "Usuários";
				}
				elContador.innerHTML = qtd + ' ' + nome;
			} else {
				elContador.innerHTML = 'Sem ' + nome;
			}
		};
		this.salvar = function() {
			var elNome = document.getElementById("add-nome");
			var elEmail = document.getElementById("add-email");
			
			var usuario =  {	nome:elNome.value,email:elEmail.value};
			
			if (usuario) {
				if (this.itemEditar == -1) {
					var xhttp = new XMLHttpRequest();
					//CallBack
					xhttp.onreadystatechange = function() {
						//processou com sucesso
						if (this.readyState == 4 && this.status == 200) {
							//inserindo no vetor
							//app.usuarios.push(usuario);
							app.listarTodos();
							//app.novo();
						}
					}
					//Faz o request
					xhttp.open("POST", "usucontroller", true);
					//Formato dos dados para envio
					xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					xhttp.send("nome="+usuario.nome+"&email="+usuario.email);
				} else {
					var xhttp = new XMLHttpRequest();
					//callback
					xhttp.onreadystatechange = function() {
						//processou com sucesso
						if (this.readyState == 4 && this.status == 200) {
							//Altera quando tem alguem para alterar
							//app.usuarios.splice(app.itemEditar, 1, usuario);
							app.listarTodos();
						}
					};
					
					params ="i="+app.idUsuEditar+"&nome="+usuario.nome+"&email="+usuario.email;
					
					//Faz o request
					xhttp.open("PUT", "usucontroller?" + params, true);
					//Formato dos dados para envio
					xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					xhttp.send();
					
					this.itemEditar = -1;
				}
				
				this.novo();
			}
			this.listarTodos();
			this.novo();
		}
		this.excluir = function(id) {
			//this.usuarios.splice(item, 1);
			var xhttp = new XMLHttpRequest();
			
			//callback
			xhttp.onreadystatechange = function() {
				//processou com sucesso
				if (this.readyState == 4 && this.status == 200) {
					app.listarTodos();
				}
			};
			
			params ="i="+id;
			
			//Faz o request
			xhttp.open("DELETE", "usucontroller?" + params, true);
			//Formato dos dados para envio
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
			
			this.listarTodos();
		}
		this.editar = function(item, id) {
			this.itemEditar = item;
			var nome = this.usuarios[item].nome;
			var email = this.usuarios[item].email;
			app.idUsuEditar = id;
			if (nome && email) {
				document.getElementById("add-nome").value = nome;
				document.getElementById("add-email").value = email;
			}
		}
		this.listarTodos = function() {
			var xhttp = new XMLHttpRequest();
			
			//callback
            /*xhttp.onreadystatechange = function() {
                //processou com sucesso
                if (this.readyState == 4 && this.status == 200) {
                        //inserindo no vetor
                        var json = JSON.parse(this.responseText);
                        app.usuarios = json;
                }
            };*/
            
            xhttp.onload = function () {
				app.usuarios = JSON.parse(xhttp.response);
				//gera String em formato tabela
		          var dados = '';
		          
		          for (i = 0; i < app.usuarios.length; i++){
		        	  dados+='<tr>';
		        	  dados+='<td>'+app.usuarios[i].id+'</td>';
		              dados+='<td>'+app.usuarios[i].nome+'</td>';
		              dados+='<td>'+app.usuarios[i].email+'</td>';
		              dados+='<td><button onclick="app.excluir('+app.usuarios[i].id+')">excluir</button></td>';
		              dados+='<td><button onclick="app.editar('+i+","+app.usuarios[i].id+')">editar</button></td>';
		            
		              dados+="</tr>";
		          }
		          
		          //atualiza contador
		          app.contar(app.usuarios.length);
		          //atualizar DOM
		          app.elUsuarios.innerHTML= dados;
			}
            
            //Faz o request
            xhttp.open("GET", "usucontroller", true);
            xhttp.send(); 
		}
		this.novo = function() {
			this.itemEditar = -1;
			document.getElementById("add-nome").value = "";
			document.getElementById("add-email").value = "";
		}
	}
	app.listarTodos();