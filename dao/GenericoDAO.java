import java.sql.ResultSet;

public interface GenericoDAO {
	
	public boolean inserir(Object o);
	public boolean atualizar(Object o);
	public boolean excluir(int id);
	public Object buscarPorID(int id);
	public ResultSet listar();
		
}
