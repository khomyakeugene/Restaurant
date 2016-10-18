package restaurant.data;

import com.company.restaurant.service.impl.CommonDataServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import restaurant.dao.common.RestaurantDao;
import restaurant.util.Util;

public class RestaurantPrepareDictionaryData extends RestaurantDao {
    private final static String EMBLEM_FILENAME = "images/Hamster.png";
    private final static String RESTAURANT_SCHEMA_FILENAME = "images/restaurant_schema.jpg";
    private final static String TRANSPORT_MAP_FILENAME = "images/transport_map.jpg";
    private final static int MANAGER_EMPLOYEE_ID = 1;
    private final static String MANAGER_PHOTO_FILENAME = "images/personnel/Manager.png";
    private final static int CHEF_EMPLOYEE_ID = 2;
    private final static String CHEF_PHOTO_FILENAME = "images/personnel/Chef.png";
    private final static int WAITER_EMPLOYEE_ID = 3;
    private final static String WAITER_PHOTO_FILENAME = "images/personnel/Waiter.png";

    private final static int SHOPSKA_SALAD_ID = 2001;
    private final static String SHOPSKA_SALAD_PHOTO_FILENAME = "images/courses/Shopska_salad.jpg";
    private final static int CHICKEN_WITH_MUSHROOMS_ID = 4001;
    private final static String CHICKEN_WITH_MUSHROOMS_PHOTO_FILENAME = "images/courses/Chicken_With_Mushrooms.jpg";
    private final static int GOULASH_WITH_POTATO_ID = 4002;
    private final static String GOULASH_WITH_POTATO_PHOTO_FILENAME = "images/courses/Goulash_With_Potato.jpg";
    private final static int BEER_DOMS_ID = 10001;
    private final static String BEER_DOMS_PHOTO_FILENAME = "images/courses/Beer_Doms.jpg";

    private boolean saveCommonDataImage(String commonDataName, String imageFilename) {
        byte[] image = Util.readResourceFileToByteArray(imageFilename);
        if (image != null) {
            commonDataDao.updCommonDataImage(commonDataName, image);
        }

        return (image != null);
    }

    private void AssertSaveCommonDataImage(String commonDataName, String imageFilename) {
        Assert.assertTrue(saveCommonDataImage(commonDataName, imageFilename));
    }

    @Test
    public void saveCommonDataImages() throws Exception {
        // Emblem image
        AssertSaveCommonDataImage(CommonDataServiceImpl.EMBLEM_NAME, EMBLEM_FILENAME);
        // Restaurant schema image
        AssertSaveCommonDataImage(CommonDataServiceImpl.RESTAURANT_SCHEMA_NAME, RESTAURANT_SCHEMA_FILENAME);
        // Transport map
        AssertSaveCommonDataImage(CommonDataServiceImpl.TRANSPORT_MAP_NAME, TRANSPORT_MAP_FILENAME);
    }

    private boolean saveEmployeeImage(int employeeId, String imageFilename) {
        byte[] image = Util.readResourceFileToByteArray(imageFilename);
        if (image != null) {
            employeeDao.updEmployeePhoto(employeeId, image);
        }

        return (image != null);
    }

    private void AssertSaveEmployeeImage(int employeeId, String imageFilename) {
        Assert.assertTrue(saveEmployeeImage(employeeId, imageFilename));
    }

    @Test
    public void saveEmployeeImages() throws Exception {
        // Manager
        AssertSaveEmployeeImage(MANAGER_EMPLOYEE_ID, MANAGER_PHOTO_FILENAME);
        // Cook
        AssertSaveEmployeeImage(CHEF_EMPLOYEE_ID, CHEF_PHOTO_FILENAME);
        // Waiter
        AssertSaveEmployeeImage(WAITER_EMPLOYEE_ID, WAITER_PHOTO_FILENAME);
    }

    private boolean saveCourseImage(int courseId, String imageFilename) {
        byte[] image = Util.readResourceFileToByteArray(imageFilename);
        if (image != null) {
            courseDao.updCoursePhoto(courseId, image);
        }

        return (image != null);
    }

    private void AssertSaveCourseImage(int courseId, String imageFilename) {
        Assert.assertTrue(saveCourseImage(courseId, imageFilename));
    }

    @Test
    public void saveCourseImages() throws Exception {
        // Shopska salad
        AssertSaveCourseImage(SHOPSKA_SALAD_ID, SHOPSKA_SALAD_PHOTO_FILENAME);
        // Chicken with mushrooms
        AssertSaveCourseImage(CHICKEN_WITH_MUSHROOMS_ID, CHICKEN_WITH_MUSHROOMS_PHOTO_FILENAME);
        // Goulash with potato
        AssertSaveCourseImage(GOULASH_WITH_POTATO_ID, GOULASH_WITH_POTATO_PHOTO_FILENAME);
        // Beer "Doms"
        AssertSaveCourseImage(BEER_DOMS_ID, BEER_DOMS_PHOTO_FILENAME);
    }
}