package petrbouda;

class Contact {

    final String firstname;
    final String lastname;
    final Address address;

    Contact(String firstname, String lastname, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
