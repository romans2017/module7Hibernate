package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.Developer;
import ua.module7.hibernate.models.ModelsList;
import ua.module7.hibernate.models.Skill;
import ua.module7.hibernate.queries.Query;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/skills/*")
public class SkillsServlet extends AbstractServlet {

    private Query serviceQueryDeveloper;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("skillQuery");
        this.serviceQueryDeveloper = (Query) getServletContext().getAttribute("developerQuery");
        this.classDbModel = Skill.class;
        this.jspView = "skills.jsp";
        this.jspEdit = "skill.jsp";
        this.redirectPath = "skills";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Skill skill = new Skill();
        skill.setId(id);
        skill.setLanguage(req.getParameter("language"));
        skill.setLevel(req.getParameter("level"));
        if (id == 0) {
            serviceQuery.create(skill);
        } else {
            serviceQuery.update(skill, id);
        }
    }

    @Override
    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer skill_id = Integer.parseInt(req.getParameter("id"));
        serviceQuery.removeFromBindingTable("developers_skills", Map.of("skill_id", skill_id));
        serviceQuery.delete(skill_id);
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
                case "/addDeveloper" -> addDeveloper(req, resp);
                case "/removeDeveloper" -> removeDeveloper(req, resp);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void postEditRequest(DbModel dbModel, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        req.setAttribute("model", dbModel);
        req.setAttribute("developersSkills", getDevelopers(dbModel));
        req.setAttribute("developerList", getAllModels(serviceQueryDeveloper));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer skill_id = Integer.parseInt(req.getParameter("skill_id"));
        serviceQuery.addToBindingTable("developers_skills", Map.of("developer_id", developer_id, "skill_id", skill_id));

        DbModel skill = getDbModel(skill_id, Skill.class);
        postEditRequest(skill, req, resp);
    }

    private void removeDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer skill_id = Integer.parseInt(req.getParameter("skill_id"));
        serviceQuery.removeFromBindingTable("developers_skills", Map.of("developer_id", id, "skill_id", skill_id));

        DbModel skill = getDbModel(skill_id, Developer.class);
        postEditRequest(skill, req, resp);
    }

    private ModelsList getDevelopers(DbModel dbModel) throws NoSuchFieldException, IllegalAccessException, SQLException {
        return serviceQuery.getFromBindingTable(serviceQueryDeveloper,
                "developers_skills",
                "developer_id",
                Map.of("skill_id", (Integer) dbModel.get("id")));
    }
}
