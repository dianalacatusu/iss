package repository;

import model.Abonat;
import model.Angajat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class RepoAngajat implements RepositoryAngajat{

    private JdbcUtils dbUtils;


    public RepoAngajat(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public Angajat login(String username, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Angajati where username = ? and password=?")) {
            preStmt.setString(1, username);
            preStmt.setString(2,password);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String username1 = result.getString("username");
                    String password1 = result.getString("password");
                    Angajat angajat = new Angajat(username1, password1);
                    angajat.setId(id);
                    return angajat;
                }
            }
        }catch(SQLException e) {

            System.err.println("Error DB" + e);
        }
        return null;
    }

    @Override
    public void adaugaAbonat(Abonat abonat) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("insert into Abonati(cnp, nume, adresa, telefon) values (?,?,?,?)")) {
            preStmt.setString(1, abonat.getCnp());
            preStmt.setString(2, abonat.getNume());
            preStmt.setString(3, abonat.getAdresa());
            preStmt.setString(4, abonat.getTelefon());
            int result = preStmt.executeUpdate();

        } catch (SQLException ex) {

            System.err.println("Error DB" + ex);
        }
    }

    @Override
    public Abonat cautaAbonat(String cnp) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Abonati where cnp=?")) {
            preStmt.setString(1, cnp);
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String nume = result.getString("nume");
                    String adresa = result.getString("adresa");
                    String telefon = result.getString("telefon");
                    Abonat abonat = new Abonat(cnp, nume, adresa, telefon);
                    abonat.setId(id);
                    return abonat;
                }
            }
        }catch(SQLException e) {

            System.err.println("Error DB" + e);
        }
        return null;
    }

    @Override
    public void stergeAbonat(String cnp) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("delete from Abonati where cnp = ?")) {
            preStmt.setString(1, cnp);
            preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB" + ex);
        }
    }

    @Override
    public void actualizareAbonat(String cnp, Abonat abonat) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStmt = con.prepareStatement("update Abonati set cnp=?, nume=?, adresa=?, telefon=? where cnp=?")) {
            preStmt.setString(1, abonat.getCnp());
            preStmt.setString(2, abonat.getNume());
            preStmt.setString(3, abonat.getAdresa());
            preStmt.setString(4, abonat.getTelefon());
            preStmt.setString(5, cnp);
            preStmt.executeUpdate();
            int result = preStmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB" + ex);
        }
    }
}
