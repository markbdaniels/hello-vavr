package mbdlabs.practical.exercise5.other;

import io.vavr.collection.List;
import mbdlabs.practical.exercise5.other.domain.ProductCategory;


public interface ProductCategoryRepository {

    List<ProductCategory> findFeaturedCategories();

}

