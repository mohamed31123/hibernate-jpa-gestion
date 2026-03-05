package ma.gestion.service;

import ma.gestion.classes.Femme;
import ma.gestion.classes.Marriage;
import ma.gestion.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractFacade<Femme> {
    public FemmeService() {
        super(Femme.class);
    }


    @Override
    public List<Femme> findAll() {
        return super.findAll();
    }

    @Override
    public Femme find(int id) {
        return findById(id);
    }


    public Long countEnfantsBetweenDates(Long femmeId, Date start, Date end) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Object result = session.createNamedQuery("Femme.countEnfantsBetweenDates")
                .setParameter(1, femmeId)
                .setParameter(2, start)
                .setParameter(3, end)
                .uniqueResult();

        session.close();

        if (result == null) return 0L;
        return ((Number) result).longValue();
    }

    public List<Femme> femmesMarieesDeuxFois() {
        Session session = null;
        Transaction tx = null;
        List<Femme> list = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            list = session.createNamedQuery("Femme.findMarriedTwice", Femme.class).list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return list;
    }

    public Long hommesMarie4Femmes(Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Marriage> root = cq.from(Marriage.class);

        cq.select(cb.countDistinct(root.get("homme")))
                .where(cb.between(root.get("dateDebut"), d1, d2))
                .groupBy(root.get("homme"))
                .having(cb.equal(cb.count(root.get("femme")), 4));

        Long result = session.createQuery(cq).getSingleResult();
        session.close();
        return result;
    }
    public Femme findFemmePlusAgee() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Femme femme = session.createQuery(
                        "FROM Femme f ORDER BY f.dateNaissance ASC",
                        Femme.class
                )
                .setMaxResults(1)
                .uniqueResult();

        session.close();

        return femme;
    }
}
