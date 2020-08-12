import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {
	private Connection conexao;
	
	public FabricaConexao(String usuario, String senha, String servidorIP, String banco) {
		super();
		try {
			//conexão com o banco de dados
			String url = "jdbc:mysql://"+ servidorIP + "/" + banco+"?useTimezone=true&serverTimezone=UTC";
			System.out.println("conectando ao sistema de dados.....");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(url, usuario, senha);			
			System.out.println("conectado com sucesso!!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
	
}
