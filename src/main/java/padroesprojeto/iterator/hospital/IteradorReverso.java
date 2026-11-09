package padroesprojeto.iterator.hospital;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteradorReverso implements Iterator<Paciente> {

    private final List<Paciente> pacientes;
    private int posicao;

    public IteradorReverso(List<Paciente> pacientes) {
        this.pacientes = new ArrayList<>(pacientes);
        this.posicao = this.pacientes.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return posicao >= 0;
    }

    @Override
    public Paciente next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return pacientes.get(posicao--);
    }
}
