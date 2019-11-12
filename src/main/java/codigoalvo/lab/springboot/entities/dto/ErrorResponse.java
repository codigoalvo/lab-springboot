package codigoalvo.lab.springboot.entities.dto;

public class ErrorResponse {

	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	private String nomeCampo;

	public ErrorResponse(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public ErrorResponse(String mensagemUsuario, String mensagemDesenvolvedor, String nomeCampo) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		this.nomeCampo = nomeCampo;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	@Override
	public String toString() {
		return "Erro{" +
				"mensagemUsuario='" + mensagemUsuario + '\'' +
				", mensagemDesenvolvedor='" + mensagemDesenvolvedor + '\'' +
				", nomeCampo='" + nomeCampo + '\'' +
				'}';
	}

}
