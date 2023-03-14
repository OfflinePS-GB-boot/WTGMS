package y88.kirill.corelib.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class WTGHttpStatus {

    private int status;
    private String message;
    private Date timestamp;

    public WTGHttpStatus(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

}
