package uz.pdp.ussdapp.payload;

import lombok.Data;

@Data
public class EntertainmentServiceDto {

    private String name;
    private double price;
    private int dueDate; //1kunli
    private Integer categoryId;

}
