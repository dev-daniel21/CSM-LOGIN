package com.csm.csmlogin.web.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Getter
@ToString
public class CustomExceptionBody {

//    @JsonIgnore
    private Timestamp timeStamp;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssxxx")
//    @Schema(example = "2023-07-09T15:20:35:01:00") // <- io.swagger dependency
    private OffsetDateTime offsetDateTime;
    private Integer statusCode;
    private String status;
    private String message;
    private String requestPath;
    private String requestMethod;

    public CustomExceptionBody(Timestamp timeStamp, HttpStatus status, String message, HttpServletRequest request) {
        this.timeStamp = timeStamp;
        this.offsetDateTime = timeStamp.toLocalDateTime().atOffset(ZoneOffset.UTC);
        this.statusCode = status.value();
        this.status = status.name();
        this.message = message;
        request.getRequestURI();
        request.getMethod();
    }

}
