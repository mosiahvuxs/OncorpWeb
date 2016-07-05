var win = null;

function NewWindow(mypage, myname, l, t, w, h, s, r) {
	settings = 'height=' + h + ',width=' + w + ',top=' + t + ',left=' + l
			+ ',scrollbars=' + s + ',resizable=' + r + ',status=no';
	win = window.open(mypage, myname, settings);
}

/* Funçãoo Pai de Mascaras */
function Mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execmascara()", 1);
}

function fullWindow(mypage, myname) {
	settings = 'height=' + screen.height + ',width=' + screen.width
			+ ',top=0,left=0,scrollbars=yes,resizable=yes,status=yes';
	win = window.open(mypage, myname, settings);
}

/* Funçãoo que Executa os objetos */
function execmascara() {
	v_obj.value = v_fun(v_obj.value);
}

/* Funçãoo que Determina as express�es regulares dos objetos */
function leech(v) {
	v = v.replace(/o/gi, "0");
	v = v.replace(/i/gi, "1");
	v = v.replace(/z/gi, "2");
	v = v.replace(/e/gi, "3");
	v = v.replace(/a/gi, "4");
	v = v.replace(/s/gi, "5");
	v = v.replace(/t/gi, "7");
	return v;
}

/* Funçãoo que permite apenas numeros */
function Integer(v) {
	return v.replace(/\D/g, "");
}

/* Funçãoo que padroniza telefone (11) 4184-1241 */
function Telefone(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/^(\d\d)(\d)/g, "($1) $2");
	v = v.replace(/(\d{4})(\d)/, "$1-$2");
	return v;
}

/* Funçãoo que padroniza telefone (11) 41841241 */
function TelefoneCall(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/^(\d\d)(\d)/g, "($1) $2");
	return v;
}

function mtel(v) {
	v = v.replace(/D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/^(d{2})(d)/g, "($1) $2"); // Coloca parênteses em volta
												// dos dois primeiros dígitos
	v = v.replace(/(d)(d{4})$/, "$1-$2"); // Coloca hífen entre o quarto e o
											// quinto dígitos
	return v;
}
/* Função que padroniza CPF */
function Cpf(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{3})(\d)/, "$1.$2");
	v = v.replace(/(\d{3})(\d)/, "$1.$2");

	v = v.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
	return v;
}

/* Função que padroniza CEP */
function Cep(v) {
	v = v.replace(/D/g, "");
	v = v.replace(/^(\d{5})(\d)/, "$1-$2");
	return v;
}

/* Funçao que padroniza CNPJ */
function Cnpj(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/^(\d{2})(\d)/, "$1.$2");
	v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
	v = v.replace(/\.(\d{3})(\d)/, ".$1/$2");
	v = v.replace(/(\d{4})(\d)/, "$1-$2");
	return v;
}

/* Função que permite apenas numeros Romanos */
function Romanos(v) {
	v = v.toUpperCase();
	v = v.replace(/[^IVXLCDM]/g, "");

	while (v.replace(
			/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/, "") != "")
		;
	v = v.replace(/.$/, "");
	return v;
}

/* Função que padroniza o Site */
function Site(v) {
	v = v.replace(/^http:\/\/?/, "");
	dominio = v;
	caminho = "";
	if (v.indexOf("/") > -1)
		dominio = v.split("/")[0];
	caminho = v.replace(/[^\/]*/, "");
	dominio = dominio.replace(/[^\w\.\+-:@]/g, "");
	caminho = caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g, "");
	caminho = caminho.replace(/([\?&])=/, "$1");
	if (caminho != "")
		dominio = dominio.replace(/\.+$/, "");
	v = "http://" + dominio + caminho;
	return v;
}

/* Funçao que padroniza DATA */
function Data(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{2})(\d)/, "$1/$2");
	v = v.replace(/(\d{2})(\d)/, "$1/$2");
	return v;
}

/* Função que padroniza DATA */
function Hora(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{2})(\d)/, "$1:$2");
	return v;
}

/* Função que padroniza valor mon�tario */
function Valor(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/, "$1.$2");
	// v=v.replace(/(\d{3})(\d)/g,"$1,$2")
	v = v.replace(/(\d)(\d{2})$/, "$1.$2"); // Coloca ponto antes dos 2 últimos
											// digitos
	return v;
}

/* Função que padroniza Area */
function Area(v) {
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d)(\d{2})$/, "$1.$2");
	return v;

}

function mascaraMoeda(campo) {
	campo.value = MaskMonetario(campo.value);
}

function MaskMonetario(v) {
	v = v.replace(/\D/g, ""); // Remove tudo o que não é dígito
	v = v.replace(/(\d{2})$/, ",$1"); // Coloca a virgula
	v = v.replace(/(\d+)(\d{3},\d{2})$/g, "$1.$2"); // Coloca o primeiro ponto
	var qtdLoop = (v.length - 3) / 3;
	var count = 0;
	while (qtdLoop > count) {
		count++;
		v = v.replace(/(\d+)(\d{3}.*)/, "$1.$2"); // Coloca o resto dos pontos
	}
	v = v.replace(/^(0)(\d)/g, "$2"); // Coloca hífen entre o quarto e o
										// quinto dígitos
	return v;
}

// Limita a quantidade de caracteres num textarea
function validarQtdeCaracteres(valor, max) {
	if (valor.length < max) {

		return true;

	}

	alert('Insira no máximo ' + max + ' caracteres.');

	window.event.returnValue = false;

	return false;
}

function mac(v) {
	v = v.toUpperCase(); // Maiúsculas
	v = v.replace(/[^ABCDEFG0123456789]/g, ""); // Remove tudo o que não for A,
												// B, C, D, E, F, G ou Numeros
	v = v.replace(/(\w\w{1})(\w{12})$/, "$1:$2");
	v = v.replace(/(\w\w{1})(\w{10})$/, "$1:$2");
	v = v.replace(/(\w\w{1})(\w{8})$/, "$1:$2");
	v = v.replace(/(\w\w{1})(\w{6})$/, "$1:$2");
	v = v.replace(/(\w\w{1})(\w{4})$/, "$1:$2");
	v = v.replace(/(\w\w{1})(\w{2})$/, "$1:$2");
	return v;
}

function mascara_num(obj) {
	valida_num(obj);
	if (obj.value.match("-")) {
		mod = "-";
	} else {
		mod = "";
	}
	valor = obj.value.replace("-", "");
	valor = valor.replace(",", "");
	obj.value = mod + valor;
}

function mascara_int(obj) {
	valida_num(obj);
	if (obj.value.match("-")) {
		mod = "";
	} else {
		mod = "";
	}
	valor = obj.value.replace("-", "");
	valor = valor.replace(",", "");
	obj.value = mod + valor;
}

function currencyFormat(fld, milSep, decSep, e) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	if (whichCode == 13)
		return true; // Enter
	key = String.fromCharCode(whichCode); // Get key value from key code
	if (strCheck.indexOf(key) == -1)
		return false; // Not a valid key
	len = fld.value.length;
	for (i = 0; i < len; i++)
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep))
			break;
	aux = '';
	for (; i < len; i++)
		if (strCheck.indexOf(fld.value.charAt(i)) != -1)
			aux += fld.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0)
		fld.value = '';
	if (len == 1)
		fld.value = '0' + decSep + '0' + aux;
	if (len == 2)
		fld.value = '0' + decSep + aux;
	if (len > 2) {
		aux2 = '';
		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += milSep;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}
		fld.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--)
			fld.value += aux2.charAt(i);
		fld.value += decSep + aux.substr(len - 3, len);
	}
	return false;
}