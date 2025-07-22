package main.model.enums;

public enum TaskStatus {
    
    TODO(1),
    IN_PROGRESS(2),
    DONE(3);

    private final int code;

    TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TaskStatus fromCode(int code) {
        for(TaskStatus status : TaskStatus.values()) {
            if(status.code == code) return status;
        }
        throw new IllegalArgumentException("Invalid code");
    }

}
