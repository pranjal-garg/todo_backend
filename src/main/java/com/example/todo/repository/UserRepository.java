package com.example.todo.repository;

import com.example.todo.models.UserModel;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class UserRepository {

    private final DynamoDbTable<UserModel> userTable;

    public UserRepository(DynamoDbEnhancedClient client) {
        this.userTable = client.table("UsersAndTasks", TableSchema.fromBean(UserModel.class));
    }

    public UserModel addUser(UserModel user) {
        userTable.putItem(user);
        return user;
    }
}
