package br.com.requeijo.eventos.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequestDTO {

    @NotBlank
    @Size(max = 50)
    private String title;

    @Size(max = 100)
    private String description;

    @FutureOrPresent
    private LocalDateTime eventDate;

    @Length(min = 5, max = 200)
    private String location;
}

