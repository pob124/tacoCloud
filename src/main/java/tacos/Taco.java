package tacos;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@Document(collection = "tacos")
public class Taco {
    @Id
    private String id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    private Date createdAt = new Date();

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    // MongoDB에서는 참조 ID만 저장
    private String orderId;  // TacoOrder의 ID 참조

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setTacoOrder(TacoOrder order) {
        this.orderId = order.getId();
    }
}