# how we copy the data from entity calss to model class and vice versa if we have souch data we cant use dto

```text
Using ModelMapper / MapStruct (Advanced Libraries)
If you don’t want to manually map every field and need custom mappings, use:
🔸 ModelMapper

ModelMapper mapper = new ModelMapper();
UserModel model = mapper.map(entity, UserModel.class);

🔸 MapStruct (compile-time, faster)

@Mapper
public interface UserMapper {
    UserModel toModel(UserEntity entity);
    UserEntity toEntity(UserModel model);
}
💡 When DTOs Are NOT Used
If you're working only with Model and Entity classes, you can still use the above techniques to:
Prevent exposing Entity to UI
Add computed or derived fields
Decouple persistence and domain logic
💬 Interview Tip – What to Say
Even if we’re not using a DTO, we often map between Entity and Model classes to separate persistence from business logic.
 For small models, manual mapping or BeanUtils.copyProperties works well. For complex cases, we prefer tools like
 ModelMapper or MapStruct to handle nested and custom field mappings efficiently.
```
