package by.HomeWork.storage.storageListForm;

import by.HomeWork.storage.storageListForm.api.IGenreList;

import javax.sql.DataSource;

public class GenreList extends AbstractList implements IGenreList {
    private static final String sql = "select * from genres " +
            "order by name_genre ASC";

    public GenreList(DataSource dataSource) {
        super(dataSource, sql);
    }
}
