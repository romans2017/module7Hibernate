package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Company;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Skill;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/skills/*")
public class SkillsServlet extends AbstractServlet<Skill> {

    private Dao<Developer> serviceDaoDeveloper;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Skill>) getServletContext().getAttribute("skillDao");
        this.serviceDaoDeveloper = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.jspView = "skills.jsp";
        this.jspEdit = "skill.jsp";
        this.redirectPath = "skills";
    }

    @Override
    protected Skill createUpdateModel(HttpServletRequest req) throws NumberFormatException {
        int id;
        Skill skill;
        if (req.getParameter("id") == null) {
            if (req.getParameter("skill_id") == null) {
                id = 0;
            } else {
                id = Integer.parseInt(req.getParameter("skill_id"));
            }
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }
        if (id == 0) {
            skill = new Skill()
                    .setLanguage(req.getParameter("language"))
                    .setLevel(req.getParameter("level"));
            serviceDao.create(skill);
        } else {
            skill = serviceDao.read(id);
            if (skill != null) {
                skill.setLanguage(req.getParameter("language"))
                        .setLevel(req.getParameter("level"));
                serviceDao.update(skill);
            }
        }
        return skill;
    }

    @Override
    protected void postEditRequest(Skill model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", model);
        req.setAttribute("developerList", ServletService.getAllModels(serviceDaoDeveloper, Developer.class));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Skill skill = createUpdateModel(req);
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        skill = addRemoveBindFromMappedToOwner(developer, skill, serviceDaoDeveloper, developer::addSkill);
        postEditRequest(skill, req, resp);
    }

    private void removeDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Skill skill = serviceDao.read(Integer.parseInt(req.getParameter("skill_id")));
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        skill = addRemoveBindFromMappedToOwner(developer, skill, serviceDaoDeveloper, developer::removeSkill);
        postEditRequest(skill, req, resp);
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
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}
