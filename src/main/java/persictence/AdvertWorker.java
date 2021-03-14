package persictence;

import model.Advertisement;
import model.candidates.ConnectorBase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

public class AdvertWorker implements DAO<Advertisement> {

    @Override
    public Advertisement add(Advertisement model) {
        Serializable id = makeTransaction(session -> session.save(model));
        return makeTransaction(session -> session.get(Advertisement.class, id));

    }

    @Override
    public Advertisement getById(int id) {
        return makeTransaction(session -> session.get(Advertisement.class, id));
    }

    @Override
    public void update(Advertisement model, int id) {
        makeTransaction(session -> session.createQuery("update Advertisement c set c.photo = :newPhoto, c.description = :newDescription, c.published = :newPublished," +
                " c.status= : newStatus" + " where c.id = :fId"))
                .setParameter("newPhoto", model.getPhoto())
                .setParameter("newDescription", model.getDescription())
                .setParameter("newPublished", model.getPublished())
                .setParameter("newStatus", model.getStatus())
                .setParameter("fId", id)
                .executeUpdate();
    }

    @Override
    public void delete(int id) {
        makeTransaction(session ->
                session.createQuery("delete from Advertisement where id = :fId")
                        .setParameter("fId", id)
                        .executeUpdate());
    }


    public static Timestamp getTimestampDayNow() throws ParseException {
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0);
        Timestamp tim = Timestamp.valueOf(localDateTime);
        System.out.println("Example Time stamp:" + tim);
        return tim;
    }

    public Collection<Advertisement> allLastDate() throws ParseException {
        Timestamp timeTodayZero = getTimestampDayNow();
        return makeTransaction(session -> session.createQuery(" from Advertisement a where a.published>= :time")
                .setParameter("time", timeTodayZero).list());
    }

    public Collection<Advertisement> allLastDate2() throws ParseException {
        return makeTransaction(session ->
                session.createQuery(" from Advertisement " +
                        " where extract(day from published) = extract(day from 'now()' ) ")
                        .list());
    }

    public Collection<Advertisement> allHavePhoto() {
        return makeTransaction(session -> session.createQuery(" from Advertisement a where a.photo!= '' or a.photo !=null ")
                .list());
    }

    public Collection<Advertisement> allHaveByModel(String model) {
       return null;
    }


    @Override
    public Collection<Advertisement> allCandidates() {
        return null;
    }

    private <T> T makeTransaction(final Function<Session, T> operationCRUID) {
        Session session = ConnectorBase.instOf().getSf().openSession();
        Transaction transaction = session.beginTransaction();
        T result = operationCRUID.apply(session);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void close() throws Exception {
        ConnectorBase.instOf().close();
    }

    public static void main(String... args) throws ParseException {
        getTimestampDayNow();
    }

}
