package AbstractFactoryDesignPattern.demo;
import AbstractFactoryDesignPattern.demo.Apple;
import AbstractFactoryDesignPattern.demo.NullPhone;
import AbstractFactoryDesignPattern.demo.Phone;

import java.util.Objects;

public class PhoneStore
{
    private PhoneStore()
    {

    }
    public static Phone getPhone(String brand, String model)
    {
        if(Objects.equals(brand, "Apple"))
            return Apple.getPhone(model);
        else if(Objects.equals(brand, "OnePlus"))
            return OnePlus.getPhone(model);
        else if(Objects.equals(brand, "Samsung"))
            return Samsung.getPhone(model);
        return new NullPhone();
    }
}
