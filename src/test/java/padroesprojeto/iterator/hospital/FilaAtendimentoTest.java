package padroesprojeto.iterator.hospital;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FilaAtendimentoTest {

    FilaAtendimento fila;

    @BeforeEach
    void setUp() {
        fila = new FilaAtendimento();
        fila.adicionar(new Paciente("Ana", 3));
        fila.adicionar(new Paciente("Bruno", 1));
        fila.adicionar(new Paciente("Carla", 2));
        fila.adicionar(new Paciente("Davi", 1));
    }

    private List<String> nomes(Iterator<Paciente> iterador) {
        List<String> nomes = new ArrayList<>();
        while (iterador.hasNext()) {
            nomes.add(iterador.next().getNome());
        }
        return nomes;
    }

    @Test
    void devePercorrerNaOrdemDeChegada() {
        assertEquals(List.of("Ana", "Bruno", "Carla", "Davi"), nomes(fila.iterator()));
    }

    @Test
    void devePercorrerComForEach() {
        List<String> nomes = new ArrayList<>();
        for (Paciente paciente : fila) {
            nomes.add(paciente.getNome());
        }
        assertEquals(List.of("Ana", "Bruno", "Carla", "Davi"), nomes);
    }

    @Test
    void devePercorrerPorPrioridadeMantendoAOrdemDeChegadaNosEmpates() {
        assertEquals(List.of("Bruno", "Davi", "Carla", "Ana"), nomes(fila.iteradorPorPrioridade()));
    }

    @Test
    void devePercorrerEmOrdemReversa() {
        assertEquals(List.of("Davi", "Carla", "Bruno", "Ana"), nomes(fila.iteradorReverso()));
    }

    @Test
    void filaVaziaNaoDeveTerProximo() {
        FilaAtendimento vazia = new FilaAtendimento();
        assertFalse(vazia.iterator().hasNext());
        assertFalse(vazia.iteradorPorPrioridade().hasNext());
        assertFalse(vazia.iteradorReverso().hasNext());
    }

    @Test
    void naoDeveRetornarProximoQuandoOsElementosAcabaram() {
        Iterator<Paciente> iterador = fila.iterator();
        while (iterador.hasNext()) {
            iterador.next();
        }
        assertThrows(NoSuchElementException.class, iterador::next);
    }

    @Test
    void doisIteradoresDevemTerPosicoesIndependentes() {
        Iterator<Paciente> primeiro = fila.iterator();
        Iterator<Paciente> segundo = fila.iterator();

        assertEquals("Ana", primeiro.next().getNome());
        assertEquals("Bruno", primeiro.next().getNome());
        assertEquals("Ana", segundo.next().getNome());
    }

    @Test
    void iteradorCriadoNaoDeveEnxergarPacientesAdicionadosDepois() {
        Iterator<Paciente> iterador = fila.iterator();
        fila.adicionar(new Paciente("Elisa", 2));

        assertEquals(4, nomes(iterador).size());
        assertEquals(5, fila.tamanho());
    }

    @Test
    void naoDevePermitirRemocaoPeloIterador() {
        Iterator<Paciente> iterador = fila.iterator();
        iterador.next();
        assertThrows(UnsupportedOperationException.class, iterador::remove);
    }
}
