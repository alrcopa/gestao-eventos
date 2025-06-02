package br.com.requeijo.eventos.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequestDTO {

    @NotBlank
    @Size(max = 100)
    private String title;

    @Size(max = 1000)
    private String description;

    @FutureOrPresent
    private LocalDateTime eventDate;

    @Size(max = 200)
    private String location;
}

