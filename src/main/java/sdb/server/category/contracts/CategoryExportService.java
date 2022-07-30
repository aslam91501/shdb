package sdb.server.category.contracts;

import java.util.List;

import org.springframework.core.io.Resource;

public interface CategoryExportService {
    Resource export(List<Long> ids);
}
