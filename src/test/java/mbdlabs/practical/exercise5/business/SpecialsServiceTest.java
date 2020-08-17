package mbdlabs.practical.exercise5.business;

import io.vavr.API;
import io.vavr.collection.List;
import mbdlabs.practical.exercise5.other.ProductCategoryRepository;
import mbdlabs.practical.exercise5.other.ProductRepository;
import mbdlabs.practical.exercise5.other.UserProfilerService;
import mbdlabs.practical.exercise5.other.domain.FeaturedProduct;
import mbdlabs.practical.exercise5.other.domain.Product;
import mbdlabs.practical.exercise5.other.domain.ProductCategory;
import mbdlabs.practical.exercise5.other.domain.UserDiscountProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpecialsServiceTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserProfilerService userProfilerService;

    @InjectMocks
    private SpecialsService specialsService = new SpecialsService(productCategoryRepository, productRepository, userProfilerService);

    @Test
    public void getFeaturedProducts() throws UserProfilerService.DiscountProfileException {

        // categories
        when(productCategoryRepository.findFeaturedCategories()).thenReturn(buildProductCategories());

        // products
        when(productRepository.findFeaturedProductsByCategoryId(1L)).thenReturn(buildProductList(1L));
        when(productRepository.findFeaturedProductsByCategoryId(2L)).thenReturn(buildProductList(2L));
        when(productRepository.findFeaturedProductsByCategoryId(3L)).thenReturn(buildProductList(3L));

        // user profiler service
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 1L)).thenReturn(buildUserDiscountProfile(true));
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 2L)).thenReturn(buildUserDiscountProfile(false));
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 3L)).thenThrow(new UserProfilerService.DiscountProfileException());

        List<FeaturedProduct> featuredProducts = specialsService.getFeaturedProducts(1L, 10);

        featuredProducts.forEach(API::println);
        assertThat(featuredProducts).hasSize(3);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 11).length()).isEqualTo(1);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 12).length()).isEqualTo(1);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 13).length()).isEqualTo(1);
    }

    @Test
    public void getFeaturedProductsV2() throws UserProfilerService.DiscountProfileException {
        // categories
        when(productCategoryRepository.findFeaturedCategories()).thenReturn(buildProductCategories());

        // products
        when(productRepository.findFeaturedProductsByCategoryId(1L)).thenReturn(buildProductList(1L));
        when(productRepository.findFeaturedProductsByCategoryId(2L)).thenReturn(buildProductList(2L));
        when(productRepository.findFeaturedProductsByCategoryId(3L)).thenReturn(buildProductList(3L));

        // user profiler service
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 1L)).thenReturn(buildUserDiscountProfile(true));
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 2L)).thenReturn(buildUserDiscountProfile(false));
        when(userProfilerService.calculateUserDiscountProfileByCategory(1L, 3L)).thenThrow(new UserProfilerService.DiscountProfileException());

        List<FeaturedProduct> featuredProducts = specialsService.getFeaturedProductsV2(1L, 10);

        featuredProducts.forEach(API::println);
        assertThat(featuredProducts).hasSize(3);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 11).length()).isEqualTo(1);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 12).length()).isEqualTo(1);
        assertThat(featuredProducts.filter(fp -> fp.getProduct().getId() == 13).length()).isEqualTo(1);
    }


    private static List<ProductCategory> buildProductCategories() {
        return List.of(
                buildProductCategory(1L),
                buildProductCategory(2L),
                buildProductCategory(3L)
        );
    }

    private static ProductCategory buildProductCategory(Long id) {
        return new ProductCategory(id);
    }


    private static List<Product> buildProductList(Long productCategory) {
        return List.of(
                new Product((productCategory * 10) + 1, buildProductCategory(productCategory)),
                new Product((productCategory * 10) + 2, buildProductCategory(productCategory)),
                new Product((productCategory * 10) + 3, buildProductCategory(productCategory))
        );
    }

    private static UserDiscountProfile buildUserDiscountProfile(boolean discountable) {
        return new UserDiscountProfile(discountable, BigDecimal.valueOf(0.25));
    }
}
