package com.gestVet.app.domain.service;

import com.gestVet.app.domain.dto.CitaDTO;
import com.gestVet.app.domain.repository.CitaRepository;
import com.gestVet.app.exceptions.CitaModificacionNoPermitidaException;
import com.gestVet.app.exceptions.CitaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    // Consultar todas las citas
    public Iterable<CitaDTO> findAll() {
        return citaRepository.findAll();
    }

    // Consultar Cita por Id
    public Optional<CitaDTO> findById(Long id) {
        return citaRepository.findById(id);
    }

    // Guardar nueva cita
    public CitaDTO save(CitaDTO citaDTO) {
        if (!validarFechaFutura(citaDTO.getFecha())) {
            throw new IllegalArgumentException("La fecha de la cita debe ser futura");
        }
        return citaRepository.save(citaDTO);
    }

    // Actualizar Cita
    public CitaDTO updateEstado(CitaDTO citaDTO) throws CitaModificacionNoPermitidaException, CitaNotFoundException {
        if (!citaRepository.existsById(citaDTO.getCitaId())) {
            throw new CitaNotFoundException();
        }
        CitaDTO citaOriginal = findById(citaDTO.getCitaId()).orElse(null);
        if(!citaDTO.getFecha().isEqual(citaOriginal.getFecha()) || !citaDTO.getVeterinarioId().equals(citaOriginal.getVeterinarioId()) || !citaDTO.getMascotaId().equals(citaOriginal.getMascotaId()) || !citaDTO.getTipoCita().equals(citaOriginal.getTipoCita())) {
            throw new CitaModificacionNoPermitidaException();
        }
        citaOriginal.setEstado(citaDTO.getEstado());
        return citaRepository.updateEstado(citaOriginal);
    }

    // Cancelar Cita
    public boolean cancelarCita(Long id) throws CitaNotFoundException {
        if (!citaRepository.existsById(id)) {
            throw new CitaNotFoundException();
        }
        CitaDTO citaCancelada = findById(id).orElse(null);
        citaCancelada.setEstado("Cancelada");
        return citaRepository.updateEstado(citaCancelada) != null;
    }

    // Validar que la fecha sea futura
    private boolean validarFechaFutura(LocalDate fecha) {
        return fecha != null && fecha.isAfter(LocalDate.now());
    }

    // Consultar Citas por veterinario
    public Iterable<CitaDTO> findByVeterinarioId(Long id){
        return citaRepository.findByVeterinarioId(id);
    }

    // Consultar Citas por mascota
    public Iterable<CitaDTO> findByMascotaId(Long id) {
        return citaRepository.findByMascotaId(id);
    }

    public Iterable<CitaDTO> findByFecha(LocalDate fecha){
        return citaRepository.findByFecha(fecha);
    }

    // Consultar Citas por estado
    public Iterable<CitaDTO> findByEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }

    // Consultar Cita por Veterinario, fecha y estado
    public Iterable<CitaDTO> findByVeterinarioIdAndFechaAndEstado(Long vetId, LocalDate fecha, String estado){
        return citaRepository.findByVeterinarioIdAndFechaAndEstado(vetId, fecha, estado);
    }

    public long count(){
        return citaRepository.count();
    }
}
