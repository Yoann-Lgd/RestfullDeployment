package fr.yl.restfulldeployment.dao;

import fr.kyo.crkf_web.entity.Famille;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilleDAO extends DAO<Famille> {

    protected FamilleDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Famille getByID(int id) {
        String requete = "SELECT id_famille, famille, id_classification from Famille where id_famille = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete);){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) return new Famille(rs.getInt(1),rs.getString(2),rs.getInt(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Famille> getByClassification(int id) {
        List<Famille> list = new ArrayList<>();
        String requete = "SELECT id_famille, famille, id_classification from Famille where id_classification = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) list.add(new Famille(rs.getInt(1),rs.getString(2),rs.getInt(3)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<Famille> getAll(int page) {
        ArrayList<Famille> liste = new ArrayList<>();
        String requete = "SELECT id_famille, famille, id_classification from Famille order by famille";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) liste.add(new Famille(rs.getInt(1),rs.getString(2),rs.getInt(3)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public int insert(Famille objet) {
        String requete = "INSERT INTO Famille (famille,id_classification) VALUES (?,?)";
        try (PreparedStatement  preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString( 1 , objet.getFamilleLibelle());
            preparedStatement.setInt(2, objet.getClassificationObject().getClassificationId());
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
    public boolean update(Famille object) {
        String requete = "UPDATE Famille SET famille = ?, id_classification = ? WHERE id_famille = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1, object.getFamilleLibelle());
            preparedStatement.setInt(2, object.getClassificationObject().getClassificationId());
            preparedStatement.setInt(3, object.getFamilleId());
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
    public boolean delete(Famille object) {
        String requete = "DELETE FROM Famille WHERE id_famille=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, object.getFamilleId());
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

    public boolean deleteAll(List<Famille> familleList) {
        try{
            for(Famille object : familleList){
                String requete = "DELETE FROM Famille WHERE id_famille=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(requete)) {
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, object.getFamilleId());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch(SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return true;
    }
}
