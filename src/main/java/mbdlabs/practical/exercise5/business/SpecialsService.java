package mbdlabs.practical.exercise5.business;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import mbdlabs.practical.exercise5.other.ProductCategoryRepository;
import mbdlabs.practical.exercise5.other.ProductRepository;
import mbdlabs.practical.exercise5.other.UserProfilerService;
import mbdlabs.practical.exercise5.other.domain.FeaturedProduct;
import mbdlabs.practical.exercise5.other.domain.Product;
import mbdlabs.practical.exercise5.other.domain.ProductCategory;
import mbdlabs.practical.exercise5.other.domain.UserDiscountProfile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

import static io.vavr.API.Function;
import static io.vavr.API.printf;

public class SpecialsService {

    private ProductCategoryRepository productCategoryRepository;
    private ProductRepository productRepository;
    private UserProfilerService userProfilerService;

    public SpecialsService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository, UserProfilerService userProfilerService) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.userProfilerService = userProfilerService;
    }

    /**
     * Returns a list of featured products.
     * A featured product is the combination of a product (that we wish to feature) and an applicable discount.
     * The applicable discount is calculated based on the product category and the users shopping profile - the calculation
     * occurs on an external micro service: userProfilerService.
     * If the user discount is unable to be retrieved the product category is simply skipped. The exception is logged.
     * Not all product categories are discountable.
     * The result order is random and limited to the limit parameter.
     */
    public List<FeaturedProduct> getFeaturedProducts(final Long userId, final int limit) {

        java.util.List<ProductCategory> featuredCategories = productCategoryRepository.findFeaturedCategories().toJavaList();

        java.util.List<FeaturedProduct> featuredProductList = new ArrayList<>();
        for (ProductCategory productCategory : featuredCategories) {

            java.util.List<Product> productList = productRepository.findFeaturedProductsByCategoryId(productCategory.getId()).toJavaList();
            for (Product product : productList) {

                try {
                    UserDiscountProfile userDiscountProfile = userProfilerService.calculateUserDiscountProfileByCategory(userId, productCategory.getId());
                    if (userDiscountProfile.isDiscountable()) {
                        FeaturedProduct featuredProduct1 = new FeaturedProduct(product, userDiscountProfile.getApplicableDiscount());
                        featuredProductList.add(featuredProduct1);
                    }

                } catch (UserProfilerService.DiscountProfileException e) {
                    printf("Unable to retrieve discount profile\n", e.getMessage());
                }

            }
        }

        // shuffle and limit
        Collections.shuffle(featuredProductList);
        java.util.List<FeaturedProduct> out = new ArrayList<>();
        for (int i = 0; i < limit && i < featuredProductList.size(); i++) {
            out.add(featuredProductList.get(i));
        }

        return List.ofAll(out);
    }


    /**
     * TODO - Rewrite getFeaturedProducts  in a functional style ():
     * * Remove all the for loops
     * * Replace the try/catch with a io.vavr.control.Try (and possible convert the try to an Option)
     * * The userProfilerService is a heavy weight webservice call - it would be nice if we could cache the result lookup per category
     */
    public List<FeaturedProduct> getFeaturedProductsV2(final Long userId, final int limit) {

        // maps Product to Tuple(Product, UserDiscountProfile). Function result is cached on product category.
        // If an error occurred the Option will be empty
        Function1<Product, Option<Tuple2<Product, UserDiscountProfile>>> calculateUserDiscountProfileByCategory =
                Function((Product p) ->
                        Try.of(() -> Tuple.of(p, userProfilerService.calculateUserDiscountProfileByCategory(userId, p.getProductCategory().getId())))
                                .onFailure((e) -> printf("Unable to retrieve discount profile\n", e.getMessage()))
                                .toOption()
                ).memoized();

        return productCategoryRepository.findFeaturedCategories()
                .map(ProductCategory::getId)
                .map(productRepository::findFeaturedProductsByCategoryId)
                .flatMap(Function.identity())
                .map(calculateUserDiscountProfileByCategory)
                .flatMap(Function.identity()) // filters out Option.None and unpacks Option.Some
                .filter(t -> t._2.isDiscountable())
                .map(FeaturedProduct::new)
                .shuffle()
                .take(limit);
    }
}
