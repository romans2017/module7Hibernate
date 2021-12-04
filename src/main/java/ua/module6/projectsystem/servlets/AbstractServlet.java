package ua.module6.projectsystem.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;
import ua.module6.projectsystem.queries.Query;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

abstract class AbstractServlet extends HttpServlet {

    protected Query serviceQuery;
    protected String jspView;
    protected String jspEdit;
    protected String redirectPath;
    protected Class<? extends DbModel> classDbModel;

    protected ModelsList getAllModels(Query sQuery) {
        ModelsList modelsList = new ModelsList();
        try {
            modelsList = sQuery.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelsList;
    }

    protected void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/" + redirectPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", getAllModels(serviceQuery));
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }

    protected DbModel getDbModel(Integer id, Class<? extends DbModel> classModel) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ModelsList modelsList;
        modelsList = serviceQuery.get(Map.of("id", id));
        if (modelsList.size() > 0) {
            return modelsList.get(0);
        } else {
            Constructor<? extends DbModel> constructor = classModel.getConstructor();
            return constructor.newInstance();
        }
    }

    abstract protected void createEditModel(HttpServletRequest req) throws NumberFormatException;

    protected void postEditRequest(DbModel dbModel, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        req.setAttribute("model", dbModel);
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    protected void newEditModel(HttpServletRequest req, HttpServletResponse resp) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, ServletException, IOException {
        String url = req.getPathInfo();
        DbModel model = getDbModel(Integer.parseInt(req.getParameter("id")), classDbModel);
        if (!model.get("id").toString().equals("0") || url.equals("/new")) {
            postEditRequest(model, req, resp);
        } else {
            redirect(resp);
        }
    }

    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer developer_id = Integer.parseInt(req.getParameter("id"));
        serviceQuery.delete(developer_id);
        redirect(resp);
    }

    protected void saveModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        createEditModel(req);
        redirect(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (url) {
                case "/edit", "/new" -> newEditModel(req, resp);
                case "/remove" -> removeModel(req, resp);
                case "/save" -> saveModel(req, resp);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}