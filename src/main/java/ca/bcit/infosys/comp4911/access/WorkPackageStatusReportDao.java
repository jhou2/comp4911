package ca.bcit.infosys.comp4911.access;

import ca.bcit.infosys.comp4911.domain.WorkPackage;
import ca.bcit.infosys.comp4911.domain.WorkPackageStatusReport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Graeme on 2/9/14.
 */
@Stateless
public class WorkPackageStatusReportDao {

    @PersistenceContext
    EntityManager em;

    public static final String STATUS_REPORT = WorkPackageStatusReport.class.getSimpleName();

    public void create (final WorkPackageStatusReport workPackageStatusReport)
    {
        em.persist(workPackageStatusReport);
    }

    public WorkPackageStatusReport read ( final int workPackageStatusReportID)
    {
        return em.find(WorkPackageStatusReport.class, workPackageStatusReportID);
    }

    public void update (final WorkPackageStatusReport workPackageStatusReport)
    {
        em.merge(workPackageStatusReport);
    }

    public void delete (final WorkPackageStatusReport workPackageStatusReport)
    {
        em.remove(read(workPackageStatusReport.getId()));
    }

    public List<WorkPackageStatusReport> getAll() {
        TypedQuery<WorkPackageStatusReport> query = em.createQuery("select r from " + STATUS_REPORT + " r",
                WorkPackageStatusReport.class);
        return query.getResultList();
    }

    public List<WorkPackageStatusReport> getAllByWorkPackage(final WorkPackage workPackage) {
        TypedQuery<WorkPackageStatusReport> query = em.createQuery("select r from " + STATUS_REPORT + " r where r.workPackage = :workPackage",
                WorkPackageStatusReport.class);
        query.setParameter("workPackage", workPackage);
        return query.getResultList();
    }
}