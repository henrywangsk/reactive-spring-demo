package com.henry.demo.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.henry.demo.model.Reservation;

import reactor.core.publisher.Mono;

@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT)
class ReservationResourceIntegrationTest {
  @Autowired
  private WebTestClient webTestClient;

  @Test
  final void testGetAllReservations() {
    webTestClient
      .get()
      .uri(ReservationResource.ROOM_V1_RESERVATIONS)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
      .expectBodyList(Reservation.class);
  }

  @Test
  final void testCreateReservation() {
    Mono<Reservation> reservationMono = Mono.just(
      new Reservation(Long.valueOf(123), LocalDate.now(), LocalDate.now().plusDays(10), 150));

    webTestClient
      .post()
      .uri(ReservationResource.ROOM_V1_RESERVATIONS)
      .body(reservationMono, Reservation.class)
      .exchange()
      .expectStatus().isOk()
      .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
      .expectBody(Reservation.class);
  }

}
