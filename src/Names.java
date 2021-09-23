
public class Names {
    
    private int numFirstNames;
    private char sex;
    private int year;
    private String firstName;
    private int totalPerYear;

    public Names(int numFirstNames, char sex, int year, String firstName, int totalPerYear) {
        this.numFirstNames = numFirstNames;
        this.sex = sex;
        this.year = year;
        this.firstName = firstName;
        this.totalPerYear = totalPerYear;
    }

    public int getNumFirstNames() {
        return numFirstNames;
    }

    public char getSex() {
        return sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public int compareTo(Names o){
        if(this.numFirstNames < o.getNumFirstNames()){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    @Override
    public String toString() {
        return "Num of First Names: " + numFirstNames + ", Sex: " + sex + ", Year: " + year + ", First Name: " + firstName + ", Total Per Year: " + totalPerYear;
    }
    
}
