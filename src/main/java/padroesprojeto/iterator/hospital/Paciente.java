package padroesprojeto.iterator.hospital;

public class Paciente {

    private String nome;
    private int prioridade;

    public Paciente(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    public String getNome() {
        return nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return nome + " (prioridade " + prioridade + ")";
    }
}
