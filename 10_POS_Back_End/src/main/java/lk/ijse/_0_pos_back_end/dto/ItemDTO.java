package lk.ijse._0_pos_back_end.dto;

//import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
public class ItemDTO {

    @NotNull(message = "Item ItemCode is Mandatory")
    private String iCode;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Item Description must contain only alphabets")
    private String iDescription;

    @NotNull
//    @Pattern(regexp = "^[0-9]+(.[0-9]+)?$", message = "Item Price must be a number")
    private double iPrice;

    @NotNull
//    @Pattern(regexp = "^[0-9]+$", message = "Item Quantity must be a number")
    private int iQty;


    public ItemDTO(String iCode, String iDescription, double iPrice, int iQty) {
        this.iCode = iCode;
        this.iDescription = iDescription;
        this.iPrice = iPrice;
        this.iQty = iQty;
    }

    public ItemDTO() {
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public String getiDescription() {
        return iDescription;
    }

    public void setiDescription(String iDescription) {
        this.iDescription = iDescription;
    }

    public double getiPrice() {
        return iPrice;
    }

    public void setiPrice(double iPrice) {
        this.iPrice = iPrice;
    }

    public int getiQty() {
        return iQty;
    }

    public void setiQty(int iQty) {
        this.iQty = iQty;
    }

}
