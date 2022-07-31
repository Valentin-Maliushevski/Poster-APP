package com.itacademy.controller.json;

import com.itacademy.service.custom_exception.multiple.Multiple400Exception;
import com.itacademy.dao.entity.Concert;
import com.itacademy.dto.ConcertRead;
import com.itacademy.dto.CustomPage;
import com.itacademy.dto.ConcertCreateUpdate;
import com.itacademy.service.api.IConcertService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/afisha/event/concerts")
public class ConcertController {

  private final IConcertService concertService;

  public ConcertController(IConcertService eventService) {
    this.concertService = eventService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody ConcertCreateUpdate dto)throws Multiple400Exception {
    this.concertService.add(dto);
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<ConcertRead> get(@PathVariable UUID uuid) {
    return new ResponseEntity<>(this.concertService.getConcertByUuid(uuid), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<CustomPage<Concert>> findPaginated(@RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "20") int size){
    return new ResponseEntity<>(this.concertService.getCustomPage(page, size), HttpStatus.OK);
  }

  @PutMapping("/{uuid}/dt_update/{dt_update}")
  @ResponseStatus(HttpStatus.OK)
  public void update(@RequestBody ConcertCreateUpdate dto,
      @PathVariable UUID uuid,  @PathVariable Long dt_update) throws Multiple400Exception {
    this.concertService.update(dto, uuid, dt_update);
  }
}