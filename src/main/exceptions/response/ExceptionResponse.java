package main.exceptions.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionResponse {
    
    private String message;
    private String type;
    private LocalDateTime errorTime;
    
    public ExceptionResponse(Exception exception) {
        this.message = exception.getMessage();
        this.type = exception.getClass().getSimpleName();
        this.errorTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(LocalDateTime errorTime) {
        this.errorTime = errorTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((errorTime == null) ? 0 : errorTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExceptionResponse other = (ExceptionResponse) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (errorTime == null) {
            if (other.errorTime != null)
                return false;
        } else if (!errorTime.equals(other.errorTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("[Erro] ").append("Ocorreu um erro durante a aplicação!").append("\n");
        sb.append("Tipo de erro: ").append(type).append("\n");
        sb.append("Momento do erro: ").append(dtf.format(errorTime)).append("\n");
        sb.append("Mensagem de erro: ").append(message).append("\n");
        return sb.toString();
    }

}
