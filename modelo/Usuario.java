import java.time.LocalDate;

public class Usuario {

	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private LocalDate ultimoAcesso;

	public Usuario() {

	}

	public Usuario(String nome, String login, String senha, LocalDate ultimoAcesso) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.ultimoAcesso = ultimoAcesso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(LocalDate ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", ultimoAcesso=" + ultimoAcesso + "]";
	}

}
