package tacos.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.IngredientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepo;
    private final Map<String, Ingredient> ingredientCache = new HashMap<>();
    private volatile boolean cacheInitialized = false;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    private void initializeCacheIfNeeded() {
        if (!cacheInitialized) {
            synchronized (this) {
                if (!cacheInitialized) {
                    ingredientRepo.findAllIngredients().forEach(i -> ingredientCache.put(i.getId(), i));
                    cacheInitialized = true;
                }
            }
        }
    }

    @Override
    public Ingredient convert(String id) {
        initializeCacheIfNeeded();
        return ingredientCache.get(id);
    }
}