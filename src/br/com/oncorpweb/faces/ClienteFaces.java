package br.com.oncorpweb.faces;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.oncorpweb.business.ClienteBS;
import br.com.oncorpweb.dao.EstadoDAO;
import br.com.oncorpweb.dao.UsuarioDAO;
import br.com.oncorpweb.model.Cliente;
import br.com.oncorpweb.model.ClienteEndereco;
import br.com.oncorpweb.model.Estado;
import br.com.oncorpweb.model.Item;
import br.com.oncorpweb.model.Pais;
import br.com.oncorpweb.model.TipoIdentificador;
import br.com.oncorpweb.model.Usuario;
import br.com.oncorpweb.util.Constantes;
import br.com.oncorpweb.util.EmailUtil;
import br.com.oncorpweb.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;

@SuppressWarnings("serial")
@ManagedBean(name = "clienteFaces")
@ViewScoped
public class ClienteFaces extends TSMainFaces {

	@EJB
	private ClienteBS clienteBS;
	private Cliente cliente;
	private Item item;
	private boolean exibirDivOk, exibirDivErro;
	private List<Estado> estados;

	public ClienteFaces() {

		this.iniciar();
	}

	private void iniciar() {

		this.cliente = new Cliente(Boolean.FALSE, new TipoIdentificador(Constantes.PESSOA_FISICA), new Usuario(), new ClienteEndereco(new Estado(new Pais(Constantes.PAIS_BRAZIL))));
		this.exibirDivOk = false;
		this.exibirDivErro = false;
		this.estados = new EstadoDAO().pesquisar(new Estado(new Pais(Constantes.PAIS_BRAZIL)));
	}

	public String setarPessoa() {

		String tipoPessoa = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tipoPessoa");

		if (!TSUtil.isEmpty(tipoPessoa)) {

			this.cliente = new Cliente(Boolean.FALSE, new TipoIdentificador(Constantes.PESSOA_FISICA), new Usuario(), new ClienteEndereco(new Estado(new Pais(Constantes.PAIS_BRAZIL))));

			this.cliente.getTipoIdentificador().setId(new Long(tipoPessoa));

		}

		return null;

	}

