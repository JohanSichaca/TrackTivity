package Logic.Services;

import javafx.beans.property.*;

public class Schedulable {

    private final StringProperty taskName;
    private final StringProperty description;
    private final StringProperty category;
    private final StringProperty priority;
    private final StringProperty subject;
    private final StringProperty expirationDate;
    private final BooleanProperty status;

    public Schedulable(String taskName, String description, String category, String priority,
                       String subject, String expirationDate) {
        this.taskName = new SimpleStringProperty(taskName);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.priority = new SimpleStringProperty(priority);
        this.subject = new SimpleStringProperty(subject);
        this.expirationDate = new SimpleStringProperty(expirationDate);
        this.status = new SimpleBooleanProperty(false);
    }

    public StringProperty taskNameProperty() { return taskName; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty categoryProperty() { return category; }
    public StringProperty priorityProperty() { return priority; }
    public StringProperty subjectProperty() { return subject; }
    public StringProperty expirationDateProperty() { return expirationDate; }
    public BooleanProperty statusProperty() { return status; }

    public String getTaskName() { return taskName.get(); }
    public void setTaskName(String value) { taskName.set(value); }
    public String getDescription() { return description.get(); }
    public void setDescription(String value) { description.set(value); }
    public String getCategory() { return category.get(); }
    public void setCategory(String value) { category.set(value); }
    public String getPriority() { return priority.get(); }
    public void setPriority(String value) { priority.set(value); }
    public String getSubject() { return subject.get(); }
    public void setSubject(String value) { subject.set(value); }
    public String getExpirationDate() { return expirationDate.get(); }
    public void setExpirationDate(String value) { expirationDate.set(value); }
    public boolean isStatus() { return status.get(); }
    public void setStatus(boolean value) { status.set(value); }
}
