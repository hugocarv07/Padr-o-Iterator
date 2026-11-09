package padroesprojeto.iterator.hospital;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteradorPrioridade implements Iterator<Paciente> {

    private final List<Paciente> ordenados;
    private int posicao = 0;

    public IteradorPrioridade(List<Paciente> pacientes) {
        this.ordenados = new ArrayList<>(pacientes);
        this.ordenados.sort(Comparator.comparingInt(Paciente::getPrioridade));
    }

    @Override
    public boolean hasNext() {
        return posicao < ordenados.size();
    }

    @Override
    public Paciente next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return ordenados.get(posicao++);
    }
}
