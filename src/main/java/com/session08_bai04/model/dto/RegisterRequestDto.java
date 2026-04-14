package com.session08_bai04.model.dto;

import com.session08_bai04.custom_validate.FieldsValueMatch;
import jakarta.validation.constraints.NotBlank;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "confirmPassword",
        message = "Mật khẩu xác nhận không trùng khớp!"
)
public class RegisterRequestDto {

    @NotBlank(message = "Tài khoản không được để trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận lại mật khẩu")
    private String confirmPassword;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
