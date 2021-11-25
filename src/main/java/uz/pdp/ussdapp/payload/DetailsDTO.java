package uz.pdp.ussdapp.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class DetailsDTO {
    private String action;
    private double amount;
}
