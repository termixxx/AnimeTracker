package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.utils.PullConnectionBuilder;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AnimeRepository animeRepository = new AnimeRepository(new PullConnectionBuilder());
        List<Anime> animeList = animeRepository.getAll();
        req.setAttribute("animeList", animeList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/welcome.jsp");
        requestDispatcher.forward(req, resp);
    }
}
