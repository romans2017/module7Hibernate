package ua.goit.module4.console;

import ua.goit.module4.console.menus.*;
import ua.goit.module4.models.*;

public enum MappingClasses {
    COMPANY             (Company.class, CompanyMenu.class, 2),
    CUSTOMER            (Customer.class, CustomerMenu.class, 2),
    DEVELOPER           (Developer.class, DeveloperMenu.class, 4),
    DEVELOPERSSKILLS    (DevelopersSkills.class, DevelopersSkillsMenu.class, 2),
    DEVELOPERSPROJECTS  (DevelopersProjects.class, DevelopersProjectsMenu.class, 2),
    PROJECT             (Project.class, ProjectMenu.class, 6),
    SKILL               (Skill.class, SkillMenu.class, 2);

    private final Class<? extends DbModel> classDbModel;
    private final Class<? extends Menu> classMenu;
    private final int numberOfMainFields;

    MappingClasses(Class<? extends DbModel> classDbModel, Class<? extends Menu> classMenu, int numberOfMainFields) {
        this.classDbModel = classDbModel;
        this.classMenu = classMenu;
        this.numberOfMainFields = numberOfMainFields;
    }

    public Class<? extends DbModel> getClassDbModel() {
        return classDbModel;
    }

    public Class<? extends Menu> getClassMenu() {
        return classMenu;
    }

    public int getNumberOfMainFields() {
        return numberOfMainFields;
    }

    public static MappingClasses getEnumByMenu(Class<? extends Menu> classMenu) {
        for (MappingClasses enumItem : values()) {
            if (enumItem.getClassMenu().equals(classMenu)) {
                return enumItem;
            }
        }
        return null;
    }
}
