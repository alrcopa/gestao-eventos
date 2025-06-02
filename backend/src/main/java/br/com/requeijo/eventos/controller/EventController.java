package br.com.requeijo.eventos.controller;

import br.com.requeijo.eventos.dto.EventRequestDTO;
import br.com.requeijo.eventos.dto.EventResponseDTO;
import br.com.requeijo.eventos.service.EventService;
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
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @GetMapping
    public Page<EventResponseDTO> list(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int pageSize) {
        return service.listEvents(page, pageSize);
    }

    @GetMapping("/{id}")
    public EventResponseDTO get(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public EventResponseDTO create(@Valid @RequestBody EventRequestDTO requestDTO) {
        return service.createEvent(requestDTO);
    }

    @PutMapping("/{id}")
    public EventResponseDTO update(@PathVariable Long id, @Valid @RequestBody EventRequestDTO requestDTO) {
        return service.updateEvent(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDeleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}


