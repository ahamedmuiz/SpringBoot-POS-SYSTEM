package lk.ijse._0_pos_back_end.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    @NotNull(message = "Customer ID is required")
    private String cId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Customer Name must contain only alphabets")
    private String cName;

    @Size(min = 10, max = 100, message = "Customer Address must be at least 10 characters long")
    private String cAddress;

    public CustomerDTO(String cId, String cName, String cAddress) {
        this.cId = cId;
        this.cName = cName;
        this.cAddress = cAddress;
    }

    public CustomerDTO() {
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }
}