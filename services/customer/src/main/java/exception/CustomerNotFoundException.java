package exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException{

    private final String msg;

    public CustomerNotFoundException(String msg){
        this.msg=msg;
    }
}
