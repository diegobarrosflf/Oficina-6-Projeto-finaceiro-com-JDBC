import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO implements GenericoDAO {

	private Connection conexao;

	public UsuarioDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}

	public boolean inserir(Object o) {
		try {
			Usuario a = (Usuario) o;
			String query = "INSERT INTO usuario (nome, login, senha, ultimo_acesso) VALUES ('" + a.getNome() + "','"
					+ a.getLogin() + "','" + a.getSenha() + "','" + a.getUltimoAcesso() + "')";
			Statement sentenca = conexao.createStatement();
			sentenca.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean atualizar(Object o) {
		try {
			Usuario a = (Usuario) o;
			String query = "UPDATE usuario SET nome = '" + a.getNome() + "', login = '" + a.getLogin() + "', senha = '"
					+ a.getSenha() + "', ultimo_acesso = '" + a.getUltimoAcesso() + "' WHERE id_usuario = " + a.getId();
			Statement sentenca = conexao.createStatement();
			sentenca.executeUpdate(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean excluir(int id) {
		try {
			Usuario usuario = (Usuario) buscarPorID(id);
			if (usuario != null) {
				String query = "DELETE FROM usuario WHERE id_usuario = " + id;
				Statement sentenca = conexao.createStatement();
				sentenca.execute(query);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Object buscarPorID(int id) {
		try {
			String query = "SELECT * FROM usuario WHERE id_usuario = " + id;
			Statement sentenca = conexao.createStatement();
			ResultSet rs = sentenca.executeQuery(query);
			if (rs.next()) {
				Usuario a = new Usuario();
				a.setId(rs.getInt("id_usuario"));
				a.setNome(rs.getString("nome"));
				a.setLogin(rs.getString("login"));
				a.setSenha(rs.getString("senha"));
				a.setUltimoAcesso(rs.getDate("ultimo_acesso").toLocalDate());
				return a;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public ResultSet listar() {
		try {
			String query = "SELECT * FROM usuario";
			Statement sentenca = conexao.createStatement();
			ResultSet rs = sentenca.executeQuery(query);
			return rs;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}
