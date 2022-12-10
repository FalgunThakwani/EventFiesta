package group11.EventFiesta.organizer;

import ch.qos.logback.core.encoder.EchoEncoder;
import group11.EventFiesta.DBConnection.IDBPersistence;
import group11.EventFiesta.EncryptPassword;
import group11.EventFiesta.ISignup;
import group11.EventFiesta.model.Account;
import group11.EventFiesta.model.Organizer;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class OrganizerSignUp extends ISignup {

    private IDBPersistence connection;

    public OrganizerSignUp(IDBPersistence connIdbPersistence){
        this.connection = connIdbPersistence;
    }
    @Override
    public boolean validateUser(Account organizer) throws Exception {
        ArrayList<HashMap<String, Object>> resultSet = connection.loadData("getFromDBUsingWhere", new Object[]{"OrganizerInfo", "email", "email", organizer.getEmail()});
        if (resultSet.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void storeInfo(Account object) throws Exception {
        Organizer org = (Organizer) object;
        Object[] params = createParams(org);
        ArrayList<HashMap<String, Object>> resultSet = connection.loadData("sp_storeOrganizerData", params);
    }

    private Object[] createParams(Organizer org) {
        String key = EncryptPassword.getNextSalt();
        String EncryptedPass = encryptReceivedPassword(org.getPassword(), key);
        System.out.println(org.getFromcontact());

        Object fromDate = new Date(System.currentTimeMillis());
        Object toDate = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        try {
            fromDate = formatter.parse(org.getFromcontact());
            toDate = formatter.parse(org.getTocontact());

        }catch (ParseException e) {
            e.printStackTrace();
        }

        Object[] params = {org.getBusiness(), org.getFirstName(), org.getLastName(), org.getEmail(), EncryptedPass, key, getSQLDateTime(15),
                org.getBusinessNo(), getSQLDateTime(0), getSQLDateTime(0), org.getAddress(), org.getCity(), org.getProvince(), org.getPincode(),
                fromDate, toDate, 0, org.getService().get(0).getName(), org.getService().get(0).getCost(), org.getService().get(1).getName(), org.getService().get(1).getCost(), org.getService().get(2).getName(), org.getService().get(2).getCost()};

        return params;
    }

    private Object getSQLDateTime(int noOfDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(date.toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.add(Calendar.DAY_OF_MONTH, noOfDays);
        String finalDate = sdf.format(cal.getTime());
        return finalDate;

    }
}
