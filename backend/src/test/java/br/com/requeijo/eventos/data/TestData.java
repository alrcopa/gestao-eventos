package br.com.requeijo.eventos.data;

import br.com.requeijo.eventos.dto.EventoDTO;
import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.model.Evento;


import java.time.LocalDateTime;
import java.util.List;

public class TestData {

    private static final String TITULO = "Titulo";
    private static final String INVALID_COURSE_NAME = "Spr";
    private static final String LOREN_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc et quam nec diam tristique mollis eget quis urna. Sed dapibus lectus in arcu rutrum, non luctus sem finibus. Cras nisl neque, pellentesque et tortor id, dapibus auctor turpis.";


    private TestData() {
    }

    public static Evento createValidEvento() {
        Evento evento = new Evento();
        evento.setId(1L);
        evento.setTitle(TITULO);
        evento.setDescription(LOREN_IPSUM);
        evento.setEventDate(LocalDateTime.now());
        evento.setLocation("Localização do Evento");


        return evento;
    }

    public static EventoDTO createValidEventoDTO() {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setTitle(TITULO);
        eventoDTO.setDescription(LOREN_IPSUM);
        eventoDTO.setEventDate(LocalDateTime.now());
        eventoDTO.setLocation("Localização do Evento");
        return eventoDTO;

    }

    public static EventoRequestDTO createValidEventoRequest() {
        return new EventoRequestDTO(TITULO, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento");
    }



    public static List<Evento> createInvalidEventos() {
        final String validName = TITULO;
        final String empty = "";

        return List.of(
                buildEvento(null, null, null, null),
                buildEvento(null, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                buildEvento(empty, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                buildEvento(INVALID_COURSE_NAME, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                buildEvento(validName, null, LocalDateTime.now(), "Localização do Evento"),
                buildEvento(validName, LOREN_IPSUM, null, "Localização do Evento"));
    }

    private static Evento buildEvento(String name, String description, LocalDateTime eventData, String location) {
        Evento course = new Evento();

        course.setTitle(name);
        course.setDescription(description);
        course.setEventDate(eventData);
        course.setLocation(location);

        return course;
    }

    public static List<EventoRequestDTO> createInvalidEventosDTO() {
        final String validName = TITULO;
        final String empty = "";

        return List.of(
                new EventoRequestDTO(null, null, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(null, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(empty, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(INVALID_COURSE_NAME, LOREN_IPSUM, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(validName, null, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(validName, empty, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(validName, INVALID_COURSE_NAME, LocalDateTime.now(), "Localização do Evento"),
                new EventoRequestDTO(validName, LOREN_IPSUM, null, "Localização do Evento"),
                new EventoRequestDTO(validName, LOREN_IPSUM, LocalDateTime.now(), null),
                new EventoRequestDTO(validName, LOREN_IPSUM, LocalDateTime.now(), empty));
    }
}
