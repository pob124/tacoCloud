package tacos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force = true)
@Document(collection="Ingredients")
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public boolean isNew() { return true;}
    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}