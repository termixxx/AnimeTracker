package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.UserAccount;
import org.example.service.impl.UserAccountServiceImpl;

import java.io.IOException;

@WebServlet("/signup")
public class SingUpServlet extends HttpServlet {
    private final UserAccountServiceImpl accountService = new UserAccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/auth/signup.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String nick = req.getParameter("nickname");
        boolean created = accountService.createUser(new UserAccount(null, nick, login, password, null));
        req.setAttribute("userCreated", created);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/auth/signup.jsp");
        requestDispatcher.forward(req, resp);
    }
}
