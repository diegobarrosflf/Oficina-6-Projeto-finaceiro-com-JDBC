import java.sql.Connection;
import java.time.LocalDate;

public class Teste {

    public static void main(String[] args) {

        // criar uma conexão com o banco para rodar localmente
        FabricaConexao fc = new FabricaConexao("root", "abcd1234", "localhost", "SGF");
        Connection conexao = fc.getConexao();

        // criar os objetos DAO de cada entidade para realizar as operações de CRUD
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        CategoriaDAO categoriaDAO = new CategoriaDAO(conexao);
        MovimentoDAO movimentoDAO = new MovimentoDAO(conexao);

        // CREATE
        // criamos 1 objeto para cada entidade e printamos o resultado do método inserir
        // no console
        Usuario u1 = new Usuario("Diego Barros", "diegogb", "abcd1234", LocalDate.now());
        System.out.println("Usuario criado? --> " + usuarioDAO.inserir(u1));

        Categoria cat1 = new Categoria("DEBT", "pagamento agua", "teste");
        System.out.println("Categoria criada? --> " + categoriaDAO.inserir(cat1));

        Movimento mov1 = new Movimento((Categoria) categoriaDAO.buscarPorID(1), (Usuario) usuarioDAO.buscarPorID(1),
                LocalDate.now(), true, 100.0, "teste movimento");
        System.out.println("Movimento criado? --> " + movimentoDAO.inserir(mov1));

        // RESEARCH buscar pelo id os 3 objetos criados

        System.out.println("\n Buscando usuario : " + usuarioDAO.buscarPorID(1));
        System.out.println("\n Buscando categoria : " + categoriaDAO.buscarPorID(1));
        System.out.println("\n Buscando movimento : " + movimentoDAO.buscarPorID(1) + "\n");

        // UPDATE atualizando um campo para cada entidade
        u1.setNome("Diego Guimarães");
        System.out.println("Usuario atualizado? -->" + usuarioDAO.atualizar(u1));

        cat1.setDescricao("alterando descrição teste...");
        System.out.println("Categoria atualizada? -->" + categoriaDAO.atualizar(cat1));

        mov1.setComentario("Alterando comentário teste....");
        System.out.println("Movimento atualizado? -->" + movimentoDAO.atualizar(mov1));

        // DELETE excluindo as entradas criadas no inicio do teste
        System.out.println("Deletando usuario: -->" + usuarioDAO.excluir(4));
        System.out.println("Deletando categoria: -->" + categoriaDAO.excluir(4));
        System.out.println("Deletando movimento: -->" + movimentoDAO.excluir(4));

    }

}