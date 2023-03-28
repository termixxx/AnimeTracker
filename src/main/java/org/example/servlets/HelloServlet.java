package org.example.servlets;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Anime;
import org.example.repository.AnimeRepository;
import org.example.utils.PullConnectionBuilder;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AnimeRepository animeRepository = new AnimeRepository(new PullConnectionBuilder());
        Anime anime = animeRepository.getById(86L);

        System.out.println(anime.toString());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + anime + "</h1>");
        out.println("</body></html>");
    }
}
