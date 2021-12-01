package ua.module6.projectsystem.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module6.projectsystem.models.Skill;
import ua.module6.projectsystem.queries.Query;

@WebServlet("/skills/*")
public class SkillsServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("skillQuery");
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
}
