public class Categoria {

	private Integer id;
	private String sigla;
	private String descricao;
	private String icone;

	public Categoria() {
	}

	public Categoria(String sigla, String descricao, String icone) {
		this.sigla = sigla;
		this.descricao = descricao;
		this.icone = icone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	@Override
	public String toString() {
		return "Categoria [sigla=" + sigla + ", descricao=" + descricao + ", icone=" + icone + "]";
	}

}
