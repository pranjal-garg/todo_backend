package com.example.todo.repository;

import com.example.todo.models.TaskModel;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

@Repository
public class TaskRepository {

    private final DynamoDbTable<TaskModel> userTable;

    public TaskRepository(DynamoDbEnhancedClient client) {
        this.userTable = client.table("UsersAndTasks", TableSchema.fromBean(TaskModel.class));
    }

    public List<TaskModel> getAllTasks(String uid) {
        return userTable.query(r -> r.queryConditional(
                QueryConditional.sortBeginsWith(
                        s -> s
                                .partitionValue(uid)
                                .sortValue("TASK#")))).items().stream().toList();
    }

    public TaskModel addTask(TaskModel task) {
        userTable.putItem(task);
        return task;
    }
}
