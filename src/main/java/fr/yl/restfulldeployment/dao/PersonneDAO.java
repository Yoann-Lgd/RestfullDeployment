package fr.yl.restfulldeployment.dao;

import fr.kyo.crkf_web.entity.Diplome;
import fr.kyo.crkf_web.entity.Instrument;
import fr.kyo.crkf_web.entity.Personne;
import fr.kyo.crkf_web.tools.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAO extends DAO<Personne> {

    protected PersonneDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Personne getByID(int id) {
        String requete = "select id_personne,Nom,Prenom,VehiculeCV,id_adresse,id_ecole from Personne where id_personne = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Personne personne = new Personne(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5), rs.getInt(6));
                getDiplomesOfPersonne(id, personne);
                return personne;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Personne> getAll(int page) {
        List<Personne> liste = new ArrayList<>();
        String requete = "SELECT id_personne,Nom,Prenom,VehiculeCV,id_adresse,id_ecole from Personne order by nom, prenom asc";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(requete);
            while (rs.next()) {
                Personne personne = new Personne(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5), rs.getInt(6));
                getDiplomesOfPersonne(rs.getInt(1), personne);
                liste.add(personne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<Personne> getByEcole (int ecoleId) {
        List<Personne> liste = new ArrayList<>();
        String requete = "SELECT id_personne,Nom,Prenom,VehiculeCV,id_adresse,id_ecole from Personne where id_ecole = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setInt(1, ecoleId);
            ResultSet rs = preparedStatement.executeQuery();
            createPersonneFromResultSet(liste,rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<Pair<Personne, Double>> getByDistance(float latitudePointA, float longitudePointA) {
        List<Pair<Personne, Double>> personnesEtDistances = new ArrayList<>();
        String requete = "SELECT Nom, Prenom, id_ecole ,id_adresse, id_personne from Personne";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                float latitudePointB = DAOFactory.getAdresseDAO().getByID(rs.getInt(4)).getVille().getLatitude();
                float longitudePointB = DAOFactory.getAdresseDAO().getByID(rs.getInt(4)).getVille().getLongitude();

                double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin((Math.toRadians(latitudePointB - latitudePointA)) / 2), 2) + Math.pow(Math.sin((Math.toRadians(longitudePointB - longitudePointA)) / 2), 2) * Math.cos((Math.toRadians(latitudePointA))) * Math.cos(Math.toRadians(latitudePointB)))) * 6371.009;
                if (distance < 50){
                    Personne personne = new Personne();
                    personne.setPersonneNom(rs.getString(1));
                    personne.setPersonnePrenom(rs.getString(2));
                    personne.setEcoleObject(DAOFactory.getEcoleDAO().getByID(rs.getInt(3)));
                    personne.setAdresseObject((DAOFactory.getAdresseDAO().getByID(rs.getInt(4))));
                    personne.setPersonneId(rs.getInt(5));
                    personnesEtDistances.add(new Pair<>(personne, ( Math.round(distance * 100.0) / 100.0 )));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personnesEtDistances;
    }

    public List<Pair<Personne, Instrument>> getInstrumentEnseignerByPersonnes(List<Personne> personneList) {
        List<Pair<Personne, Instrument>> personnesEtInstrument = new ArrayList<>();
        try{
            for(Personne personne : personneList){
                String requete = "SELECT DISTINCT(id_instrument) from Personne_Diplome where id_personne = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(requete);
                preparedStatement.setInt(1, personne.getPersonneId());
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next())
                    personnesEtInstrument.add(new Pair<>(personne, DAOFactory.getInstrumentDAO().getByID(rs.getInt(1))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnesEtInstrument;
    }


    @Override
    public int insert(Personne objet) {
        int id = 0;
        String requete = "INSERT INTO Personne (Nom,Prenom,VehiculeCV,id_adresse,id_ecole) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)){
            connection.setAutoCommit(false);
            fillStatementWithPersonne(objet, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) id = rs.getInt(1);
            connection.commit();
        } catch(SQLException e) {
            try {
                connection.rollback();
                return 0;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public boolean update(Personne object) {
        String requete = "UPDATE Personne SET Nom = ?, Prenom = ?, VehiculeCV = ?, id_adresse = ?, id_ecole = ? WHERE id_personne = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            connection.setAutoCommit(false);
            fillStatementWithPersonne(object, preparedStatement);
            preparedStatement.setInt(6, object.getPersonneId());
            preparedStatement.executeUpdate();
            connection.commit();
            return true;
        } catch(SQLException e) {
            try {
                connection.rollback();
                return false;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Personne object) {
        try {
            String requete = "DELETE FROM Personne WHERE id_personne=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, object.getPersonneId());
                preparedStatement.executeUpdate();
            }
            String requete2 = "DELETE FROM Personne_Diplome WHERE id_personne=?";
            try (PreparedStatement preparedStatement2 = connection.prepareStatement(requete2)){
                preparedStatement2.setInt(1, object.getPersonneId());
                preparedStatement2.executeUpdate();
            }
            connection.commit();
            return true;
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

    public boolean delete(List<Personne> objectList) {
        try {
            for(Personne personne : objectList){
                String requete = "DELETE FROM Personne WHERE id_personne=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, personne.getPersonneId());
                    preparedStatement.executeUpdate();
                }
                String requete2 = "DELETE FROM Personne_Diplome WHERE id_personne=?";
                try (PreparedStatement preparedStatement2 = connection.prepareStatement(requete2)){
                    preparedStatement2.setInt(1, personne.getPersonneId());
                    preparedStatement2.executeUpdate();
                }
            }
            connection.commit();
            return true;
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

    private void getDiplomesOfPersonne(int id, Personne personne){
        String requete = "select id_libelle, id_instrument from Personne_Diplome where id_personne = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(requete)){
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) personne.addDiplome(new Diplome(rs.getInt(1),rs.getInt(2)));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createPersonneFromResultSet(List<Personne> liste, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Personne personne = new Personne();
            int id = rs.getInt(1);
            personne.setPersonneId(id);
            personne.setPersonneNom(rs.getString(2));
            personne.setPersonnePrenom(rs.getString(3));
            personne.setVehiculeCv(rs.getInt(4));
            personne.setAdresseObject(DAOFactory.getAdresseDAO().getByID(rs.getInt(5)));
            personne.setEcoleObject(DAOFactory.getEcoleDAO().getByID(rs.getInt(6)));
            getDiplomesOfPersonne(id, personne);
            liste.add(personne);
        }
    }

    private void fillStatementWithPersonne(Personne object, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, object.getPersonneNom());
        preparedStatement.setString(2, object.getPersonnePrenom());
        preparedStatement.setInt(3, object.getVehiculeCv());
        preparedStatement.setInt(4, object.getAdresseId());
        preparedStatement.setInt(5, object.getEcoleID());
    }
}