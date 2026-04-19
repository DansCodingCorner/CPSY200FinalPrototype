package Business.Reports;

import java.util.List;
import Business.Interfaces.ICategory;
import Business.Reports.Interfaces.ICategoryListReport;
import Persistence.CategoryDataAccess;
import Persistence.ICategoryDataAccess;

public class CategoryListReport implements ICategoryListReport {

    public String generateReport(ICategoryDataAccess dataAccess) {

        List<ICategory> categories = dataAccess.loadCategories();

        if (categories == null || categories.isEmpty()) {
            return "No categories found.";
        }

        StringBuilder report = new StringBuilder();

        // Fancy Header
        report.append("╔════════════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                    CATEGORY LIST REPORT                                    ║\n");
        report.append("╠════════════════════════════════════════════════════════════════════════════╣\n\n");

        // Table Header
        report.append("╔═════╤══════════════════════════════════════════════════════════════════════╗\n");
        report.append("║  ID ║ CATEGORY NAME                                                        ║\n");
        report.append("╠═════╪══════════════════════════════════════════════════════════════════════╣\n");

        // Table Rows
        for (ICategory category : categories) {
            String name = category.getName();
            // Truncate name if it's too long to keep table alignment
            if (name.length() > 65) {
                name = name.substring(0, 62) + "...";
            }
            
            report.append(String.format("║ %3d ║ %-68s ║\n", 
                category.getId(), 
                name));
        }

        // Table Footer
        report.append("╚═════╧══════════════════════════════════════════════════════════════════════╝\n\n");

        // Summary
        report.append("Total Categories: ")
              .append(categories.size())
              .append("\n");

        return report.toString();
    }

    // Keep your main method for testing
    public static void main(String[] args) {
        CategoryListReport c = new CategoryListReport();
        CategoryDataAccess d = CategoryDataAccess.getInstance();
        System.out.println(c.generateReport(d));
    }
}