package br.com.alisson.carshop.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionMessage {
    private LocalDateTime timestamp;
    private String title;
    private String detail;
    private Integer status;
    private Map<String, String> errors;

    public ExceptionMessage() {
        this.errors = new HashMap<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return new HashMap<>(this.errors);
    }

    public void setError(String field, String error) {
        this.errors.put(field, error);
    }
}
