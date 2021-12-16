package nl.mengelmoestuintjes.gardening.model.tasks;

public class Task {
    /*
      Task komt voor als:
       > GardeningTask die bestaat uit:
            - title
            - beschrijving
            - uitgevoerd of niet
            - punten (xp)
            - maand waarin die uitgevoerd moet worden (optioneel)
       > ToDoTask die bestaat uit:
            - title
            - beschrijving
            - uitgevoerd of niet
            - datum van uitvoeren

      gebruikers kunnen ToDoTasks aanmaken op hun profiel
      gebruikers kunnen ToDoTasks uitvoeren
    */
    private String title;
    private String description;
    private boolean isDone;

    public Task() {}
    public Task(String title, String description, boolean isDone) {
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setIsDone(boolean done) {
        isDone = done;
    }
}
