import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO implements GenericoDAO {
	
    private Connection conexao;
    
     public CategoriaDAO(Connection conexao) {
		super();
		this.conexao = conexao;
	}
     
	public boolean inserir(Object o) {        
         try{
             Categoria a = (Categoria) o;
             String query = "INSERT INTO categoria (sigla, descricao, icone) VALUES ('" + a.getSigla() + "','" 
             + a.getDescricao() + "','" + a.getIcone() + "')"; 
             Statement sentenca = conexao.createStatement();
             sentenca.execute(query);
             return true;
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
         return false;
     }

     public boolean atualizar(Object o) {
         try{
             Categoria a = (Categoria)o;
             String query = "UPDATE categoria SET sigla = '" + a.getSigla() + "', descricao = '" + a.getDescricao() +
            		 "', icone = '" + a.getIcone() + "' WHERE id_categoria = " + a.getId();
             Statement sentenca = conexao.createStatement();
             sentenca.executeUpdate(query);
             return true;
         }catch (Exception e){
        	 System.out.println(e.getMessage());
         }
         return false;
     }

	public boolean excluir(int id) {
		try {
			Categoria categoria = (Categoria) buscarPorID(id);
			if (categoria != null) {
				String query = "DELETE FROM categoria WHERE id_categoria = " + id;
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
         try{
             String query = "SELECT * FROM categoria WHERE id_categoria = " + id; 
             Statement sentenca = conexao.createStatement();
             ResultSet rs = sentenca.executeQuery(query);
             if(rs.next()){
                 Categoria a = new Categoria();
                 a.setId(rs.getInt("id_categoria"));
                 a.setSigla(rs.getString("sigla"));
                 a.setDescricao(rs.getString("descricao"));
                 a.setIcone(rs.getString("icone"));
                 return a;
             }                    
         }catch (Exception e){
        	 System.out.println("Categoria n�o existe");
         }
         return null;

     }

	@Override
	public ResultSet listar() {
		try{
            String query = "SELECT * FROM categoria"; 
            Statement sentenca = conexao.createStatement();
            ResultSet rs = sentenca.executeQuery(query);
            return rs; 
                            
        }catch (Exception e){
       	 System.out.println("N�o existe categoria");
        }
		return null;
	}
     
}