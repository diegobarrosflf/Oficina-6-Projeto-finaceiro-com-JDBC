import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MovimentoDAO implements GenericoDAO {
	
    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    private CategoriaDAO categoriaDAO;
    
    public MovimentoDAO(Connection conexao) {
		super();
		this.conexao = conexao;
		usuarioDAO = new UsuarioDAO(conexao);
	    categoriaDAO = new CategoriaDAO(conexao);
	}    
    
     public boolean inserir(Object o) {        
         try{
             Movimento a = (Movimento) o;
             String query = "INSERT INTO movimento (id_usuario, id_categoria, data_movimento, debito, valor, comentario)"
             		+ " VALUES (" + a.getUsuario().getId() + "," + a.getCategoria().getId() +
             		",'" + a.getDataMovimento() + "',"+ a.getDebito() + ","+ a.getValor() + ",'"+ a.getComentario()+ "')"; 
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
             Movimento a = (Movimento)o;
             String query = "UPDATE movimento SET id_usuario = " + a.getUsuario().getId() + ", id_categoria = " + a.getCategoria().getId() +
            		 ", data_movimento = '" + a.getDataMovimento() +"', debito = " + a.getDebito() +", valor = " + a.getValor()+", comentario = '" + a.getComentario() + "' WHERE id_movimento = " + a.getId();
             Statement sentenca = conexao.createStatement();
             sentenca.executeUpdate(query);
             return true;
         }catch (Exception e){
        	 System.out.println(e.getMessage());
         }
         return false;
     }
     public boolean excluir(int id) {
        try{
        	Movimento movimento = (Movimento) buscarPorID(id);
        	if(movimento!=null) {
        		String query = "DELETE FROM movimento WHERE id_movimento = " + id; 
        		Statement sentenca = conexao.createStatement();
        		sentenca.execute(query);
        		return true;
        	}else {
        		return false;
        	}
            
         }catch (Exception e){
        	 System.out.println(e.getMessage());
         }
         return false;
     }
     public Object buscarPorID(int id) {
         try{
             String query = "SELECT * FROM movimento WHERE id_movimento = " + id; 
             Statement sentenca = conexao.createStatement();
             ResultSet rs = sentenca.executeQuery(query);
             if(rs.next()){
                 Movimento a = new Movimento();
                 a.setId(rs.getInt("id_movimento"));
                 a.setUsuario((Usuario) usuarioDAO.buscarPorID(rs.getInt("id_usuario")));
                 a.setCategoria((Categoria) categoriaDAO.buscarPorID(rs.getInt("id_categoria")));
                 a.setDebito(rs.getBoolean("debito"));
                 a.setDataMovimento(rs.getDate("data_movimento").toLocalDate());
                 a.setValor(rs.getDouble("valor"));
                 a.setComentario(rs.getString("comentario"));
                 return a;
             }                    
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
         return null;

     }
	@Override
	public ResultSet listar() {
		try{
            String query = "SELECT * FROM movimento"; 
            Statement sentenca = conexao.createStatement();
            ResultSet rs = sentenca.executeQuery(query);
            return rs; 
                            
        }catch (Exception e){
       	 System.out.println(e.getMessage());
        }
        return null;
	}
     
}