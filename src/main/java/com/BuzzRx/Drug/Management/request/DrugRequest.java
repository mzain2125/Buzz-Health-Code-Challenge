package com.BuzzRx.Drug.Management.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrugRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "ndc cannot be blank")
    private String ndc;

    @NotBlank(message = "Strength cannot be blank")
    private String strength;

    @NotBlank(message = "Form cannot be blank")
    private String form;

}
