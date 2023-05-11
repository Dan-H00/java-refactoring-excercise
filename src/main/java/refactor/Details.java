package refactor;

public class Details {

    private String name = null;

    private String description = null;

    public Details name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }


    public Details description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

}

