package mapper;

import model.entities.Task;

public class TaskMapper {
    
    public static Task parseJsonToTask(String json) {
        throw new UnsupportedOperationException("Unimplemented method 'jsonToTask'");
    }

    public static String parseTaskToJson(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\"").append(":");
        sb.append("\"" + task.getId() + "\"").append(",");
        sb.append("\"description\"").append(":");
        sb.append("\"" + task.getDescription() + "\"").append(",");
        sb.append("\"status\"").append(":");
        sb.append("\"" + task.getStatus() + "\"").append(",");
        sb.append("\"createdAt\"").append(":");
        sb.append("\"" + task.getCreatedAt() + "\"").append(",");
        sb.append("\"updatedAt\"").append(":");
        sb.append("\"" + task.getUpdatedAt() + "\"");
        sb.append("}");
        return sb.toString();
    }

}
