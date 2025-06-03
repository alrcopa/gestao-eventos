package br.com.requeijo.eventos.dto;

import java.util.List;

/**
 * Used as response object that represents a Page with a list of Courses.
 */
public record EventoPageDTO(
        List<EventoDTO> eventos,
        long totalElements,
        int totalPages) {

}
