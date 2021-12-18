import ua.module7.hibernate.connectors.PersistanceConnecter;
import ua.module7.hibernate.dao.CompanyDao;
import ua.module7.hibernate.dao.ProjectDao;
import ua.module7.hibernate.pojo.Company;
import ua.module7.hibernate.pojo.Project;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class app {
    public static void main(String[] args) {
        //System.out.println(CompanyDao.getInstance().readAll());
        Company company = new Company();
        Project project1 = new Project();
        Project project2 = new Project();
        company
                .setCountry("qqqq")
                .setName("www")
                .addProject(project1
                        .setName("rrr")
                        .setCost(12341452)
                        .setDescription("jklj"))
                        //.setCreationDate(LocalDate.now()))
                .addProject(project2
                        .setName("eee")
                        .setCost(45345)
                        .setDescription("xcvzcbzcb"));
                        //.setCreationDate(LocalDate.now()));
        CompanyDao.getInstance().create(company);

//        Project project1 = ProjectDao.getInstance().read(1);
//        Company company = CompanyDao.getInstance().read(113);
//        company.addProject(project1);
//        CompanyDao.getInstance().update(company);

        //Project project1 = ProjectDao.getInstance().read(112);
        //Company company = CompanyDao.getInstance().read(112);
        //company.removeProject(project1);
        //CompanyDao.getInstance().update(company);
        //CompanyDao.getInstance().delete(company);
        //ProjectDao.getInstance().update(project1);
    }
}
