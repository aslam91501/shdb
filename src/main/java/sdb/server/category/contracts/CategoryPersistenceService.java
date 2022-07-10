package sdb.server.category.contracts;

import sdb.server.category.Category;

public interface CategoryPersistenceService {
    Category save(Category category);

    Category getByName(String name);
}