	private boolean validarCampos() {

		boolean validado = true;

		this.exibirDivErro = false;
		this.exibirDivOk = false;

		if (Constantes.PESSOA_FISICA.equals(this.cliente.getTipoIdentificador().getId())) {

			if (TSUtil.isEmpty(this.cliente.getNome())) {

				validado = false;

				super.addErrorMessage("Nome: campo obrigatório.");
			}

			if (TSUtil.isEmpty(this.cliente.getIdentificador())) {

				validado = false;

				super.addErrorMessage("CPF: campo obrigatório.");

			} else {

				if (this.cliente.getIdentificador().length() < 11) {

					validado = false;

					super.addErrorMessage("CPF: campo inválido.");
				}
			}

			if (TSUtil.isEmpty(this.cliente.getNascimento())) {

				validado = false;

				super.addErrorMessage("Nascimento: campo obrigatório.");

			} else {

				if (!Utilitarios.isValidDate(this.cliente.getNascimento())) {

					validado = false;

					super.addErrorMessage("Data: campo inválido.");
				}
			}

			if (TSUtil.isEmpty(this.cliente.getEmail())) {

				validado = false;

				super.addErrorMessage("E-mail: campo obrigatório.");

			} else {

				if (!TSUtil.isEmailValid(this.cliente.getEmail())) {

					validado = false;

					super.addErrorMessage("E-mail: campo inválido.");

				} else if (!TSUtil.isEmpty(new UsuarioDAO().obterPorEmail(new Usuario(this.cliente.getEmail())))) {

					validado = false;

					super.addErrorMessage("O E-mail informado já existe!");
				}
			}

			if (TSUtil.isEmpty(this.cliente.getTelefone())) {

				validado = false;

				super.addErrorMessage("Telefone: campo obrigatório.");

			} else {

				if (this.cliente.getTelefone().length() < 14) {

					validado = false;

					super.addErrorMessage("Telefone: campo inválido.");

				}
			}

		} else {

			if (TSUtil.isEmpty(this.cliente.getNome())) {

				validado = false;

				super.addErrorMessage("Razão Social: campo obrigatório.");
			}

			if (TSUtil.isEmpty(this.cliente.getIdentificador())) {

				validado = false;

				super.addErrorMessage("CNPJ: campo obrigatório.");

			} else {

				if (this.cliente.getIdentificador().length() < 17) {

					validado = false;

					super.addErrorMessage("CNPJ: campo inválido.");
				}
			}

			if (TSUtil.isEmpty(this.cliente.getNomeContato())) {

				validado = false;

				super.addErrorMessage("Responsável para contato: campo obrigatório.");
			}

			if (TSUtil.isEmpty(this.cliente.getEmail())) {

				validado = false;

				super.addErrorMessage("E-mail: campo obrigatório.");

			} else {

				if (!TSUtil.isEmailValid(this.cliente.getEmail())) {

					validado = false;

					super.addErrorMessage("E-mail: campo inválido.");

				} else if (!TSUtil.isEmpty(new UsuarioDAO().obterPorEmail(new Usuario(this.cliente.getEmail())))) {

					validado = false;

					super.addErrorMessage("O E-mail informado já existe!");
				}
			}

			if (TSUtil.isEmpty(this.cliente.getTelefone())) {

				validado = false;

				super.addErrorMessage("Telefone: campo obrigatório.");

			} else {

				if (this.cliente.getTelefone().length() < 14) {

					validado = false;

					super.addErrorMessage("Telefone: campo inválido.");

				}
			}

		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getLogradouro())) {

			validado = false;

			super.addErrorMessage("Logradouro: campo obrigatório.");
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getBairro())) {

			validado = false;

			super.addErrorMessage("Bairro: campo obrigatório.");
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getCidade())) {

			validado = false;

			super.addErrorMessage("Cidade: campo obrigatório.");
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getEstado().getId())) {

			validado = false;

			super.addErrorMessage("Estado: campo obrigatório.");
		}

		if (TSUtil.isEmpty(this.cliente.getClienteEndereco().getCep())) {

			validado = false;

			super.addErrorMessage("CEP: campo obrigatório.");

		} else {

			if (this.cliente.getClienteEndereco().getCep().length() < 9) {

				validado = false;

				super.addErrorMessage("CEP: campo inválido.");
			}
		}

		if (!validado) {

			this.exibirDivErro = true;
		}

		return validado;
	}

	public void salvar() {

		if (this.validarCampos()) {

			try {

				if (Constantes.PESSOA_FISICA.equals(this.cliente.getTipoIdentificador().getId())) {

					this.cliente.setDataNascimento(TSParseUtil.stringToDate(this.cliente.getNascimento(), TSDateUtil.DD_MM_YYYY));

				} else {

					this.cliente.setDataNascimento(null);
				}

				this.cliente = this.clienteBS.inserir(this.cliente);

				this.exibirDivOk = true;

				String clienteCriptografado = TSCryptoUtil.criptografar(this.cliente.getId().toString());

				String url = null;

				if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

					url = "http://localhost:8080/OncorpWeb/confirmacao/clienteId=" + clienteCriptografado;

				} else {

					url = Constantes.URL_PRODUCAO + "/confirmacao/clienteId=" + clienteCriptografado;
				}

				Item item = (Item) TSFacesUtil.getObjectInSession(Constantes.ITEM_URL);

				if (!TSUtil.isEmpty(item)) {

					String itemCriptografado = TSCryptoUtil.criptografar(item.getId().toString());

					url = url + "&itemId=" + itemCriptografado;

				}

				String corpoEmail = Utilitarios.lerArquivo("/WEB-INF/templateEmails/mailing.html");

				corpoEmail = corpoEmail.replace("[cliente]", this.cliente.getNome().toUpperCase());

				corpoEmail = corpoEmail.replace("[urlConfirmacao]", url);

				EmailUtil.enviarEmailHtml(Constantes.EMAIL_NO_REPLY, this.cliente.getEmail(), "Confirmação de Cadastro", corpoEmail.toString(), Constantes.PERSONALIZACAO_EMAIL_ONCORP);

				super.addInfoMessage("Após o envio dos dados, verifique seu e-mail. Um link de confirmação foi enviado, para que, complemente o cadastro com as informações de pagamento.");

			} catch (TSApplicationException e) {

				super.addErrorMessage("Não foi possível realizar o cadastro. Tente novamente mais tarde.");

				e.printStackTrace();

			} catch (EmailException e) {

				e.printStackTrace();
			}
		}

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isExibirDivOk() {
		return exibirDivOk;
	}

	public void setExibirDivOk(boolean exibirDivOk) {
		this.exibirDivOk = exibirDivOk;
	}

	public boolean isExibirDivErro() {
		return exibirDivErro;
	}

	public void setExibirDivErro(boolean exibirDivErro) {
		this.exibirDivErro = exibirDivErro;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
