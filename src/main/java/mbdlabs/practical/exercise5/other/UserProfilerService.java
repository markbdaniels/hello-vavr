package mbdlabs.practical.exercise5.other;

import mbdlabs.practical.exercise5.other.domain.UserDiscountProfile;

public interface UserProfilerService {

    UserDiscountProfile calculateUserDiscountProfileByCategory(Long userId, Long productCategoryId) throws DiscountProfileException;

    static class DiscountProfileException extends Exception {

    }
}



