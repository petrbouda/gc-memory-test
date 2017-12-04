package petrbouda;

class Address {

    final String city;
    final String street;

    Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
