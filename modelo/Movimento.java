import java.time.LocalDate;

public class Movimento {

	private Integer id;
	private Categoria categoria;
	private Usuario usuario;
	private LocalDate dataMovimento;
	private Boolean debito;
	private Double valor;
	private String comentario;

	public Movimento() {
	}

	public Movimento(Categoria categoria, Usuario usuario, LocalDate dataMovimento, Boolean debito, Double valor,
			String comentario) {
		this.categoria = categoria;
		this.usuario = usuario;
		this.dataMovimento = dataMovimento;
		this.debito = debito;
		this.valor = valor;
		this.comentario = comentario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(LocalDate dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Boolean getDebito() {
		return debito;
	}

	public void setDebito(Boolean debito) {
		this.debito = debito;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Movimento [categoria=" + categoria + ", usuario=" + usuario + ", dataMovimento=" + dataMovimento
				+ ", debito=" + debito + ", valor=" + valor + "]";
	}

}
