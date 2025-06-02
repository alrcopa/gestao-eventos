package br.com.requeijo.eventos.controller;

import br.com.requeijo.eventos.dto.EventoRequestDTO;
import br.com.requeijo.eventos.dto.EventoResponseDTO;
import br.com.requeijo.eventos.service.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping
    public Page<EventoResponseDTO> list(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return service.listEvents(page, pageSize);
    }

    @GetMapping("/{id}")
    public EventoResponseDTO get(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public EventoResponseDTO create(@Valid @RequestBody EventoRequestDTO requestDTO) {
        return service.createEvent(requestDTO);
    }

    @PutMapping("/{id}")
    public EventoResponseDTO update(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO requestDTO) {
        return service.updateEvent(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDeleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}


