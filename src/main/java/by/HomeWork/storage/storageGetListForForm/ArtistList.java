package by.HomeWork.storage.storageGetListForForm;

import by.HomeWork.storage.storageGetListForForm.api.IArtistsList;

import javax.sql.DataSource;

public class ArtistList extends AbstractList implements IArtistsList {
    private static final String sql = "select * from artists " +
            "order by name_artist ASC";

    public ArtistList(DataSource dataSource) {
        super(dataSource, sql);
    }
}
