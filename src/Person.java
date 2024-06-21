abstract class Person {
    private String name;
    private String passportNo;
    
    Person(String name, String passportNo) {
        this.name = name;
        this.passportNo = passportNo;
    }
    
    // Getter
    public String getName(){
        return name;
    }
    
    public String getPassportNo(){
        return passportNo;
    }
    
    // Setter
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassportNo(String passportNo){
        this.passportNo = passportNo;
    }
}
