pragma solidity ^0.4.0;

interface Regulator {       // Interfaces in solidity are a lot like abstract contracts, except that their functions cannot be implemented.
    function checkValue(uint amount) external view returns (bool);
    function loan() external view returns (bool);
}

contract Bank is Regulator {     // We use Keyword "is" for extending a interface, here in this case Regulator.
    uint private value;
    
    function Banks(uint amount) public  {
        value = amount;
    }
    
    function deposit(uint amount) public  {
        value += amount;
    }
    
    function withdraw(uint amount) public  {
        if (checkValue(amount)) {
            value -= amount;
        }
    }
    
    function balance() public view returns (uint) {
        return value;
    }
    
    function checkValue(uint amount) public view returns (bool) {

        return value >= amount;
    }
    
    function loan() public view returns (bool) {
        return value > 0;
    }
}

contract MyFirstContract is Bank() {      //  We use Keyword "is" for extending a Contract.
    string private name;
    uint private age;
    
    function setName(string newName) public  {
        name = newName;
    }
    
    function getName() public view returns (string) {
        return name;
    }
    
    function setAge(uint newAge) public  {
        age = newAge;
    }
    
    function getAge() public view returns (uint) {
        return age;
    }
}
