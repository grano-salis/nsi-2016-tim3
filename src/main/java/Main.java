import ba.unsa.etf.nsi.charlie.helpers.HibernateHelper;
import ba.unsa.etf.nsi.charlie.model.ComponentDraftEntity;
import ba.unsa.etf.nsi.charlie.model.ComponentTypeEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import javax.persistence.metamodel.EntityType;

import java.util.List;

/**
 * Created by koljenovic on 12/12/2016.
 */
public class Main {

    private static void printAll(Session session) {
        System.out.println("querying all the managed entities...");
        final Metamodel metamodel = session.getSessionFactory().getMetamodel();
        for (EntityType<?> entityType : metamodel.getEntities()) {
            final String entityName = entityType.getName();
            final Query query = session.createQuery("from " + entityName);
            System.out.println("executing: " + query.getQueryString());
            for (Object o : query.list()) {
                System.out.println("  " + o);
            }
        }
    }

    // PRIMJER
    public static void main(final String[] args) throws Exception {
        final Session session = HibernateHelper.getSession();

        try {
            Transaction tx = session.beginTransaction();
            List<ComponentTypeEntity> results = session
                    .createQuery("from ComponentTypeEntity t where t.componenttype = :type")
                    .setParameter("type", "Test")
                    .list();
            ComponentTypeEntity cte;
            if (results.size() == 0) {
                cte = new ComponentTypeEntity();
                cte.setComponenttype("Test");
                session.save(cte);
                tx.commit();
            } else {
                cte = results.get(0);
            }

            ComponentDraftEntity cde = new ComponentDraftEntity();
            String data = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                    "      <SkillsPassport xmlns=\"http://europass.cedefop.europa.eu/Europass\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://europass.cedefop.europa.eu/Europass http://europass.cedefop.europa.eu/xml/EuropassSchema_V3.0.xsd\" locale=\"en\">\n" +
                    "         <!-- details omitted for brevity -->\n" +
                    "         <LearnerInfo>\n" +
                    "            <Identification>\n" +
                    "               <PersonName><FirstName>Betty</FirstName><Surname>Smith</Surname></PersonName>\n" +
                    "            </Identification>\n" +
                    "         </LearnerInfo>\n" +
                    "      </SkillsPassport>";
            cde.setComponenttypeByComponenttype(cte);
            cde.setData(data);
            cde.setStatus("WAITING");
            cde.setAdditionalinfo("-");
            session.save(cde);
            tx.commit();
        } finally {
            session.close();
        }
    }
}