public class CreatePlayer {
    private final String name;
    private final String profession;
    private final int age;

    private CreatePlayer(Builder builder) {
        this.name = builder.name;
        this.profession = builder.profession;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "CreatePlayer{" +
                "name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", age=" + age +
                '}';
    }

    public static class Builder {
        private String name;
        private String profession;
        private int age;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withProfession(String profession) {
            this.profession = profession;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public CreatePlayer build() {
            return new CreatePlayer(this);
        }
    }
}
