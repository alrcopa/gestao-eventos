package br.com.requeijo.eventos.service;

import br.com.requeijo.eventos.dto.EventoPageDTO;
import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.dto.EventoDTO;
import br.com.requeijo.eventos.exception.ResourceNotFoundException;
import br.com.requeijo.eventos.mapper.EventoMapper;
import br.com.requeijo.eventos.model.Evento;
import br.com.requeijo.eventos.repository.EventoRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    private static final String EVENTO_NAO_ENCONTRADO = "Evento não encontrado: ";

    private final EventoRepository repository;

    private final EventoMapper eventoMapper;

    public EventoPageDTO findAll(@PositiveOrZero int page, @Positive @Max(1000) int pageSize) {

        Page<Evento> coursePage = repository.findAllByDeletedFalse(PageRequest.of(page, pageSize));

        List<EventoDTO> list = coursePage.getContent().stream()
                .map(eventoMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new EventoPageDTO(list, coursePage.getTotalElements(), coursePage.getTotalPages());
    }

    public EventoService(EventoRepository repository, EventoMapper eventoMapper) {
        this.repository = repository;
        this.eventoMapper = eventoMapper;
    }

    public Page<EventoDTO> listEvents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAllByDeletedFalse(pageable)
                .map(eventoMapper::toResponseDTO);
    }

    public EventoDTO getEventById(Long id) {

        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        return eventoMapper.toResponseDTO(evento);
    }

    public EventoDTO createEvent(EventoRequestDTO dto) {
        Evento evento = eventoMapper.toModel(dto);
        return eventoMapper.toResponseDTO(repository.save(evento));
    }

    public EventoDTO updateEvent(Long id, EventoRequestDTO dto) {
        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));

        eventoMapper.partialUpdateFromRequest(dto, evento);

        return eventoMapper.toResponseDTO(repository.save(evento));
    }

    public void softDeleteEvent(Long id) {
        Evento evento = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(EVENTO_NAO_ENCONTRADO + id));
        evento.setDeleted(true);
        repository.save(evento);
    }




}

