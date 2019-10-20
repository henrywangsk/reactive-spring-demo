package com.henry.demo.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Reservation {

  @NonNull
  private Long roomNumber;
  
  @DateTimeFormat(iso=ISO.DATE)
  @NonNull
  private LocalDate checkIn;
  
  @DateTimeFormat(iso=ISO.DATE)
  @NonNull
  private LocalDate checkOut;

  @NonNull
  private Integer price;

  @Id
  private String id;
}
