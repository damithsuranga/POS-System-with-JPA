package lk.ijse.pos.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JPAUtil {

   private static EntityManagerFactory emf = buildEntityManageFactory();

   private static EntityManagerFactory buildEntityManageFactory(){
       File propFile = new File("resources/application.properties");
       Properties properties = new Properties();
       try {
           FileReader fileReader = new FileReader(propFile);
           properties.load(fileReader);

           EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit1", properties);
           fileReader.close();



           return emf;

       } catch (Exception e) {
           e.printStackTrace();
           Logger.getLogger("lk.ijse.pos.db").log(Level.SEVERE, null, e);
           System.exit(0);
           return null;
       }
   }

   public static EntityManagerFactory getEntityManagerFactory(){
       return emf;
   }

}
