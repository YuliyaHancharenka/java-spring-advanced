package com.epam.cdp.springbootlab;

import com.epam.cdp.springbootlab.good.Good;
import com.epam.cdp.springbootlab.good.GoodResourceClient;
import org.assertj.core.util.Lists;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootLabApplicationTests {

    @Autowired
    GoodResourceClient goodResourceClient;

    @Autowired
    Environment environment;

    @Test
    public void create() {
        Good good = new Good();
        good.setName("toy");
        good.setPrice(1);
        good.setQuantity(1000);

        Good newGood = goodResourceClient.create(good);

        assert newGood.getId() != null;
        assert newGood.getName().equals("toy");
    }

    @Test
    public void get() {
        List<Good> goods = Lists.newArrayList(goodResourceClient.get());
        assert goods.size() == 1;
        assert goods.get(1).getName().equals("toy");
    }

    @Test
    public void update() throws Throwable {
        Good good = new Good();
        good.setName("good toy");
        good.setPrice(1);
        good.setQuantity(1000);

        Good updatedGood = goodResourceClient.update(1L, good);
        assert updatedGood.getId().equals(1L);
        assert updatedGood.getName().equals("good toy");
    }

    @Test
    public void getById() {
        Good good = goodResourceClient.getById(1L).get();
        assert good.getName().equals("good toy");
    }
}
