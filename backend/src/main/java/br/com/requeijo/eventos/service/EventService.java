package br.com.requeijo.eventos.service;

import br.com.requeijo.eventos.dto.EventRequestDTO;
import br.com.requeijo.eventos.dto.EventResponseDTO;
import br.com.requeijo.eventos.exception.ResourceNotFoundException;
import br.com.requeijo.eventos.model.Event;
import br.com.requeijo.eventos.repository.EventRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private static final String EVENTO_NAO_ENCONTRADO = "Evento não encontrado: ";

    private final EventRepository repository;

    public Page<EventResponseDTO> listEvents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAllByDeletedFalse(pageable)
                .map(this::toResponseDTO);
    }

    public EventResponseDTO getEventById(Long id) {

        Event event = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        return toResponseDTO(event);
    }

    public EventResponseDTO createEvent(EventRequestDTO dto) {
        Event event = Event.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                .location(dto.getLocation())
                .build();
        return toResponseDTO(repository.save(event));
    }

    public EventResponseDTO updateEvent(Long id, EventRequestDTO dto) {
        Event event = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(dto.getLocation());

        return toResponseDTO(repository.save(event));
    }

    public void softDeleteEvent(Long id) {
        Event event = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        event.setDeleted(true);
        repository.save(event);
    }

    private EventResponseDTO toResponseDTO(Event event) {
        EventResponseDTO dto = new EventResponseDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setLocation(event.getLocation());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        return dto;
    }
}

