package br.com.requeijo.eventos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EventoDTO {

    @JsonProperty("_id")
    private Long id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}

