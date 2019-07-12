package nl.saxion.site.Provider;

import nl.saxion.site.Administration.Administration;
import nl.saxion.site.model.Accessory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Onyebuchi Iheuwadinachi Eleazu
 */

@Service
public class AccessoryProvider {
    public List<Accessory> getAccessories(String username) {
        List<Accessory> accessories = new ArrayList<>();
        for (Accessory accessory : Administration.accessories) {
            if (accessory == null) {
                continue;
            }
            if (username.equals("admin")) {
                accessories.add(accessory);
            } else {
                if (accessory.getUsername().equals(username)) {
                    accessories.add(accessory);
                }
            }

        }
        return accessories;
    }

    public void removeAccessory(int id) {
        Administration.accessories.set(id, null);
    }

    public void newAccessory(String name, int stock, int price, String category, String description, String username) {
        int id = Administration.accessories.size();
        Administration.accessories.add(new Accessory(id, name, stock, price, category, description, username));
    }
}