package fr.yl.restfulldeployment.dao;

import fr.kyo.crkf_web.entity.Compte;
import fr.kyo.crkf_web.entity.Famille;
import fr.kyo.crkf_web.entity.Instrument;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDAO extends DAO<Compte> {

    protected CompteDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Compte getByID(int id) {
        return null;
    }

    @Override
    public List<Compte> getAll(int page) {
        return null;
    }

    public Compte getByEmail(String email) {
        String requete = "select id_compte, email, password from Compte where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
                return new Compte(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Compte objet) {
        String requete = "INSERT INTO Compte (email,password) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, objet.getEmail());
            preparedStatement.setString(2, objet.getPassword());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            connection.commit();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            try {
                connection.rollback();
                return 0;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public boolean update(Compte object) {
        return false;
    }

    @Override
    public boolean delete(Compte object) {
        return false;
    }

    public boolean exists(String email) {
        String requete = "select id_compte from Compte where email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
