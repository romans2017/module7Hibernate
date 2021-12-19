package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Pojo;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Consumer;

abstract class AbstractServlet<E extends Pojo> extends HttpServlet {

    protected Dao<E> serviceDao;
    protected String jspView;
    protected String jspEdit;
    protected String redirectPath;
    protected Class<E> classModel;

    @Override
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        Type[] params = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        classModel = (Class<E>) params[0];
    }

    protected void redirect(HttpServletResponse resp) throws IOException {
        resp.sendRedirect(getServletContext().getContextPath() + "/" + redirectPath);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", ServletService.getAllModels(serviceDao, classModel));
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }

    @Override
    abstract protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    abstract protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException;

    abstract protected void postEditRequest(E model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    protected void newEditModel(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ServletException, IOException {
        String url = req.getPathInfo();
        E model = ServletService.getDbModel(Integer.parseInt(req.getParameter("id")), serviceDao, classModel);
        if (model.getId() != 0 || url.equals("/new")) {
            postEditRequest(model, req, resp);
        } else {
            redirect(resp);
        }
    }

    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        E entity = serviceDao.read(id);
        if (entity != null) {
            serviceDao.delete(entity);
        }
        redirect(resp);
    }

    protected void saveModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        createUpdateModel(req);
        redirect(resp);
    }

    protected <S extends Pojo> E addRemoveBindFromMappedToOwner(S owner, E mapped, Dao<S> serviceDaoOwner, Consumer<E> func) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (mapped != null && owner != null) {
            func.accept(mapped);
            serviceDaoOwner.update(owner);
        }
        if (mapped == null) {
            Constructor<E> constructor = classModel.getConstructor();
            mapped = constructor.newInstance().initEmpty();
        }
        return mapped;
    }

    protected <S extends Pojo> E addRemoveBindFromOwnerToMapped(E owner, S mapped, Consumer<S> func) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (mapped != null && owner != null) {
            func.accept(mapped);
            serviceDao.update(owner);
        }
        if (owner == null) {
            Constructor<E> constructor = classModel.getConstructor();
            owner = constructor.newInstance().initEmpty();
        }
        return owner;
    }
}