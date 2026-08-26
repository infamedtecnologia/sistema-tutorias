package edu.uees.tutorias.repository;

import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.Reserva;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementacion simple usada para desarrollo y pruebas. Cumple el
 * contrato de RepositorioReservas sin depender de infraestructura
 * externa, lo que permite probar ServicioReservas de forma aislada.
 */
public class RepositorioReservasEnMemoria implements RepositorioReservas {

    private final Map<String, Reserva> almacen = new LinkedHashMap<>();

    @Override
    public void guardar(Reserva reserva) {
        almacen.put(reserva.getId(), reserva);
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Reserva> listarPorEstudiante(Estudiante estudiante) {
        return almacen.values().stream()
                .filter(r -> r.getEstudiante().equals(estudiante))
                .collect(Collectors.toList());
    }
}
