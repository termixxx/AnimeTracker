package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Anime;
import org.example.entities.UserAccount;
import org.example.entities.UserAccountAnime;
import org.example.entities.enums.Condition;
import org.example.service.AnimeService;
import org.example.service.UserAccountAnimeService;
import org.example.service.UserAccountService;
import org.example.service.impl.AnimeServiceImpl;
import org.example.service.impl.UserAccountAnimeServiceImpl;
import org.example.service.impl.UserAccountServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/user/welcome")
public class WelcomeServlet extends HttpServlet {

    AnimeService animeService = new AnimeServiceImpl();
    UserAccountService accountService = new UserAccountServiceImpl();
    UserAccountAnimeService userAccountAnimeService = new UserAccountAnimeServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String login = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserAccount user = accountService.findByLogin(login);
        req.setAttribute("user", user);

        List<UserAccountAnime> userAccountAnimeList = userAccountAnimeService.findAllAnimeForUserId(user.getId());
        req.setAttribute("accountAnimeList", userAccountAnimeList);


        List<Anime> animeList = animeService.getAll();
        req.setAttribute("animeList", animeList);


        List<Anime> allAnime = userAccountAnimeList.stream()
                .map(accountAnime -> animeService.getAnimeById(accountAnime.getAnimeId()))
                .toList();
        req.setAttribute("allAnime", allAnime);


        List<Anime> watchingAnime = getAnimeByEnum(userAccountAnimeList, Condition.WATCHING);
        req.setAttribute("watchingAnime", watchingAnime);


        List<Anime> watchedAnime = getAnimeByEnum(userAccountAnimeList, Condition.WATCHED);
        req.setAttribute("watchedAnime", watchedAnime);


        List<Anime> plannedAnime = getAnimeByEnum(userAccountAnimeList, Condition.PLANNED);
        req.setAttribute("plannedAnime", plannedAnime);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/welcome.jsp");
        requestDispatcher.forward(req, resp);
    }

    private List<Anime> getAnimeByEnum(List<UserAccountAnime> userAccountAnimeList, Condition condition) {
        return userAccountAnimeList.stream()
                .filter(accountAnime -> accountAnime.getConditionEnum().equals(condition))
                .map(accountAnime -> animeService.getAnimeById(accountAnime.getAnimeId()))
                .toList();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Anime anime = animeService.findByName(req.getParameter("animeSelect"));
        int episodesWatched = Integer.parseInt(req.getParameter("episodesWatched"));
        boolean favorite = Boolean.parseBoolean(req.getParameter("favorite"));
        String comment = req.getParameter("comment");
        Condition condition = Condition.valueOf(req.getParameter("condition"));
        int rating = Integer.parseInt(req.getParameter("rating"));
        String login = String.valueOf(req.getSession().getAttribute("loggedUser"));

        userAccountAnimeService.save(new UserAccountAnime(
                episodesWatched,
                favorite,
                comment,
                LocalDate.now(),
                condition,
                rating,
                anime.getId(),
                accountService.findByLogin(login).getId()
        ));

        resp.sendRedirect(req.getContextPath() + "/user/welcome");
    }
}

