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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/auth/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        RequestDispatcher requestDispatcher;
        UserAccount userAccount = userAccountService.loginUser(userLogin, userPassword);
        if (userAccount != null) {
            req.getSession().setAttribute("nickname", userAccount.getName());
            req.getSession().setAttribute("loggedUser", userAccount.getLogin());
            resp.sendRedirect(req.getContextPath() + "/user/welcome");
        } else {
            req.setAttribute("loginAttempt", false);
            requestDispatcher = req.getRequestDispatcher("WEB-INF/auth/login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
