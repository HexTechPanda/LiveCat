package com.livecat.system.req;

import com.livecat.util.validator.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("Register Request.")
public class RegisterREQ implements Serializable {
    @ApiModelProperty(value = "username", required = true)
    @NotNull
    private String username;

    @ApiModelProperty(value = "password", required = true)
    @NotNull
    @Length(min=6)
    private String password;

    @ApiModelProperty(value = "repPassword", required = true)
    @NotNull
    @Length(min=6)
    private String repPassword;

    @ApiModelProperty(value = "nickName", required = false)
    private String nickName;

    @ApiModelProperty(value = "repPassword", required = false)
    @IsMobile
    private String mobile;

    @ApiModelProperty(value = "repPassword", required = false)
    @Email
    private String email;
}
