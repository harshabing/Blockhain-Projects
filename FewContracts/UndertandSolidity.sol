//Below are the most frequently used in solidity for creating any SmartContract.

// We need to always specify the version of solidity we will be using with the help of keyword "pragma"

pragma solidity ^0.4.18;

// Just like java where we create classes and all code resides in it, same way 
// all our main code will be present in the contract. We can create it using keyword "contract"

 interface Regulator {          // We can create interface using the keyword "interface".
    function checkValue(uint amount) external  returns (bool);
    function loan() external returns (bool);
}

contract ForFueled is Regulator{            // using "is" keyword we can inherit previous contract,interface
    
    // <:::::::::::::::: (START) DATATYPES :::::::::>
    
    string name;             //string datatype
    
    uint private value;      // unsigned int, only 0, +ve numbers,solidity has signed and unsigned concept
    
    int charge;             // signed int, includes -ve numbers
    
    address private owner;  //solidity has address datatype, a hexadecimal value
    
    bool myBool;             // boolean datatype,has only true or false
    
    uint8[] myStringArr;     // solidity didn't used to allow us to create an array of strings in this way -> "string[] a;" 
    
    uint[] myIntArr;         // unfixed length uint array
    
    //fixed256x8 myFixnumber = 1;  // Not  implemented yet, might come in future soon. As it is important in
                                  // finance world, we cant use floating point
    
    enum Action {ADD, REMOVE, UPDATE} //Enums can be used to create custom types with a finite set of constant values 
    
    Action myAction = Action.ADD;   // using enum Action created above.
    
    struct Account {        //Structs are custom defined types that can group several variables.
        uint balance;
        uint dailyLimit;
    }   
        Account myAccount;
    
    function structFunc() public {
        myAccount.balance = 100;
    }
    
    mapping (address => Account) _accounts;         // Mapping is just like dictionaries, has key, value pairs
    
    
   
    // </::::::::: (END) DATATYPES ::::::::::::>


    event SenderLogger(address);            // This is how you do event logging.
    event ValueLogger(uint);                // This is how you do event logging.
    
    
    modifier owneronly {                    // we can create custom modifiers. here we created owneronly which makes sure that
                                            // something can be done only with owner's permission. This is done by making sure
                                            // that the one who is interacting with contract is bears the owner's address only.
        require(owner == msg.sender);       // require keyword makes sure that the condition is met. only if true then proceed.
        _;                                  // "_;" this tells the machine that if the condition is met then go to the next
                                            // lines of code and execute it.
    }
    
    function giveValue(uint amount) public payable{     // there are payable functions and non payable beacuse it modifies the state.
        value = amount;
        owner = msg.sender;                             //msg.sender - sender of message there area many Block and Transaction Properties.
    //  SenderLogger(msg.sender);           //You need to add emit to your instruction to avoid that warning. That kind of invocation is deprecated.
    
       emit SenderLogger(msg.sender);       // This works fine.
    }
    
    function deposit(uint amount) public owneronly payable{
        value += amount;
    }
    
    
   // <:::::::::::: (START)  STATE MODIFIERS   ::::::::::>
   uint private constant varConstantValue = 55;
    uint private stateValue;
    
    function stateAccess() public returns (uint) {
        stateValue = 10;
        return stateValue;
    }
    
    function constantAccess() public constant returns (uint) {      //state contant, doesnt change at all.
        return block.number;
    }
    
    function viewAccess() public view returns (uint) {      // "view",  in this case they promise not to modify the state.
        return stateValue;
    }
    
    function pureAccess() public pure returns (uint) {      //"pure" in this case they promise not to read from or modify the state.
        return varConstantValue;
    }
    
   // </:::::::::::: (END)  STATE MODIFIERS   ::::::::::>
   
   function() public { x = 1; }     // we can create only one fallback function in a contract
    uint x;


 // <:::::::::::: (START) INLINE ASSEMBLY   ::::::::::>

 function nativeLoops() public pure returns (uint r) {  // A simple looping function 
        for(uint i = 0; i < 10; i++) {
            r++;
        }
    }
    
    function asmLoops() public returns (uint r) {       // Wrting the above function in inline assembly.As sometimes
                                                        // writing in inline assembly reduces Transaction cost dramatically
                                                        // in this case by 30% , very useful.
        assembly {                                      //use assembly to create the function
            let i := 0                                  // assign i value
            loop:                                       //loop start
            i := add(i, 1)                              // add 1 to i
            r := add(r, 1)                              // add 1 to r
            jumpi(loop, lt(i, 10))                      // end loop if i = 10
        }
    }

    function nativeConditional(uint v) public pure returns (uint) {     // normal function with if else clause
        if (5 == v) {
            return 55;
        } else if (6 == v) {
            return 66;
        } 
        return 11;
    }
    
    function asmConditional(uint v) public pure returns (uint r) {  //Wrting the above function in inline assembly.But this time
                                                        // writing in inline assembly increases Transaction cost thus stating its 
                                                        // not always efficient.
        assembly {
            switch v
            case 5 {
                r := 55
            }
            case 6 { 
                r := 66
            }
            default {
                r := 11
            }
        }
    }
    
    // </:::::::::::: (END) INLINE ASSEMBLY   ::::::::::>
    
    
    
}
