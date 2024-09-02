package com.bookstore.service;

import com.bookstore.dao.BuyerDAO;

import com.bookstore.entities.Buyer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
public class BuyerServices {
    private BuyerDAO buyerDao;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public BuyerServices(HttpServletRequest request, HttpServletResponse response) {
        super();
        this.request = request;
        this.response = response;
        this.buyerDao = new BuyerDAO();
    }

    public void listBuyer(String message) throws ServletException, IOException {
        List<Buyer> listBuyer = buyerDao.listAll();

        request.setAttribute("listBuyer", listBuyer);

        String listPage = "buyer_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request, response);

    }

    public void listBuyer() throws ServletException, IOException {
        listBuyer(null);
    }

    public void createBuyer() throws ServletException, IOException {
        String email = request.getParameter("email");
        Buyer existBuyer = buyerDao.findByEmail(email);

        if (existBuyer != null) {
            String message = "Could not create new buyer: the email "
                    + email + " is already registered by another buyer";
            listBuyer(message);
        }
        else {
            Buyer newBuyer = new Buyer();
            updateBuyerFieldsFromForm(newBuyer);
            buyerDao.create(newBuyer);

            String message = "New buyer has been created successfully";
            listBuyer(message);
        }
    }

    private void updateBuyerFieldsFromForm(Buyer buyer) {

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String building = request.getParameter("building");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String job = request.getParameter("job");


        // To be Checked as mandatory
        if (email != null && !email.equals("")) {
            buyer.setEmail(email);
        }

        buyer.setName(name);

        // to be checked as mandatory
        if (password != null && !password.equals("")) {
            buyer.setPassword(password);
        }

        buyer.setPhone(phone);

        buyer.setCity(city);
        buyer.setBuilding(building);
        buyer.setStreet(street);
        buyer.setJob(job);

    }

    public void registerBuyer() throws ServletException, IOException {
        String email = request.getParameter("email");
        Buyer existBuyer = buyerDao.findByEmail(email);
        String message = "";

        if (existBuyer != null) {
            message = "Could not register. The email "
                    + email + " is already registered by another buyer";
        }
        else {
            Buyer newBuyer = new Buyer();
            updateBuyerFieldsFromForm(newBuyer);

            buyerDao.create(newBuyer);

            message = "You have been created successfully, thank you!<br/>"
                    + "<a href='login'>Click here</a> to login";
        }

        String messagePage = "frontend/message.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
        request.setAttribute("message", message);
        requestDispatcher.forward(request, response);
    }

    public void editBuyer() throws ServletException, IOException {
        Integer buyerId = Integer.parseInt(request.getParameter("id"));
        Buyer buyer = buyerDao.get(buyerId);

        request.setAttribute("buyer", buyer);

        CommonUtility.generateCountryList(request);

        String editPage = "buyer_form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
        requestDispatcher.forward(request, response);
    }

    public void updateBuyer() throws ServletException, IOException {
        Integer buyerId = Integer.parseInt(request.getParameter("buyerId"));
        String email = request.getParameter("email");

        Buyer buyerByEmail = buyerDao.findByEmail(email);
        String message = null;

        if (buyerByEmail != null && buyerByEmail.getId() != buyerId) {
            message = "Could not update the buyer ID " + buyerId
                    + " because there's an existing buyer having the same email.";
        }else {

            Buyer buyerById = buyerDao.get(buyerId);
            updateBuyerFieldsFromForm(buyerById);
            buyerDao.update(buyerById);

            message = "The buyer is successfully updated.";
        }
        listBuyer(message);

    }

    public void deleteBuyer() throws ServletException, IOException {
        Integer buyerId = Integer.parseInt(request.getParameter("id"));
        buyerDao.delete(buyerId);

        String message = "The buyer has been deleted.";
        listBuyer(message);
    }

    public void showLogin() throws ServletException, IOException {
        String loginPage = "frontend/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
        dispatcher.forward(request, response);
    }

    public void doLogin() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("email");

        Buyer buyer = buyerDao.checkLogin(email, password);

        if (buyer == null) {
            String message = "Login failed. Please check your email and password";
            request.setAttribute("message", message);
            showLogin();
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("loggedBuyer", buyer);

            Object objRedictedURL = session.getAttribute("redirectURL");

            if (objRedictedURL != null) {
                String redirectURL = (String) objRedictedURL;
                session.removeAttribute(redirectURL);
                response.sendRedirect(redirectURL);
            }else {
                showBuyerProfile();
            }
        }
    }

    public void showBuyerProfile() throws ServletException, IOException {

        String profilePage = "frontend/buyer_profile.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
        dispatcher.forward(request, response);
    }

    public void showBuyerProfileEditForm() throws ServletException, IOException {
        CommonUtility.generateCountryList(request);

        String editPage = "frontend/edit_profile.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
        dispatcher.forward(request, response);
    }

    public void updateBuyerProfile() throws ServletException, IOException {
        Buyer buyer = (Buyer) request.getSession().getAttribute("loggedBuyer");
        updateBuyerFieldsFromForm(buyer);
        buyerDao.update(buyer);
        showBuyerProfile();
    }

    public void newBuyer() throws ServletException, IOException {
        CommonUtility.generateCountryList(request);

        String buyerForm = "buyer_form.jsp";
        request.getRequestDispatcher(buyerForm).forward(request, response);
    }

    public void ShowBuyerRegisterForm() throws ServletException, IOException {
        CommonUtility.generateCountryList(request);

        String registerForm = "frontend/register_form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
        dispatcher.forward(request, response);
    }
}