package tacos.data;

import java.util.Optional;
import java.util.List;
import tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    @Query("{}")
    Iterable<Ingredient> findAllIngredients();
    
    @Query("{ '_id' : { $in: ?0 } }")
    List<Ingredient> findByIdIn(List<String> ids);
}
