package com.kyx.factory.web.validation;

import com.baidu.unbiz.fluentvalidator.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 *
 * @author h.xu
 * @create 2017-05-10 上午10:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateError {
    String reason;

    public boolean hasError() {
        return Optional.ofNullable(reason).isPresent();
    }

    public static ValidateError toError(Result result) {
        return new ValidateError(result.getErrors().stream().findFirst().orElse(null));
    }
}
