
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

	private FabricaConexao fabricaConexao;
	private Connection conexao;
	private Scanner s;
	private UsuarioDAO usuarioDAO;
	private CategoriaDAO categoriaDAO;
	private MovimentoDAO movimentoDAO;

	public Principal() {
		this.s = new Scanner(System.in);
	}

	// método para obter a conexão
	public void getConexao(String usuario, String senha, String ipServidor, String banco) {
		this.fabricaConexao = new FabricaConexao(usuario, senha, ipServidor, banco);
		this.conexao = fabricaConexao.getConexao();
		this.usuarioDAO = new UsuarioDAO(this.conexao);
		this.categoriaDAO = new CategoriaDAO(this.conexao);
		this.movimentoDAO = new MovimentoDAO(this.conexao);

	}

	public Connection getConexao() {
		return conexao;
	}

	public Scanner getS() {
		return s;
	}

	public static void main(String[] args) throws SQLException {

		System.out.println(
				"Informe valores para conex�o ao Banco de Dados na ordem \n" + "Usuario, senha, servidor e banco");

		// É necessáŕio aperta enter para ir ao próximo item de configuração
		Principal principal = new Principal();
		try {
			principal.getConexao(principal.getS().nextLine(), principal.getS().nextLine(), principal.getS().nextLine(),
					principal.getS().nextLine());

			int opcao = 16;
			// menu
			while (opcao != 0) {
				try {
					System.out.println("Usu�rio:       \t\t  Categoria:         \t\t  Movimento");
					System.out.println(" 1 - Cadastrar \t\t    6 - Cadastrar    \t\t   11 - Cadastrar");
					System.out.println(" 2 - Excluir   \t\t    7 - Excluir      \t\t   12 - Excluir ");
					System.out.println(" 3 - Alterar   \t\t    8 - Alterar      \t\t   13 - Alterar");
					System.out.println(" 4 - Listar    \t\t    9 - Listar       \t\t   14 - Listar");
					System.out.println(" 5 - Consultar \t\t   10 - Consultar    \t\t   15 - Consultar");
					System.out.println("Informe uma op��o acima ou 0 para finalizar");
					opcao = principal.getS().nextInt();

					switch (opcao) {
						case 0:
							principal.conexao.close();
							System.out.println("FIM");
							break;
						case 1:
							principal.adicionarUsuario();
							break;
						case 2:
							principal.excluirUsuario();
							break;
						case 3:
							principal.alterarUsuario();
							break;
						case 4:
							principal.listarUsuario();
							break;
						case 5:
							principal.consultarUsuario();
							break;
						case 6:
							principal.adicionarCategoria();
							break;
						case 7:
							principal.excluirCategoria();
							break;
						case 8:
							principal.alterarCategoria();
							break;
						case 9:
							principal.listarCategoria();
							break;
						case 10:
							principal.consultarCategoria();
							break;
						case 11:
							principal.adicionarMovimento();
							break;
						case 12:
							principal.excluirMovimento();
							break;
						case 13:
							principal.alterarMovimento();
							break;
						case 14:
							principal.listarMovimento();
							break;
						case 15:
							principal.consultarMovimento();
							break;
						default:
							System.out.println("Op��o inv�lida");
					}

				} catch (Exception e) {
					System.out.println("Erro valor informado");
					System.out.println(e.getMessage());
				}
			}
		} finally {
			principal.conexao.close();
		}
	}

	public void adicionarUsuario() {
		Usuario usuario = new Usuario();
		System.out.println("Adicionando usu�rio");
		s.nextLine();
		System.out.print("Informe nome:");
		usuario.setNome(s.nextLine());
		System.out.print("Informe login:");
		usuario.setLogin(s.nextLine());
		System.out.print("Informe senha:");
		usuario.setSenha(s.nextLine());
		usuario.setUltimoAcesso(LocalDate.now());
		if (usuarioDAO.inserir(usuario)) {
			System.out.println("Cadastrado com sucesso");
		} else {
			System.out.println("Erro cadastramento");
		}
	}

	private void excluirUsuario() {

		System.out.println("Excluindo usu�rio");
		System.out.print("Informe Id do usu�rio:");
		Integer id = s.nextInt();
		if (usuarioDAO.excluir(id)) {
			System.out.println("Excluido com sucesso");
		} else {
			System.out.println("Erro exclus�o");
		}
	}

	private void alterarUsuario() {
		Usuario usuario = new Usuario();
		System.out.println("Atualizando usu�rio");

		System.out.print("Informe Id:");
		usuario = (Usuario) usuarioDAO.buscarPorID(s.nextInt());
		System.out.println("Informe novos valores para usu�rio");
		System.out.println("Nome atual: " + usuario.getNome());
		s.nextLine();
		String nome = s.nextLine();
		usuario.setNome((nome.isEmpty()) ? usuario.getNome() : nome);
		System.out.println("Login: " + usuario.getLogin());
		String login = s.nextLine();
		usuario.setLogin((login.isEmpty()) ? usuario.getLogin() : login);
		System.out.println("Senha: *******");
		String senha = s.nextLine();
		usuario.setSenha((senha.isEmpty()) ? usuario.getSenha() : senha);
		if (usuarioDAO.atualizar(usuario)) {
			System.out.println("Atualizado com sucesso");
		} else {
			System.out.println("Erro atualiza��o");
		}
	}

	private void consultarUsuario() {
		Usuario usuario = new Usuario();
		System.out.println("Consultando usu�rio");

		System.out.print("Informe Id:");
		usuario = (Usuario) usuarioDAO.buscarPorID(s.nextInt());
		System.out.println("Id: " + usuario.getId() + "\nNome: " + usuario.getNome() + "\nLogin: " + usuario.getLogin()
				+ "\n�ltimo Acesso: " + usuario.getUltimoAcesso());
	}

	private void listarUsuario() {
		ResultSet rs = usuarioDAO.listar();
		try {
			while (rs.next()) {

				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Id: " + rs.getInt("id_usuario"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("Login: " + rs.getString("login"));
				System.out.println("�ltimo acesso: " + rs.getDate("ultimo_acesso").toLocalDate());

			}
			System.out.println("----------------------------------------------------------------------------");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void consultarMovimento() {
		Movimento movimento = new Movimento();
		System.out.println("Consultando Movimento");

		System.out.print("Informe Id:");
		movimento = (Movimento) movimentoDAO.buscarPorID(s.nextInt());
		System.out.println("Id: " + movimento.getId() + "\nCategoria id\\Sigla: " + movimento.getCategoria().getSigla()
				+ "\\" + movimento.getCategoria().getSigla() + "\nUsuario id\\Nome: " + movimento.getUsuario().getId()
				+ "\\" + movimento.getUsuario().getNome() + "\nDebito : " + movimento.getDebito() + "\nValor: "
				+ movimento.getValor() + "\nComent�rio: " + movimento.getComentario() + "\nData movimenta��o: "
				+ movimento.getDataMovimento());

	}

	private void listarMovimento() {
		ResultSet rs = movimentoDAO.listar();
		try {
			while (rs.next()) {

				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Id: " + rs.getInt("id_movimento"));
				System.out.println("Id do usu�rio: " + rs.getInt("id_usuario"));
				System.out.println("Id da categoria: " + rs.getInt("id_categoria"));
				System.out.println("Debito: " + rs.getBoolean("debito"));
				System.out.println("Valor: " + rs.getDouble("valor"));
				System.out.println("Coment�rio: " + rs.getString("comentario"));
				System.out.println("Data movimento: " + rs.getDate("data_movimento").toLocalDate());

			}
			System.out.println("----------------------------------------------------------------------------");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void alterarMovimento() {
		Movimento movimento = new Movimento();
		System.out.println("Alterando Movimento");

		System.out.print("Informe Id:");
		movimento = (Movimento) movimentoDAO.buscarPorID(s.nextInt());
		System.out.println("Informe novos valores para movimento");

		System.out.println("Id categoria atual: " + movimento.getCategoria().getId());
		s.nextLine();
		Categoria categoria = (Categoria) categoriaDAO.buscarPorID(s.nextInt());
		movimento.setCategoria((categoria.equals(null)) ? movimento.getCategoria() : categoria);

		System.out.println("Id usu�rio atual: " + movimento.getUsuario().getId());
		Usuario usuario = (Usuario) usuarioDAO.buscarPorID(s.nextInt());
		movimento.setUsuario((usuario.equals(null)) ? movimento.getUsuario() : usuario);

		System.out.println("Valor atual: " + movimento.getValor());
		Double valor = s.nextDouble();
		movimento.setValor((valor.toString().isEmpty()) ? movimento.getValor() : valor);

		System.out.println("Debito atual: " + movimento.getDebito());
		Boolean debito = s.nextBoolean();
		movimento.setDebito((debito.toString().isEmpty()) ? movimento.getDebito() : debito);

		System.out.println("Coment�rio atual: " + movimento.getComentario());
		String comentario = s.nextLine();
		movimento.setComentario((comentario.isEmpty()) ? movimento.getComentario() : comentario);

		if (movimentoDAO.atualizar(movimento)) {
			System.out.println("Atualizado com sucesso");
		} else {
			System.out.println("Erro atualiza��o");
		}

	}

	private void excluirMovimento() {
		System.out.println("Excluindo movimento");
		System.out.print("Informe Id do movimento:");
		Integer id = s.nextInt();
		if (movimentoDAO.excluir(id)) {
			System.out.println("Excluido com sucesso");
		} else {
			System.out.println("Erro exclus�o");
		}

	}

	private void adicionarMovimento() {
		Movimento movimento = new Movimento();
		System.out.println("Adicionando movimento");
		s.nextLine();
		System.out.print("Informe usu�rio:");
		movimento.setUsuario((Usuario) usuarioDAO.buscarPorID(s.nextInt()));
		System.out.print("Informe categoria:");
		movimento.setCategoria((Categoria) categoriaDAO.buscarPorID(s.nextInt()));
		System.out.print("Informe debito:");
		movimento.setDebito(s.nextBoolean());
		System.out.print("Informe valor:");
		movimento.setValor(s.nextDouble());
		System.out.print("Informe Data Movimento:");
		movimento.setDataMovimento(LocalDate.now());
		System.out.print("Informe Coment�rio:");
		movimento.setComentario(s.nextLine());
		if (movimentoDAO.inserir(movimento)) {
			System.out.println("Cadastrado com sucesso");
		} else {
			System.out.println("Erro cadastramento");
		}

	}

	private void consultarCategoria() {
		Categoria categoria = new Categoria();
		System.out.println("Consultando categoria");

		System.out.print("Informe Id:");
		categoria = (Categoria) categoriaDAO.buscarPorID(s.nextInt());
		System.out.println("Id: " + categoria.getId() + "\nSigla: " + categoria.getSigla() + "\nDescri��o: "
				+ categoria.getDescricao() + "\nIcone : " + categoria.getIcone());

	}

	private void listarCategoria() {
		ResultSet rs = categoriaDAO.listar();
		try {
			while (rs.next()) {

				System.out.println("----------------------------------------------------------------------------");
				System.out.println("Id: " + rs.getInt("id_categoria"));
				System.out.println("Descri��o: " + rs.getString("descricao"));
				System.out.println("Icone: " + rs.getString("icone"));
				System.out.println("Sigla: " + rs.getString("sigla"));
			}
			System.out.println("----------------------------------------------------------------------------");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void alterarCategoria() {
		Categoria categoria = new Categoria();
		System.out.println("Atualizando categoria");

		System.out.print("Informe Id:");
		categoria = (Categoria) categoriaDAO.buscarPorID(s.nextInt());
		System.out.println("Informe novos valores para categoria");
		System.out.println("Descri��o atual: " + categoria.getDescricao());
		s.nextLine();
		String descricao = s.nextLine();
		categoria.setDescricao((descricao.isEmpty()) ? categoria.getDescricao() : descricao);
		System.out.println("icone: " + categoria.getIcone());
		String icone = s.nextLine();
		categoria.setIcone((icone.isEmpty()) ? categoria.getIcone() : icone);
		System.out.println("sigla: " + categoria.getSigla());
		String sigla = s.nextLine();
		categoria.setSigla((sigla.isEmpty()) ? categoria.getSigla() : sigla);
		if (categoriaDAO.atualizar(categoria)) {
			System.out.println("Atualizado com sucesso");
		} else {
			System.out.println("Erro atualiza��o");
		}

	}

	private void excluirCategoria() {
		System.out.println("Excluindo categoria");
		System.out.print("Informe Id da categoria:");
		Integer id = s.nextInt();
		if (categoriaDAO.excluir(id)) {
			System.out.println("Excluido com sucesso");
		} else {
			System.out.println("Erro exclus�o");
		}
	}

	private void adicionarCategoria() {
		Categoria categoria = new Categoria();
		System.out.println("Adicionando Categoria");
		s.nextLine();
		System.out.print("Informe descri��o:");
		categoria.setDescricao(s.nextLine());
		System.out.print("Informe Icone:");
		categoria.setIcone(s.nextLine());
		System.out.print("Informe sigla:");
		categoria.setSigla(s.nextLine());
		if (categoriaDAO.inserir(categoria)) {
			System.out.println("Cadastrado com sucesso");
		} else {
			System.out.println("Erro cadastramento");
		}
	}

}
