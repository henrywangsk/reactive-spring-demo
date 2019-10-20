package com.henry.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.henry.demo.model.Reservation;
import com.henry.demo.service.ReservationService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
class ReservationResourceTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private ReservationService reservationService;

  @Test
  final void testGetAllReservations() {
    given(this.reservationService.listAllReservations())
      .willReturn(Flux.just(
        new Reservation[] { new Reservation(Long.valueOf(123), LocalDate.now(), LocalDate.now().plusDays(10), 150) }));

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
      new Reservation(Long.valueOf(123), LocalDate.now(), LocalDate.now().plusDays(10), 150)
    );
    
    given(this.reservationService.createReservation(any()))
      .willReturn(reservationMono);
    
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
