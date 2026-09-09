package edu.uees.tutorias.repository;

import edu.uees.tutorias.domain.Reserva;
import java.util.*;

public class RepositorioReservasEnMemoria implements RepositorioReservas {
    private final Map<String, Reserva> tabla = new HashMap<>();

    @Override
    public void guardar(Reserva reserva) {
        tabla.put(reserva.getId(), reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return Optional.ofNullable(tabla.get(id));
    }

    @Override
    public List<Reserva> obtenerTodas() {
        return new ArrayList<>(tabla.values());
    }
}
