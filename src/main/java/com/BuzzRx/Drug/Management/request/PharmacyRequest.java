package com.BuzzRx.Drug.Management.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Npi cannot be blank and must be unique")
    private String npi;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "State cannot be blank")
    @Pattern(regexp = "^[A-Z]{2}$", message = "State must be two uppercase letters")
    private String state;
    @NotBlank(message = "Zip cannot be blank")
    @Pattern(regexp = "^\\d{5}$", message = "Zip must be 5 digits")
    private String zip;
}
