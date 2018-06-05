package org.example.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(name = "animal-servlet", urlPatterns = {"/animal"})
public class AnimalServlet extends HttpServlet {

    public static List<Animals> allAnimals = new ArrayList();
    private static List<Animals> animalsList = new ArrayList();

    static {
        allAnimals.add(new Animals("cat", "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=150&w=150"));
        allAnimals.add(new Animals("dog", "https://www.cesarsway.com/sites/newcesarsway/files/styles/large_article_preview/public/All-about-puppies--Cesar%E2%80%99s-tips%2C-tricks-and-advice.jpg"));
        allAnimals.add(new Animals("panda", "https://n-w.tv/wp-content/uploads/2017/12/panda-1.jpg"));

        animalsList.add(allAnimals.get(1));
        animalsList.add(allAnimals.get(2));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("animals", animalsList);
        req.getRequestDispatcher("/animals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        animalsList.add(allAnimals.get(ThreadLocalRandom.current().nextInt(0, allAnimals.size() + 1)));

        req.setAttribute("animals", animalsList);
        req.getRequestDispatcher("/animals.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Animals animals = allAnimals.get(ThreadLocalRandom.current().nextInt(0, allAnimals.size() + 1));
        animalsList.get(0).setImg(animals.getImg());
        animalsList.get(0).setName(animals.getName());

        req.setAttribute("animals", animalsList);
        req.getRequestDispatcher("/animals.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        animalsList.remove(0);

        req.setAttribute("animals", animalsList);
        req.getRequestDispatcher("/animals.jsp").forward(req, resp);
    }
}
