package mbdlabs.practical.exercise5.other;

import io.vavr.collection.List;
import mbdlabs.practical.exercise5.other.domain.Product;

public interface ProductRepository {

     List<Product> findFeaturedProductsByCategoryId(Long productCategoryId) ;
}
