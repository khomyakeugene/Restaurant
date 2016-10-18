package restaurant.data;

import com.company.restaurant.model.Course;
import com.company.restaurant.model.Portion;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import restaurant.service.common.RestaurantService;

/**
 * Created by Yevhen on 17.08.2016.
 */
public class RestaurantPrepareWarehouseData extends RestaurantService {
    private static float AMOUNT_MULTIPLIER = 100.0f;
    private static int KG_PORTION_ID = 1001;

    private void clearWarehouse() {
        warehouseService.clearWarehouse();
    }

    @Transactional
    private void fillWarehouse() {
        for (Course course : courseService.findAllCourses()) {
            course.getCourseIngredients().forEach(ci -> {
                Portion portion = ci.getPortion();
                if (portion == null) {
                    portion = warehouseService.findPortionById(KG_PORTION_ID);
                }

                Float amount = ci.getAmount();
                if (amount == null) {
                    amount = 1.0f;
                }
                amount = (float)(Math.round(amount *= AMOUNT_MULTIPLIER) + 1);

                warehouseService.addIngredientToWarehouse(ci.getIngredient(), portion,
                        amount);
            });
        }
    }

    @Test
    @Transactional
    public void initWarehouseContent() throws Exception {
        System.out.println(getClass().getName() + ".initWarehouseContent ...");

        clearWarehouse();
        fillWarehouse();
    }
}
