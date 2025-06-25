package by.HomeWork.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.HomeWork.connection.JDBCconnection.connect;

public class PostgreGet {
    List<String> artists = new ArrayList<String>();
    List<String> genres = new ArrayList<String>();// использовать общий лист в отдельной директории

    public List<String> getArtists() {

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from artists order by name_artist ASC");

            while (rs.next()) {
                String name = rs.getString("name_artist"); // Замените "name" на имя вашего столбца
                artists.add(name);
            }
            return artists;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getGenres() {

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from genres order by name_genre ASC");

            while (rs.next()) {
                String name = rs.getString("name_genre"); // Замените "name" на имя вашего столбца
                genres.add(name);
            }
            return genres;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
