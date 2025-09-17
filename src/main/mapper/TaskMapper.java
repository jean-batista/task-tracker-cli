package main.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.entities.Task;
import main.model.enums.TaskStatus;

/**
 * Mapeia de json para objeto Java (Task)
 * Mapeia de objeto Java (Task) para json
*/
public class TaskMapper {
    
    // Mapeia de json para Task
    public static Task parseJsonToTask(String json) {
        Map<String, String> map = new HashMap<>();
        // Limpa o json
        String string = json.replace("{", "").replace("}", "").replace("\"", "");
        // Dividi o json por "," e cria um array de String
        String[] array = string.split(",");
        for(String str : array) {
            // Substituindo o ":" por "/" para não causar conflito com o formato do LocalDateTime
            str = str.replaceFirst(":", "/");
            // Dividindo a String por / e obtendo as propriedades em chave/valor
            map.put(str.split("/")[0], str.split("/")[1]);
        }
        // Retorna uma instancia de Task, com as propriedades obtidas no map
        return new Task(
            Long.parseLong(map.get("id")),
            map.get("description"),
            TaskStatus.valueOf(map.get("status")),
            LocalDateTime.parse(map.get("createdAt")),
            LocalDateTime.parse(map.get("updatedAt"))
        );
    }

    // Mapeia uma lista de json para uma lista de Task
    public static List<Task> parseJsonListToTaskList(String json) {
        List<Task> tasks = new ArrayList<>();
        // Limpa o json e substitui "},{" por "/" para facilitar a manipulação do json
        json = json.replace("},{", "/").replace("[{", "").replace("}]", "");
        // Dividi o json por "/"
        String[] array = json.split("/");
        for(String string : array) {
            // Adiciona cada task na lista, usando a função parseJsonToTask
            tasks.add(TaskMapper.parseJsonToTask(string));
        }
        // Retorna a lista com as Tasks
        return tasks;
    }

    // Mapeia de Task para json
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
