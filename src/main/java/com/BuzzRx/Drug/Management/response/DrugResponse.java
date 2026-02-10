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
public class DrugResponse {

    private UUID id;
    private String name;
    private String ndc;
    private String strength;
    private String form;
    private LocalDateTime createdAt;
}
