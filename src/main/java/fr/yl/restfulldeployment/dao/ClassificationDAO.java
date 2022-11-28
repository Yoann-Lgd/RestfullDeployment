package fr.yl.restfulldeployment.dao;

import fr.kyo.crkf_web.entity.Classification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassificationDAO extends DAO<Classification> {

    protected ClassificationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Classification getByID(int id) {
        String requete = "SELECT id_classification, classification from Classification where id_classification = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return new Classification(rs.getInt(1), rs.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Classification> getAll(int page) {
        List<Classification> liste = new ArrayList<>();
        String requete = "SELECT id_classification, classification from Classification order by classification";
        return getClassifications(liste, requete);
    }

    @Override
    public int insert(Classification objet) {
        String requete = "INSERT INTO Classification (classification) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)){
            connection.setAutoCommit(false);
            preparedStatement.setString( 1 , objet.getClassificationLibelle());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) return rs.getInt(1);
            connection.commit();
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public boolean update(Classification object) {
        String requete = "UPDATE Classification SET classification = ? WHERE id_classification = ?";
        try (PreparedStatement  preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1, object.getClassificationLibelle());
            preparedStatement.setInt(2, object.getClassificationId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Classification object) {
        String requete = "DELETE FROM Classification WHERE id_classification=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, object.getClassificationId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch(SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public List<Classification> getLike(String classification, int page) {
        List<Classification> list = new ArrayList<>();
        StringBuilder requete = new StringBuilder("SELECT id_classification, classification from Classification");
        if(!classification.isEmpty())
            requete.append(" where classification like '%").append(classification).append("%'");
        requete.append(" order by classification OFFSET 25 * (").append(page).append(" - 1)  ROWS FETCH NEXT 25 ROWS ONLY");
        return getClassifications(list, requete.toString());
    }

    public int getAllClassification(String classification) {
        StringBuilder requete = new StringBuilder("SELECT COUNT(id_classification) from Classification");
        if(!classification.isEmpty())
            requete.append(" where classification like '%").append(classification).append("%'");
        try (PreparedStatement s = connection.prepareStatement(requete.toString())){
            ResultSet rs = s.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Classification> getClassifications(List<Classification> list, String requete) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) list.add(new Classification(rs.getInt(1), rs.getString(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
