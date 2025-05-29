package br.com.requeijo.eventos.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

