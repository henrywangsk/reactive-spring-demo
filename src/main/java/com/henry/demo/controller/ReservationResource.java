package com.henry.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henry.demo.model.Reservation;
import com.henry.demo.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ReservationResource.ROOM_V1_RESERVATIONS)
@CrossOrigin
public class ReservationResource {
  static final String ROOM_V1_RESERVATIONS = "/room/v1/reservations";
  
  private final ReservationService reservationService;

  @Autowired
  public ReservationResource(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Reservation> getReservationById(@PathVariable String id) {
    return reservationService.getReservation(id);
  }

  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Reservation> getAllReservations() {
    return reservationService.listAllReservations();
  }
  
  @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Reservation> createReservation(@RequestBody Mono<Reservation> reservationMono) {
    return reservationService.createReservation(reservationMono);
  }
  
  @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Reservation> updatePrice(@PathVariable String id, @RequestBody Mono<Reservation> reservationMono) {
    return reservationService.updateReservation(id, reservationMono);
  }
  
  @DeleteMapping(path = "/{id}")
  public Mono<Boolean> deleteReservation(@PathVariable String id) {
    return reservationService.deleteReservation(id);
  }
}