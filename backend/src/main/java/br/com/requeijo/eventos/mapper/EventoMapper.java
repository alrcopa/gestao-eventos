package br.com.requeijo.eventos.mapper;

import br.com.requeijo.eventos.dto.EventoDTO;
import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.model.Evento;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to map the Evento entity to the EventoRequestDTO and vice-versa.
 * ModelMapper currently does not support record types.
 */
@Component
public class EventoMapper {

    public Evento toModel(EventoRequestDTO eventoRequestDTO) {
        if (eventoRequestDTO == null) {
            return null;
        }
        Evento evento = new Evento();
        evento.setTitle(eventoRequestDTO.getTitle());
        evento.setDescription(eventoRequestDTO.getDescription());
        evento.setEventDate(eventoRequestDTO.getEventDate());
        evento.setLocation(eventoRequestDTO.getLocation());
        // Assuming createdAt and updatedAt are set automatically by the entity lifecycle

        return evento;
    }

    public EventoDTO toResponseDTO(Evento evento) {
        if (evento == null) {
            return null;
        }

        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTitle(evento.getTitle());
        dto.setDescription(evento.getDescription());
        dto.setEventDate(evento.getEventDate());
        dto.setLocation(evento.getLocation());
        dto.setCreatedAt(evento.getCreatedAt());
        dto.setUpdatedAt(evento.getUpdatedAt());
        return dto;

    }

    public List<EventoDTO> toResponseDTOList(List<Evento> eventos) {
        return eventos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }



    public void partialUpdateFromRequest(EventoRequestDTO dto, Evento evento) {

        if (dto == null || evento == null) {
            return;
        }
        if (dto.getTitle() != null) {
            evento.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            evento.setDescription(dto.getDescription());
        }
        if (dto.getEventDate() != null) {
            evento.setEventDate(dto.getEventDate());
        }
        if (dto.getLocation() != null) {
            evento.setLocation(dto.getLocation());
        }


    }
}
