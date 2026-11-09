package padroesprojeto.iterator.hospital;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilaAtendimento implements Iterable<Paciente> {

    private final List<Paciente> pacientes = new ArrayList<>();

    public void adicionar(Paciente paciente) {
        pacientes.add(paciente);
    }

    public int tamanho() {
        return pacientes.size();
    }

    @Override
    public Iterator<Paciente> iterator() {
        return new IteradorOrdemChegada(pacientes);
    }

    public Iterator<Paciente> iteradorPorPrioridade() {
        return new IteradorPrioridade(pacientes);
    }

    public Iterator<Paciente> iteradorReverso() {
        return new IteradorReverso(pacientes);
    }
}
