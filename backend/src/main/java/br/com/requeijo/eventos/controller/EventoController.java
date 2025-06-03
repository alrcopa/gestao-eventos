package br.com.requeijo.eventos.controller;

import br.com.requeijo.eventos.dto.EventoPageDTO;
import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.dto.EventoDTO;
import br.com.requeijo.eventos.service.EventoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    // Constructor injection is preferred for better testability and immutability

//    @GetMapping
//    public Page<EventoResponseDTO> list(@RequestParam(defaultValue = "0") int page,
//                                        @RequestParam(defaultValue = "10") int pageSize) {
//        return service.listEvents(page, pageSize);
//    }

    @GetMapping
    public EventoPageDTO findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    public EventoDTO get(@PathVariable @Positive @NotNull Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public EventoDTO create(@Valid @RequestBody EventoRequestDTO requestDTO) {
        return service.createEvent(requestDTO);
    }

    @PutMapping("/{id}")
    public EventoDTO update(@PathVariable @Positive @NotNull Long id,
                            @Valid @RequestBody EventoRequestDTO requestDTO) {
        return service.updateEvent(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDeleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}


