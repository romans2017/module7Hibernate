package ua.goit.module4.console;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.Company;
import ua.goit.module4.queries.CompanyQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyMenu extends AbstractMenu {

    private static CompanyMenu instance;

    public static CompanyMenu getInstance() {
        if (instance == null) {
            instance = new CompanyMenu();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private CompanyMenu() {
        CompanyQuery companyQuery = CompanyQuery.getInstance(InputConsole.getInstance().getDbConnector());
        menuModel = new MenuModel();
        menuModel.put("create", param -> {
            param = param.trim();
            String values = param.substring(param.indexOf(" ") + 1).trim();
            String[] params = values.split("--");

            if (params.length < 3) {
                System.out.println("Incorrect input format!!!");
                this.showMenu();
                return;
            }

            Company company = new Company(params);
            companyQuery.create(company);
            try {
                System.out.println(companyQuery.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.showMenu();
        });
        menuModel.put("update", param -> {
            param = param.trim();
            String values = param.substring(param.indexOf(" ") + 1).trim();
            String[] params = values.split("--");

            if (params.length < 4) {
                System.out.println("Incorrect input format!!!");
                this.showMenu();
                return;
            }

            List<Company> res = new ArrayList<>();
            try {
                res = (List<Company>) companyQuery.get(Map.of("id", Integer.parseInt(params[1].trim())));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if(res.size() == 0) {
                System.out.println("Element with id = " + params[1].trim() + " does not exist");
                this.showMenu();
                return;
            }
            Company company = new Company(res.get(0), params);
            companyQuery.update(company, Integer.parseInt(params[1].trim()));
            try {
                System.out.println(companyQuery.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.showMenu();
        });
    }

    @Override
    public void showMenu() {
        String textMenu = """
                ******** COMPANY MENU ********
                create (format: create --name --country)
                update (format: update --id --name(optional) --country(optional))
                read
                delete
                UP...
                EXIT...
                ******** ********""";
        System.out.println(textMenu);
    }
}
