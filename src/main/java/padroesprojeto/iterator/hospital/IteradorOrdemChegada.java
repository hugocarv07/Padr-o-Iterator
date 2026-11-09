package padroesprojeto.iterator.hospital;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteradorOrdemChegada implements Iterator<Paciente> {

    private final List<Paciente> pacientes;
    private int posicao = 0;

    public IteradorOrdemChegada(List<Paciente> pacientes) {
        this.pacientes = new ArrayList<>(pacientes);
    }

    @Override
    public boolean hasNext() {
        return posicao < pacientes.size();
    }

    @Override
    public Paciente next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return pacientes.get(posicao++);
    }
}
