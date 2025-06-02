package br.com.requeijo.eventos.service;

import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.dto.EventoResponseDTO;
import br.com.requeijo.eventos.exception.ResourceNotFoundException;
import br.com.requeijo.eventos.model.Evento;
import br.com.requeijo.eventos.repository.EventoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventoService {

    private static final String EVENTO_NAO_ENCONTRADO = "Evento não encontrado: ";

    private final EventoRepository repository;

    public Page<EventoResponseDTO> listEvents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAllByDeletedFalse(pageable)
                .map(this::toResponseDTO);
    }

    public EventoResponseDTO getEventById(Long id) {

        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        return toResponseDTO(evento);
    }

    public EventoResponseDTO createEvent(EventoRequestDTO dto) {
        Evento evento = Evento.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                .location(dto.getLocation())
                .build();
        return toResponseDTO(repository.save(evento));
    }

    public EventoResponseDTO updateEvent(Long id, EventoRequestDTO dto) {
        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        evento.setTitle(dto.getTitle());
        evento.setDescription(dto.getDescription());
        evento.setEventDate(dto.getEventDate());
        evento.setLocation(dto.getLocation());

        return toResponseDTO(repository.save(evento));
    }

    public void softDeleteEvent(Long id) {
        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        evento.setDeleted(true);
        repository.save(evento);
    }

    private EventoResponseDTO toResponseDTO(Evento evento) {
        EventoResponseDTO dto = new EventoResponseDTO();
        dto.setId(evento.getId());
        dto.setTitle(evento.getTitle());
        dto.setDescription(evento.getDescription());
        dto.setEventDate(evento.getEventDate());
        dto.setLocation(evento.getLocation());
        dto.setCreatedAt(evento.getCreatedAt());
        dto.setUpdatedAt(evento.getUpdatedAt());
        return dto;
    }
}

