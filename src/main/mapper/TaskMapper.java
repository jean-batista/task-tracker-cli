package main.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.entities.Task;
import main.model.enums.TaskStatus;

public class TaskMapper {
    
    public static Task parseJsonToTask(String json) {
        Map<String, String> map = new HashMap<>();
        String string = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] array = string.split(",");
        for(String str : array) {
            str = str.replaceFirst(":", "/");
            map.put(str.split("/")[0], str.split("/")[1]);
        }
        return new Task(
            Long.parseLong(map.get("id")),
            map.get("description"),
            TaskStatus.valueOf(map.get("status")),
            LocalDateTime.parse(map.get("createdAt")),
            LocalDateTime.parse(map.get("updatedAt"))
        );
    }

    public static List<Task> parseJsonListToTaskList(String json) {
        List<Task> tasks = new ArrayList<>();
        json = json.replace("},{", "/").replace("[{", "").replace("}]", "");
        String[] array = json.split("/");
        for(String string : array) {
            tasks.add(TaskMapper.parseJsonToTask(string));
        }
        return tasks;
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
