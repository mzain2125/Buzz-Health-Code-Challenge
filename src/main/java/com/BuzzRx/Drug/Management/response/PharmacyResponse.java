package com.BuzzRx.Drug.Management.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyResponse {

    private UUID id;
    private String npi;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private LocalDateTime createdAt;
}

