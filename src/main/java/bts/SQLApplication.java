package bts;

import bts.model.Project;
import bts.service.BTSServiceImpl;
import bts.service.SQLService;
import bts.service.SQLServiceImpl;

public class SQLApplication {

    //private static final SQLService SQL_SERVICE = new SQLServiceImpl();

    public static void main(String[] args) {
        SQLService SQL_SERVICE = new SQLServiceImpl();
        //((SQLServiceImpl) SQL_SERVICE).addProject(new Project("bts", "what!"));
        //for(Project p :((SQLServiceImpl) SQL_SERVICE).getProjects())
         //   System.out.println(p);
        ((SQLServiceImpl) SQL_SERVICE).addUserDB("Popka");
        ((SQLServiceImpl) SQL_SERVICE).getUsers();
    }
}
